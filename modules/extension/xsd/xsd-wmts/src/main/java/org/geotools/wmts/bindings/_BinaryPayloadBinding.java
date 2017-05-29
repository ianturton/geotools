package org.geotools.wmts.bindings;


import javax.xml.namespace.QName;

import org.geotools.wmts.WMTS;
import org.geotools.xml.AbstractComplexBinding;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;

import net.opengis.wmts.v_1.wmtsv_1Factory;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_BinaryPayload.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_BinaryPayload" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  			&lt;sequence&gt;
 *  				&lt;element name="Format" type="ows:MimeType"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;
 *  							MIMEType format of the PayloadContent 
 *  							once base64 decodified.
 *  						&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element name="BinaryContent" type="base64Binary"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;
 *  							Binary content encoded in base64. It could be useful to 
 *  							enclose it in a CDATA element to avoid XML parsing.
 *  						&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  			&lt;/sequence&gt;
 *  		&lt;/complexType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class _BinaryPayloadBinding extends AbstractComplexBinding {

	wmtsv_1Factory factory;		
	public _BinaryPayloadBinding( wmtsv_1Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS._BinaryPayload;
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