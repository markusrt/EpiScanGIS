package de.meningococcus.episcangis.map;

import java.util.ArrayList;
import java.util.Collection;

public class ClusterMap extends AbstractWmsMap {

	public ClusterMap(int width, int height) throws MapInitializationException {
		super(width, height);
		try {
			super.toggleLayerState("borders_default", true);
			super.toggleLayerState("singlecluster", true);
		}
		catch (LayerNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Collection toggleLayerState(String layerName, boolean active)
			throws LayerNotFoundException {
		return new ArrayList();
	}

	public synchronized Collection setParameter(String name, String value)
			throws ParameterNotFoundException, InvalidParameterValueException {
		Collection updatedLayers = super.setParameter(name, value);
		return updatedLayers;
	}

	protected MapLayerFactory getMapLayerFactory() {
		return new ClusterMapLayerFactory();
	}

	public void setClusterId(int id) {
		try {
			setParameter("CLUSTERID", String.valueOf(id));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
