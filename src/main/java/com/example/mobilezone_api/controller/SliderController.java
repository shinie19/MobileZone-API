package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.model.Slider;
import com.example.mobilezone_api.service.SliderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slider")
@CrossOrigin
@AllArgsConstructor
public class SliderController {
    private final SliderService sliderService;

    @GetMapping
    public List<Slider> getAll() {
        return sliderService.getAll();
    }

    @GetMapping("/{id}")
    public Slider getSlider(@PathVariable Long id) {
        return sliderService.getSlider(id);
    }

    @PostMapping
    public Slider create(@RequestBody Slider slider) {
        return sliderService.save(slider);
    }

    @GetMapping("/delete/{id}")
    public void deleteSlider(@PathVariable Long id) {
        sliderService.delete(id);
    }
}
