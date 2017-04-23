package org.geotools.wmts.bindings;

import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;

import net.opengis.ows11.BoundingBoxType;
import net.opengis.ows11.CodeType;
import net.opengis.wmts.v_11.TileMatrixSetType;
import net.opengis.wmts.v_11.TileMatrixType;
import net.opengis.wmts.v_11.wmts11Factory;

import java.net.URI;
import java.util.List;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_TileMatrixSet.
 *
 * <p>
 * 
 * <pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_TileMatrixSet" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  			&lt;complexContent&gt;
 *  				&lt;extension base="ows:DescriptionType"&gt;
 *  					&lt;sequence&gt;
 *  						&lt;element ref="ows:Identifier"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;Tile matrix set identifier&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  						&lt;element minOccurs="0" ref="ows:BoundingBox"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;
 *  									Minimum bounding rectangle surrounding 
 *  									the visible layer presented by this tile matrix 
 *  									set, in the supported CRS &lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  						&lt;element ref="ows:SupportedCRS"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;Reference to one coordinate reference 
 *  								system (CRS).&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  						&lt;element minOccurs="0" name="WellKnownScaleSet" type="anyURI"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;Reference to a well known scale set.
 *  									urn:ogc:def:wkss:OGC:1.0:GlobalCRS84Scale, 
 *  									urn:ogc:def:wkss:OGC:1.0:GlobalCRS84Pixel, 
 *  									urn:ogc:def:wkss:OGC:1.0:GoogleCRS84Quad and 
 *  									urn:ogc:def:wkss:OGC:1.0:GoogleMapsCompatible are 
 *  								possible values that are defined in Annex E. It has to be consistent with the 
 *  								SupportedCRS and with the ScaleDenominators of the TileMatrix elements.
 *  								&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  						&lt;element maxOccurs="unbounded" ref="wmts:TileMatrix"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;Describes a scale level and its tile matrix.&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  					&lt;/sequence&gt;
 *  				&lt;/extension&gt;
 *  			&lt;/complexContent&gt;
 *  		&lt;/complexType&gt; 
 *		
 *	  </code>
 * </pre>
 * </p>
 *
 * @generated
 */
public class _TileMatrixSetBinding extends AbstractComplexBinding {

    wmts11Factory factory;

    public _TileMatrixSetBinding(wmts11Factory factory) {
        super();
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WMTS._TileMatrixSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Class<TileMatrixSetType> getType() {
        return TileMatrixSetType.class;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    @SuppressWarnings("unchecked")
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        TileMatrixSetType matrixSet = factory.createTileMatrixSetType();
        matrixSet.setBoundingBox((BoundingBoxType) node.getChildValue("BoundingBox"));
        matrixSet.setIdentifier((CodeType) node.getChildValue("Identifier"));
        matrixSet.setSupportedCRS(((URI) node.getChildValue("SupportedCRS")).toString());
        matrixSet.setWellKnownScaleSet((String) node.getChildValue("WellKnownScaleSet"));
        matrixSet.getAbstract().addAll(node.getChildren("abstract"));
        List<Node> children = node.getChildren("TileMatrix");
        for(Node c:children) {
            matrixSet.getTileMatrix().add((TileMatrixType) c.getValue());
        }

        return matrixSet;
    }

}