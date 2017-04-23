package org.geotools.wmts.bindings;


import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;

import net.opengis.wmts.v_11.LegendURLType;
import net.opengis.wmts.v_11.wmts11Factory;

import java.math.BigInteger;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_LegendURL.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_LegendURL" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  			&lt;complexContent&gt;
 *  				&lt;extension base="ows:OnlineResourceType"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;The URL from which the legend image can be retrieved&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  					&lt;attribute name="format" type="ows:MimeType"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;A supported output format for the legend image&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/attribute&gt;
 *  					&lt;attribute name="minScaleDenominator" type="double"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;Denominator of the minimum scale (inclusive) for which this legend image is valid&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/attribute&gt;
 *  					&lt;attribute name="maxScaleDenominator" type="double"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;Denominator of the maximum scale (exclusive) for which this legend image is valid&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/attribute&gt;
 *  					&lt;attribute name="width" type="positiveInteger"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;Width (in pixels) of the legend image&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/attribute&gt;
 *  					&lt;attribute name="height" type="positiveInteger"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;Height (in pixels) of the legend image&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/attribute&gt;
 *  				&lt;/extension&gt;
 *  				&lt;!--/attributeGroup--&gt;
 *  			&lt;/complexContent&gt;
 *  		&lt;/complexType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class _LegendURLBinding extends AbstractComplexBinding {

	wmts11Factory factory;		
	public _LegendURLBinding( wmts11Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS._LegendURL;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return LegendURLType.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {
	    LegendURLType legendURL = factory.createLegendURLType();
            legendURL.setFormat((String) node.getChildValue("format"));
            legendURL.setHeight((BigInteger) node.getChildValue("height"));
            legendURL.setWidth((BigInteger) node.getChildValue("width"));
            legendURL.setHref((String) node.getChildValue("Href"));
            Object childValue = node.getChildValue("maxScaleDenominator");
            if(childValue!=null) {
                legendURL.setMaxScaleDenominator(((Double) childValue).doubleValue());
            }
            childValue = node.getChildValue("minScaleDenominator");
            if(childValue!=null) {
                legendURL.setMinScaleDenominator(((Double) childValue).doubleValue());
            }
            
            
            return legendURL;
	}

}