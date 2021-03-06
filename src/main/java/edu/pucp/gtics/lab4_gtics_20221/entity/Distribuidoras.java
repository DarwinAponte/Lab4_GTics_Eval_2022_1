package edu.pucp.gtics.lab4_gtics_20221.entity;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "distribuidoras")
public class Distribuidoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "Distribuidora no puede estar vacío")
    private Integer iddistribuidora;

    @Size(min = 3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
    private String nombre;

    @Size(min = 3, max = 198, message = "Debe contener entre 3 y 198 caracteres")
    private String descripcion;

    @URL(message = "Debe insertar un URL válido")
    @Size(min = 3, max = 198, message = "Debe contener entre 3 y 198 caracteres")
    private String web;

    @Digits(integer = 10, fraction = 0, message = "Debe ser un número entero")
    @Max(value = 2100, message = "Debe ser menor o igual a 2100")
    @Min(value = 1800, message = "Debe ser mayor o igual a 1800")
    private int fundacion = 1870;

    @ManyToOne
    @JoinColumn(name = "idsede")
    @NotNull(message = "Sede no debe estar vacío")
    private Paises pais;

    public Integer getIddistribuidora() {
        return iddistribuidora;
    }

    public void setIddistribuidora(Integer iddistribuidora) {
        this.iddistribuidora = iddistribuidora;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }
}
