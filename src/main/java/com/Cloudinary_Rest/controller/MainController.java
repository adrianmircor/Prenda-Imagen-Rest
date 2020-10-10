package com.Cloudinary_Rest.controller;

import com.Cloudinary_Rest.dto.Mensaje;
import com.Cloudinary_Rest.entity.Imagen;
import com.Cloudinary_Rest.service.CloudinaryService;
import com.Cloudinary_Rest.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class MainController {

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImagenService imagenService;
    

    /*@GetMapping("/lista")
    public ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }*/

    @PostMapping("/subir")
    //El nombre de la variable de destino es el mismo que el nombre del parámetro -> multipartFile
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        //Si no es imagen el archivo a subir
        if(bi == null){
            return new ResponseEntity(new Mensaje("Imagen no valida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);//result tiene el body q se envió a Cloudinary
        //Imagen(String name, String imagenUrl, String imagenId)
        Imagen imagen = new Imagen(
                    (String)result.get("original_filename"), //String name
                    (String)result.get("url"), //String imagenUrl
                    (String)result.get("public_id")); //String imagenId
        imagenService.save(imagen);
        return new ResponseEntity(new Mensaje("imagen subida"), HttpStatus.OK); //Ya que se debe retornar ResponseEntity<?> = ResponseEntity<Mensaje>
    }

    //En la BD, se elimina por medio de la PK, que es entero autoincrementable
    /*@DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws IOException {

        if(!imagenService.existe(id)){
            return new ResponseEntity(new Mensaje("No existe la imagen"), HttpStatus.NOT_FOUND);
        }

        Imagen imagen = imagenService.conseguirUno(id).get(); //.get() ya que es de tipo Optional<?>
        Map result = cloudinaryService.delete(imagen.getImagenId()); //Se elimina de mi nube de Cloudinary
        imagenService.delete(id); //Se elimina de la BD
        return new ResponseEntity(new Mensaje("Imagen eliminada"), HttpStatus.OK);
    }*/

}
