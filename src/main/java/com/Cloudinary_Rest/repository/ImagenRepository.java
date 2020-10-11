package com.Cloudinary_Rest.repository;

import com.Cloudinary_Rest.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen,String> {

    //List<Imagen> findByOrderById();

}
