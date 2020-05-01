package com.example.tienda.services.impl;

import com.example.tienda.model.Contrato;
import com.example.tienda.repositories.ContratoRepository;
import com.example.tienda.services.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;

    @Autowired
    public ContratoServiceImpl(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public Contrato saveContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    @Override
    public Optional<Contrato> findByNumeroDeContrato(Long numerDeContrato) {
        return contratoRepository.findByNumeroDeContrato(numerDeContrato);
    }
}
