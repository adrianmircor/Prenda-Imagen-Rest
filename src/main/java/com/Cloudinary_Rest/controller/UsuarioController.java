package com.Cloudinary_Rest.controller;

import com.Cloudinary_Rest.entity.Usuario;
import com.Cloudinary_Rest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> lista() {

        List<Usuario> lUsers = new ArrayList<>();
        Usuario usuario;

        for (Usuario user : usuarioService.listaUsuario()) {
            usuario = new Usuario(user.getEmail(), user.getNombre());
            lUsers.add(usuario);
        }

        return new ResponseEntity(lUsers, HttpStatus.OK);

    }


}
