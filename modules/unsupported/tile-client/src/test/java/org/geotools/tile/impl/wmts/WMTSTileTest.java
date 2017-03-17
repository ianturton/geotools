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

import org.geotools.tile.Tile;
import org.geotools.tile.TileService;
import org.geotools.tile.impl.WebMercatorZoomLevel;
import org.geotools.tile.impl.osm.OSMService;
import org.geotools.tile.impl.osm.OSMTile;
import org.geotools.tile.impl.osm.OSMTileIdentifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ian
 *
 */
public class WMTSTileTest {

    private Tile restTile;
    private Tile kvpTile;

    @Before
    public void beforeTest() {

        String baseURL = "http://raspberrypi:9000/wmts/1.0.0/WMTSCapabilities.xml";
        TileService restService = new WMTSService("states", baseURL,"states","webmercator", WMTSServiceType.REST);
        String url = "http://raspberrypi:8080/geoserver/gwc/service/wmts?REQUEST=GetCapabilities";
        TileService kvpService = new WMTSService("states", url,"topp:states","epsg:900913", WMTSServiceType.KVP);
        WMTSTileIdentifier tileIdentifier = new WMTSTileIdentifier(10, 12,
                new WebMercatorZoomLevel(5), kvpService.getName());

        this.restTile = new WMTSTile(tileIdentifier, restService);
        this.kvpTile = new WMTSTile(tileIdentifier, kvpService);

    }

    @Test
    public void testConstructor() {

        Assert.assertNotNull(this.restTile);
        Assert.assertNotNull(this.kvpTile);

    }

    @Test
    public void testGetURL() {
        Assert.assertEquals("http://raspberrypi:8080/geoserver/gwc/service/wmts?request=getTile&tilematrixset=epsg%3A900913&TileRow=12&service=WMTS&format=image%2Fpng&style=&TileCol=10&version=1.0.0&layer=topp%3Astates&TileMatrix=epsg%3A900913%3A5&",
                this.kvpTile.getUrl().toString());

        
        
        Assert.assertEquals("http://raspberrypi:9000/wmts/states/webmercator/5/10/12.png",
                this.restTile.getUrl().toString());
    }

}
