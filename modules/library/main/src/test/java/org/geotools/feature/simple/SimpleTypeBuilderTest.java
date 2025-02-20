/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.feature.simple;

import java.util.Collections;
import junit.framework.TestCase;
import org.geotools.feature.NameImpl;
import org.geotools.feature.type.FeatureTypeFactoryImpl;
import org.geotools.feature.type.SchemaImpl;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.Schema;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class SimpleTypeBuilderTest extends TestCase {

    static final String URI = "gopher://localhost/test";

    SimpleFeatureTypeBuilder builder;

    protected void setUp() throws Exception {
        Schema schema = new SchemaImpl("test");

        FeatureTypeFactoryImpl typeFactory = new FeatureTypeFactoryImpl();
        AttributeType pointType =
                typeFactory.createGeometryType(
                        new NameImpl("test", "pointType"),
                        Point.class,
                        null,
                        false,
                        false,
                        Collections.emptyList(),
                        null,
                        null);
        schema.put(new NameImpl("test", "pointType"), pointType);

        AttributeType intType =
                typeFactory.createAttributeType(
                        new NameImpl("test", "intType"),
                        Integer.class,
                        false,
                        false,
                        Collections.emptyList(),
                        null,
                        null);
        schema.put(new NameImpl("test", "intType"), intType);

        builder = new SimpleFeatureTypeBuilder(new FeatureTypeFactoryImpl());
        builder.setBindings(schema);
    }

    public void testSanity() {
        builder.setName("testName");
        builder.setNamespaceURI("testNamespaceURI");
        builder.add("point", Point.class, (CoordinateReferenceSystem) null);
        builder.add("integer", Integer.class);

        SimpleFeatureType type = builder.buildFeatureType();
        assertNotNull(type);

        assertEquals(2, type.getAttributeCount());

        AttributeType t = type.getType("point");
        assertNotNull(t);
        assertEquals(Point.class, t.getBinding());

        t = type.getType("integer");
        assertNotNull(t);
        assertEquals(Integer.class, t.getBinding());

        t = type.getGeometryDescriptor().getType();
        assertNotNull(t);
        assertEquals(Point.class, t.getBinding());
    }

    public void testCRS() {
        builder.setName("testName");
        builder.setNamespaceURI("testNamespaceURI");

        builder.setCRS(DefaultGeographicCRS.WGS84);
        builder.crs(null).add("point", Point.class);
        builder.add("point2", Point.class, DefaultGeographicCRS.WGS84);
        builder.setDefaultGeometry("point");
        SimpleFeatureType type = builder.buildFeatureType();
        assertEquals(DefaultGeographicCRS.WGS84, type.getCoordinateReferenceSystem());

        assertNull(type.getGeometryDescriptor().getType().getCoordinateReferenceSystem());
        assertEquals(
                DefaultGeographicCRS.WGS84,
                ((GeometryType) type.getType("point2")).getCoordinateReferenceSystem());
    }

    public void testAttributeDefaultValue() {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("buggy");
        builder.nillable(false).defaultValue(12).add("attrWithDefault", Integer.class);
        builder.nillable(true).defaultValue(null).add("attrWithoutDefault", Integer.class);
        SimpleFeatureType featureType = builder.buildFeatureType();
        assertFalse(featureType.getDescriptor("attrWithDefault").isNillable());
        assertEquals(12, featureType.getDescriptor("attrWithDefault").getDefaultValue());
        assertTrue(featureType.getDescriptor("attrWithoutDefault").isNillable());
        assertNull(featureType.getDescriptor("attrWithoutDefault").getDefaultValue());
    }

    public void testMaintainDefaultGeometryOnRetype() {
        builder.setName("testGeometries");
        builder.add("geo1", Point.class, DefaultGeographicCRS.WGS84);
        builder.add("geo2", Polygon.class, DefaultGeographicCRS.WGS84);
        builder.setDefaultGeometry("geo1");
        SimpleFeatureType type = builder.buildFeatureType();

        // performing an attribute selection, even changing order, should not change
        // the default geometry, that had a special meaning in the original source
        SimpleFeatureType retyped =
                SimpleFeatureTypeBuilder.retype(type, new String[] {"geo2", "geo1"});
        assertEquals("geo1", retyped.getGeometryDescriptor().getLocalName());
    }

    public void testRetypeGeometryless() {
        builder.setName("testGeometryless");
        builder.add("geo1", Point.class, DefaultGeographicCRS.WGS84);
        builder.add("integer", Integer.class);
        builder.setDefaultGeometry("geo1");
        SimpleFeatureType type = builder.buildFeatureType();

        // performing an attribute selection, even changing order, should not change
        // the default geometry, that had a special meaning in the original source
        SimpleFeatureType retyped = SimpleFeatureTypeBuilder.retype(type, new String[] {"integer"});
        assertNotNull(retyped);
        assertNull(retyped.getGeometryDescriptor());
        assertEquals(1, retyped.getAttributeCount());
        assertEquals("integer", retyped.getAttributeDescriptors().get(0).getLocalName());
    }

    public void testInitGeometryless() {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("testGeometryless");
        builder.add("integer", Integer.class);
        SimpleFeatureType type1 = builder.buildFeatureType();

        builder = new SimpleFeatureTypeBuilder();
        builder.init(type1);
        SimpleFeatureType type2 = builder.buildFeatureType();

        assertEquals(type1, type2);
    }

    public void testMaintainDefaultGeometryOnInit() {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("testGeometries");
        builder.add("geo1", Point.class, DefaultGeographicCRS.WGS84);
        builder.add("geo2", Polygon.class, DefaultGeographicCRS.WGS84);
        builder.setDefaultGeometry("geo2");
        SimpleFeatureType type1 = builder.buildFeatureType();

        builder = new SimpleFeatureTypeBuilder();
        builder.init(type1);
        SimpleFeatureType type2 = builder.buildFeatureType();

        assertEquals("geo2", type1.getGeometryDescriptor().getLocalName());
        assertEquals("geo2", type2.getGeometryDescriptor().getLocalName());
    }

    public void testRemoveDefaultGeometryAfterInit() {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("testGeometries");
        builder.add("geo1", Point.class, DefaultGeographicCRS.WGS84);
        builder.add("geo2", Polygon.class, DefaultGeographicCRS.WGS84);
        builder.setDefaultGeometry("geo2");
        SimpleFeatureType type1 = builder.buildFeatureType();

        builder = new SimpleFeatureTypeBuilder();
        builder.init(type1);
        builder.remove("geo2");
        SimpleFeatureType type2 = builder.buildFeatureType();

        assertEquals("geo2", type1.getGeometryDescriptor().getLocalName());
        assertEquals("geo1", type2.getGeometryDescriptor().getLocalName());
    }
}
