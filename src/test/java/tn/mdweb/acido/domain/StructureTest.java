package tn.mdweb.acido.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.mdweb.acido.web.rest.TestUtil;

class StructureTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Structure.class);
        Structure structure1 = new Structure();
        structure1.setId("id1");
        Structure structure2 = new Structure();
        structure2.setId(structure1.getId());
        assertThat(structure1).isEqualTo(structure2);
        structure2.setId("id2");
        assertThat(structure1).isNotEqualTo(structure2);
        structure1.setId(null);
        assertThat(structure1).isNotEqualTo(structure2);
    }
}
