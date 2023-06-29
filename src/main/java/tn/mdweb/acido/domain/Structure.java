package tn.mdweb.acido.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tn.mdweb.acido.domain.enumeration.estructure;

/**
 * A Structure.
 */
@Document(collection = "structure")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Structure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("nom")
    private String nom;

    @Field("typestructure")
    private estructure typestructure;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Structure id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Structure nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public estructure getTypestructure() {
        return this.typestructure;
    }

    public Structure typestructure(estructure typestructure) {
        this.setTypestructure(typestructure);
        return this;
    }

    public void setTypestructure(estructure typestructure) {
        this.typestructure = typestructure;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Structure)) {
            return false;
        }
        return id != null && id.equals(((Structure) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Structure{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", typestructure='" + getTypestructure() + "'" +
            "}";
    }
}
