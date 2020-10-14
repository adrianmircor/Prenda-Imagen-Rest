package com.Cloudinary_Rest.controller;

import com.Cloudinary_Rest.entity.Boleta;
import com.Cloudinary_Rest.entity.Prenda;
import com.Cloudinary_Rest.entity.Usuario;
import com.Cloudinary_Rest.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boletas")
@CrossOrigin
public class BoletaController {

    @Autowired
    BoletaService boletaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> lista() {

        //Mostra todos los rows que pertenecen a SOLO UNA BOLETA

        //
        List<Boleta> boletas = boletaService.listaBoleta();

        return new ResponseEntity(boletas, HttpStatus.OK);

    }

    @PostMapping("/usuarios/{email}")
    public ResponseEntity<?> crearBoleta(
            @PathVariable("email") String email,
            @RequestBody List<Boleta> boleta
    ) {

        //List<Object> list = new ArrayList(compra.values());
        //FALTA:
        // 2° GUARDAR TODOS LOS REGISTROS DE 'boleta' CON SOLO UN id
        System.out.println("Email usuario: " + email);

        //Codigo que agrega el usuario a los objetos 'bol' de la lista boleta
        Usuario us;
        for (Boleta bol : boleta) {
            us = new Usuario();
            us.setEmail(email);
            System.out.println("Boleta prenda id: " + bol.getPrenda().getIdPrenda());
            System.out.println("Boleta cantidad: " + bol.getCantidad());
            System.out.println("Boleta fecha: " + bol.getFecha());
            bol.setUsuario(us);
        }
        //Se ASIGNA el email del usuario en los registros de boleta que estuvieron en POST
        for (Boleta bol : boleta) {
            System.out.println("Boleta bol: " + bol.toString());
        }

        //boletaService.guardar(boleta);

        return new ResponseEntity("", HttpStatus.OK);
    }
}
