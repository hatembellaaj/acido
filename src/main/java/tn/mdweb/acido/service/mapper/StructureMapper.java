package tn.mdweb.acido.service.mapper;

import org.mapstruct.*;
import tn.mdweb.acido.domain.Structure;
import tn.mdweb.acido.service.dto.StructureDTO;

/**
 * Mapper for the entity {@link Structure} and its DTO {@link StructureDTO}.
 */
@Mapper(componentModel = "spring")
public interface StructureMapper extends EntityMapper<StructureDTO, Structure> {}
