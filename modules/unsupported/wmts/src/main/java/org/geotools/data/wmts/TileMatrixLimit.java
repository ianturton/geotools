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

/**
 * @author ian
 *
 */
public class TileMatrixLimit {
    String id;
    int minTileRow,maxTileRow,minTileCol,maxTileCol;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the minTileRow
     */
    public int getMinTileRow() {
        return minTileRow;
    }
    /**
     * @param minTileRow the minTileRow to set
     */
    public void setMinTileRow(int minTileRow) {
        this.minTileRow = minTileRow;
    }
    /**
     * @return the maxTileRow
     */
    public int getMaxTileRow() {
        return maxTileRow;
    }
    /**
     * @param maxTileRow the maxTileRow to set
     */
    public void setMaxTileRow(int maxTileRow) {
        this.maxTileRow = maxTileRow;
    }
    /**
     * @return the minTileCol
     */
    public int getMinTileCol() {
        return minTileCol;
    }
    /**
     * @param minTileCol the minTileCol to set
     */
    public void setMinTileCol(int minTileCol) {
        this.minTileCol = minTileCol;
    }
    /**
     * @return the maxTileCol
     */
    public int getMaxTileCol() {
        return maxTileCol;
    }
    /**
     * @param maxTileCol the maxTileCol to set
     */
    public void setMaxTileCol(int maxTileCol) {
        this.maxTileCol = maxTileCol;
    }
}
