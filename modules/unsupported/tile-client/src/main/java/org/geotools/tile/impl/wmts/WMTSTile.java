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

import java.net.MalformedURLException;
import java.net.URL;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.tile.Tile;
import org.geotools.tile.TileIdentifier;
import org.geotools.tile.TileService;
import org.geotools.tile.impl.WebMercatorTileFactory;
import org.geotools.tile.impl.ZoomLevel;
import org.geotools.tile.impl.osm.OSMTileIdentifier;
import org.geotools.util.UnsupportedImplementationException;

import com.google.common.base.Ticker;

/**
 * @author ian
 *
 */
public class WMTSTile extends Tile {
    public enum Types {
        KVP, REST
    }

    Types type = Types.REST;

    public static final int DEFAULT_TILE_SIZE = 256;

    private WMTSService service;

    /**
     * @return the type
     */
    public Types getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Types type) {
        this.type = type;
    }

    /**
     * @param tileId
     * @param env
     * @param tileSize
     */
    public WMTSTile(TileIdentifier tileId, ReferencedEnvelope env, int tileSize) {
        super(tileId, env, tileSize);

    }

    public WMTSTile(int x, int y, ZoomLevel zoomLevel, TileService service) {
        this(new WMTSTileIdentifier(x, y, zoomLevel, service.getName()), service);
    }

    /**
     * @param tileIdentifier
     * @param service
     */
    public WMTSTile(WMTSTileIdentifier tileIdentifier, TileService service) {
        this(tileIdentifier, WebMercatorTileFactory.getExtentFromTileName(tileIdentifier),
                DEFAULT_TILE_SIZE);
        this.service = (WMTSService) service;
    }

    String template = "http://raspberrypi:9000/wmts/states/{TileMatrixSet}/{TileMatrix}/{TileCol}/{TileRow}.png";

    @Override
    public URL getUrl() {
        if (Types.KVP == type) {
            throw new UnsupportedImplementationException("KVP is not supported yet.");
        } else if (Types.REST == type) {
            // fill in template from identifier http://raspberrypi:9000/wmts/states/webmercator/5/5/11.png
            String baseUrl = new String(service.getTemplateURL());
            baseUrl = baseUrl.replace("{TileMatrixSet}", service.getTileMatrixSetName());
            TileIdentifier tileIdentifier = getTileIdentifier();
            baseUrl = baseUrl.replace("{TileMatrix}", "" + tileIdentifier.getZ());
            baseUrl = baseUrl.replace("{TileCol}", "" + tileIdentifier.getX());
            baseUrl = baseUrl.replace("{TileRow}", "" + tileIdentifier.getY());
            System.out.println("requesting " + tileIdentifier.getCode());
            try {
                return new URL(baseUrl);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

}
