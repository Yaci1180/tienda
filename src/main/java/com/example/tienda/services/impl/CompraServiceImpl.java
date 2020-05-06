package com.example.tienda.services.impl;

import com.example.tienda.model.Compra;
import com.example.tienda.model.request.CompraRequest;
import com.example.tienda.repositories.CompraRepository;
import com.example.tienda.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService {

    private final  CompraRepository compraRepository;

    @Autowired
    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    public Compra saveCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public Optional<Compra> findByNumeroDeCompra(Long numeroDeCompra) {
        return compraRepository.findByNumeroDeCompra(numeroDeCompra);
    }
}
