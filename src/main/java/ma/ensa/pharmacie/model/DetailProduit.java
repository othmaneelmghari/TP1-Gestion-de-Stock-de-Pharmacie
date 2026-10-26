package ma.ensa.pharmacie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detail_produit")
@Getter
@Setter
@NoArgsConstructor
public class DetailProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fabricant;

    private String paysOrigine;

    private String composition;

    private String dosage;

    @Column(length = 1000)
    private String indications;
}
