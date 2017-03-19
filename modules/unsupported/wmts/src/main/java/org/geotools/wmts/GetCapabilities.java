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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.geotools.wmts.internal.GetCapabilitiesRequest;

/**
 * @author ian
 *
 */
public class GetCapabilities {
    String requestURL = "";

    WMTSOperationType getCapabilities;

    WMTSOperationType getTile;

    WMTSOperationType getFeatureInfo;

    private GetCapabilitiesRequest req;

    /**
     * @param requestURL
     */
    public GetCapabilities(String baseURL, Map<String, String> params) {
        TreeMap<String, String> lowerParams = new TreeMap<>();
        if (!baseURL.contains("?")) {
            baseURL += "?";
        } else {
            int index = baseURL.indexOf('?');
            String[] extraParams = baseURL.substring(index + 1).split("&");
            baseURL = baseURL.substring(0, index + 1);

            for (String x : extraParams) {
                if (!x.trim().isEmpty()) {
                    String[] bits = x.split("=");
                    lowerParams.put(bits[0].toLowerCase(), bits[1]);
                }
            }
        }

        if (params != null) {

            for (Entry<String, String> e : params.entrySet()) {
                lowerParams.put(e.getKey().toLowerCase(), e.getValue());
            }
        }
        if (!lowerParams.containsKey("service")) {
            lowerParams.put("service", "wmts");
        }
        if (!lowerParams.containsKey("request")) {
            lowerParams.put("request", "GetCapabilities");
        }
        if(!lowerParams.containsKey("version")) {
            lowerParams.put("version", "1.0.0");
        }
        this.requestURL = baseURL + generateParamString(lowerParams);

    }

    private void fetchCapabilities() {
        req = new GetCapabilitiesRequest(requestURL);
        getCapabilities = req.getCapabilities();
        getTile = req.getTile();
        getFeatureInfo = req.getGetFeatureInfo();
    }

    /**
     * @param params
     * @return
     */
    private String generateParamString(SortedMap<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> e : params.entrySet()) {
            try {
                builder.append(e.getKey() + "=" + URLEncoder.encode(e.getValue(), "UTF-8"));
                builder.append('&');
            } catch (UnsupportedEncodingException e1) {

            }
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    /**
     * @return the requestURL
     */
    public String getRequestURL() {
        return requestURL;
    }

    /**
     * @param requestURL the requestURL to set
     */
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    /**
     * @return the getCapabilities
     */
    public WMTSOperationType getGetCapabilities() {
        if (req == null) {
            fetchCapabilities();
        }
        return getCapabilities;
    }

    /**
     * @return the getTile
     */
    public WMTSOperationType getGetTile() {
        if (req == null) {
            fetchCapabilities();
        }
        return getTile;
    }

    /**
     * @return the getFeatureInfo
     */
    public WMTSOperationType getGetFeatureInfo() {
        if (req == null) {
            fetchCapabilities();
        }
        return getFeatureInfo;
    }

    public List<WMTSLayer> getLayers(){
        if(req==null) {
            fetchCapabilities();
        }
        return req.getLayers();
    }
}
