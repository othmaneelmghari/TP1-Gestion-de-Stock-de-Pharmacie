package ma.ensa.pharmacie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "produit")
@Getter
@Setter
@NoArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(length = 1000)
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal prix;

    private String codeBarre;

    private LocalDate dateExpiration;

    // N produits -> 1 categorie
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    // N produits -> 1 fournisseur
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    // 1 produit <-> 1 detail (le detail est cree/modifie/supprime avec le produit)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "detail_id")
    private DetailProduit detailProduit;

    // N produits <-> N tags (table de jointure produit_tag)
    @ManyToMany
    @JoinTable(name = "produit_tag",
            joinColumns = @JoinColumn(name = "produit_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
}
