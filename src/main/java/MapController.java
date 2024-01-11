import entity.Geisternetz;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MapController implements Serializable {

    private MapModel model;
    private Marker marker;

    @PostConstruct
    public void init(){
        model = new DefaultMapModel();

        List<Geisternetz> geisternetzeList = Netzliste.getInstance().getListe();

        for (Geisternetz geisternetz : geisternetzeList) {
            LatLng location = new LatLng(geisternetz.getBreitengrad(), geisternetz.getLaengengrad());
            String icon = geisternetz.getStatus().equals("Geborgen") ? "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
                    : "http://maps.google.com/mapfiles/ms/icons/red-dot.png";
            Marker marker = new Marker(location, "Größe:" + " " + geisternetz.getGroeße() + " " + "Meter", "Status:" + " "+ geisternetz.getStatus());
            marker.setIcon(icon);
            model.addOverlay(marker);
        }
    }

    public MapModel getModel() {
        return model;
    }

    public Marker getMarker() {
        return marker;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    public String map(){
        return "map.xhtml";
    }
}
