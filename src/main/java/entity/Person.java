package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "person", schema = "geisternetze")
public class Person implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Vorname")
    private String vorname;
    @Basic
    @Column(name = "Nachname")
    private String nachname;
    @Basic
    @Column(name = "Telefonnummer")
    private String telefonnummer;
    @OneToMany(mappedBy = "PersonByPersonenId")
    private Collection<Geisternetz> geisternetzesById = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person that = (Person) o;

        if (id != that.id) return false;
        if (vorname != null ? !vorname.equals(that.vorname) : that.vorname != null) return false;
        if (nachname != null ? !nachname.equals(that.nachname) : that.nachname != null) return false;
        if (telefonnummer != null ? !telefonnummer.equals(that.telefonnummer) : that.telefonnummer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vorname != null ? vorname.hashCode() : 0);
        result = 31 * result + (nachname != null ? nachname.hashCode() : 0);
        result = 31 * result + (telefonnummer != null ? telefonnummer.hashCode() : 0);
        return result;
    }

    public Collection<Geisternetz> getGeisternetzesById() {
        return geisternetzesById;
    }

    public void setGeisternetzesById(Collection<Geisternetz> geisternetzesById) {
        this.geisternetzesById = geisternetzesById;
    }
}
