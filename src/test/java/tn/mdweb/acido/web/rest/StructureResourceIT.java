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
import tn.mdweb.acido.domain.Structure;
import tn.mdweb.acido.domain.enumeration.estructure;
import tn.mdweb.acido.repository.StructureRepository;
import tn.mdweb.acido.service.dto.StructureDTO;
import tn.mdweb.acido.service.mapper.StructureMapper;

/**
 * Integration tests for the {@link StructureResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StructureResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final estructure DEFAULT_TYPESTRUCTURE = estructure.CABINET_MEDICAL;
    private static final estructure UPDATED_TYPESTRUCTURE = estructure.CLINIQUE_PRIVEE;

    private static final String ENTITY_API_URL = "/api/structures";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private StructureRepository structureRepository;

    @Autowired
    private StructureMapper structureMapper;

    @Autowired
    private MockMvc restStructureMockMvc;

    private Structure structure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Structure createEntity() {
        Structure structure = new Structure().nom(DEFAULT_NOM).typestructure(DEFAULT_TYPESTRUCTURE);
        return structure;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Structure createUpdatedEntity() {
        Structure structure = new Structure().nom(UPDATED_NOM).typestructure(UPDATED_TYPESTRUCTURE);
        return structure;
    }

    @BeforeEach
    public void initTest() {
        structureRepository.deleteAll();
        structure = createEntity();
    }

    @Test
    void createStructure() throws Exception {
        int databaseSizeBeforeCreate = structureRepository.findAll().size();
        // Create the Structure
        StructureDTO structureDTO = structureMapper.toDto(structure);
        restStructureMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureDTO)))
            .andExpect(status().isCreated());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeCreate + 1);
        Structure testStructure = structureList.get(structureList.size() - 1);
        assertThat(testStructure.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testStructure.getTypestructure()).isEqualTo(DEFAULT_TYPESTRUCTURE);
    }

    @Test
    void createStructureWithExistingId() throws Exception {
        // Create the Structure with an existing ID
        structure.setId("existing_id");
        StructureDTO structureDTO = structureMapper.toDto(structure);

        int databaseSizeBeforeCreate = structureRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStructureMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = structureRepository.findAll().size();
        // set the field null
        structure.setNom(null);

        // Create the Structure, which fails.
        StructureDTO structureDTO = structureMapper.toDto(structure);

        restStructureMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureDTO)))
            .andExpect(status().isBadRequest());

        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllStructures() throws Exception {
        // Initialize the database
        structureRepository.save(structure);

        // Get all the structureList
        restStructureMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(structure.getId())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].typestructure").value(hasItem(DEFAULT_TYPESTRUCTURE.toString())));
    }

    @Test
    void getStructure() throws Exception {
        // Initialize the database
        structureRepository.save(structure);

        // Get the structure
        restStructureMockMvc
            .perform(get(ENTITY_API_URL_ID, structure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(structure.getId()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.typestructure").value(DEFAULT_TYPESTRUCTURE.toString()));
    }

    @Test
    void getNonExistingStructure() throws Exception {
        // Get the structure
        restStructureMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingStructure() throws Exception {
        // Initialize the database
        structureRepository.save(structure);

        int databaseSizeBeforeUpdate = structureRepository.findAll().size();

        // Update the structure
        Structure updatedStructure = structureRepository.findById(structure.getId()).get();
        updatedStructure.nom(UPDATED_NOM).typestructure(UPDATED_TYPESTRUCTURE);
        StructureDTO structureDTO = structureMapper.toDto(updatedStructure);

        restStructureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, structureDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structureDTO))
            )
            .andExpect(status().isOk());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
        Structure testStructure = structureList.get(structureList.size() - 1);
        assertThat(testStructure.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testStructure.getTypestructure()).isEqualTo(UPDATED_TYPESTRUCTURE);
    }

    @Test
    void putNonExistingStructure() throws Exception {
        int databaseSizeBeforeUpdate = structureRepository.findAll().size();
        structure.setId(UUID.randomUUID().toString());

        // Create the Structure
        StructureDTO structureDTO = structureMapper.toDto(structure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStructureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, structureDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchStructure() throws Exception {
        int databaseSizeBeforeUpdate = structureRepository.findAll().size();
        structure.setId(UUID.randomUUID().toString());

        // Create the Structure
        StructureDTO structureDTO = structureMapper.toDto(structure);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamStructure() throws Exception {
        int databaseSizeBeforeUpdate = structureRepository.findAll().size();
        structure.setId(UUID.randomUUID().toString());

        // Create the Structure
        StructureDTO structureDTO = structureMapper.toDto(structure);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructureMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateStructureWithPatch() throws Exception {
        // Initialize the database
        structureRepository.save(structure);

        int databaseSizeBeforeUpdate = structureRepository.findAll().size();

        // Update the structure using partial update
        Structure partialUpdatedStructure = new Structure();
        partialUpdatedStructure.setId(structure.getId());

        partialUpdatedStructure.typestructure(UPDATED_TYPESTRUCTURE);

        restStructureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStructure.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStructure))
            )
            .andExpect(status().isOk());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
        Structure testStructure = structureList.get(structureList.size() - 1);
        assertThat(testStructure.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testStructure.getTypestructure()).isEqualTo(UPDATED_TYPESTRUCTURE);
    }

    @Test
    void fullUpdateStructureWithPatch() throws Exception {
        // Initialize the database
        structureRepository.save(structure);

        int databaseSizeBeforeUpdate = structureRepository.findAll().size();

        // Update the structure using partial update
        Structure partialUpdatedStructure = new Structure();
        partialUpdatedStructure.setId(structure.getId());

        partialUpdatedStructure.nom(UPDATED_NOM).typestructure(UPDATED_TYPESTRUCTURE);

        restStructureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStructure.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStructure))
            )
            .andExpect(status().isOk());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
        Structure testStructure = structureList.get(structureList.size() - 1);
        assertThat(testStructure.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testStructure.getTypestructure()).isEqualTo(UPDATED_TYPESTRUCTURE);
    }

    @Test
    void patchNonExistingStructure() throws Exception {
        int databaseSizeBeforeUpdate = structureRepository.findAll().size();
        structure.setId(UUID.randomUUID().toString());

        // Create the Structure
        StructureDTO structureDTO = structureMapper.toDto(structure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStructureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, structureDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(structureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchStructure() throws Exception {
        int databaseSizeBeforeUpdate = structureRepository.findAll().size();
        structure.setId(UUID.randomUUID().toString());

        // Create the Structure
        StructureDTO structureDTO = structureMapper.toDto(structure);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(structureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamStructure() throws Exception {
        int databaseSizeBeforeUpdate = structureRepository.findAll().size();
        structure.setId(UUID.randomUUID().toString());

        // Create the Structure
        StructureDTO structureDTO = structureMapper.toDto(structure);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructureMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(structureDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Structure in the database
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteStructure() throws Exception {
        // Initialize the database
        structureRepository.save(structure);

        int databaseSizeBeforeDelete = structureRepository.findAll().size();

        // Delete the structure
        restStructureMockMvc
            .perform(delete(ENTITY_API_URL_ID, structure.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Structure> structureList = structureRepository.findAll();
        assertThat(structureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
