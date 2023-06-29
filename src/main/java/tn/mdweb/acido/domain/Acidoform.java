package tn.mdweb.acido.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tn.mdweb.acido.domain.enumeration.esexe;
import tn.mdweb.acido.domain.enumeration.estatut;
import tn.mdweb.acido.domain.enumeration.etypeobservation;

/**
 * A Acidoform.
 */
@Document(collection = "acidoform")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Acidoform implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("homocystinurie")
    private Boolean homocystinurie;

    @Field("leucinose")
    private Boolean leucinose;

    @Field("phenylcetonurie")
    private Boolean phenylcetonurie;

    @Field("tyrosinemie")
    private Boolean tyrosinemie;

    @Field("updatedate")
    private LocalDate updatedate;

    @Field("typeobservation")
    private etypeobservation typeobservation;

    @Field("createddate")
    private LocalDate createddate;

    @Field("sexe")
    private esexe sexe;

    @Field("datenaissance")
    private LocalDate datenaissance;

    @Field("statut")
    private estatut statut;

    @Field("ageactuelans")
    private Integer ageactuelans;

    @Field("ageactuelmois")
    private Integer ageactuelmois;

    @Field("ageactueljours")
    private Integer ageactueljours;

    @Field("datedeces")
    private LocalDate datedeces;

    @Field("agedecesan")
    private Integer agedecesan;

    @Field("agedecesmois")
    private Integer agedecesmois;

    @Field("agedecesjours")
    private Integer agedecesjours;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Acidoform id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getHomocystinurie() {
        return this.homocystinurie;
    }

    public Acidoform homocystinurie(Boolean homocystinurie) {
        this.setHomocystinurie(homocystinurie);
        return this;
    }

    public void setHomocystinurie(Boolean homocystinurie) {
        this.homocystinurie = homocystinurie;
    }

    public Boolean getLeucinose() {
        return this.leucinose;
    }

    public Acidoform leucinose(Boolean leucinose) {
        this.setLeucinose(leucinose);
        return this;
    }

    public void setLeucinose(Boolean leucinose) {
        this.leucinose = leucinose;
    }

    public Boolean getPhenylcetonurie() {
        return this.phenylcetonurie;
    }

    public Acidoform phenylcetonurie(Boolean phenylcetonurie) {
        this.setPhenylcetonurie(phenylcetonurie);
        return this;
    }

    public void setPhenylcetonurie(Boolean phenylcetonurie) {
        this.phenylcetonurie = phenylcetonurie;
    }

    public Boolean getTyrosinemie() {
        return this.tyrosinemie;
    }

    public Acidoform tyrosinemie(Boolean tyrosinemie) {
        this.setTyrosinemie(tyrosinemie);
        return this;
    }

    public void setTyrosinemie(Boolean tyrosinemie) {
        this.tyrosinemie = tyrosinemie;
    }

    public LocalDate getUpdatedate() {
        return this.updatedate;
    }

    public Acidoform updatedate(LocalDate updatedate) {
        this.setUpdatedate(updatedate);
        return this;
    }

    public void setUpdatedate(LocalDate updatedate) {
        this.updatedate = updatedate;
    }

    public etypeobservation getTypeobservation() {
        return this.typeobservation;
    }

    public Acidoform typeobservation(etypeobservation typeobservation) {
        this.setTypeobservation(typeobservation);
        return this;
    }

    public void setTypeobservation(etypeobservation typeobservation) {
        this.typeobservation = typeobservation;
    }

    public LocalDate getCreateddate() {
        return this.createddate;
    }

    public Acidoform createddate(LocalDate createddate) {
        this.setCreateddate(createddate);
        return this;
    }

    public void setCreateddate(LocalDate createddate) {
        this.createddate = createddate;
    }

    public esexe getSexe() {
        return this.sexe;
    }

    public Acidoform sexe(esexe sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(esexe sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDatenaissance() {
        return this.datenaissance;
    }

    public Acidoform datenaissance(LocalDate datenaissance) {
        this.setDatenaissance(datenaissance);
        return this;
    }

    public void setDatenaissance(LocalDate datenaissance) {
        this.datenaissance = datenaissance;
    }

    public estatut getStatut() {
        return this.statut;
    }

    public Acidoform statut(estatut statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(estatut statut) {
        this.statut = statut;
    }

    public Integer getAgeactuelans() {
        return this.ageactuelans;
    }

    public Acidoform ageactuelans(Integer ageactuelans) {
        this.setAgeactuelans(ageactuelans);
        return this;
    }

    public void setAgeactuelans(Integer ageactuelans) {
        this.ageactuelans = ageactuelans;
    }

    public Integer getAgeactuelmois() {
        return this.ageactuelmois;
    }

    public Acidoform ageactuelmois(Integer ageactuelmois) {
        this.setAgeactuelmois(ageactuelmois);
        return this;
    }

    public void setAgeactuelmois(Integer ageactuelmois) {
        this.ageactuelmois = ageactuelmois;
    }

    public Integer getAgeactueljours() {
        return this.ageactueljours;
    }

    public Acidoform ageactueljours(Integer ageactueljours) {
        this.setAgeactueljours(ageactueljours);
        return this;
    }

    public void setAgeactueljours(Integer ageactueljours) {
        this.ageactueljours = ageactueljours;
    }

    public LocalDate getDatedeces() {
        return this.datedeces;
    }

    public Acidoform datedeces(LocalDate datedeces) {
        this.setDatedeces(datedeces);
        return this;
    }

    public void setDatedeces(LocalDate datedeces) {
        this.datedeces = datedeces;
    }

    public Integer getAgedecesan() {
        return this.agedecesan;
    }

    public Acidoform agedecesan(Integer agedecesan) {
        this.setAgedecesan(agedecesan);
        return this;
    }

    public void setAgedecesan(Integer agedecesan) {
        this.agedecesan = agedecesan;
    }

    public Integer getAgedecesmois() {
        return this.agedecesmois;
    }

    public Acidoform agedecesmois(Integer agedecesmois) {
        this.setAgedecesmois(agedecesmois);
        return this;
    }

    public void setAgedecesmois(Integer agedecesmois) {
        this.agedecesmois = agedecesmois;
    }

    public Integer getAgedecesjours() {
        return this.agedecesjours;
    }

    public Acidoform agedecesjours(Integer agedecesjours) {
        this.setAgedecesjours(agedecesjours);
        return this;
    }

    public void setAgedecesjours(Integer agedecesjours) {
        this.agedecesjours = agedecesjours;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Acidoform)) {
            return false;
        }
        return id != null && id.equals(((Acidoform) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Acidoform{" +
            "id=" + getId() +
            ", homocystinurie='" + getHomocystinurie() + "'" +
            ", leucinose='" + getLeucinose() + "'" +
            ", phenylcetonurie='" + getPhenylcetonurie() + "'" +
            ", tyrosinemie='" + getTyrosinemie() + "'" +
            ", updatedate='" + getUpdatedate() + "'" +
            ", typeobservation='" + getTypeobservation() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", datenaissance='" + getDatenaissance() + "'" +
            ", statut='" + getStatut() + "'" +
            ", ageactuelans=" + getAgeactuelans() +
            ", ageactuelmois=" + getAgeactuelmois() +
            ", ageactueljours=" + getAgeactueljours() +
            ", datedeces='" + getDatedeces() + "'" +
            ", agedecesan=" + getAgedecesan() +
            ", agedecesmois=" + getAgedecesmois() +
            ", agedecesjours=" + getAgedecesjours() +
            "}";
    }
}
