package ma.ensa.pharmacie.controller.web;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.repository.CategorieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieRepository repository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", repository.findAll());
        return "categories/list";
    }
}
