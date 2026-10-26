package ma.ensa.pharmacie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@NoArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(length = 1000)
    private String description;

    // 1 categorie -> N produits (cote inverse, ignore en JSON pour eviter les boucles)
    @OneToMany(mappedBy = "categorie")
    @JsonIgnore
    private List<Produit> produits = new ArrayList<>();
}
