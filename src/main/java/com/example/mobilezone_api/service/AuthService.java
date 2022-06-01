package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.AuthenticationResponse;
import com.example.mobilezone_api.dto.LoginRequest;
import com.example.mobilezone_api.dto.RegisterRequest;
import com.example.mobilezone_api.exception.EmailNotFoundException;
import com.example.mobilezone_api.exception.SpringException;
import com.example.mobilezone_api.model.NotificationEmail;
import com.example.mobilezone_api.model.User;
import com.example.mobilezone_api.model.VerificationToken;
import com.example.mobilezone_api.repository.UserRepository;
import com.example.mobilezone_api.repository.VerificationTokenRepository;
import com.example.mobilezone_api.security.JWTProvider;
import com.example.mobilezone_api.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;


    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setCreateDate(Instant.now());
        user.setIsAdmin(false);
        user.setStatus(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + Constants.ACTIVATION_EMAIL + "/" + token);

        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("Email not found!"));
        return new AuthenticationResponse(authenticationToken, loginRequest.getEmail(), user.getIsAdmin());
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new SpringException("Invalid Token"));
        fetchUserAndEnable(verificationTokenOptional.get());
    }

    @Transactional(readOnly = true)
    User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new EmailNotFoundException("Email not found - " + principal.getUsername()));
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new SpringException("User Not Found with email - " + email));
        user.setStatus(true);
        userRepository.save(user);
    }
}