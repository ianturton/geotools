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

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.tile.Tile;
import org.geotools.tile.TileFactory;
import org.geotools.tile.TileFactoryTest;
import org.geotools.tile.TileService;
import org.geotools.tile.impl.WebMercatorTileFactory;
import org.junit.Assert;
import org.junit.Test;

public class WMTSTileFactory4326Test extends TileFactoryTest {
    class TestPoint {
        double lat;

        double lon;

        int zoomlevel;

        int expectedRow;

        int expectedCol;

        /**
         * @param lat
         * @param lon
         * @param zoomlevel
         * @param expectedRow
         * @param expectedCol
         */
        public TestPoint(double lat, double lon, int zoomlevel, int expectedRow,
                int expectedCol) {
            super();
            this.lat = lat;
            this.lon = lon;
            this.zoomlevel = zoomlevel;
            this.expectedRow = expectedRow;
            this.expectedCol = expectedCol;
        }

    }

    @Test
    public void testGetTileFromCoordinate() {
        int i=0;
        TileService[] services = new TileService[2];
        for (WMTSServiceType t : WMTSServiceType.values()) {
            
            services[i++] = createService(t);
        }
        TestPoint[] tests = {new TestPoint(90, -180, 2, 0, 0),new TestPoint(75, -173, 2, 0, 0),new TestPoint(90, -180, 0, 0, 0), new TestPoint(0, 0, 3, 3, 7),new TestPoint(0, 0, 2, 1, 3),new TestPoint(0, 0, 1, 0, 1),new TestPoint(50, -70, 0, 0, 0), new TestPoint(50, 70, 0, 0, 1),
                new TestPoint(-50, -70, 1, 1, 1), new TestPoint(50, 70, 1, 0, 2),new TestPoint(50, -70, 1, 0, 1), new TestPoint(-50, 70, 1, 1, 2)
                };
        for (TestPoint tp : tests) {
            for (int i1=0;i1<2;i1++) {
                TileService service = services[i1];
                // For some reason map proxy has an extra level compared to GeoServer!
                int offset = 0;
                if (((WMTSService)service).getType().equals(WMTSServiceType.REST)) {
                    offset = 1;
                }
                WMTSZoomLevel zoomLevel = ((WMTSService) service).getZoomLevel(tp.zoomlevel + offset);// new WMTSZoomLevel(1,(WMTSService) service);
                // top right
                Tile tile = factory.findTileAtCoordinate(tp.lon, tp.lat, zoomLevel, service);

                WMTSTile expectedTile = new WMTSTile(tp.expectedCol, tp.expectedRow, zoomLevel, service);
                System.out.println(tp.lat+","+ tp.lon+" expected:"+expectedTile+" got "+tile);
                Assert.assertEquals(""+tp.lat+","+ tp.lon,expectedTile.getTileIdentifier(), tile.getTileIdentifier());

            }
        }

    }

    @Test
    public void testFindRightNeighbour() {
        for (WMTSServiceType t : WMTSServiceType.values()) {
            WMTSService service = (WMTSService) createService(t);
            WMTSZoomLevel zoomLevel = service.getZoomLevel(5);
            WMTSTile tile = new WMTSTile(20, 15, zoomLevel, service);

            Tile neighbour = factory.findRightNeighbour(tile, service);
            Assert.assertNotNull(neighbour);
            // assertTrue(neighbour.getContextState().equals(ContextState.OKAY));
            WMTSTile expectedNeighbour = new WMTSTile(21, 15, zoomLevel, service);

            Assert.assertEquals(expectedNeighbour.getTileIdentifier(),
                    neighbour.getTileIdentifier());
        }
    }

    @Test
    public void testFindLowerNeighbour() {
        for (WMTSServiceType t : WMTSServiceType.values()) {
            TileService service = createService(t);
            WMTSTile tile = new WMTSTile(10, 5, new WMTSZoomLevel(5, (WMTSService) service),
                    service);

            Tile neighbour = factory.findLowerNeighbour(tile, service);

            WMTSTile expectedNeighbour = new WMTSTile(10, 6,
                    new WMTSZoomLevel(5, (WMTSService) service), service);

            Assert.assertEquals(expectedNeighbour.getTileIdentifier(),
                    neighbour.getTileIdentifier());
        }

    }

    @Test
    public void testGetExtentFromTileName() {

        for (WMTSServiceType t : WMTSServiceType.values()) {
            TileService service = createService(t);
         // For some reason map proxy has an extra level compared to GeoServer!
            int offset = 0;
            if (((WMTSService)service).getType().equals(WMTSServiceType.REST)) {
                offset = 1;
            }
            WMTSZoomLevel zoomLevel = ((WMTSService) service).getZoomLevel(1 + offset);
            WMTSTileIdentifier tileId = new WMTSTileIdentifier(1, 1,
                    zoomLevel, "SomeName");
            WMTSTile tile = new WMTSTile(tileId, service);

            ReferencedEnvelope env = WMTSTileFactory.getExtentFromTileName(tileId,service);

            Assert.assertEquals(tile.getExtent(), env);

            ReferencedEnvelope expectedEnv = new ReferencedEnvelope(-90, 0.00, -90.0,
                    0.0, DefaultGeographicCRS.WGS84);

            Assert.assertEquals(expectedEnv.getMinX(), env.getMinX(), 0.001);
            Assert.assertEquals(expectedEnv.getMinY(), env.getMinY(), 0.001);
            Assert.assertEquals(expectedEnv.getMaxX(), env.getMaxX(), 0.001);
            Assert.assertEquals(expectedEnv.getMaxY(), env.getMaxY(), 0.001);
        }
    }

    private TileService createService(WMTSServiceType type) {

        // TODO: replace with local files
        if (WMTSServiceType.REST.equals(type)) {
            String baseURL = "http://raspberrypi:9000/wmts/1.0.0/WMTSCapabilities.xml";
            return new WMTSService("states", baseURL, "states", "epsg4326", type);
        } else {
            String baseURL = "http://raspberrypi:8080/geoserver/gwc/service/wmts?REQUEST=GetCapabilities";
            return new WMTSService("states", baseURL, "states", "EPSG:4326", type);
        }

    }

    protected TileFactory createFactory() {
        return new WMTSTileFactory();
    }
}
