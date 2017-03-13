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
    private WMTSService service;

    

    @Before
    public void setup() {
        String url = "http://raspberrypi:9000/wmts/1.0.0/WMTSCapabilities.xml";
        service = new WMTSService("test", url,"Local GeoServer", "webmercator");
    }

    @Test
    public void testScales() {
        double delta = 0.000001;
        double[] scales = service.getScaleList();
        assertEquals(20, scales.length);
        assertEquals(559082264.029, scales[0], delta);
        assertEquals(1066.36479192, scales[19], delta);
    }

    @Test
    public void testCRS() throws NoSuchAuthorityCodeException, FactoryException {
        CoordinateReferenceSystem crs = service.getProjectedTileCrs();
        CoordinateReferenceSystem expected = null;
        expected = CRS.decode("EPSG:3857");
        assertTrue(expected.getName().equals(crs.getName()));
    }

    @Test
    public void testBounds() {
        double delta = 0.01;
        ReferencedEnvelope env = service.getBounds();
        assertEquals(WebMercatorTileService.MIN_LATITUDE, env.getMinimum(0), delta);
        assertEquals(WebMercatorTileService.MIN_LONGITUDE, env.getMinimum(1), delta);
        assertEquals(WebMercatorTileService.MAX_LATITUDE, env.getMaximum(0), delta);
        assertEquals(WebMercatorTileService.MAX_LONGITUDE, env.getMaximum(1), delta);
    }
}
