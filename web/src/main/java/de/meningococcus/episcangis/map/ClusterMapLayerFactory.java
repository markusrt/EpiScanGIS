package de.meningococcus.episcangis.map;

import de.meningococcus.episcangis.map.layer.BordersLayer;
import de.meningococcus.episcangis.map.layer.MapLayer;
import de.meningococcus.episcangis.map.layer.SingleClusterLayer;
import java.util.Locale;


final class ClusterMapLayerFactory
    implements MapLayerFactory
{

    ClusterMapLayerFactory()
    {
    }

    public MapLayer getMapLayer(String name, String title, boolean hasLegend, AbstractWmsMap map)
    {
        if(name == null)
            name = "<unnamed layer>";
        int index = name.lastIndexOf('_');
        String realName;
        if(index != -1)
            realName = name.substring(0, index).toLowerCase(Locale.ENGLISH);
        else
            realName = name;
        MapLayer ret;
        if(realName.equals("borders"))
            ret = new BordersLayer(name, title, hasLegend, map);
        else
        if(realName.equals("singlecluster"))
            ret = new SingleClusterLayer(name, title, hasLegend, map);
        else
            ret = null;
        return ret;
    }

    public String getMapUrlKeyPrefix()
    {
        return "cluster.";
    }
}
