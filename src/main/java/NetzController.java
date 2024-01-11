import java.io.Serializable;

import entity.Geisternetz;
import entity.Person;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;


@Named
@ViewScoped
@FacesConfig
public class NetzController implements Serializable
{
    private Geisternetz geisternetz;
    private Geisternetz neuesGeisternetz = null;
    private Person neuePerson = null;

    public Geisternetz getGeisternetz(){
        return geisternetz;
    }

    public void setGeisternetz(Geisternetz geisternetz) {
        this.geisternetz = geisternetz;
    }
    
    public String add(){
        return "add.xhtml";
    }

    public String abbrechen(){
        return "index.xhtml";
    }

    public Geisternetz getNeuesGeisternetz(){
        if (null == neuesGeisternetz){
            this.neuesGeisternetz = new Geisternetz();
        }
        neuesGeisternetz.setStatus("Gemeldet");
        return this.neuesGeisternetz;
    }

    public Person getNeuePerson(){
        if (null == neuePerson){
            this.neuePerson = new Person();
        }

        return this.neuePerson;
    }

    public String speichern(){
        Netzliste.getInstance().saveGeisternetzForPerson(neuePerson, neuesGeisternetz);
        return "index.xhtml";
    }

    public String announceBergung(){
        Netzliste.getInstance().announceBergungFuerNetz(neuePerson, geisternetz);
        this.neuePerson = null;
        return "index.xhtml";
    }

    public String announceGeborgen(){
        Netzliste.getInstance().announceGeborgenesNetz(neuePerson, geisternetz);
        this.neuePerson = null;
        return "index.xhtml";
    }
}
