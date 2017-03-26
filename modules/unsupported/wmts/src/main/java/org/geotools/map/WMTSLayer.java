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

import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.data.wmts.Layer;
import org.geotools.data.wmts.WMTSServer;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.RasterSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.opengis.parameter.GeneralParameterValue;

/**
 * @author ian
 *
 */
public class WMTSLayer extends GridReaderLayer{
    /**
     * The default raster style
     */
    static Style STYLE;
    static {
        StyleFactory factory = CommonFactoryFinder.getStyleFactory(null);
        RasterSymbolizer symbolizer = factory.createRasterSymbolizer();

        Rule rule = factory.createRule();
        rule.symbolizers().add(symbolizer);

        FeatureTypeStyle type = factory.createFeatureTypeStyle();
        type.rules().add(rule);

        STYLE = factory.createStyle();
        STYLE.featureTypeStyles().add(type);
    }

    /**
     * @param reader
     * @param style
     * @param title
     * @param params
     */
    public WMTSLayer(WMTSServer server, Layer layer) {
        super(new WMTSCoverageReader(server, layer), STYLE);
        // TODO Auto-generated constructor stub
    }



    
}
