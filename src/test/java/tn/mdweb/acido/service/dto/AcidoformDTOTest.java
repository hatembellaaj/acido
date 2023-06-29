package tn.mdweb.acido.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.acido.web.rest.TestUtil;

class AcidoformDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcidoformDTO.class);
        AcidoformDTO acidoformDTO1 = new AcidoformDTO();
        acidoformDTO1.setId("id1");
        AcidoformDTO acidoformDTO2 = new AcidoformDTO();
        assertThat(acidoformDTO1).isNotEqualTo(acidoformDTO2);
        acidoformDTO2.setId(acidoformDTO1.getId());
        assertThat(acidoformDTO1).isEqualTo(acidoformDTO2);
        acidoformDTO2.setId("id2");
        assertThat(acidoformDTO1).isNotEqualTo(acidoformDTO2);
        acidoformDTO1.setId(null);
        assertThat(acidoformDTO1).isNotEqualTo(acidoformDTO2);
    }
}
