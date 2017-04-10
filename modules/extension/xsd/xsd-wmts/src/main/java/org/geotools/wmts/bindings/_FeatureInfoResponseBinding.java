package org.geotools.wmts.bindings;


import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;

import net.opengis.wmts.v_11.wmts11Factory;		

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_FeatureInfoResponse.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_FeatureInfoResponse" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  			&lt;choice&gt;
 *  				&lt;element ref="gml:_FeatureCollection"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;
 *  							This allows to define any FeatureCollection that is a substitutionGroup 
 *  							of gml:_GML and use it here. A Geography Markup Language GML 
 *  							Simple Features Profile level 0 response format is strongly 
 *  							recommended as a FeatureInfo response.
 *  						&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element ref="wmts:TextPayload"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;
 *  							This allows to include any text format that is not a gml:_FeatureCollection 
 *  							like HTML, TXT, etc
 *  						&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element ref="wmts:BinaryPayload"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;
 *  							This allows to include any binary format. Binary formats are not 
 *  							common response for a GeFeatureInfo requests but possible for 
 *  							some imaginative implementations.
 *  						&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="AnyContent" type="anyType"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;
 *  							This allows to include any XML content that it is not any of 
 *  							the previous ones.
 *  						&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  			&lt;/choice&gt;
 *  		&lt;/complexType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class _FeatureInfoResponseBinding extends AbstractComplexBinding {

	wmts11Factory factory;		
	public _FeatureInfoResponseBinding( wmts11Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS._FeatureInfoResponse;
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