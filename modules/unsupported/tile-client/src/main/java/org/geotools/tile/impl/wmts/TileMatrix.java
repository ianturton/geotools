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

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class TileMatrix {
    static private final GeometryFactory gf = new GeometryFactory();

    String identifier;

    double denominator;

    double resolution;

    double pixelWidth = 0.00028;

    Point topLeft;

    int tileWidth = 256;

    int tileHeight = 256;

    int matrixWidth;

    int matrixHeight;

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the denominator
     */
    public double getDenominator() {
        return denominator;
    }

    public double getResolution() {
        return resolution;
    }

    /**
     * @param denominator the denominator to set
     */
    public void setDenominator(double denominator) {
        this.denominator = denominator;
        resolution = denominator * pixelWidth;
    }

    /**
     * @return the topLeft
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * @param topLeft the topLeft to set
     */
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * @return the tileWidth
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * @param tileWidth the tileWidth to set
     */
    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    /**
     * @return the tileHeight
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * @param tileHeight the tileHeight to set
     */
    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    /**
     * @return the matrixWidth
     */
    public int getMatrixWidth() {
        return matrixWidth;
    }

    /**
     * @param matrixWidth the matrixWidth to set
     */
    public void setMatrixWidth(int matrixWidth) {
        this.matrixWidth = matrixWidth;
    }

    /**
     * @return the matrixHeight
     */
    public int getMatrixHeight() {
        return matrixHeight;
    }

    /**
     * @param matrixHeight the matrixHeight to set
     */
    public void setMatrixHeight(int matrixHeight) {
        this.matrixHeight = matrixHeight;
    }

    public static TileMatrix parse(Element e) {
        TileMatrix ret = new TileMatrix();
        NodeList kids = e.getChildNodes();
        for (int i = 0; i < kids.getLength(); i++) {
            if (!(kids.item(i) instanceof Element)) {
                continue;
            }
            Element kid = (Element) kids.item(i);
            String tagName = kid.getLocalName();
            if (tagName.equalsIgnoreCase("Identifier")) {
                ret.setIdentifier(kid.getTextContent());
            } else if (tagName.equalsIgnoreCase("ScaleDenominator")) {
                ret.setDenominator(Double.parseDouble(kid.getTextContent()));
            } else if (tagName.equalsIgnoreCase("TopLeftCorner")) {
                String[] part = kid.getTextContent().split(" ");
                double x = Double.parseDouble(part[0]);
                double y = Double.parseDouble(part[1]);
                Point tl = gf.createPoint(new Coordinate(x, y));
                ret.setTopLeft(tl);
            } else if (tagName.equalsIgnoreCase("TileWidth")) {
                ret.setTileWidth(Integer.parseInt(kid.getTextContent()));
            } else if (tagName.equalsIgnoreCase("TileHeight")) {
                ret.setTileHeight(Integer.parseInt(kid.getTextContent()));
            } else if (tagName.equalsIgnoreCase("MatrixWidth")) {
                ret.setMatrixWidth(Integer.parseInt(kid.getTextContent()));
            } else if (tagName.equalsIgnoreCase("MatrixHeight")) {
                ret.setMatrixHeight(Integer.parseInt(kid.getTextContent()));
            }
        }
        return ret;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getIdentifier()).append("\t").append(getDenominator()).append("\t")
                .append(getResolution()).append("\t");
        sb.append(getTopLeft()).append("\t");
        sb.append(getTileWidth()).append("x").append(getTileHeight()).append("\n");
        return sb.toString();
    }

    /**
     * @param doubleValue
     * @param doubleValue2
     */
    public void setTopLeft(double x, double y) {
        topLeft = gf.createPoint(new Coordinate(x,y));
    }
}
