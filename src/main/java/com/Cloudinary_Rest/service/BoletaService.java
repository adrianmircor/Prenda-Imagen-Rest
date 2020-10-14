package com.Cloudinary_Rest.service;

import com.Cloudinary_Rest.entity.Boleta;
import com.Cloudinary_Rest.repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletaService {

    @Autowired
    BoletaRepository boletaRepository;

    public void guardar(List<Boleta> boletas){

        boletaRepository.saveAll(boletas);
    }

    public void eliminar(int id){

        boletaRepository.deleteById(id);
    }

    public List<Boleta> listaBoleta(){
        return boletaRepository.findAll();
    }


}
