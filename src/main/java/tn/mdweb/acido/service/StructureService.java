package tn.mdweb.acido.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.mdweb.acido.domain.Structure;
import tn.mdweb.acido.repository.StructureRepository;
import tn.mdweb.acido.service.dto.StructureDTO;
import tn.mdweb.acido.service.mapper.StructureMapper;

/**
 * Service Implementation for managing {@link Structure}.
 */
@Service
public class StructureService {

    private final Logger log = LoggerFactory.getLogger(StructureService.class);

    private final StructureRepository structureRepository;

    private final StructureMapper structureMapper;

    public StructureService(StructureRepository structureRepository, StructureMapper structureMapper) {
        this.structureRepository = structureRepository;
        this.structureMapper = structureMapper;
    }

    /**
     * Save a structure.
     *
     * @param structureDTO the entity to save.
     * @return the persisted entity.
     */
    public StructureDTO save(StructureDTO structureDTO) {
        log.debug("Request to save Structure : {}", structureDTO);
        Structure structure = structureMapper.toEntity(structureDTO);
        structure = structureRepository.save(structure);
        return structureMapper.toDto(structure);
    }

    /**
     * Update a structure.
     *
     * @param structureDTO the entity to save.
     * @return the persisted entity.
     */
    public StructureDTO update(StructureDTO structureDTO) {
        log.debug("Request to update Structure : {}", structureDTO);
        Structure structure = structureMapper.toEntity(structureDTO);
        structure = structureRepository.save(structure);
        return structureMapper.toDto(structure);
    }

    /**
     * Partially update a structure.
     *
     * @param structureDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StructureDTO> partialUpdate(StructureDTO structureDTO) {
        log.debug("Request to partially update Structure : {}", structureDTO);

        return structureRepository
            .findById(structureDTO.getId())
            .map(existingStructure -> {
                structureMapper.partialUpdate(existingStructure, structureDTO);

                return existingStructure;
            })
            .map(structureRepository::save)
            .map(structureMapper::toDto);
    }

    /**
     * Get all the structures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<StructureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Structures");
        return structureRepository.findAll(pageable).map(structureMapper::toDto);
    }

    /**
     * Get one structure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<StructureDTO> findOne(String id) {
        log.debug("Request to get Structure : {}", id);
        return structureRepository.findById(id).map(structureMapper::toDto);
    }

    /**
     * Delete the structure by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Structure : {}", id);
        structureRepository.deleteById(id);
    }
}
