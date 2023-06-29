package tn.mdweb.acido.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AcidoformMapperTest {

    private AcidoformMapper acidoformMapper;

    @BeforeEach
    public void setUp() {
        acidoformMapper = new AcidoformMapperImpl();
    }
}
