/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2017, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.map;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.geotools.data.wmts.WMTSLayer;
import org.geotools.data.wmts.WebMapTileServer;
import org.geotools.data.wmts.request.GetTileRequest;
import org.geotools.factory.Hints;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.ows.ServiceException;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.tile.Tile;
import org.geotools.tile.TileService;
import org.geotools.tile.impl.wmts.WMTSService;
import org.geotools.tile.impl.wmts.WMTSServiceType;
import org.hsqldb.Server;
import org.junit.Before;
import org.junit.Test;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

/**
 * @author ian
 *
 */
public class WMTSCoverageReaderTest {
    static WMTSCoverageReader[] wcr = new WMTSCoverageReader[2];
    
    @Before
    public void setup() throws ServiceException, MalformedURLException, IOException {
        int i=0;
        for(WMTSServiceType t:WMTSServiceType.values()) {
            
            
            WebMapTileServer server = createServer(t);
            WMTSLayer layer2 = (WMTSLayer) server.getCapabilities().getLayer("topp:states");
            wcr[i++] = new WMTSCoverageReader(server  , layer2);
        }
        
    }
    /**
     * Test method for {@link org.geotools.map.WMTSCoverageReader#initMapRequest(org.geotools.geometry.jts.ReferencedEnvelope, int, int, java.awt.Color)}.
     * @throws IOException 
     * @throws ServiceException 
     * @throws FactoryException 
     * @throws NoSuchAuthorityCodeException 
     * @throws MismatchedDimensionException 
     */
    @Test
    public void testInitMapRequest() throws IOException, ServiceException, MismatchedDimensionException, NoSuchAuthorityCodeException, FactoryException {
        for(int i=0;i<2;i++) {
            ReferencedEnvelope bbox = new ReferencedEnvelope(-180, 180, -90, 90, CRS.decode("EPSG:4326"));
            int width=400;
            int height=200;
            Color backgroundColor = Color.WHITE;
            ReferencedEnvelope grid = wcr[i].initMapRequest(bbox, width, height, backgroundColor );
            assertNotNull(grid);
            GetTileRequest mapRequest = wcr[i].getMapRequest();
            mapRequest.setCRS(grid.getCoordinateReferenceSystem());
            Set<Tile> responses = wcr[i].wmts.issueRequest(mapRequest);
            for(Tile t:responses) {
                //System.out.println(t);
                //System.out.println(t.getTileIdentifier()+" "+t.getExtent());
            }
            
        }
    }
    private WebMapTileServer createServer(WMTSServiceType type) throws ServiceException, MalformedURLException, IOException {

        String baseURL;
        // TODO: replace with local files
        if (WMTSServiceType.REST.equals(type)) {
            baseURL = "http://raspberrypi:9000/wmts/1.0.0/WMTSCapabilities.xml";
            
        } else {
            baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?REQUEST=GetCapabilities";
            
        }
        return new WebMapTileServer(new URL(baseURL));
    }
}
