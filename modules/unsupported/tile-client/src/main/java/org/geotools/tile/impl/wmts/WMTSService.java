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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.tile.TileFactory;
import org.geotools.tile.TileService;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * A tile service for WMTS servers.
 * 
 * @author ian
 *
 */
public class WMTSService extends TileService {
    private static final TileFactory tileFactory = new WMTSTileFactory();

    private static final String OWS = "http://www.opengis.net/ows/1.1";

    private String tileMatrixSetName = "";

    private double[] scaleList;

    private TileMatrixSet matrixSet;

    private String layerName;

    private ReferencedEnvelope envelope;

    private String templateURL;

    /**
     * 
     * @param name - a name to refer to the layer by.
     * @param baseURL - the templated ResourceURL from the getcapabilities document.
     */
    public WMTSService(String name, String baseURL, String layerName, String tileMatrixSetName) {
        super(name, baseURL);
        setLayerName(layerName);
        setTileMatrixSetName(tileMatrixSetName);

    }

    /**
     * @param layerName
     */
    private void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /**
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }

    @Override
    public double[] getScaleList() {
        return scaleList;
    }

    @Override
    public ReferencedEnvelope getBounds() {

        if (envelope != null) {
            return envelope;
        }
        // Look this up from the CRS
        Extent extent = this.getProjectedTileCrs().getDomainOfValidity();
        Iterator<? extends GeographicExtent> itr = extent.getGeographicElements().iterator();
        while (itr.hasNext()) {
            GeographicExtent ex = itr.next();
            if (ex instanceof GeographicBoundingBox) {
                envelope = new ReferencedEnvelope();
                envelope.expandToInclude(
                        new Coordinate(((GeographicBoundingBox) ex).getEastBoundLongitude(),
                                ((GeographicBoundingBox) ex).getNorthBoundLatitude()));
                envelope.expandToInclude(
                        new Coordinate(((GeographicBoundingBox) ex).getWestBoundLongitude(),
                                ((GeographicBoundingBox) ex).getSouthBoundLatitude()));
                return envelope;
            }

        }
        return null;
    }

    @Override
    public CoordinateReferenceSystem getProjectedTileCrs() {
        try {
            return matrixSet.getCoordinateReferenceSystem();
        } catch (FactoryException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public TileFactory getTileFactory() {
        return tileFactory;
    }

    /**
     * @return the tileMatrixSetName
     */
    public String getTileMatrixSetName() {
        return tileMatrixSetName;
    }

    /**
     * @param tileMatrixSetName the tileMatrixSetName to set
     */
    public void setTileMatrixSetName(String tileMatrixSetName) {
        if (tileMatrixSetName == null || tileMatrixSetName.isEmpty()) {
            throw new IllegalArgumentException("Tile matrix set name cannot be null");
        }

        this.tileMatrixSetName = tileMatrixSetName;
        extractTileMatrixSet();
    }

    /**
     * Extract the scales, bbox etc from capabilities.
     */
    private void extractTileMatrixSet() {

        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(getBaseUrl());
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            // System.out.println(new String(responseBody,"UTF-8"));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();

            org.w3c.dom.Document doc = db.parse(new ByteArrayInputStream(responseBody));

            // read layer info

            NodeList layers = doc.getElementsByTagName("Layer");
            for (int i = 0; i < layers.getLength(); i++) {
                Element element = (Element) layers.item(i);
                NodeList names = element.getElementsByTagNameNS("http://www.opengis.net/ows/1.1",
                        "Identifier");
                if (layerName.equalsIgnoreCase(names.item(0).getTextContent())) {
                    envelope = new ReferencedEnvelope(DefaultGeographicCRS.WGS84);

                    Element layer = (Element) layers.item(i);
                    Node wgsbbox = layer.getElementsByTagNameNS(OWS, "WGS84BoundingBox").item(0);
                    NodeList bbox = wgsbbox.getChildNodes();
                    for (int j = 0; j < bbox.getLength(); j++) {
                        if (!(bbox.item(j) instanceof Element))
                            continue;
                        Element e = (Element) bbox.item(j);

                        if (e.getLocalName().equals("LowerCorner")
                                || e.getLocalName().equals("UpperCorner")) {
                            String coords[] = e.getTextContent().split(" ");

                            envelope.expandToInclude(Double.parseDouble(coords[0]),
                                    Double.parseDouble(coords[1]));
                        }
                    }
                    Element resource = (Element) layer.getElementsByTagName("ResourceURL").item(0);
                    templateURL = resource.getAttribute("template");
                }
            }

            NodeList tms = doc.getElementsByTagName("TileMatrixSet");
            for (int i = 0; i < tms.getLength(); i++) {
                Element e = (Element) tms.item(i);
                NodeList names = e.getElementsByTagNameNS("http://www.opengis.net/ows/1.1",
                        "Identifier");
                if (names.getLength() == 0) { // annoyingly TileMatrixSet tag is used in layers too!
                    continue;
                }

                if (tileMatrixSetName.equalsIgnoreCase(names.item(0).getTextContent())) {
                    matrixSet = TileMatrixSet.parseTileMatrixSet(e);
                    scaleList = new double[matrixSet.size()];
                    int j = 0;
                    for (TileMatrix tm : matrixSet.getMatrices()) {
                        scaleList[j++] = tm.getDenominator();
                    }
                }
            }
            // TODO: Tidy these exceptions up and throw something
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
    }

    /**
     * @return the templateURL
     */
    public String getTemplateURL() {
        return templateURL;
    }

    /**
     * @param templateURL the templateURL to set
     */
    public void setTemplateURL(String templateURL) {
        this.templateURL = templateURL;
    }

    /**
     * @param zoomLevel
     * @return
     */
    public TileMatrix getTileMatrix(int zoomLevel) {
        return matrixSet.getMatrices().get(zoomLevel);
    }

}
