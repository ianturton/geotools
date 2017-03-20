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
package org.geotools.wmts.internal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.geotools.data.ows.HTTPResponse;
import org.geotools.data.ows.SimpleHttpClient;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.tile.impl.wmts.WMTSServiceType;
import org.geotools.wmts.TileMatrixLimit;
import org.geotools.wmts.WMTSLayer;
import org.geotools.wmts.WMTSOperationType;

import net.opengis.ows.v_1_1_0.BoundingBoxType;
import net.opengis.ows.v_1_1_0.DCP;
import net.opengis.ows.v_1_1_0.DatasetDescriptionSummaryBaseType;
import net.opengis.ows.v_1_1_0.DomainType;
import net.opengis.ows.v_1_1_0.LanguageStringType;
import net.opengis.ows.v_1_1_0.Operation;
import net.opengis.ows.v_1_1_0.OperationsMetadata;
import net.opengis.ows.v_1_1_0.RequestMethodType;
import net.opengis.ows.v_1_1_0.ValueType;
import net.opengis.ows.v_1_1_0.WGS84BoundingBoxType;
import net.opengis.wmts.v_1_0_0.Capabilities;
import net.opengis.wmts.v_1_0_0.LayerType;
import net.opengis.wmts.v_1_0_0.Style;
import net.opengis.wmts.v_1_0_0.TileMatrix;
import net.opengis.wmts.v_1_0_0.TileMatrixLimits;
import net.opengis.wmts.v_1_0_0.TileMatrixSet;
import net.opengis.wmts.v_1_0_0.TileMatrixSetLimits;
import net.opengis.wmts.v_1_0_0.TileMatrixSetLink;
import net.opengis.wmts.v_1_0_0.URLTemplateType;

/**
 * Wrapper to hide the JAXB
 * 
 * Make a WMTS get capabilities request.
 * 
 * @author ian
 *
 */
public class GetCapabilitiesRequest {
    private static final Logger LOGGER = Logger
            .getLogger("org.geotools.wmts.internal.GetCapabilitiesRequest");

    Capabilities caps;

    String url = "";

    public GetCapabilitiesRequest(String request) {
        url = request;
        SimpleHttpClient client = new SimpleHttpClient();
        try {
            HTTPResponse resp = client.get(new URL(url));
            JAXBContext ctx = JAXBContext
                    .newInstance(new Class[] { net.opengis.wmts.v_1_0_0.Capabilities.class });
            Unmarshaller um = ctx.createUnmarshaller();
            caps = (Capabilities) um.unmarshal(resp.getResponseStream());

        } catch (IOException | JAXBException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }

    public List<WMTSLayer> getLayers() {
        ArrayList<WMTSLayer> ret = new ArrayList<>();
        for (JAXBElement<DatasetDescriptionSummaryBaseType> l : caps.getContents()
                .getDatasetDescriptionSummary()) {
            if (l.getName().getLocalPart().equalsIgnoreCase("Layer")) {

                LayerType layer = (LayerType) l.getValue();
                String name = layer.getIdentifier().getValue();
                HashSet<String> styles = new HashSet<>();
                String defaultStyle = "";
                for (Style s : layer.getStyle()) {
                    
                    String styleName = s.getIdentifier().getValue();
                    
                    styles.add(styleName);
                    if(s.isIsDefault()) {
                        defaultStyle =styleName; 
                    }
                }
                Set<String> matrixes = new HashSet<>();
                WMTSLayer wLayer = new WMTSLayer(name, styles, matrixes);
                wLayer.setStyle(defaultStyle);
                List<WGS84BoundingBoxType> boundingBox = layer.getWGS84BoundingBox();
                if (boundingBox.size() > 0) {
                    BoundingBoxType bbox = boundingBox.get(0);
                    ReferencedEnvelope bounds = new ReferencedEnvelope(
                            bbox.getLowerCorner().get(0).doubleValue(),
                            bbox.getLowerCorner().get(1).doubleValue(),
                            bbox.getUpperCorner().get(0).doubleValue(),
                            bbox.getUpperCorner().get(1).doubleValue(), DefaultGeographicCRS.WGS84);
                    wLayer.setBounds(bounds);
                }

                for (TileMatrixSetLink tmsl : layer.getTileMatrixSetLink()) {
                    wLayer.getTileMatrixSetNames().add(tmsl.getTileMatrixSet());
                    ArrayList<TileMatrixLimit> limits = new ArrayList<>();
                    TileMatrixSetLimits tileMatrixSetLimits = tmsl.getTileMatrixSetLimits();
                    if (tileMatrixSetLimits != null) {
                        for (TileMatrixLimits tmsLimit : tileMatrixSetLimits
                                .getTileMatrixLimits()) {
                            String id = tmsLimit.getTileMatrix();
                            TileMatrixLimit limit = new TileMatrixLimit();
                            limit.setId(id);
                            limit.setMinTileCol(tmsLimit.getMinTileCol().intValue());
                            limit.setMinTileRow(tmsLimit.getMinTileRow().intValue());
                            limit.setMaxTileCol(tmsLimit.getMaxTileCol().intValue());
                            limit.setMaxTileRow(tmsLimit.getMaxTileRow().intValue());
                            limits.add(limit);
                        }
                        wLayer.setLimits(tmsl.getTileMatrixSet(), limits);
                    }
                }
                for(URLTemplateType rUrl:layer.getResourceURL()) {
                    if(rUrl.getResourceType().equalsIgnoreCase("tile")){
                        if(rUrl.isSetFormat()) {
                            wLayer.setFormat(rUrl.getFormat());
                        }
                        wLayer.setTemplate(rUrl.getTemplate());
                    }
                }
                for (LanguageStringType title : layer.getTitle()) {
                    wLayer.setAbstract(title.getValue());
                    break;
                    // TODO: handle other languages
                }
                for (LanguageStringType abs : layer.getAbstract()) {
                    wLayer.setAbstract(abs.getValue());
                    break;
                    // TODO: handle other languages
                }
                ret.add(wLayer);
            }
            for (TileMatrixSet ts : caps.getContents().getTileMatrixSet()) {
                org.geotools.tile.impl.wmts.TileMatrixSet tms = new org.geotools.tile.impl.wmts.TileMatrixSet();
                tms.setIdentifier(ts.getIdentifier().getValue());
                tms.setCRS(ts.getSupportedCRS());
                ArrayList<org.geotools.tile.impl.wmts.TileMatrix> matrices = new ArrayList<>();
                for (TileMatrix mat : ts.getTileMatrix()) {
                    org.geotools.tile.impl.wmts.TileMatrix tm = new org.geotools.tile.impl.wmts.TileMatrix();
                    tm.setIdentifier(mat.getIdentifier().getValue());
                    tm.setDenominator(mat.getScaleDenominator());
                    tm.setMatrixHeight(mat.getMatrixHeight().intValue());
                    tm.setMatrixWidth(mat.getMatrixWidth().intValue());
                    tm.setTileHeight(mat.getTileHeight().intValue());
                    tm.setTileWidth(mat.getMatrixWidth().intValue());
                    tm.setTopLeft(mat.getTopLeftCorner().get(0).doubleValue(),
                            mat.getTopLeftCorner().get(1).doubleValue());
                    matrices.add(tm);
                }
                tms.setMatrices(matrices);
            }
        }

        return ret;
    }

    public WMTSOperationType getCapabilities() {
        return getOperation("GetCapabilities");
    }

    public WMTSOperationType getTile() {
        return getOperation("GetTile");
    }

    public WMTSOperationType getGetFeatureInfo() {
        return getOperation("GetFeatureInfo");
    }

    private WMTSOperationType getOperation(String name) {
        WMTSOperationType ret = new WMTSOperationType();
        OperationsMetadata ops = caps.getOperationsMetadata();
        if (ops == null) {
            //probably a RESTFul service
            return null;
        } else {
            for (Operation op : ops.getOperation()) {
                if (op.getName().equalsIgnoreCase(name)) {

                    for (DCP dcp : op.getDCP()) {
                        List<JAXBElement<RequestMethodType>> methods = dcp.getHTTP().getGetOrPost();
                        for (JAXBElement<RequestMethodType> method : methods) {
                            RequestMethodType value = method.getValue();
                            String type = method.getName().getLocalPart();

                            if ("Get".equalsIgnoreCase(type)) {
                                try {
                                    ret.setGet(new URL(value.getHref()));
                                } catch (MalformedURLException e) {
                                    LOGGER.log(Level.WARNING, e.getMessage(), e);
                                }
                            } else if ("Post".equalsIgnoreCase(type)) {
                                try {
                                    ret.setPost(new URL(value.getHref()));
                                } catch (MalformedURLException e) {
                                    LOGGER.log(Level.WARNING, e.getMessage(), e);
                                }
                            }

                            for (DomainType cons : value.getConstraint()) {
                                for (Object v : cons.getAllowedValues().getValueOrRange()) {
                                    if (v instanceof ValueType) {
                                        if (((ValueType) v).getValue().equalsIgnoreCase("KVP")) {
                                            ret.setType(WMTSServiceType.KVP);
                                        }
                                    }
                                }
                            }

                        }

                    }
                    break;
                }
            }
            return ret;
        }
    }
}
