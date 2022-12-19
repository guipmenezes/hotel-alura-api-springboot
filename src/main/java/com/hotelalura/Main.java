package com.hotelalura;

import org.apache.coyote.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    record NewHospedeRequest(
            Date data,
            String nacionalidade,
            String nome,
            String sobrenome,
            String telefone
    ) {

    }
    @PostMapping
    public void addHospede(@RequestBody NewHospedeRequest request) {
        Hospede hospede = new Hospede();
        hospede.setDataNascimento(request.data());
        hospede.setNacionalidade(request.nacionalidade());
        hospede.setNome(request.nome());
        hospede.setSobrenome(request.sobrenome());
        hospede.setTelefone(request.telefone());
        hospedeRepository.save(hospede);
    }

    @DeleteMapping("{hospedeId}")
    public void deleteHospede(@PathVariable("hospedeId") Integer id) {
        hospedeRepository.deleteById(id);
    }

    @PutMapping("{hospedeId}")
    public ResponseEntity<Hospede> updateCostumer(@PathVariable(value = "hospedeId") Integer id, @RequestBody NewHospedeRequest request) {
        Hospede hospede = hospedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível encontrar o hóspede de id: " + id));
        hospede.setDataNascimento(request.data());
        hospede.setNacionalidade(request.nacionalidade());
        hospede.setNome(request.nome());
        hospede.setSobrenome(request.sobrenome());
        hospede.setTelefone(request.telefone());
        final Hospede update = hospedeRepository.save(hospede);
        return ResponseEntity.ok(update);
    }
}
