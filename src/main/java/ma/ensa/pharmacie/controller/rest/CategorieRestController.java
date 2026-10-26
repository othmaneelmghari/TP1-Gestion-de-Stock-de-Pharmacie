package ma.ensa.pharmacie.controller.rest;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.model.Categorie;
import ma.ensa.pharmacie.repository.CategorieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieRestController {

    private final CategorieRepository repository;

    @GetMapping
    public List<Categorie> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Categorie getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Catégorie introuvable : " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categorie create(@RequestBody Categorie categorie) {
        categorie.setId(null);
        return repository.save(categorie);
    }

    @PutMapping("/{id}")
    public Categorie update(@PathVariable Long id, @RequestBody Categorie in) {
        Categorie c = getOne(id);
        c.setNom(in.getNom());
        c.setDescription(in.getDescription());
        return repository.save(c);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.delete(getOne(id));
    }
}
