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
package org.geotools.map;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.media.jai.ImageLayout;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridGeometry2D;
import org.geotools.coverage.grid.io.AbstractGridCoverage2DReader;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.OverviewPolicy;
import org.geotools.data.ResourceInfo;
import org.geotools.data.ServiceInfo;
import org.geotools.data.wmts.Layer;
import org.geotools.data.wmts.WMTSServer;
import org.geotools.geometry.GeneralEnvelope;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.coverage.grid.Format;
import org.opengis.coverage.grid.GridEnvelope;
import org.opengis.geometry.Envelope;
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterValue;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.MathTransform;

import it.geosolutions.imageio.maskband.DatasetLayout;

/**
 * Provide a map layer by making (parallel) WMTS tile requests to the Server provided.
 * 
 * @author ian
 *
 */
public class WMTSCoverageReader extends AbstractGridCoverage2DReader {
    String format;
    Layer layer;
    WMTSServer server;
    /**
     * The last GetMap response
     */
    GridCoverage2D grid;
    private String tms;
    /**
     * @param server
     * @param layer
     */
    public WMTSCoverageReader(WMTSServer server, Layer layer) {
        setServer(server);
        setLayer(layer);
    }

    /**
     * @param layer
     */
    private void setLayer(Layer layer) {
        this.layer = layer;
    }

    /**
     * @param server
     */
    private void setServer(WMTSServer server) {
        this.server = server;
    }

    @Override
    public Format getFormat() {
        // this reader has no backing format
        return null;
    }

    @Override
    public GridCoverage2D read(GeneralParameterValue[] parameters)
            throws IllegalArgumentException, IOException {
        // try to get request params from the request
        Envelope requestedEnvelope = null;
        int width = -1;
        int height = -1;
        Color backgroundColor = null;
        if (parameters != null) {
            for (GeneralParameterValue param : parameters) {
                final ReferenceIdentifier name = param.getDescriptor().getName();
                if (name.equals(AbstractGridFormat.READ_GRIDGEOMETRY2D.getName())) {
                    final GridGeometry2D gg = (GridGeometry2D) ((ParameterValue) param)
                            .getValue();
                    requestedEnvelope = gg.getEnvelope();
                    // the range high value is the highest pixel included in the raster,
                    // the actual width and height is one more than that
                    width = gg.getGridRange().getHigh(0) + 1;
                    height = gg.getGridRange().getHigh(1) + 1;
                } else if(name.equals(AbstractGridFormat.BACKGROUND_COLOR.getName())) {
                    backgroundColor = (Color)  ((ParameterValue) param).getValue();
                }
            }
        }

        // fill in a reasonable default if we did not manage to get the params
        if (requestedEnvelope == null) {
            requestedEnvelope = getOriginalEnvelope();
            width = 640;
            height = (int) Math.round(requestedEnvelope.getSpan(1)
                    / requestedEnvelope.getSpan(0) * width);
        }

        // if the structure did not change reuse the same response
        if (grid != null && grid.getGridGeometry().getGridRange2D().getWidth() == width
                && grid.getGridGeometry().getGridRange2D().getHeight() == height
                && grid.getEnvelope().equals(requestedEnvelope))
            return grid;

        grid = getTiles(reference(requestedEnvelope), width, height, backgroundColor);
        return grid;
    }

    /**
     * @param reference
     * @param width
     * @param height
     * @param backgroundColor
     * @return
     */
    private GridCoverage2D getTiles(ReferencedEnvelope reference, int width, int height,
            Color backgroundColor) {
        //calc zoom level
        server.getTileMatrix(tms);
        //determine required tiles
        //stick tiles into image and return
        
        return null;
    }

    /**
     * Converts a {@link Envelope} into a {@link ReferencedEnvelope}
     * 
     * @param envelope
     * @return
     */
    ReferencedEnvelope reference(Envelope envelope) {
        ReferencedEnvelope env = new ReferencedEnvelope(envelope.getCoordinateReferenceSystem());
        env.expandToInclude(envelope.getMinimum(0), envelope.getMinimum(1));
        env.expandToInclude(envelope.getMaximum(0), envelope.getMaximum(1));
        return env;
    }

    /**
     * Converts a {@link GeneralEnvelope} into a {@link ReferencedEnvelope}
     * 
     * @param ge
     * @return
     */
    ReferencedEnvelope reference(GeneralEnvelope ge) {
        return new ReferencedEnvelope(ge.getMinimum(0), ge.getMaximum(0), ge.getMinimum(1), ge
                .getMaximum(1), ge.getCoordinateReferenceSystem());
    }
    
    @Override
    public String[] getMetadataNames() {
        return new String[] { REPROJECTING_READER };
    }
    
    @Override
    public String getMetadataValue(String name) {
        if(REPROJECTING_READER.equals(name)) {
            return "true";
        }
        return super.getMetadataValue(name);
    }

}
