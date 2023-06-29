package tn.mdweb.acido.service.mapper;

import org.mapstruct.*;
import tn.mdweb.acido.domain.Acidoform;
import tn.mdweb.acido.service.dto.AcidoformDTO;

/**
 * Mapper for the entity {@link Acidoform} and its DTO {@link AcidoformDTO}.
 */
@Mapper(componentModel = "spring")
public interface AcidoformMapper extends EntityMapper<AcidoformDTO, Acidoform> {}
