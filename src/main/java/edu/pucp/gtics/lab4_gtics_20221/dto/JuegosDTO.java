package edu.pucp.gtics.lab4_gtics_20221.dto;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

public interface JuegosDTO {
    Integer getIdjuego();
    String getImage();
    String getNombre();
    String getGenero();
    String getDescripcion();
    String getPlataform();
    String getDistribuidor();
    String getPrecio();
}
