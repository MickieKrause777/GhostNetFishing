import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.Geisternetz;
import entity.Person;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.*;
import org.primefaces.PrimeFaces;

@Named
@ApplicationScoped
public class Netzliste implements Serializable
{
    private static Netzliste instance = new Netzliste();

    private List<String> statusListe = new ArrayList<>();

    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Fallstudie");

    @PostConstruct
    public void init(){
        statusListe.add("Gemeldet");
        statusListe.add("Bergung bevorstehend");
        statusListe.add("Verschollen");
        statusListe.add("Geborgen");
    }

    public Netzliste()
    {
    }

    public static Netzliste getInstance()
    {
        return instance;
    }

    public List<String> getStatusListe(){
        return statusListe;
    }

    public List<Geisternetz> getListe()
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select a from Geisternetz a");
        List<Geisternetz> list = q.getResultList();
        return list;
    }

    public void saveGeisternetzForPerson(Person person, Geisternetz geisternetz){
        person.getGeisternetzesById().add(geisternetz);
        geisternetz.setPersonByPersonenId(person);

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.persist(person);
        em.persist(geisternetz);
        t.commit();
        em.close();
    }

    public void announceBergungFuerNetz(Person person, Geisternetz geisternetz){
        person.getGeisternetzesById().add(geisternetz);
        geisternetz.setPersonByPersonenId(person);
        geisternetz.setStatus("Bergung bevorstehend");

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();

        t.begin();
        em.persist(person);
        em.merge(geisternetz);
        t.commit();
        em.close();

        PrimeFaces.current().executeScript("PF('aendereStatusZuBergungBevorstehend').hide()");
        PrimeFaces.current().ajax().update("form:dt-Geisternetze");
    }

    public void announceGeborgenesNetz(Person bergendePerson, Geisternetz geisternetz){
        Person eingetragenePerson = geisternetz.getPersonByPersonenId();

        if (bergendePerson.getVorname().equals(eingetragenePerson.getVorname()) &&
                bergendePerson.getNachname().equals(eingetragenePerson.getNachname()) &&
                bergendePerson.getTelefonnummer().equals(eingetragenePerson.getTelefonnummer())){

            bergendePerson.getGeisternetzesById().add(geisternetz);
            geisternetz.setStatus("Geborgen");

            EntityManager em = emf.createEntityManager();
            EntityTransaction t = em.getTransaction();

            t.begin();
            em.merge(geisternetz);
            t.commit();
            em.close();

            PrimeFaces.current().executeScript("PF('aendereStatusZuGeborgen').hide()");
            PrimeFaces.current().ajax().update("form:dt-Geisternetze");
        } else {
            FacesContext.getCurrentInstance().addMessage("dialog:manage-geborgen",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentifizierungsfehler",
                            "Die eingegebenen Daten stimmen nicht mit denen der aktuell bergenden Person überein."));
        }
    }
}
