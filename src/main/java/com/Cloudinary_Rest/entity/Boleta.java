package com.Cloudinary_Rest.entity;

import com.Cloudinary_Rest.entity.pk.BoletaId;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(BoletaId.class)
@Table(name = "boleta")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBoleta;

    @Id
    @OneToOne
    @JoinColumn(name = "fk_email") //De tabla boleta
    private Usuario usuario;

    @Id
    @OneToOne
    @JoinColumn(name = "fk_idPrenda") //De tabla boleta
    private Prenda prenda;

    private int cantidad;
    private Date fecha;

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Boleta{" +
                "idBoleta=" + idBoleta +
                ", usuario=" + usuario +
                ", prenda=" + prenda +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }
}
