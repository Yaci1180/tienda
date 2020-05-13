package com.example.tienda.services.impl;

import com.example.tienda.model.Compra;
import com.example.tienda.model.Tarjeta;
import com.example.tienda.model.enums.TipoDeTarjeta;
import com.example.tienda.model.request.CompraRequest;
import com.example.tienda.repositories.CompraRepository;
import com.example.tienda.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.tienda.model.enums.TipoDeTarjeta.GALICIA;
import static com.example.tienda.model.enums.TipoDeTarjeta.MASTERCARD;

@Service
public class CompraServiceImpl implements CompraService {

    private final  CompraRepository compraRepository;

    @Autowired
    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    public Compra saveCompra(Compra compra) {
        compra.setPrecioTotalCompra(calcularPorcentajeDescuento(compra));
        return compraRepository.save(compra);
    }

    @Override
    public Optional<Compra> findByNumeroDeCompra(Long numeroDeCompra) {
        return compraRepository.findByNumeroDeCompra(numeroDeCompra);
    }

    @Override
    public double calcularPorcentajeDescuento(Compra compra) {

        double precioFinalVenta;

        if(compra.getTipoDeTarjeta() == GALICIA){

            double precioTotalCompra = ((precioFinalVenta * 10) / 100) + precioFinalVenta;

            /* precio total compra seria el precio final que tiene seteado el auto?
            */
        } else if(compra.getTipoDeTarjeta() == MASTERCARD){

            double precioTotalCompra = ((precioFinalVenta * 7) / 100) + precioFinalVenta;

        }else{

            double precioTotalCompra = ((precioFinalVenta * 5/ 100) + precioFinalVenta;

        }
        compra.setPrecioTotalCompra(precioTotalCompra);
        return precioTotalCompra;
    }
}
