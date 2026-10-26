package ma.ensa.pharmacie.controller.web;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("produits", service.findAll());
        model.addAttribute("today", LocalDate.now());
        return "produits/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("produit", service.findById(id));
        model.addAttribute("today", LocalDate.now());
        return "produits/detail";
    }
}
