package tn.mdweb.acido.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.mdweb.acido.repository.ServicestructureRepository;
import tn.mdweb.acido.service.ServicestructureService;
import tn.mdweb.acido.service.dto.ServicestructureDTO;
import tn.mdweb.acido.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.acido.domain.Servicestructure}.
 */
@RestController
@RequestMapping("/api")
public class ServicestructureResource {

    private final Logger log = LoggerFactory.getLogger(ServicestructureResource.class);

    private static final String ENTITY_NAME = "servicestructure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServicestructureService servicestructureService;

    private final ServicestructureRepository servicestructureRepository;

    public ServicestructureResource(
        ServicestructureService servicestructureService,
        ServicestructureRepository servicestructureRepository
    ) {
        this.servicestructureService = servicestructureService;
        this.servicestructureRepository = servicestructureRepository;
    }

    /**
     * {@code POST  /servicestructures} : Create a new servicestructure.
     *
     * @param servicestructureDTO the servicestructureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new servicestructureDTO, or with status {@code 400 (Bad Request)} if the servicestructure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/servicestructures")
    public ResponseEntity<ServicestructureDTO> createServicestructure(@Valid @RequestBody ServicestructureDTO servicestructureDTO)
        throws URISyntaxException {
        log.debug("REST request to save Servicestructure : {}", servicestructureDTO);
        if (servicestructureDTO.getId() != null) {
            throw new BadRequestAlertException("A new servicestructure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServicestructureDTO result = servicestructureService.save(servicestructureDTO);
        return ResponseEntity
            .created(new URI("/api/servicestructures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /servicestructures/:id} : Updates an existing servicestructure.
     *
     * @param id the id of the servicestructureDTO to save.
     * @param servicestructureDTO the servicestructureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servicestructureDTO,
     * or with status {@code 400 (Bad Request)} if the servicestructureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the servicestructureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/servicestructures/{id}")
    public ResponseEntity<ServicestructureDTO> updateServicestructure(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody ServicestructureDTO servicestructureDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Servicestructure : {}, {}", id, servicestructureDTO);
        if (servicestructureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, servicestructureDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!servicestructureRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ServicestructureDTO result = servicestructureService.update(servicestructureDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, servicestructureDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /servicestructures/:id} : Partial updates given fields of an existing servicestructure, field will ignore if it is null
     *
     * @param id the id of the servicestructureDTO to save.
     * @param servicestructureDTO the servicestructureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servicestructureDTO,
     * or with status {@code 400 (Bad Request)} if the servicestructureDTO is not valid,
     * or with status {@code 404 (Not Found)} if the servicestructureDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the servicestructureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/servicestructures/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ServicestructureDTO> partialUpdateServicestructure(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody ServicestructureDTO servicestructureDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Servicestructure partially : {}, {}", id, servicestructureDTO);
        if (servicestructureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, servicestructureDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!servicestructureRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServicestructureDTO> result = servicestructureService.partialUpdate(servicestructureDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, servicestructureDTO.getId())
        );
    }

    /**
     * {@code GET  /servicestructures} : get all the servicestructures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servicestructures in body.
     */
    @GetMapping("/servicestructures")
    public ResponseEntity<List<ServicestructureDTO>> getAllServicestructures(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Servicestructures");
        Page<ServicestructureDTO> page = servicestructureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /servicestructures/:id} : get the "id" servicestructure.
     *
     * @param id the id of the servicestructureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the servicestructureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/servicestructures/{id}")
    public ResponseEntity<ServicestructureDTO> getServicestructure(@PathVariable String id) {
        log.debug("REST request to get Servicestructure : {}", id);
        Optional<ServicestructureDTO> servicestructureDTO = servicestructureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(servicestructureDTO);
    }

    /**
     * {@code DELETE  /servicestructures/:id} : delete the "id" servicestructure.
     *
     * @param id the id of the servicestructureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/servicestructures/{id}")
    public ResponseEntity<Void> deleteServicestructure(@PathVariable String id) {
        log.debug("REST request to delete Servicestructure : {}", id);
        servicestructureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
