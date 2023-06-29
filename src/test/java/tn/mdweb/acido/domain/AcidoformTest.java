package tn.mdweb.acido.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.acido.web.rest.TestUtil;

class AcidoformTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Acidoform.class);
        Acidoform acidoform1 = new Acidoform();
        acidoform1.setId("id1");
        Acidoform acidoform2 = new Acidoform();
        acidoform2.setId(acidoform1.getId());
        assertThat(acidoform1).isEqualTo(acidoform2);
        acidoform2.setId("id2");
        assertThat(acidoform1).isNotEqualTo(acidoform2);
        acidoform1.setId(null);
        assertThat(acidoform1).isNotEqualTo(acidoform2);
    }
}
