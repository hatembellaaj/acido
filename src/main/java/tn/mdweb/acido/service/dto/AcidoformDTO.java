package tn.mdweb.acido.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import tn.mdweb.acido.domain.enumeration.esexe;
import tn.mdweb.acido.domain.enumeration.estatut;
import tn.mdweb.acido.domain.enumeration.etypeobservation;

/**
 * A DTO for the {@link tn.mdweb.acido.domain.Acidoform} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AcidoformDTO implements Serializable {

    private String id;

    private Boolean homocystinurie;

    private Boolean leucinose;

    private Boolean phenylcetonurie;

    private Boolean tyrosinemie;

    private LocalDate updatedate;

    private etypeobservation typeobservation;

    private LocalDate createddate;

    private esexe sexe;

    private LocalDate datenaissance;

    private estatut statut;

    private Integer ageactuelans;

    private Integer ageactuelmois;

    private Integer ageactueljours;

    private LocalDate datedeces;

    private Integer agedecesan;

    private Integer agedecesmois;

    private Integer agedecesjours;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getHomocystinurie() {
        return homocystinurie;
    }

    public void setHomocystinurie(Boolean homocystinurie) {
        this.homocystinurie = homocystinurie;
    }

    public Boolean getLeucinose() {
        return leucinose;
    }

    public void setLeucinose(Boolean leucinose) {
        this.leucinose = leucinose;
    }

    public Boolean getPhenylcetonurie() {
        return phenylcetonurie;
    }

    public void setPhenylcetonurie(Boolean phenylcetonurie) {
        this.phenylcetonurie = phenylcetonurie;
    }

    public Boolean getTyrosinemie() {
        return tyrosinemie;
    }

    public void setTyrosinemie(Boolean tyrosinemie) {
        this.tyrosinemie = tyrosinemie;
    }

    public LocalDate getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(LocalDate updatedate) {
        this.updatedate = updatedate;
    }

    public etypeobservation getTypeobservation() {
        return typeobservation;
    }

    public void setTypeobservation(etypeobservation typeobservation) {
        this.typeobservation = typeobservation;
    }

    public LocalDate getCreateddate() {
        return createddate;
    }

    public void setCreateddate(LocalDate createddate) {
        this.createddate = createddate;
    }

    public esexe getSexe() {
        return sexe;
    }

    public void setSexe(esexe sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(LocalDate datenaissance) {
        this.datenaissance = datenaissance;
    }

    public estatut getStatut() {
        return statut;
    }

    public void setStatut(estatut statut) {
        this.statut = statut;
    }

    public Integer getAgeactuelans() {
        return ageactuelans;
    }

    public void setAgeactuelans(Integer ageactuelans) {
        this.ageactuelans = ageactuelans;
    }

    public Integer getAgeactuelmois() {
        return ageactuelmois;
    }

    public void setAgeactuelmois(Integer ageactuelmois) {
        this.ageactuelmois = ageactuelmois;
    }

    public Integer getAgeactueljours() {
        return ageactueljours;
    }

    public void setAgeactueljours(Integer ageactueljours) {
        this.ageactueljours = ageactueljours;
    }

    public LocalDate getDatedeces() {
        return datedeces;
    }

    public void setDatedeces(LocalDate datedeces) {
        this.datedeces = datedeces;
    }

    public Integer getAgedecesan() {
        return agedecesan;
    }

    public void setAgedecesan(Integer agedecesan) {
        this.agedecesan = agedecesan;
    }

    public Integer getAgedecesmois() {
        return agedecesmois;
    }

    public void setAgedecesmois(Integer agedecesmois) {
        this.agedecesmois = agedecesmois;
    }

    public Integer getAgedecesjours() {
        return agedecesjours;
    }

    public void setAgedecesjours(Integer agedecesjours) {
        this.agedecesjours = agedecesjours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AcidoformDTO)) {
            return false;
        }

        AcidoformDTO acidoformDTO = (AcidoformDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, acidoformDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AcidoformDTO{" +
            "id='" + getId() + "'" +
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
