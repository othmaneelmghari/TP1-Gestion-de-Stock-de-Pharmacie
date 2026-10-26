package ma.ensa.pharmacie.controller.rest;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.model.Produit;
import ma.ensa.pharmacie.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitRestController {

    private final ProduitService service;

    @GetMapping
    public List<Produit> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Produit getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produit create(@RequestBody Produit produit) {
        return service.create(produit);
    }

    @PutMapping("/{id}")
    public Produit update(@PathVariable Long id, @RequestBody Produit produit) {
        return service.update(id, produit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
