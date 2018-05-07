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

import static org.geotools.data.wmts.client.WMTSTileFactory4326Test.createCapabilities;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.net.URL;
import org.geotools.data.wmts.WMTSOnlineTest;
import org.geotools.data.wmts.model.TileMatrixSet;
import org.geotools.data.wmts.model.WMTSCapabilities;
import org.geotools.data.wmts.model.WMTSLayer;
import org.geotools.data.wmts.model.WMTSServiceType;
import org.geotools.tile.TileIdentifier;
import org.geotools.tile.impl.ZoomLevel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WMTSTileIdentifierTest extends WMTSOnlineTest {
    private WMTSTileService service;

    protected TileIdentifier tileId;

    @Before
    public void beforeTest() throws Exception {
        this.tileId = createTestTileIdentifier(5, 10, 12, "SomeService");
    }

    @After
    public void afterTest() {
        this.tileId = null;
    }

    protected TileIdentifier createTestTileIdentifier(int z, int x, int y, String name)
            throws Exception {
        if (service == null) {
            this.setup();
        }
        return createTestTileIdentifier(new WMTSZoomLevel(z, service), x, y, name);
    }

    @Before
    public void setup() throws Exception {
        service = createKVPService();
    }

    protected WMTSTileService createKVPService() throws Exception {
        try {
            URL capaKvp = new URL(kvp_baseURL);
            assertNotNull(capaKvp);

            WMTSCapabilities capa = createCapabilities(capaKvp);

            WMTSLayer layer = capa.getLayer("spearfish");
            TileMatrixSet matrixSet = capa.getMatrixSet("EPSG:4326");
            assertNotNull(layer);
            assertNotNull(matrixSet);

            return new WMTSTileService(kvp_baseURL, WMTSServiceType.KVP, layer, null, matrixSet);
        } catch (URISyntaxException ex) {
            fail(ex.getMessage());
            return null;
        }
    }

    @Test
    public void testGetId() {
        Assert.assertEquals("SomeService_5_10_12", this.tileId.getId());
    }

    @Test
    public void testGetCode() {
        Assert.assertEquals("5/10/12", this.tileId.getCode());
    }

    @Test
    public void testGetRightNeighbour() {
        WMTSTileIdentifier neighbour =
                new WMTSTileIdentifier(11, 12, new WMTSZoomLevel(5, service), "SomeService");

        Assert.assertEquals(neighbour, this.tileId.getRightNeighbour());
    }

    @Test
    public void testGetLowertNeighbour() {
        WMTSTileIdentifier neighbour =
                new WMTSTileIdentifier(10, 13, new WMTSZoomLevel(5, service), "SomeService");

        Assert.assertEquals(neighbour, this.tileId.getLowerNeighbour());
    }

    protected TileIdentifier createTestTileIdentifier(
            ZoomLevel zoomLevel, int x, int y, String name) {
        return new WMTSTileIdentifier(x, y, zoomLevel, name);
    }
}
