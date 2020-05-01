package com.example.tienda.services;

import com.example.tienda.model.Cliente;

import java.util.Optional;

public interface ClienteService {

    Cliente saveCliente(Cliente cliente);
    Optional<Cliente> findByDni(int dni);
}
