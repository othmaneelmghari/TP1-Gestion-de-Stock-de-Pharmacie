package ma.ensa.pharmacie.controller.rest;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.model.Produit;
import ma.ensa.pharmacie.model.Tag;
import ma.ensa.pharmacie.repository.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagRestController {

    private final TagRepository repository;

    @GetMapping
    public List<Tag> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Tag getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag introuvable : " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag create(@RequestBody Tag tag) {
        tag.setId(null);
        return repository.save(tag);
    }

    @PutMapping("/{id}")
    public Tag update(@PathVariable Long id, @RequestBody Tag in) {
        Tag t = getOne(id);
        t.setLibelle(in.getLibelle());
        return repository.save(t);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable Long id) {
        Tag t = getOne(id);
        // le tag est detache des produits (table produit_tag) avant suppression
        for (Produit p : t.getProduits()) {
            p.getTags().remove(t);
        }
        repository.delete(t);
    }
}
