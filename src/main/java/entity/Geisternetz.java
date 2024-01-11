package entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "geisternetz", schema = "geisternetze")
public class Geisternetz implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Breitengrad")
    private double breitengrad;
    @Basic
    @Column(name = "Längengrad")
    private double laengengrad;

    @Basic
    @Column(name = "Größe_in_Meter")
    private int größe;
    @Basic
    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "PersonID", referencedColumnName = "ID", nullable = false)
    private Person PersonByPersonenId;

    public Geisternetz()
    {
    }

    public Geisternetz(double breitengrad, double laengengrad, int größe, String status)
    {
        this.breitengrad = breitengrad;
        this.laengengrad = laengengrad;
        this.größe = größe;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public int getGroeße() {
        return größe;
    }

    public void setGroeße(int groeße) {
        this.größe = groeße;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geisternetz that = (Geisternetz) o;

        if (id != that.id) return false;
        if (Double.compare(breitengrad, that.breitengrad) != 0) return false;
        if (Double.compare(laengengrad, that.laengengrad) != 0) return false;
        if (größe != that.größe) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(breitengrad);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(laengengrad);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + größe;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public Person getPersonByPersonenId() {
        return PersonByPersonenId;
    }

    public void setPersonByPersonenId(Person personenByPersonenId) {
        this.PersonByPersonenId = personenByPersonenId;
    }
}
