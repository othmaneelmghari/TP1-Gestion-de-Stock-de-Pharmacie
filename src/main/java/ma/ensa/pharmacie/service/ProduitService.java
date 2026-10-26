package ma.ensa.pharmacie.service;

import lombok.RequiredArgsConstructor;
import ma.ensa.pharmacie.model.*;
import ma.ensa.pharmacie.repository.CategorieRepository;
import ma.ensa.pharmacie.repository.FournisseurRepository;
import ma.ensa.pharmacie.repository.ProduitRepository;
import ma.ensa.pharmacie.repository.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final FournisseurRepository fournisseurRepository;
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Produit findById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable : " + id));
    }

    @Transactional
    public Produit create(Produit in) {
        Produit p = new Produit();
        copyScalars(in, p);
        p.setCategorie(resolveCategorie(in.getCategorie()));
        p.setFournisseur(resolveFournisseur(in.getFournisseur()));
        p.setTags(resolveTags(in.getTags()));
        if (in.getDetailProduit() != null) {
            DetailProduit d = new DetailProduit();
            copyDetail(in.getDetailProduit(), d);
            p.setDetailProduit(d);
        }
        return produitRepository.save(p);
    }

    @Transactional
    public Produit update(Long id, Produit in) {
        Produit p = findById(id);
        copyScalars(in, p);
        p.setCategorie(resolveCategorie(in.getCategorie()));
        p.setFournisseur(resolveFournisseur(in.getFournisseur()));
        p.setTags(resolveTags(in.getTags()));
        if (in.getDetailProduit() != null) {
            if (p.getDetailProduit() == null) {
                p.setDetailProduit(new DetailProduit());
            }
            copyDetail(in.getDetailProduit(), p.getDetailProduit());
        }
        return produitRepository.save(p);
    }

    @Transactional
    public void delete(Long id) {
        produitRepository.delete(findById(id));
    }

    // ---------- helpers ----------

    private void copyScalars(Produit src, Produit dst) {
        dst.setNom(src.getNom());
        dst.setDescription(src.getDescription());
        dst.setPrix(src.getPrix());
        dst.setCodeBarre(src.getCodeBarre());
        dst.setDateExpiration(src.getDateExpiration());
    }

    private void copyDetail(DetailProduit src, DetailProduit dst) {
        dst.setFabricant(src.getFabricant());
        dst.setPaysOrigine(src.getPaysOrigine());
        dst.setComposition(src.getComposition());
        dst.setDosage(src.getDosage());
        dst.setIndications(src.getIndications());
    }

    private Categorie resolveCategorie(Categorie c) {
        if (c == null || c.getId() == null) return null;
        return categorieRepository.findById(c.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Catégorie introuvable : " + c.getId()));
    }

    private Fournisseur resolveFournisseur(Fournisseur f) {
        if (f == null || f.getId() == null) return null;
        return fournisseurRepository.findById(f.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fournisseur introuvable : " + f.getId()));
    }

    private Set<Tag> resolveTags(Set<Tag> tags) {
        Set<Tag> result = new HashSet<>();
        if (tags == null) return result;
        for (Tag t : tags) {
            if (t.getId() == null) continue;
            result.add(tagRepository.findById(t.getId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag introuvable : " + t.getId())));
        }
        return result;
    }
}
