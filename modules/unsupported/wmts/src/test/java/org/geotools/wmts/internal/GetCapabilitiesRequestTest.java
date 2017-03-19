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

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.geotools.data.ows.OperationType;
import org.junit.Before;
import org.junit.Test;

import net.opengis.ows.v_1_1_0.DCP;
import net.opengis.ows.v_1_1_0.Operation;
import net.opengis.ows.v_1_1_0.OperationsMetadata;
import net.opengis.ows.v_1_1_0.RequestMethodType;
import net.opengis.wmts.v_1_0_0.Capabilities;
import net.opengis.wmts.v_1_0_0.GetTile;

/**
 * @author ian
 *
 */
public class GetCapabilitiesRequestTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link org.geotools.wmts.internal.GetCapabilitiesRequest#getCapabilities()}.
     */
    @Test
    public void testGetCapabilities() {
        GetCapabilitiesRequest req = new GetCapabilitiesRequest("http://raspberrypi:9000/service?REQUEST=GetCapabilities&SERVICE=WMTS");
        OperationType caps = req.getCapabilities();
        assertNotNull(caps);
        System.out.println(caps.getGet());
        System.out.println(caps.getPost());
        OperationType tiles = req.getTile();
        assertNotNull(tiles);
        System.out.println(tiles.getGet());
        System.out.println(tiles.getPost());
        
    }

}
