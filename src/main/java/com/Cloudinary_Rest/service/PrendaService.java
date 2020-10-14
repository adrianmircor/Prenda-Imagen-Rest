package com.Cloudinary_Rest.service;

import com.Cloudinary_Rest.entity.Prenda;
import com.Cloudinary_Rest.repository.PrendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrendaService {

    @Autowired
    PrendaRepository prendaRepository;

    public void guardar(Prenda prenda) {
        prendaRepository.save(prenda);
    }

    public void eliminar(int id) {
        prendaRepository.deleteById(id);
    }

    /*---*/

    public Prenda obtenerPrenda(int id) {

        return prendaRepository.findById(id);
    }

    public List<Prenda> obtenerListaPrenda() {
        return prendaRepository.findAll();
    }


    public boolean existePrenda(int id) {
        return prendaRepository.existsById(id);
    }


}
