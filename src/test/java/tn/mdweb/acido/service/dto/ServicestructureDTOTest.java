package tn.mdweb.acido.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.acido.web.rest.TestUtil;

class ServicestructureDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServicestructureDTO.class);
        ServicestructureDTO servicestructureDTO1 = new ServicestructureDTO();
        servicestructureDTO1.setId("id1");
        ServicestructureDTO servicestructureDTO2 = new ServicestructureDTO();
        assertThat(servicestructureDTO1).isNotEqualTo(servicestructureDTO2);
        servicestructureDTO2.setId(servicestructureDTO1.getId());
        assertThat(servicestructureDTO1).isEqualTo(servicestructureDTO2);
        servicestructureDTO2.setId("id2");
        assertThat(servicestructureDTO1).isNotEqualTo(servicestructureDTO2);
        servicestructureDTO1.setId(null);
        assertThat(servicestructureDTO1).isNotEqualTo(servicestructureDTO2);
    }
}
