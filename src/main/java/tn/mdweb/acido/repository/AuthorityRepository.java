package tn.mdweb.acido.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.mdweb.acido.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {}
