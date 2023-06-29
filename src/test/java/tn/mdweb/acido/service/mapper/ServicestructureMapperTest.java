package tn.mdweb.acido.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicestructureMapperTest {

    private ServicestructureMapper servicestructureMapper;

    @BeforeEach
    public void setUp() {
        servicestructureMapper = new ServicestructureMapperImpl();
    }
}
