package tn.mdweb.acido.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import tn.mdweb.acido.domain.enumeration.estructure;

/**
 * A DTO for the {@link tn.mdweb.acido.domain.Structure} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StructureDTO implements Serializable {

    private String id;

    @NotNull
    private String nom;

    private estructure typestructure;

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

    public estructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(estructure typestructure) {
        this.typestructure = typestructure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StructureDTO)) {
            return false;
        }

        StructureDTO structureDTO = (StructureDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, structureDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StructureDTO{" +
            "id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", typestructure='" + getTypestructure() + "'" +
            "}";
    }
}
