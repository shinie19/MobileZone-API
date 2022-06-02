package com.example.mobilezone_api.service;

import com.example.mobilezone_api.model.Slider;
import com.example.mobilezone_api.repository.SliderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SliderService {
    private final SliderRepository sliderRepository;

    @Transactional(readOnly = true)
    public List<Slider> getAll() {
        return sliderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Slider getSlider(Long id) {
        return sliderRepository.findById(id).orElseThrow(() -> new RuntimeException("Slider not found with id - " + id));
    }

    @Transactional
    public Slider save(Slider slider) {
        return sliderRepository.save(slider);
    }

    @Transactional
    public void delete(Long id) {
        sliderRepository.deleteById(id);
    }
}
