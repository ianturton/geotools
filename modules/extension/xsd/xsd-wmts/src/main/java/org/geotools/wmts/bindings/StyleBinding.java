package org.geotools.wmts.bindings;

import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;

import net.opengis.ows11.CodeType;
import net.opengis.wmts.v_11.StyleType;
import net.opengis.wmts.v_11.wmts11Factory;

import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/wmts/1.0:Style.
 *
 * <p>
 * 
 * <pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;element name="Style" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;complexType&gt;
 *  			&lt;complexContent&gt;
 *  				&lt;extension base="ows:DescriptionType"&gt;
 *  					&lt;sequence&gt;
 *  						&lt;element ref="ows:Identifier"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;
 *  									An unambiguous reference to this style, identifying 
 *  									a specific version when needed, normally used by software
 *  								&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  						&lt;element maxOccurs="unbounded" minOccurs="0" ref="wmts:LegendURL"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;Description of an image that represents 
 *  								the legend of the map&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  					&lt;/sequence&gt;
 *  					&lt;attribute name="isDefault" type="boolean"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;This style is used when no style is specified&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/attribute&gt;
 *  				&lt;/extension&gt;
 *  			&lt;/complexContent&gt;
 *  		&lt;/complexType&gt;
 *  	&lt;/element&gt; 
 *		
 *	  </code>
 * </pre>
 * </p>
 *
 * @generated
 */
public class StyleBinding extends AbstractSimpleBinding {

    wmts11Factory factory;

    public StyleBinding(wmts11Factory factory) {
        super();
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WMTS.Style;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Class getType() {
        return StyleType.class;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        StyleType style = factory.createStyleType();
        
        style.setIdentifier((CodeType) node.getChildValue("Identifier"));
        style.setIsDefault((boolean) node.getAttributeValue("default"));
        style.getLegendURL().addAll(node.getChildren("LegendURL"));
        style.getKeywords().addAll(node.getChildren("Keywords"));
        style.getTitle().addAll(node.getChildren("title"));
        style.getAbstract().addAll(node.getChildren("Abstract"));
        
        
        return style;
    }

}