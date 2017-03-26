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
package org.geotools.data.wmts;

import java.util.HashMap;
import java.util.Map;

import org.geotools.tile.impl.wmts.TileMatrix;
import org.geotools.tile.impl.wmts.TileMatrixSet;

/**
 * @author ian
 *
 */
public class WMTSServer {
    GetCapabilities capabilities;
    /**
     * 
     */
    public WMTSServer(HashMap<String, String> map) {
        capabilities = new GetCapabilities(map);
        WMTSOperationType getTile = capabilities.getGetTile();
        getTile.getType();
    }

    public TileMatrixSet getTileMatrix(String setName) {
        return capabilities.getTileMatracies().get(setName);
    }
 
    public Map<String, TileMatrixSet> getTileMatrixForCRS(String crs) {
        return capabilities.getTileMatracies(crs);
    }
}
