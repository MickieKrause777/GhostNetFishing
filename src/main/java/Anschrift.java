import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ApplicationScoped
public class Anschrift implements Serializable
{
    private String name = "Shea Sepherd";

    private String strasse = "Seestraße";

    private int hausnummer = 7;

    private String plz = "92200";

    private String ort = "Meerdorf";

    public Anschrift()
    {
    }

    public String getName()
    {
        return name;
    }

    public String getStrasse()
    {
        return strasse;
    }

    public int getHausnummer()
    {
        return hausnummer;
    }

    public String getPlz()
    {
        return plz;
    }

    public String getOrt()
    {
        return ort;
    }
}