package de.meningococcus.episcangis.map;

import java.util.ArrayList;
import java.util.List;

import org.geotools.data.ows.CRSEnvelope;
import org.geotools.data.ows.Layer;

import de.meningococcus.episcangis.db.model.BoundingBox;

public class MockNrzmMap extends NrzmMap
{

  public MockNrzmMap(int width, int height) throws MapInitializationException
  {
    super(width, height);
  }

  @Override
  protected void initialize() throws MapInitializationException
  {
    int layerNumber = 0;
    List<Layer> layers = new ArrayList<Layer>();

    Layer root = new Layer();
    root.setLatLonBoundingBox(new CRSEnvelope("not important", 0, 0, 100, 100));

    Layer casetypes = new Layer();
    casetypes.setName("casetypes");
    casetypes.setQueryable(true);
    layers.add(casetypes);

    Layer serogroups = new Layer();
    serogroups.setName("serogroups_default");
    serogroups.setStyles(layers);
    layers.add(serogroups);

    Layer incidence = new Layer();
    incidence.setName("incidence");
    layers.add(incidence);

    for (Layer layer : layers)
    {
      if (layerNumber == 0 /* root layer */)
      {
        CRSEnvelope llbox = layer.getLatLonBoundingBox();
        if (llbox != null)
        {
          setBbox(new BoundingBox(llbox.getMinX(), llbox.getMinY(), llbox
              .getMaxX(), llbox.getMaxY()));
        }
      }
      else if (layerNumber != 0)
      {
        boolean hasLegend = false;
        if (layer.getStyles().size() > 0)
        {
          hasLegend = true;
        }
        MapLayer mlb = getMapLayerFactory().getMapLayer(layer.getName(),
            layer.getTitle(), hasLegend, this);
        if (mlb != null && mlb.isValid())
        {
          mlb.setQueryable(layer.isQueryable());
          addLayer(mlb);
        }
      }
      layerNumber++;
    }
  }
}
