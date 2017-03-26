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
package org.geotools.wmts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.geotools.data.wmts.GetCapabilities;
import org.geotools.data.wmts.Layer;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ian
 *
 */
public class GetCapabilitiesTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void urlGeneration() {
        String expected = "http://raspberrypi:8080/geoserver/gwc/service/wmts?request=GetCapabilities&service=wmts&version=1.0.0";
        String baseURL;
        GetCapabilities caps;
        String obs;

        baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?SERVICE=wmts&REQUEST=GetCapabilities";
        caps = new GetCapabilities(baseURL, null);
        obs = caps.getRequestURL();
        assertEquals(expected, obs);

        baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?SERVICE=wmts&REQUEST=GetCapabilities";
        caps = new GetCapabilities(baseURL, null);
        obs = caps.getRequestURL();
        assertEquals(expected, obs);

        baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?";
        caps = new GetCapabilities(baseURL, null);
        obs = caps.getRequestURL();
        assertEquals(expected, obs);

        baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts";
        caps = new GetCapabilities(baseURL, null);
        obs = caps.getRequestURL();
        assertEquals(expected, obs);

        HashMap<String, String> params = new HashMap<>();
        String[] testParams = {"","request=GetCapabilities","service=wmts","version=1.0.0","","REQUEST=GetCapabilities","SERVICE=wmts","VERSION=1.0.0"};
        for (int i = 0; i < testParams.length; i++) {
            if(testParams[i].contains("=")) {
                String[] parts = testParams[i].split("=");
                params.put(parts[0], parts[1]);
            } else {
                params = new HashMap<>();
            }
            baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?SERVICE=wmts&REQUEST=GetCapabilities";
            caps = new GetCapabilities(baseURL, params);
            obs = caps.getRequestURL();
            assertEquals(expected, obs);

            baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?SERVICE=wmts&REQUEST=GetCapabilities";
            caps = new GetCapabilities(baseURL, params);
            obs = caps.getRequestURL();
            assertEquals(expected, obs);

            baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?";
            caps = new GetCapabilities(baseURL, params);
            obs = caps.getRequestURL();
            assertEquals(expected, obs);

            baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts";
            caps = new GetCapabilities(baseURL, params);
            obs = caps.getRequestURL();
            assertEquals(expected, obs);
        }
    }

    @Test
    public void testLayers() {
        String baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?SERVICE=wmts&REQUEST=GetCapabilities";
        baseURL = "http://raspberrypi:9000/wmts/1.0.0/WMTSCapabilities.xml";
        baseURL = "http://raspberrypi:9000/service?REQUEST=GetCapabilities&SERVICE=WMTS";
        GetCapabilities caps = new GetCapabilities(baseURL, null);
        List<Layer> layers = new ArrayList<>(caps.getLayers().values());
        for(Layer layer:layers) {
            System.out.println(layer);
        }
    }
}
