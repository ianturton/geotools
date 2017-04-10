package org.geotools.wmts.bindings;


import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;

import net.opengis.wmts.v_11.BinaryPayloadType;
import net.opengis.wmts.v_11.wmts11Factory;		

import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/wmts/1.0:BinaryPayload.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;element name="BinaryPayload" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;complexType&gt;
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
 *  	&lt;/element&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class BinaryPayloadBinding extends AbstractSimpleBinding {

	wmts11Factory factory;		
	public BinaryPayloadBinding( wmts11Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS.BinaryPayload;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return BinaryPayloadType.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {
		BinaryPayloadType payload = factory.createBinaryPayloadType();
		payload.setFormat((String)node.getChildValue("Format"));
		payload.setBinaryContent((byte[]) node.getChildValue("BinaryContent"));
		return payload;
	}

}