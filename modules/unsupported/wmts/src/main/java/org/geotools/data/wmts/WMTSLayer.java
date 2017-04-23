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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.ows.Layer;

/**
 * @author ian
 *
 */
public class WMTSLayer extends Layer{
    Map<String,TileMatrixSetLink> limits = new HashMap<>();
    List<String> formats = new ArrayList<>();
    List<String> infoFormats = new ArrayList<>();
    Map<String, String> templates = new HashMap<>();
    
    /**
     * @param title
     */
    public WMTSLayer(String title) {
        super(title);
    }
    /**
     * @return the limits
     */
    public Map<String, TileMatrixSetLink> getTileMatrixLinks() {
        return limits;
    }
    /**
     * @param limitList
     */
    public void addTileMatrixLinks(List<TileMatrixSetLink> limitList) {
         
        for(TileMatrixSetLink limit:limitList) {
            limits.put(limit.getIdentifier(),limit);
        }
        
    }
    
    public void addTileMatrixLink(TileMatrixSetLink link) {
        limits.put(link.getIdentifier(),link);
        
    }
    /**
     * @return the formats
     */
    public List<String> getFormats() {
        return formats;
    }
    /**
     * @param formats the formats to set
     */
    public void setFormats(List<String> formats) {
        this.formats = formats;
    }
    /**
     * @return the infoFormats
     */
    public List<String> getInfoFormats() {
        return infoFormats;
    }
    /**
     * @param infoFormats the infoFormats to set
     */
    public void setInfoFormats(List<String> infoFormats) {
        this.infoFormats = infoFormats;
    }
    
    /**
     * @param format
     * @param template
     */
    public void putResourceURL(String format, String template) {
        templates.put(format,template);
        
    }
    /**
     * @param object
     * @return
     */
    public String getTemplate(String key) {
       
        return templates.get(key);
    }

}
