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
package org.geotools.validation.spatial;

import java.util.Map;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.validation.DefaultIntegrityValidation;
import org.geotools.validation.ValidationResults;
import org.locationtech.jts.geom.Envelope;

/**
 * LineCoveredByFeatureLineValidation purpose.
 *
 * <p>TODO No idea, fill this in.
 *
 * @author dzwiers, Refractions Research, Inc.
 * @author $Author: jive $ (last modification)
 * @version $Id$
 */
public class LineCoveredByFeatureLineValidation extends DefaultIntegrityValidation {

    /**
     * LineCoveredByFeatureLineValidation constructor.
     *
     * <p>Description
     */
    public LineCoveredByFeatureLineValidation() {
        super();
    }

    /**
     * Ensure Line is covered by the Line.
     *
     * <p>
     *
     * @param layers a HashMap of key="TypeName" value="FeatureSource"
     * @param envelope The bounding box of modified features
     * @param results Storage for the error and warning messages
     * @return True if no features intersect. If they do then the validation failed.
     * @see org.geotools.validation.IntegrityValidation#validate(java.util.Map,
     *     org.locationtech.jts.geom.Envelope, org.geotools.validation.ValidationResults)
     */
    public boolean validate(
            Map<String, SimpleFeatureSource> layers, Envelope envelope, ValidationResults results)
            throws Exception {

        // TODO Fix Me
        return false;
    }
}
