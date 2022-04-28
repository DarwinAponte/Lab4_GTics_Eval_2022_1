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
        model.addAttribute("listaJuegos1", juegosRepository.findAll(Sort.by("precio").descending()));
        return "juegos/lista";
    }

    public String vistaJuegos ( ){
        return "juegos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoJuegos(@ModelAttribute("juegos") Juegos juegos,Model model){
        model.addAttribute("listaGenero", generosRepository.findAll());
        model.addAttribute("listaPlataforma", plataformasRepository.findAll());
        model.addAttribute("listaDistribuidora", distribuidorasRepository.findAll());
        return "juegos/editarFrm";
    }

    @GetMapping("/editar")
    public String editarJuegos(@ModelAttribute("juegos") Juegos juegos,Model model,@RequestParam("id") int id){
        Optional<Juegos> optionalJuegos=juegosRepository.findById(id);
        if (optionalJuegos.isPresent()) {
            Juegos juegos1 =optionalJuegos.get();
            model.addAttribute("juegos", juegos1);
            model.addAttribute("listaGenero", generosRepository.findAll());
            model.addAttribute("listaPlataforma", plataformasRepository.findAll());
            model.addAttribute("listaDistribuidora", distribuidorasRepository.findAll());
            return "juegos/editarFrm";
        } else {
            return "redirect:/juegos/lista";
        }

    }

    @PostMapping("/guardar")
    public String guardarJuegos(@ModelAttribute("juegos") @Valid Juegos juegos,
                                BindingResult bindingResult,
                                RedirectAttributes attr, Model model ){
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaGenero", generosRepository.findAll());
            model.addAttribute("listaPlataforma", plataformasRepository.findAll());
            model.addAttribute("listaDistribuidora", distribuidorasRepository.findAll());
            return "juegos/editarFrm";
        } else {
            if (juegos.getIdjuego() == 0) {
                attr.addFlashAttribute("msg", "Juego creado exitosamente");
                juegosRepository.save(juegos);
            } else {
                juegosRepository.save(juegos);
                attr.addFlashAttribute("msg", "Juego actualizado exitosamente");
            }
            return "redirect:/juegos";
        }
    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id, RedirectAttributes attr){
        Optional<Juegos> opt = juegosRepository.findById(id);
        if (opt.isPresent()) {
            juegosRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Distribuidora borrada exitosamente");
        }
        return "redirect:/juegos/lista";
    }

}
