package tn.mdweb.acido.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.acido.web.rest.TestUtil;

class ServicestructureTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Servicestructure.class);
        Servicestructure servicestructure1 = new Servicestructure();
        servicestructure1.setId("id1");
        Servicestructure servicestructure2 = new Servicestructure();
        servicestructure2.setId(servicestructure1.getId());
        assertThat(servicestructure1).isEqualTo(servicestructure2);
        servicestructure2.setId("id2");
        assertThat(servicestructure1).isNotEqualTo(servicestructure2);
        servicestructure1.setId(null);
        assertThat(servicestructure1).isNotEqualTo(servicestructure2);
    }
}
