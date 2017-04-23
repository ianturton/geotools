/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.geotools.data.ows.CRSEnvelope;
import org.geotools.data.ows.Capabilities;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.OperationType;
import org.geotools.tile.impl.wmts.TileMatrix;
import org.geotools.tile.impl.wmts.TileMatrixSet;
import org.geotools.tile.impl.wmts.WMTSServiceType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

import net.opengis.ows11.WGS84BoundingBoxType;
import net.opengis.ows11.AllowedValuesType;
import net.opengis.ows11.BoundingBoxType;
import net.opengis.ows11.DCPType;
import net.opengis.ows11.DomainType;
import net.opengis.ows11.LanguageStringType;
import net.opengis.ows11.OperationsMetadataType;
import net.opengis.ows11.RequestMethodType;
import net.opengis.ows11.ValueType;
import net.opengis.wmts.v_11.CapabilitiesType;
import net.opengis.wmts.v_11.ContentsType;
import net.opengis.wmts.v_11.LayerType;
import net.opengis.wmts.v_11.TileMatrixLimitsType;
import net.opengis.wmts.v_11.TileMatrixSetLimitsType;
import net.opengis.wmts.v_11.TileMatrixSetLinkType;
import net.opengis.wmts.v_11.TileMatrixSetType;
import net.opengis.wmts.v_11.TileMatrixType;
import net.opengis.wmts.v_11.URLTemplateType;

/**
 * Represents a base object for a WMTS getCapabilities response.
 *
 * @author Richard Gould, Refractions Research
 * @author Ian Turton
 *
 * @source $URL$
 */
public class WMTSCapabilities extends Capabilities {
    private WMTSRequest request;

    private GeometryFactory gf = new GeometryFactory();

    CapabilitiesType caps;

    private Map<String, Layer> layerMap = new HashMap<>();

    private List<Layer> layers = new ArrayList<Layer>(); // cache

    private String[] exceptions = new String[0];

    private List<TileMatrixSet> matrixes = new ArrayList<>();

    private WMTSServiceType type;

    /**
     * @param object
     */
    public WMTSCapabilities(CapabilitiesType capabilities) {
        caps = capabilities;
        setService(new WMTSService(caps.getServiceIdentification()));
        setVersion(caps.getServiceIdentification().getServiceTypeVersion().get(0).toString());
        ContentsType contents = caps.getContents();

        for (Object l : contents.getDatasetDescriptionSummary()) {

            if (l instanceof LayerType) {
                LayerType layerType = (LayerType) l;

                String title = (String) ((LanguageStringType) layerType.getTitle().get(0))
                        .getValue().toString();

                WMTSLayer layer = new WMTSLayer(title);
                layer.setName(layerType.getIdentifier().getValue());

                EList<TileMatrixSetLinkType> tmsl = layerType.getTileMatrixSetLink();
                for (int i = 0; i < tmsl.size(); i++) {
                    TileMatrixSetLinkType t = tmsl.get(i);
                    TileMatrixSetLink tms = new TileMatrixSetLink();
                    tms.setIdentifier(t.getTileMatrixSet());
                    TileMatrixSetLimitsType limits = t.getTileMatrixSetLimits();
                    if (limits != null) {
                        for (TileMatrixLimitsType tmlt : limits.getTileMatrixLimits()) {
                            TileMatrixLimits tml = new TileMatrixLimits();
                            tml.setTileMatix(tmlt.getTileMatrix());
                            tml.setMinCol(tmlt.getMinTileCol().longValue());
                            tml.setMaxCol(tmlt.getMaxTileCol().longValue());
                            tml.setMinRow(tmlt.getMinTileRow().longValue());
                            tml.setMaxRow(tmlt.getMaxTileRow().longValue());
                            tms.addLimit(tml);
                        }

                    }
                    layer.addTileMatrixLink(tms);

                }
                layer.getFormats().addAll(layerType.getFormat());
                layer.getInfoFormats().addAll(layerType.getInfoFormat());
                @SuppressWarnings("unchecked")
                EList<BoundingBoxType> bboxes = layerType.getBoundingBox();
                Map<String, CRSEnvelope> boundingBoxes = new HashMap<>();
                for (BoundingBoxType bbox : bboxes) {
                    boundingBoxes.put(bbox.getCrs(),
                            new CRSEnvelope(bbox.getCrs(),
                                    ((Double) bbox.getLowerCorner().get(0)).doubleValue(),
                                    ((Double) bbox.getLowerCorner().get(1)).doubleValue(),
                                    ((Double) bbox.getUpperCorner().get(0)).doubleValue(),
                                    ((Double) bbox.getUpperCorner().get(1)).doubleValue()));
                }
                WGS84BoundingBoxType wgsBBox = (WGS84BoundingBoxType) layerType
                        .getWGS84BoundingBox().get(0);
                if (wgsBBox != null) {
                    boundingBoxes.put("EPSG:4326",
                            new CRSEnvelope("EPSG:4326",
                                    ((Double) wgsBBox.getLowerCorner().get(0)).doubleValue(),
                                    ((Double) wgsBBox.getLowerCorner().get(1)).doubleValue(),
                                    ((Double) wgsBBox.getUpperCorner().get(0)).doubleValue(),
                                    ((Double) wgsBBox.getUpperCorner().get(1)).doubleValue()));
                }
                layer.setBoundingBoxes(boundingBoxes);

                EList<URLTemplateType> resourceURL = layerType.getResourceURL();
                if (resourceURL != null && !resourceURL.isEmpty()) {
                    for (URLTemplateType resource : resourceURL) {
                        String template = resourceURL.get(0).getTemplate();
                        String format = resourceURL.get(0).getFormat();
                        //layer.formats.add(format);
                        
                        layer.putResourceURL(format,template);
                    }

                }

                layers.add(layer);
                layerMap.put(layer.getName(), layer);
            }

        }
        for (TileMatrixSetType tm : contents.getTileMatrixSet()) {
            TileMatrixSet matrixSet = new TileMatrixSet();
            matrixSet.setCRS(tm.getSupportedCRS());
            matrixSet.setIdentifier(tm.getIdentifier().getValue());
            for (TileMatrixType mat : tm.getTileMatrix()) {
                TileMatrix matrix = new TileMatrix();

                matrix.setIdentifier(mat.getIdentifier().getValue());
                matrix.setDenominator(mat.getScaleDenominator());
                matrix.setMatrixHeight(mat.getMatrixHeight().intValue());
                matrix.setMatrixWidth(mat.getMatrixWidth().intValue());
                matrix.setTileHeight(mat.getTileHeight().intValue());
                matrix.setTileWidth(mat.getTileWidth().intValue());
                List<Double> c = mat.getTopLeftCorner();
                // TODO: May need to revisit if axis order is an issue
                matrix.setTopLeft(gf.createPoint(
                        new Coordinate(c.get(0).doubleValue(), c.get(1).doubleValue())));
                matrixSet.addMatrix(matrix);
            }
            matrixes.add(matrixSet);
        }

        // set layer SRS
        Set<String> srs = new TreeSet<>();
        for (TileMatrixSet tms : matrixes) {

            srs.add(tms.getCrs());
        }
        for (Layer l : layers) {
            l.setSrs(srs);
        }

        request = new WMTSRequest();
        OperationsMetadataType operationsMetadata = caps.getOperationsMetadata();
        setType(WMTSServiceType.REST);
        if (operationsMetadata != null) {
            for (Object op : operationsMetadata.getOperation()) {
                OperationType opt = new OperationType();
                net.opengis.ows11.OperationType opx = (net.opengis.ows11.OperationType) op;

                EList dcps = opx.getDCP();
                for (int i = 0; i < dcps.size(); i++) {
                    DCPType dcp = (DCPType) dcps.get(i);

                    EList gets = dcp.getHTTP().getGet();
                    for (int j = 0; j < gets.size(); j++) {
                        RequestMethodType get = (RequestMethodType) gets.get(j);
                        try {
                            opt.setGet(new URL(get.getHref()));
                            if (!get.getConstraint().isEmpty()) {
                                for (Object con : get.getConstraint()) {
                                    DomainType dt = (DomainType) con;
                                    AllowedValuesType t = dt.getAllowedValues();
                                    for (Object v : t.getValue()) {
                                        ValueType vt = (ValueType) v;
                                        if (vt.getValue().equalsIgnoreCase("KVP")) {
                                            setType(WMTSServiceType.KVP);
                                        } else if (vt.getValue().equalsIgnoreCase("REST")) {
                                            setType(WMTSServiceType.REST);
                                        }
                                    }
                                }
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    EList posts = dcp.getHTTP().getPost();
                    for (int j = 0; j < posts.size(); j++) {
                        RequestMethodType post = (RequestMethodType) posts.get(j);
                        try {
                            opt.setPost(new URL(post.getHref()));
                            if (!post.getConstraint().isEmpty()) {
                                for (Object con : post.getConstraint()) {
                                    DomainType dt = (DomainType) con;
                                    AllowedValuesType t = dt.getAllowedValues();
                                    for (Object v : t.getValue()) {
                                        ValueType vt = (ValueType) v;
                                        if (vt.getValue().equalsIgnoreCase("KVP")) {
                                            setType(WMTSServiceType.KVP);
                                        } else if (vt.getValue().equalsIgnoreCase("REST")) {
                                            setType(WMTSServiceType.REST);
                                        }
                                    }
                                }
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (opx.getName().equalsIgnoreCase("GetCapabilities")) {
                    request.setGetCapabilities(opt);
                } else if (opx.getName().equalsIgnoreCase("GetTile")) {

                    request.setGetTile(opt);
                } else if (opx.getName().equalsIgnoreCase("GetFeatureInfo")) {
                    request.setGetFeatureInfo(opt);
                }
            }
        }

    }

    /**
     * @param rest
     */
    private void setType(WMTSServiceType rest) {
        this.type = rest;

    }

    /**
     * @return the type
     */
    public WMTSServiceType getType() {
        return type;
    }

    /**
     * Access a flat view of the layers available in the WMS.
     * <p>
     * The information available here is the same as doing a top down walk of all the layers available via getLayer().
     * 
     * @return List of all available layers
     */
    public List<Layer> getLayerList() {
        return Collections.unmodifiableList(layers);
    }

    private void addChildrenRecursive(List<Layer> layers, Layer layer) {
        if (layer.getChildren() != null) {
            for (Layer child : layer.getChildren()) {
                layers.add(child);
                addChildrenRecursive(layers, child);
            }
        }
    }

    /**
     * The request contains information about possible Requests that can be made against this server, including URLs and formats.
     *
     * @return Returns the request.
     */
    public WMTSRequest getRequest() {
        return request;
    }

    /**
     * @param request The request to set.
     */
    public void setRequest(WMTSRequest request) {
        this.request = request;
    }

    /**
     * Exceptions declare what kind of formats this server can return exceptions in. They are used during subsequent requests.
     */
    public String[] getExceptions() {
        return exceptions;
    }

    public void setExceptions(String[] exceptions) {
        this.exceptions = exceptions;
    }

    /**
     * @return the layers
     */
    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * @param layers the layers to set
     */
    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    /**
     * @return the matrixes
     */
    public List<TileMatrixSet> getMatrixes() {
        return matrixes;
    }

    /**
     * @param matrixes the matrixes to set
     */
    public void setMatrixes(List<TileMatrixSet> matrixes) {
        this.matrixes = matrixes;
    }

    /**
     * @param string
     * @return
     */
    public Layer getLayer(String name) {
        // TODO Auto-generated method stub
        return layerMap.get(name);
    }
}
