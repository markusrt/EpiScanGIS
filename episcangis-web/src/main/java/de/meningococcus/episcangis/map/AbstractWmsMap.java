package de.meningococcus.episcangis.map;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geotools.data.ows.CRSEnvelope;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.xml.sax.SAXException;

import de.meningococcus.episcangis.db.model.BoundingBox;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public abstract class AbstractWmsMap
{

  private static Log log = LogFactory.getLog(AbstractWmsMap.class);

  private String wmsUrl, srs = "epsg:31467";

  private Map<String, MapLayer> mapLayers = new LinkedHashMap<String, MapLayer>();

  private Vector<AbstractParameter> mapParameters = new Vector<AbstractParameter>();

  private int width = 640, height = 480;

  private BoundingBox bbox = new BoundingBox();

  public AbstractWmsMap() throws MapInitializationException
  {
    Configuration config;
    String configfile = "conf/epidegis-web.properties";
    try
    {
      config = new PropertiesConfiguration(configfile);
      this.wmsUrl = config.getString("wms.url", "");
      initialize();
    }
    catch (ConfigurationException e)
    {
      log.warn("Exception while loading " + configfile + ": " + e.getMessage());
    }
  }

  /**
   * Classes which extend AbstractWmsMap need to provide a MapLayerFactory
   * implementation. The initialize() function calls this method to retrieve
   * specific layer objects.
   * @return MapLayerFactory to create MapLayers
   */
  protected abstract MapLayerFactory getMapLayerFactory();

  public interface Exporter
  {
    void addBbox(BoundingBox box);

    void addHeight(int height);

    void addWidth(int width);

    void addSrs(String srs);

    void addWmsUrl(String wmsUrl);

    void addLayers(Map<String, MapLayer> layers);

    void addMapparameters(Vector<AbstractParameter> parameters);
  }

  public synchronized void export(Exporter e)
  {
    e.addBbox(bbox);
    e.addHeight(height);
    e.addWidth(width);
    e.addSrs(srs);
    e.addWmsUrl(wmsUrl);
    e.addLayers(mapLayers);
    e.addMapparameters(mapParameters);
  }

  /**
   * <p>
   * Initializes this map by querying a server with Web Map Service (<a
   * href="http://portal.opengeospatial.org/files/?artifact_id=5316"
   * target="_blank">OpenGIS� Web Map Service (WMS) Implementation Specification</a>)
   * capabilities.
   * </p>
   * <p>
   * The layerlist is retrieved from wmsUrl via <a
   * href="http://www.geotools.org/">Geotools</a>. A MapLayerFactory
   * implementation provides MapLayer objects for every layer. The queryable
   * flag is set if appropriate.
   * </p>
   * @throws MapInitializationException
   */
  private void initialize() throws MapInitializationException
  {
    log.debug("Initialize map from WMS URL: " + wmsUrl);
    try
    {
      URL url = new URL(wmsUrl + "&request=GetCapabilities");
      WebMapServer wms = new WebMapServer(url);
      WMSCapabilities caps = wms.getCapabilities();

      int layerNumber = 0;
      // Iterate over layers and create MapLayerBeans
      for (Layer layer : (List<Layer>) caps.getLayerList())
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
            // if( l.get_abstract() != null && l.get_abstract().length() > 0 ) {
            // mlb.setDescription(l.get_abstract());
            // }
            addLayer(mlb);
          }
        }
        layerNumber++;
      }
    }
    catch (MalformedURLException e)
    {
      throw new MapInitializationException(e);
    }
    catch (IOException e)
    {
      throw new MapInitializationException(e);
    }
    catch (SAXException e)
    {
      throw new MapInitializationException(e);
    }
  }

  /**
   * @param box new BoundingBox for this map
   */
  protected void setBbox(BoundingBox box)
  {
    this.bbox = box;
    bbox.mapToPixels(width, height);
  }

  /**
   * TODO subsequent calls result in invalid bounding box.
   * @param mapHeight height for map in pixels
   */
  private void setHeight(int mapHeight)
  {
    this.height = mapHeight;
    bbox.mapToPixels(width, height);
  }

  /**
   * TODO subsequent calls result in invalid bounding box.
   * @param mapWidth width for map in pixels
   */
  private void setWidth(int mapWidth)
  {
    this.width = mapWidth;
    bbox.mapToPixels(width, height);
  }

  /**
   * Adds a new layer to this map. TODO Check for duplicates
   * @param layer
   */
  public void addLayer(MapLayer layer)
  {
    mapLayers.put(layer.getName(), layer);
  }

  /**
   * Sets the parameter with given name (either layer- or mapparameter) to the
   * specified value. Also accepts two predefined parameters called <b>width</b>
   * and <b>height</b> which accept Integer values from 1 to 2048.
   * @param name parameter to change
   * @param value new value
   * @throws ParameterNotFoundException if the parameter with the specified name
   *           could not be found
   * @throws InvalidParameterValueException if the value is invalid for this
   *           parameter
   */
  public Collection<String> setParameter(String name, String value)
      throws ParameterNotFoundException, InvalidParameterValueException
  {
    Collection<String> updateLayers = new Vector<String>();
    log.debug("Set parameter " + name + "=" + value);

    if (name.toLowerCase(Locale.ENGLISH).equals("width")
        || name.toLowerCase(Locale.ENGLISH).equals("height"))
    {
      int size = Integer.parseInt(value);
      if (size >= 1 && size <= 2048)
      {
        if (name.toLowerCase(Locale.ENGLISH).equals("width"))
        {
          setWidth(size);
        }
        else
        {
          setHeight(size);
        }
        for (MapLayer mlb : mapLayers.values())
        {
          if (mlb.isActive())
          {
            updateLayers.add(mlb.getName());
          }
        }
      }
      else
      {
        throw new InvalidParameterValueException();
      }
    }
    else
    {
      AbstractParameter p = getParameter(name);
      if (p == null)
      {
        throw new ParameterNotFoundException();
      }
      if (p instanceof ValueParameter)
      {
        String oldValue = ((ValueParameter) p).getValue();
        ((ValueParameter) p).setValue(value);
        if (!((ValueParameter) p).getValue().equals(value))
        {
          ((ValueParameter) p).setValue(oldValue);
          throw new InvalidParameterValueException();
        }
      }
    }
    updateLayers.addAll(findLayersWithParameter(name));

    return updateLayers;
  }

  /**
   * Searches all layers, that either have a parameter with the provided name
   * defined, or reference such a parameter
   * @param parameterName Paramter to search
   * @return Collection containing all found layernames
   */
  private Collection<String> findLayersWithParameter(String parameterName)
  {
    List<String> dependentLayers = new LinkedList<String>();
    for (MapLayer mlb : mapLayers.values())
    {
      if (/* mlb.isActive() && */mlb.dependsOnParameter(parameterName))
      {
        if (mlb.isActive())
        {
          dependentLayers.add(0, mlb.getName());
        }
        else
        {
          dependentLayers.add(dependentLayers.size(), mlb.getName());
        }
      }
    }
    return dependentLayers;
  }

  /**
   * Toggles the active state of the layer with specified name
   * @param layerName layer to activate/deactivate
   * @param active new value for layers active Attribute
   * @throws LayerNotFoundException If a layer with this name does not exist
   */
  public void toggleLayerState(String layerName, boolean active)
      throws LayerNotFoundException
  {
    MapLayer layer = mapLayers.get(layerName);
    if (layer == null)
    {
      throw new LayerNotFoundException(layerName);
    }
    layer.setActive(active);
  }

  public AbstractParameter getParameter(String name)
  {
    AbstractParameter para = null;
    for (AbstractParameter p : mapParameters)
    {
      para = p.getNamedParameter(name);
      if (para != null)
      {
        return para;
      }
    }
    for (MapLayer mlb : mapLayers.values())
    {
      for (AbstractParameter p : mlb.getParameters(false))
      {
        if (p != null && p.getName().equals(name)
            && !(p instanceof ReferenceParameter))
        {
          para = p;
          break;
        }
      }
    }
    return para;
  }

  protected void addMapParameter(AbstractParameter p)
  {
    mapParameters.add(p);
  }

  public void move(double xOffset, double yOffset)
  {
    bbox.move(xOffset, yOffset);
  }
}
