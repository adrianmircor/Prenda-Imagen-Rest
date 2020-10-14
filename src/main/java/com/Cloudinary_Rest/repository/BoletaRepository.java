package com.Cloudinary_Rest.repository;

import com.Cloudinary_Rest.entity.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta,Integer> {
}
