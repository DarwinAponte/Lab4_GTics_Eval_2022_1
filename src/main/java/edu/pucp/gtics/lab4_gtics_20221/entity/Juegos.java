package edu.pucp.gtics.lab4_gtics_20221.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "juegos")
public class Juegos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idjuego;
    @Size(min = 3, max = 45,message = "Debe contener entre 3 y 45 caracteres")
    private String nombre;

    @Size(min = 3, max = 400,message = "Debe contener entre 3 y 400 caracteres")
    private String descripcion;

    @Digits(integer = 10, fraction = 0, message = "Debe ser un número")
    @Max(value = 500,message = "Valor máximo 500")
    @Min(value = 10,message = "Valor mínimo 10")
    private double precio;

    private String image;

    @ManyToOne
    @JoinColumn(name = "idplataforma")
    @NotNull(message = "Plataforma no debe estar vacía")
    private Plataformas plataforma;

    @ManyToOne
    @JoinColumn(name = "iddistribuidora")
    @NotNull(message = "Distribuidora no debe estar vacía")
    private Distribuidoras distribuidora;

    @ManyToOne
    @JoinColumn(name = "idgenero",nullable = false)
    @NotNull(message = "Género no debe estar vacío")
    private Generos genero;

    public int getIdjuego() {
        return idjuego;
    }

    public void setIdjuego(int idjuego) {
        this.idjuego = idjuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Plataformas getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataformas plataforma) {
        this.plataforma = plataforma;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Distribuidoras getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(Distribuidoras distribuidora) {
        this.distribuidora = distribuidora;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }
}
