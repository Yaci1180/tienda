package com.example.tienda.services.impl;

import com.example.tienda.model.Tarjeta;
import com.example.tienda.model.enums.TipoDeTarjeta;
import com.example.tienda.repositories.TarjetaRepository;
import com.example.tienda.services.TarjetaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaServiceImpl(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    @Override
    public Tarjeta saveTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public Optional<Tarjeta> findByNumeroDeTarjeta(Long numeroDeTarjeta) {
        return tarjetaRepository.findByNumeroDeLaTarjeta(numeroDeTarjeta);
    }

    @Override
    public List<Tarjeta> findAllByTipoDeTarjeta(TipoDeTarjeta tipoDeTarjeta) {
        return tarjetaRepository.findAllByTipoDeTarjeta(tipoDeTarjeta);
    }
}
