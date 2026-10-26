package ma.ensa.pharmacie.repository;

import ma.ensa.pharmacie.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}
