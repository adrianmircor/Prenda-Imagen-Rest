package com.Cloudinary_Rest.repository;

import com.Cloudinary_Rest.entity.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda,Integer> {

    Prenda findById(int id);

}
