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
package org.geotools.wmts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.geotools.data.wms.SimpleLayer;
import org.geotools.geometry.jts.ReferencedEnvelope;

/**
 * @author ian
 *
 */
public class WMTSLayer extends SimpleLayer {
    Set<String> tileMatrixSetNames = new HashSet<>();
    HashMap<String,List<TileMatrixLimit>> limits = new HashMap<String, List<TileMatrixLimit>>();
    ReferencedEnvelope bounds;
    String title = "";
    String _abstract = "";
    private String format = "image/png";
    private String template = "";
    /**
     * SimpleLayer creation.
     *
     * @param name Name of layer
     * @param style Name of style, null indicates default.
     */
    public WMTSLayer(String name, String style, Set<String> tileMatrixSetNames) {
        super(name, style);
        setTileMatrixSetNames(tileMatrixSetNames);
    }

    /**
     * @param name
     * @param validStyles
     */
    public WMTSLayer(String name, Set<String> validStyles, Set<String> tileMatrixSetNames) {
        super(name, validStyles);
        setTileMatrixSetNames(tileMatrixSetNames);
    }

    /**
     * @return the tileMatrixSets
     */
    public Set<String> getTileMatrixSetNames() {
        return tileMatrixSetNames;
    }

    /**
     * @param tileMatrixSets the tileMatrixSets to set
     */
    public void setTileMatrixSetNames(Set<String> tileMatrixSets) {
        this.tileMatrixSetNames = tileMatrixSets;
    }

    /**
     * @return the bounds
     */
    public ReferencedEnvelope getBounds() {
        return bounds;
    }

    /**
     * @param bounds the bounds to set
     */
    public void setBounds(ReferencedEnvelope bounds) {
        this.bounds = bounds;
    }

    /**
     * @return the limits
     */
    public List<TileMatrixLimit> getLimits(String name) {
        return limits.get(name);
    }

    /**
     * @param limits the limits to set
     */
    public void setLimits(String name, List<TileMatrixLimit> limits) {
        this.limits.put(name,limits);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * @param _abstract the _abstract to set
     */
    public void setAbstract(String text) {
        this._abstract = text;
    }

    @Override
    public String toString() {
        return "WMTSLayer [" + (getName() != null ? "name: " + getName() + ", " : "")
                + (getStyle() != null ? "Style = " + getStyle() + ", ": "default style, ")
                + (getValidStyles() != null ? "getValidStyles()=" + getValidStyles() + ", ": ", ")
                + (bounds != null ? "bounds=" + bounds + ", " : "")
                + (tileMatrixSetNames != null ? "tileMatrixSets=" + tileMatrixSetNames + ", " : "") + "]";
    }

    /**
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
        
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param template
     */
    public void setTemplate(String template) {
       this.template =template;
        
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

}
