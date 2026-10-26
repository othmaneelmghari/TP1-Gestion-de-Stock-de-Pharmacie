package ma.ensa.pharmacie.controller.rest;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.model.Fournisseur;
import ma.ensa.pharmacie.repository.FournisseurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
@RequiredArgsConstructor
public class FournisseurRestController {

    private final FournisseurRepository repository;

    @GetMapping
    public List<Fournisseur> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Fournisseur getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur introuvable : " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fournisseur create(@RequestBody Fournisseur fournisseur) {
        fournisseur.setId(null);
        return repository.save(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur update(@PathVariable Long id, @RequestBody Fournisseur in) {
        Fournisseur f = getOne(id);
        f.setNom(in.getNom());
        f.setContact(in.getContact());
        f.setAdresse(in.getAdresse());
        return repository.save(f);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.delete(getOne(id));
    }
}
