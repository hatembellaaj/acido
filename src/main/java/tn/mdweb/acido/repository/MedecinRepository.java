package tn.mdweb.acido.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.mdweb.acido.domain.Medecin;

/**
 * Spring Data MongoDB repository for the Medecin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedecinRepository extends MongoRepository<Medecin, String> {}
