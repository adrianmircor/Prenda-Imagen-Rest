package com.Cloudinary_Rest.entity.pk;

import com.Cloudinary_Rest.entity.Prenda;
import com.Cloudinary_Rest.entity.Usuario;

import java.io.Serializable;

public class BoletaId implements Serializable {

    private int idBoleta;
    private Usuario usuario;
    private Prenda prenda;

    public BoletaId() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
