package com.hotelalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/hospede")
public class Main {
    private final HospedeRepository hospedeRepository;

    public Main(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Hospede> getHospede() {
        return hospedeRepository.findAll();
    }
}
