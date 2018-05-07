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
package org.geotools.data.wmts.client;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.data.wmts.WMTSOnlineTest;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.tile.Tile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * @author ian
 * @author Emanuele Tajariol (etj at geo-solutions dot it)
 */
public class WMTSServiceTest extends WMTSOnlineTest {

    private WMTSTileService[] services = new WMTSTileService[2];

    private CoordinateReferenceSystem[] _crs = new CoordinateReferenceSystem[2];

    @BeforeClass
    public static void init() {
        Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools");
        LOGGER.setLevel(Level.FINE);
    }

    @Before
    public void setup() throws Exception {
        services[0] = createRESTService();
        services[1] = createKVPService();

        _crs[0] = CRS.decode("urn:ogc:def:crs:EPSG::3857");
        _crs[1] = CRS.decode("EPSG:3857");
    }

    @Test
    public void testScales() {

        double[][] expected = {
            {20, 5.590822640285016e8, 68247.34667369298}, // REST
            {31, 5.590822639508929E8, 68247.34667369298}
        }; // KVP
        double delta = 0.00001;
        for (int i = 0; i < services.length; i++) {
            double[] scales = services[i].getScaleList();
            String msg = services[i].getType() + "::" + services[i].getLayerName();
            assertEquals(msg, (int) expected[i][0], scales.length);
            assertEquals(msg, expected[i][1], scales[0], delta);
            assertEquals(msg, expected[i][2], scales[13], delta);
        }
    }

    @Test
    public void testCRS() throws NoSuchAuthorityCodeException, FactoryException {
        for (int i = 0; i < services.length; i++) {
            CoordinateReferenceSystem crs = services[i].getProjectedTileCrs();
            assertEquals(
                    "Mismatching CRS in " + services[i].getName(),
                    _crs[i].getName(),
                    crs.getName());
        }
    }

    @Test
    @Ignore("there is no reason to assume the selected layer is in mercator")
    public void testWebMercatorBounds() {
        ReferencedEnvelope[] expected = new ReferencedEnvelope[2];
        expected[0] = new ReferencedEnvelope(0.0, 180.0, -1.0, 0.0, _crs[0]);
        // expected[1] = new
        // ReferencedEnvelope(-180.0,180.0,-85.06,85.06,DefaultGeographicCRS.WGS84);
        expected[1] =
                new ReferencedEnvelope(
                        7.4667, 18.0339, 36.6749, 46.6564, DefaultGeographicCRS.WGS84);

        double delta = 0.001;

        for (int i = 1; i < 2; i++) { // FIXME: fix env for rest
            ReferencedEnvelope env = services[i].getBounds();
            String msg = services[i].getType() + "::" + services[i].getLayerName();
            assertEquals(msg, expected[i].getMinimum(1), env.getMinimum(1), delta);
            assertEquals(msg, expected[i].getMinimum(0), env.getMinimum(0), delta);
            assertEquals(msg, expected[i].getMaximum(1), env.getMaximum(1), delta);
            assertEquals(msg, expected[i].getMaximum(0), env.getMaximum(0), delta);
        }
    }

    @Test
    public void testFindTilesInExtent() {
        ReferencedEnvelope env =
                new ReferencedEnvelope(-80, 80, -180.0, 180.0, DefaultGeographicCRS.WGS84);
        int million = (int) 1e6;
        int scales[] = {100 * million, 25 * million, 10 * million, million, 500000};
        for (int i = 0; i < services.length; i++) {
            for (int k = 0; k < scales.length; k++) {
                Set<Tile> tiles = services[i].findTilesInExtent(env, scales[k], true, 100);
                System.out.println(tiles.size());
            }
        }
    }
}
