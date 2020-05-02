package com.example.tienda.controllers;

import com.example.tienda.model.Cliente;
import com.example.tienda.model.request.ClienteRequest;
import com.example.tienda.model.response.ClienteResponse;
import com.example.tienda.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PutMapping(value = "/saveCliente")
    public ResponseEntity<ClienteResponse> saveCliente(@RequestBody ClienteRequest clienteRequest){

        Cliente cliente = Cliente.builder()
                .nombre(clienteRequest.getNombre())
                .clienteDni(clienteRequest.getClienteDni())
                .build();

        clienteService.saveCliente(cliente);

        return ResponseEntity.ok(parseClienteResponse(clienteService.saveCliente(cliente)));
    }

    private ClienteResponse parseClienteResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .nombre(cliente.getNombre())
                .clienteDni(cliente.getClienteDni())
                .build();
    }
}