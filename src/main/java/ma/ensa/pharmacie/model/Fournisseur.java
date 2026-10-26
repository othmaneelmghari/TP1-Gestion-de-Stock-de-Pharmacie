package ma.ensa.pharmacie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fournisseur")
@Getter
@Setter
@NoArgsConstructor
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String contact;

    private String adresse;

    // 1 fournisseur -> N produits
    @OneToMany(mappedBy = "fournisseur")
    @JsonIgnore
    private List<Produit> produits = new ArrayList<>();
}
