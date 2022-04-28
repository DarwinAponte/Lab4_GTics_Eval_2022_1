package edu.pucp.gtics.lab4_gtics_20221.controller;

import edu.pucp.gtics.lab4_gtics_20221.entity.*;
import edu.pucp.gtics.lab4_gtics_20221.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/juegos")
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @Autowired
    PlataformasRepository plataformasRepository;

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    GenerosRepository generosRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"", "/","/lista"})
    public String listaJuegos (Model model){
        model.addAttribute("listaJuegos", juegosRepository.obtenerJuegos());
        model.addAttribute("listaJuegos1", juegosRepository.findAll(Sort.by("precio").descending()));
        return "juegos/lista";
    }



    public String vistaJuegos ( ){
        return "juegos/editarFrm";
    }

    @GetMapping("/nuevo")
    public String nuevoJuegos(){

        return "juegos/editarFrm";
    }

    public String editarJuegos( @ModelAttribute("juegos") Juegos juegos,Model model,@RequestParam("id") int id){
        Optional<Juegos> optionalJuegos=juegosRepository.findById(id);
        return "juegos/editarFrm";
    }

    public String guardarJuegos( ){
        return "juegos/editarFrm";
    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
        }
        return "redirect:/juegos/lista";
    }

}
