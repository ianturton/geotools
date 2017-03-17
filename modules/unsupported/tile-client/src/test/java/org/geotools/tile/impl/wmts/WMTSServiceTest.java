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
package org.geotools.tile.impl.wmts;

import static org.junit.Assert.*;

import java.util.logging.Level;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.tile.impl.WebMercatorTileFactory;
import org.geotools.tile.impl.WebMercatorTileService;
import org.junit.Before;
import org.junit.Test;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * @author ian
 *
 */
public class WMTSServiceTest {
    private WMTSService[] services = new WMTSService[2];

    @Before
    public void setup() {
        String restUrl = "http://raspberrypi:9000/wmts/1.0.0/WMTSCapabilities.xml";
        services[0] = new WMTSService("rest", restUrl, "Local GeoServer", "webmercator",
                WMTSServiceType.REST);
        
        String kvpUrl = "http://raspberrypi:8080/geoserver/gwc/service/wmts?REQUEST=GetCapabilities";
        services[1] = new WMTSService("kvp", kvpUrl, "topp:states", "EPSG:900913",
                WMTSServiceType.KVP);
    }

    @Test
    public void testScales() {
        double[][] expected = {{20,31},{559082264.029,5.590822639508929E8},{1066.36479192,1066.36479192}};
        double delta = 0.00001;
        for (int i = 0; i < services.length; i++) {
            double[] scales = services[i].getScaleList();
            assertEquals(services[i].getName(), (int)expected[0][i], scales.length);
            assertEquals(services[i].getName(), expected[1][i], scales[0], delta);
            assertEquals(services[i].getName(), expected[2][i], scales[19], delta);
        }
    }

    @Test
    public void testCRS() throws NoSuchAuthorityCodeException, FactoryException {
        for (int i = 0; i < services.length; i++) {
            CoordinateReferenceSystem crs = services[i].getProjectedTileCrs();
            CoordinateReferenceSystem expected = null;
            expected = CRS.decode("EPSG:3857");
            assertTrue(services[i].getName(), expected.getName().equals(crs.getName()));
        }
    }

    @Test
    public void testWebMercatorBounds() {
        ReferencedEnvelope[] expected = new ReferencedEnvelope[2];
        expected[0] = new ReferencedEnvelope(-180.0,180.0,-85.06,85.06,DefaultGeographicCRS.WGS84);
        expected[1] = new ReferencedEnvelope(-124.73142200000001, -66.969849, 24.955967, 49.371735, DefaultGeographicCRS.WGS84);
        double delta = 0.1;
        for (int i = 0; i < services.length; i++) {
            ReferencedEnvelope env = services[i].getBounds();
            assertEquals(services[i].getName(), expected[i].getMinimum(1),
                    env.getMinimum(1), delta);
            assertEquals(services[i].getName(), expected[i].getMinimum(0),
                    env.getMinimum(0), delta);
            assertEquals(services[i].getName(), expected[i].getMaximum(1),
                    env.getMaximum(1), delta);
            assertEquals(services[i].getName(), expected[i].getMaximum(0),
                    env.getMaximum(0), delta);
        }
    }
}
