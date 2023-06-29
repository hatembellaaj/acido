package tn.mdweb.acido.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import tn.mdweb.acido.domain.enumeration.especialite;

/**
 * A DTO for the {@link tn.mdweb.acido.domain.Medecin} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MedecinDTO implements Serializable {

    private String id;

    @NotNull
    private String nom;

    private String prenom;

    private especialite specialite;

    private String autrespecialite;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public especialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(especialite specialite) {
        this.specialite = specialite;
    }

    public String getAutrespecialite() {
        return autrespecialite;
    }

    public void setAutrespecialite(String autrespecialite) {
        this.autrespecialite = autrespecialite;
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
        if (!(o instanceof MedecinDTO)) {
            return false;
        }

        MedecinDTO medecinDTO = (MedecinDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, medecinDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedecinDTO{" +
            "id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", specialite='" + getSpecialite() + "'" +
            ", autrespecialite='" + getAutrespecialite() + "'" +
            ", structure=" + getStructure() +
            "}";
    }
}
