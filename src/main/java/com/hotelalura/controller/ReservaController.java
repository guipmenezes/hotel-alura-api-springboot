package com.hotelalura.controller;

import com.hotelalura.DAO.ReservaRepository;
import com.hotelalura.models.Reserva;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/reserva")
public class ReservaController {

    private final ReservaRepository reservaRepository;

    public ReservaController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @GetMapping
    public List<Reserva> getReserva() {
        return reservaRepository.findAll();
    }

    record NewReservaRequest(
        Date dataEntrada,
        Date dataSaida,
        Integer valor,
        String formaPagamento
    ){}

    @PostMapping
    public void addReserva(@RequestBody NewReservaRequest request) {
        Reserva reserva = new Reserva();
        reserva.setDataEntrada(request.dataEntrada);
        reserva.setDataSaida(request.dataSaida);
        reserva.setValor(request.valor);
        reserva.setFormaPagamento(request.formaPagamento);
        reservaRepository.save(reserva);
    }

    @DeleteMapping("{reservaId}")
    public void deleteReserva(@PathVariable("reservaId") Integer id) {
        reservaRepository.deleteById(id);
    }

    @PutMapping("{reservaId}")
    public ResponseEntity<Reserva> editReserva(@PathVariable("reservaId") Integer id, @RequestBody NewReservaRequest request) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível editar a reserva de id: " + id));
        reserva.setDataEntrada(request.dataEntrada);
        reserva.setDataSaida(request.dataSaida);
        reserva.setFormaPagamento(request.formaPagamento);
        reserva.setValor(request.valor);
        final Reserva update =reservaRepository.save(reserva);
        return ResponseEntity.ok(update);
    }

}
