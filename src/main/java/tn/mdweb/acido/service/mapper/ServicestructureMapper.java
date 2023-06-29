package tn.mdweb.acido.service.mapper;

import org.mapstruct.*;
import tn.mdweb.acido.domain.Servicestructure;
import tn.mdweb.acido.domain.Structure;
import tn.mdweb.acido.service.dto.ServicestructureDTO;
import tn.mdweb.acido.service.dto.StructureDTO;

/**
 * Mapper for the entity {@link Servicestructure} and its DTO {@link ServicestructureDTO}.
 */
@Mapper(componentModel = "spring")
public interface ServicestructureMapper extends EntityMapper<ServicestructureDTO, Servicestructure> {
    @Mapping(target = "structure", source = "structure", qualifiedByName = "structureId")
    ServicestructureDTO toDto(Servicestructure s);

    @Named("structureId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StructureDTO toDtoStructureId(Structure structure);
}
