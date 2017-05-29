/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2015, Open Source Geospatial Foundation (OSGeo)
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

import org.geotools.tile.TileIdentifier;
import org.geotools.tile.TileIdentifierTest;
import org.geotools.tile.impl.ZoomLevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WMTSTileIdentifierTest extends TileIdentifierTest {
    private WMTSService service;
    @Override
    protected TileIdentifier createTestTileIdentifier(int z, int x, int y,
            String name) {
        if(service==null) {
            this.setup();
        }
        return createTestTileIdentifier(new WMTSZoomLevel(z,service), x, y, name);
    };
    @Before
    public void setup() {
        String kvpUrl = "http://raspberrypi:8080/geoserver/gwc/service/wmts?REQUEST=GetCapabilities";
        service = new WMTSService("kvp", kvpUrl, "topp:states", "EPSG:900913",
                WMTSServiceType.KVP);
        
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
        WMTSTileIdentifier neighbour = new WMTSTileIdentifier(11, 12,
                new WMTSZoomLevel(5,service), "SomeService");

        Assert.assertEquals(neighbour, this.tileId.getRightNeighbour());
    }

    @Test
    public void testGetLowertNeighbour() {
        WMTSTileIdentifier neighbour = new WMTSTileIdentifier(10, 13,
                new WMTSZoomLevel(5,service), "SomeService");

        Assert.assertEquals(neighbour, this.tileId.getLowerNeighbour());
    }

    protected TileIdentifier createTestTileIdentifier(ZoomLevel zoomLevel,
            int x, int y, String name) {
        return new WMTSTileIdentifier(x, y, zoomLevel, name);

    }
}
