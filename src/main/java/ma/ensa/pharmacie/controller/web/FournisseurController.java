package ma.ensa.pharmacie.controller.web;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.repository.FournisseurRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    private final FournisseurRepository repository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("fournisseurs", repository.findAll());
        return "fournisseurs/list";
    }
}
