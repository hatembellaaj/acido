package tn.mdweb.acido.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.mdweb.acido.domain.Acidoform;
import tn.mdweb.acido.repository.AcidoformRepository;
import tn.mdweb.acido.service.dto.AcidoformDTO;
import tn.mdweb.acido.service.mapper.AcidoformMapper;

/**
 * Service Implementation for managing {@link Acidoform}.
 */
@Service
public class AcidoformService {

    private final Logger log = LoggerFactory.getLogger(AcidoformService.class);

    private final AcidoformRepository acidoformRepository;

    private final AcidoformMapper acidoformMapper;

    public AcidoformService(AcidoformRepository acidoformRepository, AcidoformMapper acidoformMapper) {
        this.acidoformRepository = acidoformRepository;
        this.acidoformMapper = acidoformMapper;
    }

    /**
     * Save a acidoform.
     *
     * @param acidoformDTO the entity to save.
     * @return the persisted entity.
     */
    public AcidoformDTO save(AcidoformDTO acidoformDTO) {
        log.debug("Request to save Acidoform : {}", acidoformDTO);
        Acidoform acidoform = acidoformMapper.toEntity(acidoformDTO);
        acidoform = acidoformRepository.save(acidoform);
        return acidoformMapper.toDto(acidoform);
    }

    /**
     * Update a acidoform.
     *
     * @param acidoformDTO the entity to save.
     * @return the persisted entity.
     */
    public AcidoformDTO update(AcidoformDTO acidoformDTO) {
        log.debug("Request to update Acidoform : {}", acidoformDTO);
        Acidoform acidoform = acidoformMapper.toEntity(acidoformDTO);
        acidoform = acidoformRepository.save(acidoform);
        return acidoformMapper.toDto(acidoform);
    }

    /**
     * Partially update a acidoform.
     *
     * @param acidoformDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AcidoformDTO> partialUpdate(AcidoformDTO acidoformDTO) {
        log.debug("Request to partially update Acidoform : {}", acidoformDTO);

        return acidoformRepository
            .findById(acidoformDTO.getId())
            .map(existingAcidoform -> {
                acidoformMapper.partialUpdate(existingAcidoform, acidoformDTO);

                return existingAcidoform;
            })
            .map(acidoformRepository::save)
            .map(acidoformMapper::toDto);
    }

    /**
     * Get all the acidoforms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<AcidoformDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Acidoforms");
        return acidoformRepository.findAll(pageable).map(acidoformMapper::toDto);
    }

    /**
     * Get one acidoform by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<AcidoformDTO> findOne(String id) {
        log.debug("Request to get Acidoform : {}", id);
        return acidoformRepository.findById(id).map(acidoformMapper::toDto);
    }

    /**
     * Delete the acidoform by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Acidoform : {}", id);
        acidoformRepository.deleteById(id);
    }
}
