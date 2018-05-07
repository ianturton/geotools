/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2018, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.wmts;

import static org.geotools.data.wmts.client.WMTSTileFactory4326Test.createCapabilities;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import org.geotools.data.wmts.client.WMTSTileService;
import org.geotools.data.wmts.model.TileMatrixSet;
import org.geotools.data.wmts.model.WMTSCapabilities;
import org.geotools.data.wmts.model.WMTSLayer;
import org.geotools.data.wmts.model.WMTSServiceType;
import org.geotools.test.OnlineTestSupport;

/** @author ian */
public class WMTSOnlineTest extends OnlineTestSupport {
    protected String kvp_baseURL;
    protected String rest_baseURL;
    protected String geosolutions;

    @Override
    public void setUpInternal() {

        kvp_baseURL = getFixture().getProperty("kvp_baseURL");
        rest_baseURL = getFixture().getProperty("rest_baseURL");
        geosolutions = getFixture().getProperty("geosolutions");
    }

    @Override
    public Properties createExampleFixture() {
        Properties props = new Properties();
        props.put("kvp_baseURL", "http://demo.geo-solutions.it/geoserver/gwc/service/wmts");
        props.put(
                "rest_baseURL",
                "https://basemap.nationalmap.gov/arcgis/rest/services/USGSHydroCached/MapServer/WMTS/1.0.0/WMTSCapabilities.xml");
        props.put("geosolutions", "http:////demo.geo-solutions.it/geoserver/gwc/service/wmts");
        props.put("skip.on.failure", true);
        return props;
    }

    @Override
    protected String getFixtureId() {
        // TODO Auto-generated method stub
        return "wmts";
    }

    protected WMTSTileService createKVPService() throws Exception {
        try {
            URL capaResource = new URL(kvp_baseURL);

            assertNotNull("Can't find KVP getCapa file", capaResource);

            WMTSCapabilities capa = createCapabilities(capaResource);

            String baseURL = kvp_baseURL;
            // fetch the first layer
            WMTSLayer layer = capa.getLayerList().get(0);
            // WMTSLayer layer = capa.getLayer("unesco:Unesco_point");
            TileMatrixSet matrixSet = capa.getMatrixSet("EPSG:900913");
            assertNotNull(layer);
            assertNotNull(matrixSet);

            return new WMTSTileService(baseURL, WMTSServiceType.KVP, layer, null, matrixSet);

        } catch (URISyntaxException ex) {
            fail(ex.getMessage());
            return null;
        }
    }

    protected WMTSTileService createRESTService() throws Exception {
        try {
            URL capaResource = new URL(rest_baseURL);

            assertNotNull("Can't find REST getCapa file", capaResource);

            WMTSCapabilities capa = createCapabilities(capaResource);
            WMTSLayer layer = capa.getLayerList().get(0);
            TileMatrixSet ms = capa.getMatrixSets().get(0);
            return new WMTSTileService(rest_baseURL, WMTSServiceType.REST, layer, null, ms);

        } catch (URISyntaxException ex) {
            fail(ex.getMessage());
            return null;
        }
    }
}
