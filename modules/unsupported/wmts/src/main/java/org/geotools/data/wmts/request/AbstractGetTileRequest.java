/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2004-2016, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.wmts.request;

import java.awt.Dimension;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.ows.HTTPResponse;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.Response;
import org.geotools.data.ows.StyleImpl;
import org.geotools.data.wmts.TileMatrixLimits;
import org.geotools.data.wmts.TileMatrixSetLink;
import org.geotools.data.wmts.WMTSCapabilities;
import org.geotools.data.wmts.WMTSLayer;
import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.ows.ServiceException;
import org.geotools.referencing.CRS;
import org.geotools.referencing.CRS.AxisOrder;
import org.geotools.referencing.crs.DefaultEngineeringCRS;
import org.geotools.renderer.lite.RendererUtilities;
import org.geotools.tile.Tile;
import org.geotools.tile.TileService;
import org.geotools.tile.impl.ScaleZoomLevelMatcher;
import org.geotools.tile.impl.ZoomLevel;
import org.geotools.tile.impl.wmts.TileMatrix;
import org.geotools.tile.impl.wmts.TileMatrixSet;
import org.geotools.tile.impl.wmts.WMTSService;
import org.geotools.tile.impl.wmts.WMTSServiceType;
import org.geotools.tile.impl.wmts.WMTSTileFactory;
import org.geotools.util.logging.Logging;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

/**
 * 
 * @author Richard Gould
 *
 *
 * @source $URL$
 */
public abstract class AbstractGetTileRequest extends AbstractWMTSRequest implements GetTileRequest {
    /** DPI */
    private static final double DPI = 96.0;

    static WMTSTileFactory factory = new WMTSTileFactory();

    public static final String LAYER = "Layer";

    public static final String STYLE = "Style";

    public static final String TILECOL = "TileCol";

    public static final String TILEROW = "TileRow";

    public static final String TILEMATRIX = "TileMatrix";

    public static final String TILEMATRIXSET = "TileMatrixSet";

    private WMTSLayer layer = null;

    private String styleName = "";

    private String srs;

    static final Logger LOGGER = Logging.getLogger(AbstractGetTileRequest.class);

    private static final int resolution = 96;

    protected WMTSServiceType type;

    protected WMTSCapabilities capabilities;

    private int zoomLevel;

    private ReferencedEnvelope bbox;

    /**
     * Constructs a GetMapRequest. The data passed in represents valid values that can be used.
     * 
     * @param onlineResource the location that the request should be applied to
     * @param properties pre-set properties to be used. Can be null.
     */
    public AbstractGetTileRequest(URL onlineResource, Properties properties) {
        super(onlineResource, properties);
    }

    /**
     * fetch the tiles we need to generate the image
     * 
     * @throws ServiceException
     */
    public Set<Tile> getTiles() throws ServiceException {
        Set<Tile> tiles = new HashSet<>();
        if (layer == null) {
            throw new ServiceException("GetTiles called with no layer set");
        }

        String layerString = ""; //$NON-NLS-1$
        String styleString = ""; //$NON-NLS-1$

        try {
            // spaces are converted to plus signs, but must be %20 for url calls [GEOT-4317]
            layerString = URLEncoder.encode(layer.getName(), "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException | NullPointerException e) {
            layerString = layerString + layer.getName();
        }
        styleName = styleName == null ? "" : styleName;
        try {
            styleString = URLEncoder.encode(styleName, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException | NullPointerException e1) {
            styleString = styleString + styleName;
        }

        setProperty(LAYER, layerString);
        setProperty(STYLE, styleString);
        String width = properties.getProperty(WIDTH);
        String height = properties.getProperty(HEIGHT);
        if (width == null || width.isEmpty() || height == null || height.isEmpty()) {
            throw new ServiceException("Can't request TILES without width and height being set");
        }

        int w = Integer.parseInt(width);
        int h = Integer.parseInt(height);
        TileMatrixSet matrixSet = null;
        Map<String, TileMatrixSetLink> links = layer.getTileMatrixLinks();
        CoordinateReferenceSystem requestCRS;
        try {
            requestCRS = CRS.decode(srs);

            for (TileMatrixSet matrix : capabilities.getMatrixes()) {

                if (requestCRS.equals(matrix.getCoordinateReferenceSystem())) {// matching SRS
                    if (links.containsKey((matrix.getIdentifier()))) { // and available for this layer

                        setProperty(TILEMATRIXSET, matrix.getIdentifier());
                        matrixSet = matrix;

                        break;
                    }
                }
            }
        } catch (FactoryException e) {
            LOGGER.log(Level.FINER, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        if (matrixSet == null) {
            throw new ServiceException("Unable to find a matching TileMatrixSet for layer "
                    + layer.getName() + " and " + srs);
        }
        String requestUrl = onlineResource.toString();
        if (WMTSServiceType.REST.equals(type)) {
            String format = (String) getProperties().get("Format");
            if (format == null || format.isEmpty()) {
                format = "image/png";
            }
            requestUrl = layer.getTemplate(format);
        }
        TileService wmtsService = new WMTSService(requestUrl, type, layerString, styleString,
                matrixSet);

        // zoomLevel = factory.getZoomLevel(zoom, wmtsService);
        int scale = 0;

        try {
            scale = (int) Math.round(RendererUtilities.calculateScale(bbox, w, h, DPI));
        } catch (FactoryException | TransformException ex) {
            throw new RuntimeException("Failed to calculate scale", ex);
        }
        tiles = wmtsService.findTilesInExtent(bbox, scale, false, 128);

        
        Tile first = tiles.iterator().next();
        int z = first.getTileIdentifier().getZ();
        List<TileMatrixLimits> limits = layer.getTileMatrixLinks().get(matrixSet.getIdentifier())
                .getLimits();
        if (!limits.isEmpty()) {
            
            TileMatrixLimits limit = limits.get(z);
            
            ArrayList<Tile> remove = new ArrayList<>();
            for (Tile tile : tiles) {
                
                int x = tile.getTileIdentifier().getX();
                int y = tile.getTileIdentifier().getY();
                if (x < limit.getMincol() || x > limit.getMaxcol()) {
                    LOGGER.finest(x + " exceeds col limits " + limit.getMincol() + " "
                            + limit.getMaxcol());
                    remove.add(tile);
                    continue;
                }

                if (y < limit.getMinrow() || y > limit.getMaxrow()) {
                    LOGGER.finest(y + " exceeds row limits " + limit.getMinrow() + " "
                            + limit.getMaxrow());
                    remove.add(tile);
                }
            }
            tiles.removeAll(remove);
        } else {
            // seems that MapProxy (and all REST APIs?) don't create limits
            // so there is nothing we can do here?
        }
        return tiles;

    }

    protected abstract void initVersion();

    protected void initRequest() {
        setProperty(REQUEST, "GetTile"); //$NON-NLS-1$
    }

    /**
     * Sets the version number of the request.
     *
     * @param version A String indicating a WMTS Version ("1.0.0")
     */
    public void setVersion(String version) {
        properties.setProperty(VERSION, version);
    }

    public void addLayer(Layer layer, String style) {
        this.layer = (WMTSLayer) layer;
    }

    public void addLayer(Layer layer) {
        addLayer(layer, "");
    }

    public void addLayer(Layer layer, StyleImpl style) {
        if (style == null) {
            addLayer(layer, "");
        } else {
            addLayer(layer, style.getName());
        }
    }

    @Override
    public Response createResponse(HTTPResponse response) throws ServiceException, IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addLayer(String layerName, StyleImpl style) {
        for (Layer layer : capabilities.getLayerList()) {
            if (layer.getName().equalsIgnoreCase(layerName)) {
                addLayer(layer, style);
            }
        }

    }

    @Override
    public void addLayer(String layerName, String styleName) {
        for (Layer layer : capabilities.getLayerList()) {
            if (layer.getName().equalsIgnoreCase(layerName)) {
                addLayer(layer, styleName);
            }
        }
    }

    /**
     * From the Web Map Service Implementation Specification: "The required SRS parameter states which Spatial Reference System applies to the values
     * in the BBOX parameter. The value of the SRS parameter shall be on of the values defined in the character data section of an &lt;SRS> element
     * defined or inherited by the requested layer. The same SRS applies to all layers in a single request. If the WMS has declared SRS=NONE for a
     * Layer, then the Layer does not have a well-defined spatial reference system and should not be shown in conjunction with other layers. The
     * client shall specify SRS as "none" in the GetMap request and the Server may issue a Service Exception otherwise."
     *
     * @param srs A String indicating the Spatial Reference System to render the layers in.
     */
    public void setSRS(String srs) {
        this.srs = srs;
    }

    /**
     * From the Web Map Service Implementation Specification: "The required BBOX parameter allows a Client to request a particular Bounding Box. The
     * value of the BBOX parameter in a GetMap request is a list of comma-separated numbers of the form "minx,miny,maxx,maxy". If the WMS server has
     * declared that a Layer is not subsettable, then the Client shall specify exactly the declared Bounding Box values in the GetMap request and the
     * Server may issue a Service Exception otherwise."
     * <p>
     * You must also call setSRS to provide the spatial reference system information (or CRS:84 will be assumed)
     * 
     * @param bbox A string representing a bounding box in the format "minx,miny,maxx,maxy"
     */
    public void setBBox(String bbox) {
        String[] c = bbox.split(",");
        double x1 = Double.parseDouble(c[0]);
        double x2 = Double.parseDouble(c[2]);
        double y1 = Double.parseDouble(c[1]);
        double y2 = Double.parseDouble(c[3]);

        CoordinateReferenceSystem crs = toServerCRS(srs, false);

        this.bbox = new ReferencedEnvelope(y1, y2, x1, x2, crs);

    }

    public static CoordinateReferenceSystem toServerCRS(String srsName, boolean forceXY) {
        try {
            if (srsName != null) {
                if (forceXY) {
                    CoordinateReferenceSystem crs = CRS.decode(srsName, true);
                    // have we been requested a srs that cannot be forced to lon/lat?
                    if (CRS.getAxisOrder(crs) == AxisOrder.NORTH_EAST) {
                        Integer epsgCode = CRS.lookupEpsgCode(crs, false);
                        if (epsgCode == null) {
                            throw new IllegalArgumentException(
                                    "Could not find EPSG code for " + srsName);
                        }
                        return CRS.decode("EPSG:" + epsgCode, true);
                    } else {
                        return crs;
                    }
                } else if (srsName.startsWith("EPSG:")
                        && isGeotoolsLongitudeFirstAxisOrderForced()) {
                    // how do we look up the unmodified axis order?
                    String explicit = srsName.replace("EPSG:", "urn:x-ogc:def:crs:EPSG::");
                    return CRS.decode(explicit, false);
                } else {
                    return CRS.decode(srsName, false);
                }
            } else {
                return CRS.decode("CRS:84");
            }
        } catch (NoSuchAuthorityCodeException e) {
            LOGGER.log(Level.FINE, "Failed to build a coordiante reference system from " + srsName
                    + " with forceXY " + forceXY, e);
        } catch (FactoryException e) {
            LOGGER.log(Level.FINE, "Failed to build a coordiante reference system from " + srsName
                    + " with forceXY " + forceXY, e);
        }
        return DefaultEngineeringCRS.CARTESIAN_2D;
    }

    private static boolean isGeotoolsLongitudeFirstAxisOrderForced() {
        return Boolean.getBoolean(GeoTools.FORCE_LONGITUDE_FIRST_AXIS_ORDER) || GeoTools
                .getDefaultHints().get(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER) == Boolean.TRUE;
    }

    /**
     * Sets BBOX and SRS using the provided Envelope.
     */
    public void setBBox(Envelope envelope) {
        String version = properties.getProperty(VERSION);
        boolean forceXY = version == null || !version.startsWith("1.3");
        String srsName = CRS.toSRS(envelope.getCoordinateReferenceSystem());

        CoordinateReferenceSystem crs = toServerCRS(srsName, forceXY);

        try {
            bbox = new ReferencedEnvelope(CRS.transform(envelope, crs));
        } catch (TransformException e) {
            bbox = (ReferencedEnvelope) envelope;
        }
        /*
         * StringBuffer sb = new StringBuffer(); sb.append(bbox.getMinimum(0)); sb.append(","); sb.append(bbox.getMinimum(1) + ",");
         * sb.append(bbox.getMaximum(0) + ","); sb.append(bbox.getMaximum(1)); setBBox(sb.toString());
         */
    }

    /**
     * From the Web Map Service Implementation Specification: "The required FORMAT parameter states the desired format of the response to an
     * operation. Supported values for a GetMap request on a WMS instance are listed in one or more &lt;Format> elements in the
     * &;ltRequest>&lt;GetMap> element of its Capabilities XML. The entire MIME type string in &lt;Format> is used as the value of the FORMAT
     * parameter."
     *
     * @param format The desired format for the GetMap response
     */
    public void setFormat(String format) {
        properties.setProperty(FORMAT, format);
    }

    /**
     * From the Web Map Service Implementation Specification: "The required WIDTH and HEIGHT parameters specify the size in integer pixels of the map
     * image to be produced. WIDTH specifies the number of pixels to be used between the minimum and maximum X values (inclusive) in the BBOX
     * parameter, while HEIGHT specifies the number of pixels between the minimum and maximum Y values. If the WMS server has declared that a Layer
     * has fixed width and height, then the Client shall specify exactly those WIDTH and HEIGHT values in the GetMap request and the Server may issue
     * a Service Exception otherwise."
     *
     * @param width
     * @param height
     */
    public void setDimensions(String width, String height) {
        properties.setProperty(HEIGHT, height);
        properties.setProperty(WIDTH, width);
    }

    public void setDimensions(Dimension imageDimension) {
        setDimensions(imageDimension.width, imageDimension.height);
    }

    // End required parameters, being optional ones.
    // TODO Implement optional parameters.

    /**
     * From the Web Map Service Implementation Specification: "The optional TRANSPARENT parameter specifies whether the map background is to be made
     * transparent or not. The default value is false if the parameter is absent from the request."
     *
     * @param transparent true for transparency, false otherwise
     */
    public void setTransparent(boolean transparent) {
        String value = "FALSE"; //$NON-NLS-1$

        if (transparent) {
            value = "TRUE"; //$NON-NLS-1$
        }

        properties.setProperty(TRANSPARENT, value);
    }

    /**
     * Specifies the colour, in hexidecimal format, to be used as the background of the map. It is a String representing RGB values in hexidecimal
     * format, prefixed by "0x". The format is: 0xRRGGBB. The default value is 0xFFFFFF (white)
     *
     * @param bgColour the background colour of the map, in the format 0xRRGGBB
     */
    public void setBGColour(String bgColour) {
        properties.setProperty(BGCOLOR, bgColour);
    }

    /**
     * The exceptions type specifies what format the server should return exceptions in.
     * 
     * <p>
     * Valid values are:
     * 
     * <ul>
     * <li>"application/vnd.ogc.se_xml" (the default)</li>
     * <li>"application/vnd.ogc.se_inimage"</li>
     * <li>"application/vnd.ogc.se_blank"</li>
     * </ul>
     * </p>
     *
     * @param exceptions
     */
    public void setExceptions(String exceptions) {
        properties.setProperty(EXCEPTIONS, exceptions);
    }

    /**
     * See the Web Map Server Implementation Specification 1.1.1, Annexes B and C
     *
     * @param time See the Web Map Server Implementation Specification 1.1.1, Annexes B and C
     */
    public void setTime(String time) {
        properties.setProperty(TIME, time);
    }

    /**
     * See the Web Map Server Implementation Specification 1.1.1, Annex C, in particular section C.4
     *
     * @param elevation See the Web Map Server Implementation Specification 1.1.1, Annex C
     */
    public void setElevation(String elevation) {
        properties.setProperty(ELEVATION, elevation);
    }

    /**
     * See the Web Map Server Implementation Specification 1.1.1, Annex C, in particular section C.4.2
     * 
     * <p>
     * Example use: <code>request.setSampleDimensionValue("DIM_WAVELENGTH",
     * "4000");</code>
     * </p>
     *
     * @param name the request parameter name to set (usually with 'dim_' as prefix)
     * @param value the value of the request parameter (value, interval or comma-separated list)
     */
    public void setSampleDimensionValue(String name, String value) {
        properties.setProperty(name, value);
    }

    /**
     * Used to implement vendor specific parameters. Entirely optional.
     *
     * @param name a request parameter name
     * @param value a value to accompany the name
     */
    public void setVendorSpecificParameter(String name, String value) {
        properties.setProperty(name, value);
    }

    public void setDimensions(int width, int height) {
        setDimensions("" + width, "" + height);
    }

    public void setProperties(Properties p) {
        properties = p;
    }

}
