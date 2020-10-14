package com.Cloudinary_Rest.service;

import com.Cloudinary_Rest.entity.Usuario;
import com.Cloudinary_Rest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> listaUsuario() {

        return usuarioRepository.findAll();
    }

}
