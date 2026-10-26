package ma.ensa.pharmacie.controller.web;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.repository.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagRepository repository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tags", repository.findAll());
        return "tags/list";
    }
}
