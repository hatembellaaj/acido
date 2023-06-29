package tn.mdweb.acido.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.mdweb.acido.domain.Servicestructure;
import tn.mdweb.acido.repository.ServicestructureRepository;
import tn.mdweb.acido.service.dto.ServicestructureDTO;
import tn.mdweb.acido.service.mapper.ServicestructureMapper;

/**
 * Service Implementation for managing {@link Servicestructure}.
 */
@Service
public class ServicestructureService {

    private final Logger log = LoggerFactory.getLogger(ServicestructureService.class);

    private final ServicestructureRepository servicestructureRepository;

    private final ServicestructureMapper servicestructureMapper;

    public ServicestructureService(ServicestructureRepository servicestructureRepository, ServicestructureMapper servicestructureMapper) {
        this.servicestructureRepository = servicestructureRepository;
        this.servicestructureMapper = servicestructureMapper;
    }

    /**
     * Save a servicestructure.
     *
     * @param servicestructureDTO the entity to save.
     * @return the persisted entity.
     */
    public ServicestructureDTO save(ServicestructureDTO servicestructureDTO) {
        log.debug("Request to save Servicestructure : {}", servicestructureDTO);
        Servicestructure servicestructure = servicestructureMapper.toEntity(servicestructureDTO);
        servicestructure = servicestructureRepository.save(servicestructure);
        return servicestructureMapper.toDto(servicestructure);
    }

    /**
     * Update a servicestructure.
     *
     * @param servicestructureDTO the entity to save.
     * @return the persisted entity.
     */
    public ServicestructureDTO update(ServicestructureDTO servicestructureDTO) {
        log.debug("Request to update Servicestructure : {}", servicestructureDTO);
        Servicestructure servicestructure = servicestructureMapper.toEntity(servicestructureDTO);
        servicestructure = servicestructureRepository.save(servicestructure);
        return servicestructureMapper.toDto(servicestructure);
    }

    /**
     * Partially update a servicestructure.
     *
     * @param servicestructureDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ServicestructureDTO> partialUpdate(ServicestructureDTO servicestructureDTO) {
        log.debug("Request to partially update Servicestructure : {}", servicestructureDTO);

        return servicestructureRepository
            .findById(servicestructureDTO.getId())
            .map(existingServicestructure -> {
                servicestructureMapper.partialUpdate(existingServicestructure, servicestructureDTO);

                return existingServicestructure;
            })
            .map(servicestructureRepository::save)
            .map(servicestructureMapper::toDto);
    }

    /**
     * Get all the servicestructures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ServicestructureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Servicestructures");
        return servicestructureRepository.findAll(pageable).map(servicestructureMapper::toDto);
    }

    /**
     * Get one servicestructure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ServicestructureDTO> findOne(String id) {
        log.debug("Request to get Servicestructure : {}", id);
        return servicestructureRepository.findById(id).map(servicestructureMapper::toDto);
    }

    /**
     * Delete the servicestructure by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Servicestructure : {}", id);
        servicestructureRepository.deleteById(id);
    }
}
