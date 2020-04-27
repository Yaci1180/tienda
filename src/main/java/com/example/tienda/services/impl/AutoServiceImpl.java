package com.example.tienda.services.impl;

import com.example.tienda.exceptions.ResourceNotFoundException;
import com.example.tienda.model.Auto;
import com.example.tienda.model.Concesionaria;
import com.example.tienda.model.enums.TipoDeAuto;
import com.example.tienda.model.enums.TipoDeEstado;
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
    public Auto calcularPrecioVenta(Long autoId) {

        int precioFinal;

        Optional<Auto> auto = findById(autoId);
        if (!auto.isPresent()) {
            throw new ResourceNotFoundException("Auto no encontrado con la id: " + autoId);
        } else {

            if (auto.get().getTipoDeEstado() == BUENO) {
                int precioVenta = (int) (auto.get().getPrecioOriginal() - (auto.get().getPrecioOriginal() * (auto.get().getKilometraje() * 50000 * 0.03)));
                precioFinal = (int) (precioVenta - auto.get().getPrecioOriginal() * 0.10);

            } else if (auto.get().getTipoDeEstado() == MEDIO) {
                int precioVenta = (int) (auto.get().getPrecioOriginal() - (auto.get().getPrecioOriginal() * (auto.get().getKilometraje() * 50000 * 0.03)));
                precioFinal = (int) (precioVenta - auto.get().getPrecioOriginal() * 0.15);

            } else {
                int precioVenta = (int) (auto.get().getPrecioOriginal() - (auto.get().getPrecioOriginal() * (auto.get().getKilometraje() * 50000 * 0.03)));
                precioFinal = (int) (precioVenta - auto.get().getPrecioOriginal() * 0.25);

            }

            auto.get().setPrecioVenta(precioFinal);
            return auto.get();
        }
    }
}