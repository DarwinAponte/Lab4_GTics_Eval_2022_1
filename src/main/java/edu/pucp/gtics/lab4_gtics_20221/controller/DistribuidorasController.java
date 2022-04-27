package edu.pucp.gtics.lab4_gtics_20221.controller;

import edu.pucp.gtics.lab4_gtics_20221.entity.Distribuidoras;
import edu.pucp.gtics.lab4_gtics_20221.entity.Paises;
import edu.pucp.gtics.lab4_gtics_20221.repository.DistribuidorasRepository;
import edu.pucp.gtics.lab4_gtics_20221.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/distribuidoras")
public class DistribuidorasController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    PaisesRepository paisesRepository;

    @GetMapping(value = {"", "/", "lista"})
    public String listaDistribuidoras(Model model) {
        model.addAttribute("listaDistribuidoras", distribuidorasRepository.findAll(Sort.by("nombre")));
        return "distribuidoras/lista";
    }


//    public String editarDistribuidoras() {
//
//    }
//
//    public String nuevaDistribuidora() {
//
//    }

    @PostMapping("/guardar")
    public String guardarDistribuidora(@ModelAttribute("distribuidora") @Valid Distribuidoras distribuidoras, BindingResult bindingResult,
                                       RedirectAttributes attr, Model model
//                                       @RequestParam(name = "fechaContrato", required = false) String fechaContrato,
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaPaises", paisesRepository.findAll());
            return "distribuidoras/editarFrm";
        } else {
            if (distribuidoras.getIddistribuidora() == 0) {
                attr.addFlashAttribute("msg", "Distribuidora creada exitosamente");
                distribuidorasRepository.save(distribuidoras);
                return "redirect:/gameshop3/distribuidoras";
            } else {
                distribuidorasRepository.save(distribuidoras);
                attr.addFlashAttribute("msg", "Distribuidora actualizada exitosamente");
                return "redirect:/gameshop3/distribuidoras";
            }
        }
    }

    @GetMapping("/editar")
    public String  editarDistribuidoras(Model model, @RequestParam("id") int id,
                                 @ModelAttribute("distribuidora") Distribuidoras distribuidoras) {

        Optional<Distribuidoras> optionalDistrib = distribuidorasRepository.findById(id);

        if (optionalDistrib.isPresent()) {
            distribuidoras = optionalDistrib.get();
            model.addAttribute("employee", distribuidoras);
            model.addAttribute("listaPaises", paisesRepository.findAll());
            return "distribuidoras/editarFrm";
        } else {
            return "redirect:/gameshop3/distribuidoras";
        }
    }

    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id) {
        Optional<Distribuidoras> opt = distribuidorasRepository.findById(id);
        if (opt.isPresent()) {
            distribuidorasRepository.deleteById(id);
        }
        return "redirect:/gameshop3/distribuidoras";
    }

}
