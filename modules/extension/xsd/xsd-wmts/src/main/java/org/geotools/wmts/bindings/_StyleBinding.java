package org.geotools.wmts.bindings;

import java.util.List;

import javax.xml.namespace.QName;

import org.geotools.wmts.WMTS;
import org.geotools.xml.AbstractComplexBinding;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;

import net.opengis.ows11.CodeType;
import net.opengis.wmts.v_11.LegendURLType;
import net.opengis.wmts.v_11.StyleType;
import net.opengis.wmts.v_11.wmts11Factory;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_Style.
 *
 * <p>
 * 
 * <pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_Style" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
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
 *		
 *	  </code>
 * </pre>
 * </p>
 *
 * @generated
 */
public class _StyleBinding extends AbstractComplexBinding {

    wmts11Factory factory;

    public _StyleBinding(wmts11Factory factory) {
        super();
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WMTS._Style;
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
    @SuppressWarnings("unchecked")
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        StyleType style = factory.createStyleType();

        style.setIdentifier((CodeType) node.getChildValue("Identifier"));
        Object def = node.getAttributeValue("isDefault");
        if(def!=null) {
            style.setIsDefault((boolean) def);
        }else {
            style.setIsDefault(false);
        }

        List<Node> children = node.getChildren("LegendURL");
        for (Node c : children) {
            style.getLegendURL().add((LegendURLType) c.getValue());
        }
        children = node.getChildren("Keywords");
        for (Node c : children) {
            style.getKeywords().add(c.getValue());
        }
        children = node.getChildren("Title");
        for (Node c : children) {
            style.getTitle().add(c.getValue());
        }
        children = node.getChildren("Abstract");
        for (Node c : children) {
            style.getAbstract().add(c.getValue());
        }
        return style;
    }

}