package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.dto.ColorDTO;
import com.example.mobilezone_api.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/color")
@AllArgsConstructor
public class ColorController {
    private final ColorService colorService;

    @GetMapping
    public List<ColorDTO> getAll() {
        return colorService.getAll();
    }

    @GetMapping("/{id}")
    public ColorDTO getColor(@PathVariable Long id) {
        return colorService.getColor(id);
    }

    @PostMapping
    public ColorDTO create(@RequestBody ColorDTO colorDTO) {
        return colorService.save(colorDTO);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        colorService.delete(id);
    }
}
