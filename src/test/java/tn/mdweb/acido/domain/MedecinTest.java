package tn.mdweb.acido.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.acido.web.rest.TestUtil;

class MedecinTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Medecin.class);
        Medecin medecin1 = new Medecin();
        medecin1.setId("id1");
        Medecin medecin2 = new Medecin();
        medecin2.setId(medecin1.getId());
        assertThat(medecin1).isEqualTo(medecin2);
        medecin2.setId("id2");
        assertThat(medecin1).isNotEqualTo(medecin2);
        medecin1.setId(null);
        assertThat(medecin1).isNotEqualTo(medecin2);
    }
}
