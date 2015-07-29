/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2015, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.styling;

import java.util.Map;

import org.geotools.factory.CommonFactoryFinder;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.expression.Expression;
import org.opengis.style.ContrastMethod;
import org.opengis.style.StyleVisitor;

/**
 * @author ian
 *
 */
public class Exponential implements ContrastMethod {

    FilterFactory ff = CommonFactoryFinder.getFilterFactory2();

    final static String NAME = "Exponential";

    /**
     * 
     */
    public Exponential() {
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     */
    public Exponential(FilterFactory f) {
        ff = f;
    }

    /**
     * 
     */
    public Exponential(Exponential e) {
        ff = e.getFilterFactory();
    }

    @Override
    public Expression getType() {
        return ff.literal(name());
    }

    @Override
    public Expression getAlgorithm() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Expression> getParameters() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void accept(StyleVisitor visitor) {
        // TODO Auto-generated method stub

    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public FilterFactory getFilterFactory() {
        return ff;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result /*+ ((ff == null) ? 0 : ff.hashCode())*/;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Exponential)) {
            return false;
        }
        //TODO implement if we ever have fields
        return true;
    }

}
