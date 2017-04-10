package org.geotools.wmts.bindings;


import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;

import net.opengis.wmts.v_11.wmts11Factory;		

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_GetTile.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_GetTile" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  			&lt;sequence&gt;
 *  				&lt;element name="Layer" type="string"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;A layer identifier has to be referenced&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="Style" type="string"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;A style identifier has to be referenced.&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="Format" type="ows:MimeType"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Output format of the tile&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element maxOccurs="unbounded" minOccurs="0" ref="wmts:DimensionNameValue"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Dimension name and value&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="TileMatrixSet" type="string"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;A TileMatrixSet identifier has to be referenced&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="TileMatrix" type="string"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;A TileMatrix identifier has to be referenced&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="TileRow" type="nonNegativeInteger"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Row index of tile matrix&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="TileCol" type="nonNegativeInteger"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Column index of tile matrix&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  			&lt;/sequence&gt;
 *  			&lt;attribute fixed="WMTS" name="service" type="string" use="required"/&gt;
 *  			&lt;attribute fixed="1.0.0" name="version" type="string" use="required"/&gt;
 *  		&lt;/complexType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class _GetTileBinding extends AbstractComplexBinding {

	wmts11Factory factory;		
	public _GetTileBinding( wmts11Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS._GetTile;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return null;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {
		
		//TODO: implement and remove call to super
		return super.parse(instance,node,value);
	}

}