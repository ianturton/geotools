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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import org.geotools.data.wmts.WMTSOnlineTest;
import org.geotools.data.wmts.WebMapTileServer;
import org.geotools.data.wmts.model.WMTSLayer;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.renderer.lite.RendererUtilities;
import org.geotools.renderer.lite.StreamingRenderer;
import org.junit.Test;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/** @author ian */
public class WMTSMapLayerTest extends WMTSOnlineTest {

    private URL serverURL;

    private URL restWMTS;

    private WMTSMapLayer kvpMapLayer;

    private WMTSMapLayer restMapLayer;

    @Override
    protected void connect() throws Exception {
        serverURL = new URL(kvp_baseURL.substring(0, kvp_baseURL.indexOf('?')));
        WebMapTileServer server = new WebMapTileServer(serverURL);
        WMTSLayer wlayer = (WMTSLayer) server.getCapabilities().getLayer("topp:states");

        kvpMapLayer = new WMTSMapLayer(server, wlayer);
        restWMTS = new URL(rest_baseURL);
        WebMapTileServer server2 = new WebMapTileServer(restWMTS);
        WMTSLayer w2layer = (WMTSLayer) server2.getCapabilities().getLayer("topp:states");
        restMapLayer = new WMTSMapLayer(server2, w2layer);
    }

    /**
     * Test method for {@link org.geotools.map.WMTSMapLayer#getBounds()}.
     *
     * @throws FactoryException
     */
    @Test
    public void testGetBounds() throws FactoryException {
        ReferencedEnvelope env = kvpMapLayer.getBounds();
        checkEnv(env);
        env = restMapLayer.getBounds();
        // work out how to make MapProxy set bounds to layer size
        // checkEnv(env);
    }

    /**
     * @param env
     * @throws FactoryException
     */
    private void checkEnv(ReferencedEnvelope env) throws FactoryException {
        assertEquals(
                "wrong CRS",
                "EPSG:4326",
                CRS.lookupIdentifier(env.getCoordinateReferenceSystem(), true));
        assertEquals(env.getMinimum(0), 24.955967, 0.001);
        assertEquals(env.getMinimum(1), -134.731422, 0.001);
        assertEquals(env.getMaximum(0), 49.371735, 0.001);
        assertEquals(env.getMaximum(1), -66.969849, 0.001);
    }

    /**
     * Test method for {@link org.geotools.map.WMTSMapLayer#getCoordinateReferenceSystem()}.
     *
     * @throws FactoryException
     */
    @Test
    public void testGetCoordinateReferenceSystem() throws FactoryException {
        assertNotNull(kvpMapLayer);
        assertNotNull(restMapLayer);
        assertEquals(
                "wrong CRS",
                "EPSG:3857",
                CRS.lookupIdentifier(kvpMapLayer.getCoordinateReferenceSystem(), true));
        assertEquals(
                "wrong CRS",
                "EPSG:4326",
                CRS.lookupIdentifier(restMapLayer.getCoordinateReferenceSystem(), true));
    }

    /** Test method for {@link org.geotools.map.WMTSMapLayer#getLastGetMap()}. */
    @Test
    public void testGetLastGetMap() {
        StreamingRenderer renderer = new StreamingRenderer();
        MapContent mapContent = new MapContent();
        mapContent.addLayer(kvpMapLayer);
        renderer.setMapContent(mapContent);
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

        Rectangle paintArea = new Rectangle(0, 0, 100, 100);
        AffineTransform transform =
                RendererUtilities.worldToScreenTransform(kvpMapLayer.getBounds(), paintArea);
        renderer.paint(image.createGraphics(), paintArea, transform);
        assertNotNull(kvpMapLayer.getLastGetMap());
    }

    /**
     * Test method for {@link
     * org.geotools.map.WMTSMapLayer#isNativelySupported(org.opengis.referencing.crs.CoordinateReferenceSystem)}.
     *
     * @throws FactoryException
     * @throws NoSuchAuthorityCodeException
     */
    @Test
    public void testIsNativelySupported() throws NoSuchAuthorityCodeException, FactoryException {
        CoordinateReferenceSystem crs = CRS.decode("urn:ogc:def:crs:EPSG::3857");
        assertTrue(restMapLayer.isNativelySupported(crs));
        crs = DefaultGeographicCRS.WGS84;
        assertTrue(kvpMapLayer.isNativelySupported(crs));
        crs = CRS.decode("epsg:3857");
        // Sort out web mercator lookup
        // assertTrue(restMapLayer.isNativelySupported(crs));
        // assertTrue(kvpMapLayer.isNativelySupported(crs));
        crs = CRS.decode("epsg:27700");
        // assertFalse(restMapLayer.isNativelySupported(crs));
        assertFalse(kvpMapLayer.isNativelySupported(crs));
    }
}
