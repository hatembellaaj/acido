package tn.mdweb.acido.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tn.mdweb.acido.domain.enumeration.especialite;

/**
 * A Medecin.
 */
@Document(collection = "medecin")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("nom")
    private String nom;

    @Field("prenom")
    private String prenom;

    @Field("specialite")
    private especialite specialite;

    @Field("autrespecialite")
    private String autrespecialite;

    @DBRef
    @Field("structure")
    private Structure structure;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Medecin id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Medecin nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Medecin prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public especialite getSpecialite() {
        return this.specialite;
    }

    public Medecin specialite(especialite specialite) {
        this.setSpecialite(specialite);
        return this;
    }

    public void setSpecialite(especialite specialite) {
        this.specialite = specialite;
    }

    public String getAutrespecialite() {
        return this.autrespecialite;
    }

    public Medecin autrespecialite(String autrespecialite) {
        this.setAutrespecialite(autrespecialite);
        return this;
    }

    public void setAutrespecialite(String autrespecialite) {
        this.autrespecialite = autrespecialite;
    }

    public Structure getStructure() {
        return this.structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Medecin structure(Structure structure) {
        this.setStructure(structure);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Medecin)) {
            return false;
        }
        return id != null && id.equals(((Medecin) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Medecin{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", specialite='" + getSpecialite() + "'" +
            ", autrespecialite='" + getAutrespecialite() + "'" +
            "}";
    }
}
