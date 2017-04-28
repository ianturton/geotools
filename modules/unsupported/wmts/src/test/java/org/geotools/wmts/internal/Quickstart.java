package org.geotools.wmts.internal;

import java.io.File;
import java.net.URL;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.wmts.WMTSLayer;
import org.geotools.data.wmts.WebMapTileServer;
import org.geotools.factory.Hints;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.map.WMTSMapLayer;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.referencing.crs.DefaultProjectedCRS;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.tile.ServiceTest;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Prompts the user for a shapefile and displays the contents on the screen in a map frame.
 * <p>
 * This is the GeoTools Quickstart application used in documentationa and tutorials. *
 */
public class Quickstart {

    /**
     * GeoTools Quickstart demo application. Prompts the user for a shapefile and displays its contents on the screen in a map frame
     */
    public static void main(String[] args) throws Exception {
        MapContent map = new MapContent();
        
        map.setTitle("Quickstart");
        
       
        
        CoordinateReferenceSystem crs = DefaultGeographicCRS.WGS84;
        ReferencedEnvelope env = new ReferencedEnvelope(-180, 180, -80, 80,
                crs);
        crs = CRS.decode("epsg:3857");
        env = env.transform(crs, true);
        
        map.getViewport().setCoordinateReferenceSystem(crs);
        map.getViewport().setBounds(env);
        WebMapTileServer server = new WebMapTileServer(
                new URL("http://astun-desktop:8080/geoserver/gwc/service/wmts?REQUEST=GetCapabilities"));
        WMTSLayer wlayer = (WMTSLayer) server.getCapabilities().getLayer("topp:states");
        
        WMTSMapLayer mapLayer = new WMTSMapLayer(server, wlayer);
        map.addLayer(mapLayer);
        WMTSLayer wlayer2 = (WMTSLayer) server.getCapabilities().getLayer("vecmap-district:Foreshore");
        
        WMTSMapLayer mapLayer2 = new WMTSMapLayer(server, wlayer2);
        map.addLayer(mapLayer2);
        File file = new File("/data/natural_earth/110m_physical/110m_coastline.shp");
        FileDataStore store = FileDataStoreFinder.getDataStore(file );
        SimpleFeatureSource featureSource = store.getFeatureSource();
        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);
        // Now display the map
        JMapFrame.showMap(map);
    }

}