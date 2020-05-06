package com.example.tienda.services.impl;

import com.example.tienda.model.Cliente;
import com.example.tienda.repositories.ClienteRepository;
import com.example.tienda.services.ClienteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> findById(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }
}
