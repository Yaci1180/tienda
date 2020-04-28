package com.example.tienda.services.impl;

import com.example.tienda.model.Auto;
import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.repositories.AutoRepository;
import com.example.tienda.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.tienda.model.enums.TipoDeEstado.BUENO;
import static com.example.tienda.model.enums.TipoDeEstado.MEDIO;

@Service
public class AutoServiceImpl implements AutoService {

    private final AutoRepository autoRepository;

    @Autowired
    public AutoServiceImpl(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    @Override
    public Auto saveAuto(Auto auto) {
        auto.setPrecioVenta(calcularPrecioVenta(auto));
        return autoRepository.save(auto);
    }

    @Override
    public Optional<Auto> findByNombre(String nombre) {
        return autoRepository.findByNombreDelAuto(nombre);
    }

    @Override
    public List<Auto> findAllByTipoDeAuto(TipoDeAuto tipoDeAuto) {
        return autoRepository.findAllByTipoDeAuto(tipoDeAuto);
    }

    @Override
    public Optional<Auto> findById(Long Id) {
        return autoRepository.findById(Id);
    }

    @Override
    public void deleteAuto(Auto auto) {
        autoRepository.delete(auto);
    }

    @Override
    public double calcularPrecioVenta(Auto auto) {

        double precioVentaFinal;
        if (auto.getTipoDeEstado() == BUENO) {
            double precioVenta = auto.getPrecioOriginal() - (auto.getPrecioOriginal() *((int) (auto.getKilometraje() /50000)) * 0.03);
            precioVentaFinal = precioVenta - auto.getPrecioOriginal() * 0.10;

        } else if (auto.getTipoDeEstado() == MEDIO) {
            double precioVenta = auto.getPrecioOriginal() - (auto.getPrecioOriginal() *((int) (auto.getKilometraje() /50000)) * 0.03);
            precioVentaFinal = precioVenta - auto.getPrecioOriginal() * 0.15;

        } else {
            double precioVenta = auto.getPrecioOriginal() - (auto.getPrecioOriginal() *((int) (auto.getKilometraje() /50000))* 0.03);
            precioVentaFinal = precioVenta - auto.getPrecioOriginal() * 0.25;

        }

        auto.setPrecioVenta(precioVentaFinal);
        return precioVentaFinal;
        }
    }

