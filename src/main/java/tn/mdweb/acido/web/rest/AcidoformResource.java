package tn.mdweb.acido.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import tn.mdweb.acido.repository.AcidoformRepository;
import tn.mdweb.acido.service.AcidoformService;
import tn.mdweb.acido.service.dto.AcidoformDTO;
import tn.mdweb.acido.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.acido.domain.Acidoform}.
 */
@RestController
@RequestMapping("/api")
public class AcidoformResource {

    private final Logger log = LoggerFactory.getLogger(AcidoformResource.class);

    private static final String ENTITY_NAME = "acidoform";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AcidoformService acidoformService;

    private final AcidoformRepository acidoformRepository;

    public AcidoformResource(AcidoformService acidoformService, AcidoformRepository acidoformRepository) {
        this.acidoformService = acidoformService;
        this.acidoformRepository = acidoformRepository;
    }

    /**
     * {@code POST  /acidoforms} : Create a new acidoform.
     *
     * @param acidoformDTO the acidoformDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new acidoformDTO, or with status {@code 400 (Bad Request)} if the acidoform has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/acidoforms")
    public ResponseEntity<AcidoformDTO> createAcidoform(@RequestBody AcidoformDTO acidoformDTO) throws URISyntaxException {
        log.debug("REST request to save Acidoform : {}", acidoformDTO);
        if (acidoformDTO.getId() != null) {
            throw new BadRequestAlertException("A new acidoform cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcidoformDTO result = acidoformService.save(acidoformDTO);
        return ResponseEntity
            .created(new URI("/api/acidoforms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /acidoforms/:id} : Updates an existing acidoform.
     *
     * @param id the id of the acidoformDTO to save.
     * @param acidoformDTO the acidoformDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acidoformDTO,
     * or with status {@code 400 (Bad Request)} if the acidoformDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the acidoformDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/acidoforms/{id}")
    public ResponseEntity<AcidoformDTO> updateAcidoform(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody AcidoformDTO acidoformDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Acidoform : {}, {}", id, acidoformDTO);
        if (acidoformDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, acidoformDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!acidoformRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AcidoformDTO result = acidoformService.update(acidoformDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, acidoformDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /acidoforms/:id} : Partial updates given fields of an existing acidoform, field will ignore if it is null
     *
     * @param id the id of the acidoformDTO to save.
     * @param acidoformDTO the acidoformDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acidoformDTO,
     * or with status {@code 400 (Bad Request)} if the acidoformDTO is not valid,
     * or with status {@code 404 (Not Found)} if the acidoformDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the acidoformDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/acidoforms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AcidoformDTO> partialUpdateAcidoform(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody AcidoformDTO acidoformDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Acidoform partially : {}, {}", id, acidoformDTO);
        if (acidoformDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, acidoformDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!acidoformRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AcidoformDTO> result = acidoformService.partialUpdate(acidoformDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, acidoformDTO.getId())
        );
    }

    /**
     * {@code GET  /acidoforms} : get all the acidoforms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of acidoforms in body.
     */
    @GetMapping("/acidoforms")
    public ResponseEntity<List<AcidoformDTO>> getAllAcidoforms(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Acidoforms");
        Page<AcidoformDTO> page = acidoformService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /acidoforms/:id} : get the "id" acidoform.
     *
     * @param id the id of the acidoformDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the acidoformDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/acidoforms/{id}")
    public ResponseEntity<AcidoformDTO> getAcidoform(@PathVariable String id) {
        log.debug("REST request to get Acidoform : {}", id);
        Optional<AcidoformDTO> acidoformDTO = acidoformService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acidoformDTO);
    }

    /**
     * {@code DELETE  /acidoforms/:id} : delete the "id" acidoform.
     *
     * @param id the id of the acidoformDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/acidoforms/{id}")
    public ResponseEntity<Void> deleteAcidoform(@PathVariable String id) {
        log.debug("REST request to delete Acidoform : {}", id);
        acidoformService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
