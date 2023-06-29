package tn.mdweb.acido.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.mdweb.acido.domain.Servicestructure} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServicestructureDTO implements Serializable {

    private String id;

    @NotNull
    private String nom;

    private StructureDTO structure;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public StructureDTO getStructure() {
        return structure;
    }

    public void setStructure(StructureDTO structure) {
        this.structure = structure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServicestructureDTO)) {
            return false;
        }

        ServicestructureDTO servicestructureDTO = (ServicestructureDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, servicestructureDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServicestructureDTO{" +
            "id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", structure=" + getStructure() +
            "}";
    }
}
