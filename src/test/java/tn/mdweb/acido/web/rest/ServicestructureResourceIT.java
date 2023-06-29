package tn.mdweb.acido.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import tn.mdweb.acido.domain.Servicestructure;
import tn.mdweb.acido.repository.ServicestructureRepository;
import tn.mdweb.acido.service.dto.ServicestructureDTO;
import tn.mdweb.acido.service.mapper.ServicestructureMapper;

/**
 * Integration tests for the {@link ServicestructureResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServicestructureResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/servicestructures";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ServicestructureRepository servicestructureRepository;

    @Autowired
    private ServicestructureMapper servicestructureMapper;

    @Autowired
    private MockMvc restServicestructureMockMvc;

    private Servicestructure servicestructure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicestructure createEntity() {
        Servicestructure servicestructure = new Servicestructure().nom(DEFAULT_NOM);
        return servicestructure;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicestructure createUpdatedEntity() {
        Servicestructure servicestructure = new Servicestructure().nom(UPDATED_NOM);
        return servicestructure;
    }

    @BeforeEach
    public void initTest() {
        servicestructureRepository.deleteAll();
        servicestructure = createEntity();
    }

    @Test
    void createServicestructure() throws Exception {
        int databaseSizeBeforeCreate = servicestructureRepository.findAll().size();
        // Create the Servicestructure
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);
        restServicestructureMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeCreate + 1);
        Servicestructure testServicestructure = servicestructureList.get(servicestructureList.size() - 1);
        assertThat(testServicestructure.getNom()).isEqualTo(DEFAULT_NOM);
    }

    @Test
    void createServicestructureWithExistingId() throws Exception {
        // Create the Servicestructure with an existing ID
        servicestructure.setId("existing_id");
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);

        int databaseSizeBeforeCreate = servicestructureRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServicestructureMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = servicestructureRepository.findAll().size();
        // set the field null
        servicestructure.setNom(null);

        // Create the Servicestructure, which fails.
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);

        restServicestructureMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isBadRequest());

        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllServicestructures() throws Exception {
        // Initialize the database
        servicestructureRepository.save(servicestructure);

        // Get all the servicestructureList
        restServicestructureMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(servicestructure.getId())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)));
    }

    @Test
    void getServicestructure() throws Exception {
        // Initialize the database
        servicestructureRepository.save(servicestructure);

        // Get the servicestructure
        restServicestructureMockMvc
            .perform(get(ENTITY_API_URL_ID, servicestructure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(servicestructure.getId()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM));
    }

    @Test
    void getNonExistingServicestructure() throws Exception {
        // Get the servicestructure
        restServicestructureMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingServicestructure() throws Exception {
        // Initialize the database
        servicestructureRepository.save(servicestructure);

        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();

        // Update the servicestructure
        Servicestructure updatedServicestructure = servicestructureRepository.findById(servicestructure.getId()).get();
        updatedServicestructure.nom(UPDATED_NOM);
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(updatedServicestructure);

        restServicestructureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, servicestructureDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isOk());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
        Servicestructure testServicestructure = servicestructureList.get(servicestructureList.size() - 1);
        assertThat(testServicestructure.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    void putNonExistingServicestructure() throws Exception {
        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();
        servicestructure.setId(UUID.randomUUID().toString());

        // Create the Servicestructure
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicestructureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, servicestructureDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchServicestructure() throws Exception {
        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();
        servicestructure.setId(UUID.randomUUID().toString());

        // Create the Servicestructure
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicestructureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamServicestructure() throws Exception {
        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();
        servicestructure.setId(UUID.randomUUID().toString());

        // Create the Servicestructure
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicestructureMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateServicestructureWithPatch() throws Exception {
        // Initialize the database
        servicestructureRepository.save(servicestructure);

        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();

        // Update the servicestructure using partial update
        Servicestructure partialUpdatedServicestructure = new Servicestructure();
        partialUpdatedServicestructure.setId(servicestructure.getId());

        partialUpdatedServicestructure.nom(UPDATED_NOM);

        restServicestructureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServicestructure.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServicestructure))
            )
            .andExpect(status().isOk());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
        Servicestructure testServicestructure = servicestructureList.get(servicestructureList.size() - 1);
        assertThat(testServicestructure.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    void fullUpdateServicestructureWithPatch() throws Exception {
        // Initialize the database
        servicestructureRepository.save(servicestructure);

        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();

        // Update the servicestructure using partial update
        Servicestructure partialUpdatedServicestructure = new Servicestructure();
        partialUpdatedServicestructure.setId(servicestructure.getId());

        partialUpdatedServicestructure.nom(UPDATED_NOM);

        restServicestructureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServicestructure.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServicestructure))
            )
            .andExpect(status().isOk());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
        Servicestructure testServicestructure = servicestructureList.get(servicestructureList.size() - 1);
        assertThat(testServicestructure.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    void patchNonExistingServicestructure() throws Exception {
        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();
        servicestructure.setId(UUID.randomUUID().toString());

        // Create the Servicestructure
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicestructureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, servicestructureDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchServicestructure() throws Exception {
        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();
        servicestructure.setId(UUID.randomUUID().toString());

        // Create the Servicestructure
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicestructureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamServicestructure() throws Exception {
        int databaseSizeBeforeUpdate = servicestructureRepository.findAll().size();
        servicestructure.setId(UUID.randomUUID().toString());

        // Create the Servicestructure
        ServicestructureDTO servicestructureDTO = servicestructureMapper.toDto(servicestructure);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicestructureMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(servicestructureDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Servicestructure in the database
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteServicestructure() throws Exception {
        // Initialize the database
        servicestructureRepository.save(servicestructure);

        int databaseSizeBeforeDelete = servicestructureRepository.findAll().size();

        // Delete the servicestructure
        restServicestructureMockMvc
            .perform(delete(ENTITY_API_URL_ID, servicestructure.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Servicestructure> servicestructureList = servicestructureRepository.findAll();
        assertThat(servicestructureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
