package org.geotools.wmts.bindings;

import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;

import net.opengis.wmts.v_11.TileMatrixSetLimitsType;
import net.opengis.wmts.v_11.TileMatrixSetLinkType;
import net.opengis.wmts.v_11.wmts11Factory;
import net.opengis.wmts.v_11.impl.TileMatrixSetLinkTypeImpl;

import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/wmts/1.0:TileMatrixSetLink.
 *
 * <p>
 * 
 * <pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;element name="TileMatrixSetLink" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;annotation&gt;
 *  			&lt;documentation&gt;Metadata about the TileMatrixSet reference.&lt;/documentation&gt;
 *  		&lt;/annotation&gt;
 *  		&lt;complexType&gt;
 *  			&lt;sequence&gt;
 *  				&lt;element name="TileMatrixSet" type="string"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Reference to a tileMatrixSet&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  				&lt;element minOccurs="0" ref="wmts:TileMatrixSetLimits"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;Indices limits for this tileMatrixSet. The absence of this 
 *  						element means that tile row and tile col indices are only limited by 0 
 *  						and the corresponding tileMatrixSet maximum definitions.&lt;/documentation&gt;
 *  					&lt;/annotation&gt;
 *  				&lt;/element&gt;
 *  			&lt;/sequence&gt;
 *  		&lt;/complexType&gt;
 *  	&lt;/element&gt; 
 *		
 *	  </code>
 * </pre>
 * </p>
 *
 * @generated
 */
public class TileMatrixSetLinkBinding extends AbstractSimpleBinding {

    wmts11Factory factory;

    public TileMatrixSetLinkBinding(wmts11Factory factory) {
        super();
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WMTS.TileMatrixSetLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Class getType() {
        return TileMatrixSetLinkType.class;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        TileMatrixSetLinkType link = factory.createTileMatrixSetLinkType();
        
        link.setTileMatrixSet((String) node.getChildValue("TileMatrixSet"));
        link.setTileMatrixSetLimits((TileMatrixSetLimitsType) node.getChildValue("TileMatrixSetLimits"));
        return link;
    
    }

}