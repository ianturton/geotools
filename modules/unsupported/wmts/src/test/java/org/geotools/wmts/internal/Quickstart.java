package org.geotools.wmts.internal;

import java.net.URL;

import org.geotools.data.wmts.WMTSLayer;
import org.geotools.data.wmts.WebMapTileServer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.map.WMTSMapLayer;
import org.geotools.swing.JMapFrame;

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

     
        WebMapTileServer server = new WebMapTileServer(
                new URL("http://astun-desktop:8080/geoserver/gwc/service/wmts?REQUEST=GetCapabilities"));
        WMTSLayer layer = (WMTSLayer) server.getCapabilities().getLayer("topp:states");
        Layer mapLayer = new WMTSMapLayer(server, layer);
        map.addLayer(mapLayer);
        
        // Now display the map
        JMapFrame.showMap(map);
    }

}