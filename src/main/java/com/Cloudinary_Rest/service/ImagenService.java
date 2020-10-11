package com.Cloudinary_Rest.service;

import com.Cloudinary_Rest.entity.Imagen;
import com.Cloudinary_Rest.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    /*public List<Imagen> list(){
        return imagenRepository.findByOrderById();
    }*/

    public void save(Imagen imagen) {
        imagenRepository.save(imagen);
    }

    public void delete(String id) {
        imagenRepository.deleteById(id);
    }

    /*---*/

    public Optional<Imagen> conseguirUno(String id) {
        return imagenRepository.findById(id);
    }

    public boolean existe(String id) {
        return imagenRepository.existsById(id);
    }

}
