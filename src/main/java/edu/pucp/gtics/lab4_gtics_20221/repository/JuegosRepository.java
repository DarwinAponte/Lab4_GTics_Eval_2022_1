package edu.pucp.gtics.lab4_gtics_20221.repository;

import edu.pucp.gtics.lab4_gtics_20221.dto.JuegosDTO;
import edu.pucp.gtics.lab4_gtics_20221.entity.Juegos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface JuegosRepository extends JpaRepository<Juegos,Integer> {

    @Query(value = "select ju.idjuego ,ju.image ,ju.nombre, ge.nombre as genero,ju.descripcion,pa.nombre as plataform,di.nombre as distribuidor,ju.precio from juegos as ju\n" +
            "left join generos ge on ge.idgenero=ju.idgenero\n" +
            "left join distribuidoras di on di.iddistribuidora=ju.iddistribuidora\n" +
            "left join plataformas pa on pa.idplataforma=ju.idplataforma\n" +
            "order by ju.precio desc", nativeQuery = true)
    List<JuegosDTO> obtenerJuegos();
}
