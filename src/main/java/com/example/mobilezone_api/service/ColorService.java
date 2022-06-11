package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.ColorDTO;
import com.example.mobilezone_api.exception.ColorNotFoundException;
import com.example.mobilezone_api.mapper.ColorMapper;
import com.example.mobilezone_api.model.Color;
import com.example.mobilezone_api.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    @Transactional(readOnly = true)
    public List<ColorDTO> getAll() {
        return colorRepository.findAll()
                .stream()
                .map(colorMapper::mapColorToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ColorDTO getColor(Long id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new ColorNotFoundException("Color not found with id -" + id));
        return colorMapper.mapColorToDTO(color);
    }

    @Transactional
    public ColorDTO save(ColorDTO colorDTO) {
        Color color = colorMapper.mapDTOToColor(colorDTO);

        Color colorSaved = colorRepository.save(color);
        colorDTO.setId(colorSaved.getId());

        return colorDTO;
    }

    @Transactional
    public void delete(Long id) {
        colorRepository.deleteById(id);
    }
}
