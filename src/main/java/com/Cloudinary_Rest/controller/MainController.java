package com.Cloudinary_Rest.controller;

import com.Cloudinary_Rest.dto.Mensaje;
import com.Cloudinary_Rest.entity.Imagen;
import com.Cloudinary_Rest.entity.Prenda;
import com.Cloudinary_Rest.service.CloudinaryService;
import com.Cloudinary_Rest.service.ImagenService;
import com.Cloudinary_Rest.service.PrendaService;
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

    @Autowired
    PrendaService prendaService;
    

    /*@GetMapping("/lista")
    public ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }*/

    /*@PostMapping("/subir")
    //El nombre de la variable de destino es el mismo que el nombre del parámetro -> multipartFile
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        //Si no es imagen el archivo a subir
        if (bi == null) {
            return new ResponseEntity(new Mensaje("Imagen no valida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);//result tiene el body q se envió a Cloudinary
        //Imagen(String name, String imagenUrl, String imagenId)
        Imagen imagen = new Imagen(
                (String) result.get("original_filename"), //String name
                (String) result.get("url"), //String imagenUrl
                (String) result.get("public_id")); //String imagenId
        imagenService.save(imagen);
        return new ResponseEntity(new Mensaje("imagen subida"), HttpStatus.OK); //Ya que se debe retornar ResponseEntity<?> = ResponseEntity<Mensaje>
    }*/

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

    @PostMapping("/crear")
    //El nombre de la variable de destino es el mismo que el nombre del parámetro -> multipartFile
    public ResponseEntity<?> crearPrenda(
            @RequestParam MultipartFile multipartFile,
            @RequestParam String nombre,
            @RequestParam float precio
            ) throws IOException {

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        //Si no es imagen el archivo a subir
        if (bi == null) {
            return new ResponseEntity(new Mensaje("Imagen no valida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);//result tiene el body q se envió a Cloudinary
        //Imagen(String name, String imagenUrl, String imagenId)
        Imagen imagen = new Imagen(
                (String) result.get("original_filename"), //String name
                (String) result.get("url"), //String imagenUrl
                (String) result.get("public_id")); //String imagenId
        imagenService.save(imagen);

        //Guardar en objeto prenda
        Prenda prenda = new Prenda(nombre,precio,imagen);
        System.out.println("LA PRENDA ES: "+prenda);

        //Guardar en tabla prenda
        prendaService.guardar(prenda);

        return new ResponseEntity(new Mensaje("Prenda CREADA"), HttpStatus.OK); //Ya que se debe retornar ResponseEntity<?> = ResponseEntity<Mensaje>
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws IOException {

        if(!prendaService.existePrenda(id)){
            return new ResponseEntity(new Mensaje("No existe la prenda"), HttpStatus.NOT_FOUND);
        }
        Prenda prenda = prendaService.obtenerPrenda(id); //.get() ya que es de tipo Optional<?>
        //Eliminar de Cloudinary
        Map result = cloudinaryService.delete(prenda.getImagen().getImagenId()); //Se elimina de mi nube de Cloudinary

        //Eliminar Prenda de BD
        prendaService.eliminar(id);

        //Eliminar Imagen de BD
        imagenService.delete(prenda.getImagen().getImagenId()); //Se elimina de la BD
        return new ResponseEntity(new Mensaje("Prenda ELIMINADA"), HttpStatus.OK);
    }

    @GetMapping("/prenda/{id}")
    public ResponseEntity<?> recuperarPrenda(@PathVariable("id") int id) throws IOException {

        if(!prendaService.existePrenda(id)){
            return new ResponseEntity(new Mensaje("No existe la prenda"), HttpStatus.NOT_FOUND);
        }
        Prenda prenda = prendaService.obtenerPrenda(id); //.get() ya que es de tipo Optional<?>

        return new ResponseEntity(prenda, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> recuperarListaPrenda() throws IOException {

        List<Prenda> listaPrenda = prendaService.obtenerListaPrenda(); //.get() ya que es de tipo Optional<?>

        return new ResponseEntity(listaPrenda, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> modificar(
            @PathVariable("id") int id,
            @RequestParam MultipartFile multipartFile,
            @RequestParam String nombre,
            @RequestParam float precio
    ) throws IOException {

        //Buscar la prenda por el id... si existe:
        if(prendaService.existePrenda(id)){
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            //Si no es imagen el archivo a subir
            if (bi == null) {
                return new ResponseEntity(new Mensaje("Imagen no valida"), HttpStatus.BAD_REQUEST);
            }

            //result tiene el body q se envió a Cloudinary
            Map result = cloudinaryService.upload(multipartFile); //Subir a Cloudinary
            //Imagen(String name, String imagenUrl, String imagenId)
            Imagen imagen = new Imagen(
                    (String) result.get("original_filename"), //String name
                    (String) result.get("url"), //String imagenUrl
                    (String) result.get("public_id")); //String imagenId

            //Guarda la imagen en BD
            imagenService.save(imagen);

            //Buscar la prenda por el id
            Prenda prendaAnterior = prendaService.obtenerPrenda(id);
            String idImagen_AntesModificar = prendaAnterior.getImagen().getImagenId();

            Prenda prendaNueva = new Prenda(id,nombre,precio,imagen);

            //Guardar la NUEVA prenda en tabla Prenda
            prendaService.guardar(prendaNueva);

            System.out.println("ID IMAGEN ANTES: "+idImagen_AntesModificar + " / "+ "ID IMAGEN LUEGO: "+imagen.getImagenId());

            //Eliminar la imagen de la BD y Cloudinary ANTERIOR
            imagenService.delete(idImagen_AntesModificar);
            cloudinaryService.delete(idImagen_AntesModificar);

            System.out.println("LA PRENDA MODIFICADA ES: "+prendaNueva);

            return new ResponseEntity(new Mensaje("Prenda MODIFICADA"), HttpStatus.OK); //Ya que se debe retornar ResponseEntity<?> = ResponseEntity<Mensaje>
        }

        return new ResponseEntity(new Mensaje("NO EXISTE LA PRENDA"), HttpStatus.BAD_REQUEST); //Ya que se debe retornar ResponseEntity<?> = ResponseEntity<Mensaje>

    }

}
