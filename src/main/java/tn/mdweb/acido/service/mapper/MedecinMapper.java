package tn.mdweb.acido.service.mapper;

import org.mapstruct.*;
import tn.mdweb.acido.domain.Medecin;
import tn.mdweb.acido.domain.Structure;
import tn.mdweb.acido.service.dto.MedecinDTO;
import tn.mdweb.acido.service.dto.StructureDTO;

/**
 * Mapper for the entity {@link Medecin} and its DTO {@link MedecinDTO}.
 */
@Mapper(componentModel = "spring")
public interface MedecinMapper extends EntityMapper<MedecinDTO, Medecin> {
    @Mapping(target = "structure", source = "structure", qualifiedByName = "structureId")
    MedecinDTO toDto(Medecin s);

    @Named("structureId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StructureDTO toDtoStructureId(Structure structure);
}
