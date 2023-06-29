package tn.mdweb.acido.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.mdweb.acido.domain.Acidoform;

/**
 * Spring Data MongoDB repository for the Acidoform entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcidoformRepository extends MongoRepository<Acidoform, String> {}
