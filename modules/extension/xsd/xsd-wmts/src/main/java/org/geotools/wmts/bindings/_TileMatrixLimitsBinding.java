package org.geotools.wmts.bindings;

import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;

import net.opengis.wmts.v_11.TileMatrixLimitsType;
import net.opengis.wmts.v_11.wmts11Factory;

import java.math.BigInteger;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_TileMatrixLimits.
 *
 * <p>
 * 
 * <pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_TileMatrixLimits" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  			&lt;sequence&gt;
 *  				&lt;element name="TileMatrix" type="string"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Reference to a TileMatrix identifier&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="MinTileRow" type="positiveInteger"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Minimum tile row index valid for this 
 *  						layer. From 0 to maxTileRow&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="MaxTileRow" type="positiveInteger"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Maximim tile row index valid for this 
 *  						layer. From minTileRow to matrixWidth-1 of the tileMatrix 
 *  						section of this tileMatrixSet&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="MinTileCol" type="positiveInteger"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Minimum tile column index valid for this 
 *  						layer. From 0 to maxTileCol&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="MaxTileCol" type="positiveInteger"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Maximim tile column index valid for this layer. 
 *  						From minTileCol to tileHeight-1 of the tileMatrix section 
 *  						of this tileMatrixSet.&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  			&lt;/sequence&gt;
 *  		&lt;/complexType&gt; 
 *		
 *	  </code>
 * </pre>
 * </p>
 *
 * @generated
 */
public class _TileMatrixLimitsBinding extends AbstractComplexBinding {

    wmts11Factory factory;

    public _TileMatrixLimitsBinding(wmts11Factory factory) {
        super();
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WMTS._TileMatrixLimits;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Class getType() {
        return TileMatrixLimitsType.class;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        TileMatrixLimitsType limits = factory.createTileMatrixLimitsType();

        String intValue = ((Integer)node.getChildValue("MaxTileCol")).toString();
        limits.setMaxTileCol(new BigInteger(intValue));
        intValue = ((Integer)node.getChildValue("MaxTileRow")).toString();
        limits.setMaxTileRow(new BigInteger(intValue));
        intValue = ((Integer)node.getChildValue("MinTileCol")).toString();
        limits.setMinTileCol( new BigInteger(intValue));
        intValue = ((Integer)node.getChildValue("MinTileRow")).toString();
        limits.setMinTileRow(new BigInteger(intValue));
        limits.setTileMatrix((String) node.getChildValue("TileMatrix"));

        return limits;
    }

}