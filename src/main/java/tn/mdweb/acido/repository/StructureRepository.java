package tn.mdweb.acido.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.mdweb.acido.domain.Structure;

/**
 * Spring Data MongoDB repository for the Structure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StructureRepository extends MongoRepository<Structure, String> {}
