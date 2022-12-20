package com.hotelalura.controller;

import com.hotelalura.models.Hospede;
import com.hotelalura.DAO.HospedeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/hospede")
public class HospedeController {

    private final HospedeRepository hospedeRepository;

    public HospedeController(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
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
    ) {}
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
