package tn.mdweb.acido.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.mdweb.acido.domain.Servicestructure;

/**
 * Spring Data MongoDB repository for the Servicestructure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicestructureRepository extends MongoRepository<Servicestructure, String> {}
