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

import java.util.logging.Logger;

import org.geotools.tile.Tile;
import org.geotools.tile.TileFactory;
import org.geotools.tile.TileService;
import org.geotools.tile.impl.ZoomLevel;
import org.geotools.util.logging.Logging;

/**
 * Implementation of TileFactory for WMTS
 * 
 * @author ian
 *
 */
public class WMTSTileFactory2 extends TileFactory {
    private static final Logger LOGGER = Logging
            .getLogger(WMTSTileFactory2.class.getPackage().getName());

    /**
     * 
     */
    public WMTSTileFactory2() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Tile findTileAtCoordinate(double lon, double lat, ZoomLevel zoomLevel,
            TileService service) {
        WMTSZoomLevel zl = (WMTSZoomLevel) zoomLevel;
        TileMatrix tileMatrix = ((WMTSService) service).getMatrixSet().getMatrices()
                .get(zl.getZoomLevel());
        TileMatrixLimits tileMatrixLimits = ((WMTSService) service).getLimits()
                .get(zl.getZoomLevel());
        long matrixWidth = (tileMatrixLimits.getMaxcol() - tileMatrixLimits.getMincol());
        long matrixHeight = (tileMatrixLimits.getMaxrow() - tileMatrixLimits.getMinrow());
        double tileSpanY = (tileMatrix.getTileHeight() * tileMatrix.getResolution());
        double tileSpanX = (tileMatrix.getTileWidth() * tileMatrix.getResolution());
        double tileMatrixMinX = tileMatrix.getTopLeft().getX();
        double tileMatrixMaxY = tileMatrix.getTopLeft().getY();
        // to compensate for floating point computation inaccuracies
        double epsilon = 1e-6;
        long xTile = (int) Math.floor((lon - tileMatrixMinX) / tileSpanX + epsilon);
        long yTile = (int) Math.floor((tileMatrixMaxY -(90.0 - lat)) / tileSpanY + epsilon);
        // to avoid requesting out-of-range tiles
        if (xTile < 0)
            xTile = 0;
        if (xTile >= matrixWidth)
            xTile = matrixWidth - 1;
        if (yTile < 0)
            yTile = 0;
        if (yTile >= matrixHeight)
            yTile = matrixHeight - 1;

        LOGGER.fine("fetching tile: " + xTile + " " + yTile + " " + zoomLevel.getZoomLevel());
        System.out.println("tile at " + lat + "," + lon + " is z:" + zoomLevel + " x:" + xTile
                + " y:" + yTile);
        return new WMTSTile((int) xTile, (int) yTile, zoomLevel, service);
    }

    @Override
    public ZoomLevel getZoomLevel(int zoomLevel, TileService service) {
        return new WMTSZoomLevel(zoomLevel, (WMTSService) service);
    }

    @Override
    public Tile findRightNeighbour(Tile tile, TileService service) {

        return new WMTSTile((WMTSTileIdentifier) tile.getTileIdentifier().getRightNeighbour(),
                service);
    }

    @Override
    public Tile findLowerNeighbour(Tile tile, TileService service) {

        return new WMTSTile((WMTSTileIdentifier) tile.getTileIdentifier().getLowerNeighbour(),
                service);
    }

}
