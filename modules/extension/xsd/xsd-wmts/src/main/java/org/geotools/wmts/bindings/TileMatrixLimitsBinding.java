package org.geotools.wmts.bindings;


import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;

import net.opengis.wmts.v_11.TileMatrixLimitsType;
import net.opengis.wmts.v_11.wmts11Factory;

import java.math.BigInteger;

import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/wmts/1.0:TileMatrixLimits.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;element name="TileMatrixLimits" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;annotation&gt;
 *  			&lt;documentation&gt;Metadata describing the limits of a TileMatrix 
 *  						for this layer.&lt;/documentation&gt;
 *  		&lt;/annotation&gt;
 *  		&lt;complexType&gt;
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
 *  	&lt;/element&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class TileMatrixLimitsBinding extends AbstractSimpleBinding {

	wmts11Factory factory;		
	public TileMatrixLimitsBinding( wmts11Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS.TileMatrixLimits;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return TileMatrixLimitsType.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {
		
	    TileMatrixLimitsType limits = factory.createTileMatrixLimitsType();
	    
	    limits.setMaxTileCol((BigInteger) node.getChildValue("MaxTileCol"));
	    limits.setMaxTileRow((BigInteger) node.getChildValue("MaxTileRow"));
	    limits.setMinTileCol((BigInteger) node.getChildValue("MinTileCol"));
	    limits.setMinTileRow((BigInteger) node.getChildValue("MinTileRow"));
	    limits.setTileMatrix((String) node.getChildValue("TileMatrix"));
	    
	    return limits;
		
	}

}