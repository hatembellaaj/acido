package tn.mdweb.acido.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import tn.mdweb.acido.IntegrationTest;
import tn.mdweb.acido.domain.Acidoform;
import tn.mdweb.acido.domain.enumeration.esexe;
import tn.mdweb.acido.domain.enumeration.estatut;
import tn.mdweb.acido.domain.enumeration.etypeobservation;
import tn.mdweb.acido.repository.AcidoformRepository;
import tn.mdweb.acido.service.dto.AcidoformDTO;
import tn.mdweb.acido.service.mapper.AcidoformMapper;

/**
 * Integration tests for the {@link AcidoformResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AcidoformResourceIT {

    private static final Boolean DEFAULT_HOMOCYSTINURIE = false;
    private static final Boolean UPDATED_HOMOCYSTINURIE = true;

    private static final Boolean DEFAULT_LEUCINOSE = false;
    private static final Boolean UPDATED_LEUCINOSE = true;

    private static final Boolean DEFAULT_PHENYLCETONURIE = false;
    private static final Boolean UPDATED_PHENYLCETONURIE = true;

    private static final Boolean DEFAULT_TYROSINEMIE = false;
    private static final Boolean UPDATED_TYROSINEMIE = true;

    private static final LocalDate DEFAULT_UPDATEDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATEDATE = LocalDate.now(ZoneId.systemDefault());

    private static final etypeobservation DEFAULT_TYPEOBSERVATION = etypeobservation.P;
    private static final etypeobservation UPDATED_TYPEOBSERVATION = etypeobservation.F;

    private static final LocalDate DEFAULT_CREATEDDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATEDDATE = LocalDate.now(ZoneId.systemDefault());

    private static final esexe DEFAULT_SEXE = esexe.F;
    private static final esexe UPDATED_SEXE = esexe.M;

    private static final LocalDate DEFAULT_DATENAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATENAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final estatut DEFAULT_STATUT = estatut.ENCORE_SUIVI;
    private static final estatut UPDATED_STATUT = estatut.DECEDE;

    private static final Integer DEFAULT_AGEACTUELANS = 1;
    private static final Integer UPDATED_AGEACTUELANS = 2;

    private static final Integer DEFAULT_AGEACTUELMOIS = 1;
    private static final Integer UPDATED_AGEACTUELMOIS = 2;

    private static final Integer DEFAULT_AGEACTUELJOURS = 1;
    private static final Integer UPDATED_AGEACTUELJOURS = 2;

    private static final LocalDate DEFAULT_DATEDECES = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEDECES = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGEDECESAN = 1;
    private static final Integer UPDATED_AGEDECESAN = 2;

    private static final Integer DEFAULT_AGEDECESMOIS = 1;
    private static final Integer UPDATED_AGEDECESMOIS = 2;

    private static final Integer DEFAULT_AGEDECESJOURS = 1;
    private static final Integer UPDATED_AGEDECESJOURS = 2;

    private static final String ENTITY_API_URL = "/api/acidoforms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AcidoformRepository acidoformRepository;

    @Autowired
    private AcidoformMapper acidoformMapper;

    @Autowired
    private MockMvc restAcidoformMockMvc;

    private Acidoform acidoform;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Acidoform createEntity() {
        Acidoform acidoform = new Acidoform()
            .homocystinurie(DEFAULT_HOMOCYSTINURIE)
            .leucinose(DEFAULT_LEUCINOSE)
            .phenylcetonurie(DEFAULT_PHENYLCETONURIE)
            .tyrosinemie(DEFAULT_TYROSINEMIE)
            .updatedate(DEFAULT_UPDATEDATE)
            .typeobservation(DEFAULT_TYPEOBSERVATION)
            .createddate(DEFAULT_CREATEDDATE)
            .sexe(DEFAULT_SEXE)
            .datenaissance(DEFAULT_DATENAISSANCE)
            .statut(DEFAULT_STATUT)
            .ageactuelans(DEFAULT_AGEACTUELANS)
            .ageactuelmois(DEFAULT_AGEACTUELMOIS)
            .ageactueljours(DEFAULT_AGEACTUELJOURS)
            .datedeces(DEFAULT_DATEDECES)
            .agedecesan(DEFAULT_AGEDECESAN)
            .agedecesmois(DEFAULT_AGEDECESMOIS)
            .agedecesjours(DEFAULT_AGEDECESJOURS);
        return acidoform;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Acidoform createUpdatedEntity() {
        Acidoform acidoform = new Acidoform()
            .homocystinurie(UPDATED_HOMOCYSTINURIE)
            .leucinose(UPDATED_LEUCINOSE)
            .phenylcetonurie(UPDATED_PHENYLCETONURIE)
            .tyrosinemie(UPDATED_TYROSINEMIE)
            .updatedate(UPDATED_UPDATEDATE)
            .typeobservation(UPDATED_TYPEOBSERVATION)
            .createddate(UPDATED_CREATEDDATE)
            .sexe(UPDATED_SEXE)
            .datenaissance(UPDATED_DATENAISSANCE)
            .statut(UPDATED_STATUT)
            .ageactuelans(UPDATED_AGEACTUELANS)
            .ageactuelmois(UPDATED_AGEACTUELMOIS)
            .ageactueljours(UPDATED_AGEACTUELJOURS)
            .datedeces(UPDATED_DATEDECES)
            .agedecesan(UPDATED_AGEDECESAN)
            .agedecesmois(UPDATED_AGEDECESMOIS)
            .agedecesjours(UPDATED_AGEDECESJOURS);
        return acidoform;
    }

    @BeforeEach
    public void initTest() {
        acidoformRepository.deleteAll();
        acidoform = createEntity();
    }

    @Test
    void createAcidoform() throws Exception {
        int databaseSizeBeforeCreate = acidoformRepository.findAll().size();
        // Create the Acidoform
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(acidoform);
        restAcidoformMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acidoformDTO)))
            .andExpect(status().isCreated());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeCreate + 1);
        Acidoform testAcidoform = acidoformList.get(acidoformList.size() - 1);
        assertThat(testAcidoform.getHomocystinurie()).isEqualTo(DEFAULT_HOMOCYSTINURIE);
        assertThat(testAcidoform.getLeucinose()).isEqualTo(DEFAULT_LEUCINOSE);
        assertThat(testAcidoform.getPhenylcetonurie()).isEqualTo(DEFAULT_PHENYLCETONURIE);
        assertThat(testAcidoform.getTyrosinemie()).isEqualTo(DEFAULT_TYROSINEMIE);
        assertThat(testAcidoform.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
        assertThat(testAcidoform.getTypeobservation()).isEqualTo(DEFAULT_TYPEOBSERVATION);
        assertThat(testAcidoform.getCreateddate()).isEqualTo(DEFAULT_CREATEDDATE);
        assertThat(testAcidoform.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testAcidoform.getDatenaissance()).isEqualTo(DEFAULT_DATENAISSANCE);
        assertThat(testAcidoform.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testAcidoform.getAgeactuelans()).isEqualTo(DEFAULT_AGEACTUELANS);
        assertThat(testAcidoform.getAgeactuelmois()).isEqualTo(DEFAULT_AGEACTUELMOIS);
        assertThat(testAcidoform.getAgeactueljours()).isEqualTo(DEFAULT_AGEACTUELJOURS);
        assertThat(testAcidoform.getDatedeces()).isEqualTo(DEFAULT_DATEDECES);
        assertThat(testAcidoform.getAgedecesan()).isEqualTo(DEFAULT_AGEDECESAN);
        assertThat(testAcidoform.getAgedecesmois()).isEqualTo(DEFAULT_AGEDECESMOIS);
        assertThat(testAcidoform.getAgedecesjours()).isEqualTo(DEFAULT_AGEDECESJOURS);
    }

    @Test
    void createAcidoformWithExistingId() throws Exception {
        // Create the Acidoform with an existing ID
        acidoform.setId("existing_id");
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(acidoform);

        int databaseSizeBeforeCreate = acidoformRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcidoformMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acidoformDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllAcidoforms() throws Exception {
        // Initialize the database
        acidoformRepository.save(acidoform);

        // Get all the acidoformList
        restAcidoformMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acidoform.getId())))
            .andExpect(jsonPath("$.[*].homocystinurie").value(hasItem(DEFAULT_HOMOCYSTINURIE.booleanValue())))
            .andExpect(jsonPath("$.[*].leucinose").value(hasItem(DEFAULT_LEUCINOSE.booleanValue())))
            .andExpect(jsonPath("$.[*].phenylcetonurie").value(hasItem(DEFAULT_PHENYLCETONURIE.booleanValue())))
            .andExpect(jsonPath("$.[*].tyrosinemie").value(hasItem(DEFAULT_TYROSINEMIE.booleanValue())))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(DEFAULT_UPDATEDATE.toString())))
            .andExpect(jsonPath("$.[*].typeobservation").value(hasItem(DEFAULT_TYPEOBSERVATION.toString())))
            .andExpect(jsonPath("$.[*].createddate").value(hasItem(DEFAULT_CREATEDDATE.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].datenaissance").value(hasItem(DEFAULT_DATENAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].ageactuelans").value(hasItem(DEFAULT_AGEACTUELANS)))
            .andExpect(jsonPath("$.[*].ageactuelmois").value(hasItem(DEFAULT_AGEACTUELMOIS)))
            .andExpect(jsonPath("$.[*].ageactueljours").value(hasItem(DEFAULT_AGEACTUELJOURS)))
            .andExpect(jsonPath("$.[*].datedeces").value(hasItem(DEFAULT_DATEDECES.toString())))
            .andExpect(jsonPath("$.[*].agedecesan").value(hasItem(DEFAULT_AGEDECESAN)))
            .andExpect(jsonPath("$.[*].agedecesmois").value(hasItem(DEFAULT_AGEDECESMOIS)))
            .andExpect(jsonPath("$.[*].agedecesjours").value(hasItem(DEFAULT_AGEDECESJOURS)));
    }

    @Test
    void getAcidoform() throws Exception {
        // Initialize the database
        acidoformRepository.save(acidoform);

        // Get the acidoform
        restAcidoformMockMvc
            .perform(get(ENTITY_API_URL_ID, acidoform.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(acidoform.getId()))
            .andExpect(jsonPath("$.homocystinurie").value(DEFAULT_HOMOCYSTINURIE.booleanValue()))
            .andExpect(jsonPath("$.leucinose").value(DEFAULT_LEUCINOSE.booleanValue()))
            .andExpect(jsonPath("$.phenylcetonurie").value(DEFAULT_PHENYLCETONURIE.booleanValue()))
            .andExpect(jsonPath("$.tyrosinemie").value(DEFAULT_TYROSINEMIE.booleanValue()))
            .andExpect(jsonPath("$.updatedate").value(DEFAULT_UPDATEDATE.toString()))
            .andExpect(jsonPath("$.typeobservation").value(DEFAULT_TYPEOBSERVATION.toString()))
            .andExpect(jsonPath("$.createddate").value(DEFAULT_CREATEDDATE.toString()))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.datenaissance").value(DEFAULT_DATENAISSANCE.toString()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.ageactuelans").value(DEFAULT_AGEACTUELANS))
            .andExpect(jsonPath("$.ageactuelmois").value(DEFAULT_AGEACTUELMOIS))
            .andExpect(jsonPath("$.ageactueljours").value(DEFAULT_AGEACTUELJOURS))
            .andExpect(jsonPath("$.datedeces").value(DEFAULT_DATEDECES.toString()))
            .andExpect(jsonPath("$.agedecesan").value(DEFAULT_AGEDECESAN))
            .andExpect(jsonPath("$.agedecesmois").value(DEFAULT_AGEDECESMOIS))
            .andExpect(jsonPath("$.agedecesjours").value(DEFAULT_AGEDECESJOURS));
    }

    @Test
    void getNonExistingAcidoform() throws Exception {
        // Get the acidoform
        restAcidoformMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAcidoform() throws Exception {
        // Initialize the database
        acidoformRepository.save(acidoform);

        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();

        // Update the acidoform
        Acidoform updatedAcidoform = acidoformRepository.findById(acidoform.getId()).get();
        updatedAcidoform
            .homocystinurie(UPDATED_HOMOCYSTINURIE)
            .leucinose(UPDATED_LEUCINOSE)
            .phenylcetonurie(UPDATED_PHENYLCETONURIE)
            .tyrosinemie(UPDATED_TYROSINEMIE)
            .updatedate(UPDATED_UPDATEDATE)
            .typeobservation(UPDATED_TYPEOBSERVATION)
            .createddate(UPDATED_CREATEDDATE)
            .sexe(UPDATED_SEXE)
            .datenaissance(UPDATED_DATENAISSANCE)
            .statut(UPDATED_STATUT)
            .ageactuelans(UPDATED_AGEACTUELANS)
            .ageactuelmois(UPDATED_AGEACTUELMOIS)
            .ageactueljours(UPDATED_AGEACTUELJOURS)
            .datedeces(UPDATED_DATEDECES)
            .agedecesan(UPDATED_AGEDECESAN)
            .agedecesmois(UPDATED_AGEDECESMOIS)
            .agedecesjours(UPDATED_AGEDECESJOURS);
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(updatedAcidoform);

        restAcidoformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, acidoformDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(acidoformDTO))
            )
            .andExpect(status().isOk());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
        Acidoform testAcidoform = acidoformList.get(acidoformList.size() - 1);
        assertThat(testAcidoform.getHomocystinurie()).isEqualTo(UPDATED_HOMOCYSTINURIE);
        assertThat(testAcidoform.getLeucinose()).isEqualTo(UPDATED_LEUCINOSE);
        assertThat(testAcidoform.getPhenylcetonurie()).isEqualTo(UPDATED_PHENYLCETONURIE);
        assertThat(testAcidoform.getTyrosinemie()).isEqualTo(UPDATED_TYROSINEMIE);
        assertThat(testAcidoform.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
        assertThat(testAcidoform.getTypeobservation()).isEqualTo(UPDATED_TYPEOBSERVATION);
        assertThat(testAcidoform.getCreateddate()).isEqualTo(UPDATED_CREATEDDATE);
        assertThat(testAcidoform.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testAcidoform.getDatenaissance()).isEqualTo(UPDATED_DATENAISSANCE);
        assertThat(testAcidoform.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testAcidoform.getAgeactuelans()).isEqualTo(UPDATED_AGEACTUELANS);
        assertThat(testAcidoform.getAgeactuelmois()).isEqualTo(UPDATED_AGEACTUELMOIS);
        assertThat(testAcidoform.getAgeactueljours()).isEqualTo(UPDATED_AGEACTUELJOURS);
        assertThat(testAcidoform.getDatedeces()).isEqualTo(UPDATED_DATEDECES);
        assertThat(testAcidoform.getAgedecesan()).isEqualTo(UPDATED_AGEDECESAN);
        assertThat(testAcidoform.getAgedecesmois()).isEqualTo(UPDATED_AGEDECESMOIS);
        assertThat(testAcidoform.getAgedecesjours()).isEqualTo(UPDATED_AGEDECESJOURS);
    }

    @Test
    void putNonExistingAcidoform() throws Exception {
        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();
        acidoform.setId(UUID.randomUUID().toString());

        // Create the Acidoform
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(acidoform);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcidoformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, acidoformDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(acidoformDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAcidoform() throws Exception {
        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();
        acidoform.setId(UUID.randomUUID().toString());

        // Create the Acidoform
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(acidoform);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAcidoformMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(acidoformDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAcidoform() throws Exception {
        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();
        acidoform.setId(UUID.randomUUID().toString());

        // Create the Acidoform
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(acidoform);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAcidoformMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acidoformDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAcidoformWithPatch() throws Exception {
        // Initialize the database
        acidoformRepository.save(acidoform);

        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();

        // Update the acidoform using partial update
        Acidoform partialUpdatedAcidoform = new Acidoform();
        partialUpdatedAcidoform.setId(acidoform.getId());

        partialUpdatedAcidoform
            .homocystinurie(UPDATED_HOMOCYSTINURIE)
            .leucinose(UPDATED_LEUCINOSE)
            .tyrosinemie(UPDATED_TYROSINEMIE)
            .createddate(UPDATED_CREATEDDATE)
            .datenaissance(UPDATED_DATENAISSANCE)
            .statut(UPDATED_STATUT)
            .ageactuelmois(UPDATED_AGEACTUELMOIS)
            .datedeces(UPDATED_DATEDECES)
            .agedecesan(UPDATED_AGEDECESAN)
            .agedecesmois(UPDATED_AGEDECESMOIS)
            .agedecesjours(UPDATED_AGEDECESJOURS);

        restAcidoformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAcidoform.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAcidoform))
            )
            .andExpect(status().isOk());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
        Acidoform testAcidoform = acidoformList.get(acidoformList.size() - 1);
        assertThat(testAcidoform.getHomocystinurie()).isEqualTo(UPDATED_HOMOCYSTINURIE);
        assertThat(testAcidoform.getLeucinose()).isEqualTo(UPDATED_LEUCINOSE);
        assertThat(testAcidoform.getPhenylcetonurie()).isEqualTo(DEFAULT_PHENYLCETONURIE);
        assertThat(testAcidoform.getTyrosinemie()).isEqualTo(UPDATED_TYROSINEMIE);
        assertThat(testAcidoform.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
        assertThat(testAcidoform.getTypeobservation()).isEqualTo(DEFAULT_TYPEOBSERVATION);
        assertThat(testAcidoform.getCreateddate()).isEqualTo(UPDATED_CREATEDDATE);
        assertThat(testAcidoform.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testAcidoform.getDatenaissance()).isEqualTo(UPDATED_DATENAISSANCE);
        assertThat(testAcidoform.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testAcidoform.getAgeactuelans()).isEqualTo(DEFAULT_AGEACTUELANS);
        assertThat(testAcidoform.getAgeactuelmois()).isEqualTo(UPDATED_AGEACTUELMOIS);
        assertThat(testAcidoform.getAgeactueljours()).isEqualTo(DEFAULT_AGEACTUELJOURS);
        assertThat(testAcidoform.getDatedeces()).isEqualTo(UPDATED_DATEDECES);
        assertThat(testAcidoform.getAgedecesan()).isEqualTo(UPDATED_AGEDECESAN);
        assertThat(testAcidoform.getAgedecesmois()).isEqualTo(UPDATED_AGEDECESMOIS);
        assertThat(testAcidoform.getAgedecesjours()).isEqualTo(UPDATED_AGEDECESJOURS);
    }

    @Test
    void fullUpdateAcidoformWithPatch() throws Exception {
        // Initialize the database
        acidoformRepository.save(acidoform);

        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();

        // Update the acidoform using partial update
        Acidoform partialUpdatedAcidoform = new Acidoform();
        partialUpdatedAcidoform.setId(acidoform.getId());

        partialUpdatedAcidoform
            .homocystinurie(UPDATED_HOMOCYSTINURIE)
            .leucinose(UPDATED_LEUCINOSE)
            .phenylcetonurie(UPDATED_PHENYLCETONURIE)
            .tyrosinemie(UPDATED_TYROSINEMIE)
            .updatedate(UPDATED_UPDATEDATE)
            .typeobservation(UPDATED_TYPEOBSERVATION)
            .createddate(UPDATED_CREATEDDATE)
            .sexe(UPDATED_SEXE)
            .datenaissance(UPDATED_DATENAISSANCE)
            .statut(UPDATED_STATUT)
            .ageactuelans(UPDATED_AGEACTUELANS)
            .ageactuelmois(UPDATED_AGEACTUELMOIS)
            .ageactueljours(UPDATED_AGEACTUELJOURS)
            .datedeces(UPDATED_DATEDECES)
            .agedecesan(UPDATED_AGEDECESAN)
            .agedecesmois(UPDATED_AGEDECESMOIS)
            .agedecesjours(UPDATED_AGEDECESJOURS);

        restAcidoformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAcidoform.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAcidoform))
            )
            .andExpect(status().isOk());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
        Acidoform testAcidoform = acidoformList.get(acidoformList.size() - 1);
        assertThat(testAcidoform.getHomocystinurie()).isEqualTo(UPDATED_HOMOCYSTINURIE);
        assertThat(testAcidoform.getLeucinose()).isEqualTo(UPDATED_LEUCINOSE);
        assertThat(testAcidoform.getPhenylcetonurie()).isEqualTo(UPDATED_PHENYLCETONURIE);
        assertThat(testAcidoform.getTyrosinemie()).isEqualTo(UPDATED_TYROSINEMIE);
        assertThat(testAcidoform.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
        assertThat(testAcidoform.getTypeobservation()).isEqualTo(UPDATED_TYPEOBSERVATION);
        assertThat(testAcidoform.getCreateddate()).isEqualTo(UPDATED_CREATEDDATE);
        assertThat(testAcidoform.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testAcidoform.getDatenaissance()).isEqualTo(UPDATED_DATENAISSANCE);
        assertThat(testAcidoform.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testAcidoform.getAgeactuelans()).isEqualTo(UPDATED_AGEACTUELANS);
        assertThat(testAcidoform.getAgeactuelmois()).isEqualTo(UPDATED_AGEACTUELMOIS);
        assertThat(testAcidoform.getAgeactueljours()).isEqualTo(UPDATED_AGEACTUELJOURS);
        assertThat(testAcidoform.getDatedeces()).isEqualTo(UPDATED_DATEDECES);
        assertThat(testAcidoform.getAgedecesan()).isEqualTo(UPDATED_AGEDECESAN);
        assertThat(testAcidoform.getAgedecesmois()).isEqualTo(UPDATED_AGEDECESMOIS);
        assertThat(testAcidoform.getAgedecesjours()).isEqualTo(UPDATED_AGEDECESJOURS);
    }

    @Test
    void patchNonExistingAcidoform() throws Exception {
        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();
        acidoform.setId(UUID.randomUUID().toString());

        // Create the Acidoform
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(acidoform);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcidoformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, acidoformDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(acidoformDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAcidoform() throws Exception {
        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();
        acidoform.setId(UUID.randomUUID().toString());

        // Create the Acidoform
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(acidoform);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAcidoformMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(acidoformDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAcidoform() throws Exception {
        int databaseSizeBeforeUpdate = acidoformRepository.findAll().size();
        acidoform.setId(UUID.randomUUID().toString());

        // Create the Acidoform
        AcidoformDTO acidoformDTO = acidoformMapper.toDto(acidoform);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAcidoformMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(acidoformDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Acidoform in the database
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAcidoform() throws Exception {
        // Initialize the database
        acidoformRepository.save(acidoform);

        int databaseSizeBeforeDelete = acidoformRepository.findAll().size();

        // Delete the acidoform
        restAcidoformMockMvc
            .perform(delete(ENTITY_API_URL_ID, acidoform.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Acidoform> acidoformList = acidoformRepository.findAll();
        assertThat(acidoformList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
