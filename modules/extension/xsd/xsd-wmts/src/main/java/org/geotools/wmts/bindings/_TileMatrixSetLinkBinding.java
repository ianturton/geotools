package org.geotools.wmts.bindings;

import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;

import net.opengis.wmts.v_1.TileMatrixSetLimitsType;
import net.opengis.wmts.v_1.TileMatrixSetLinkType;
import net.opengis.wmts.v_1.wmtsv_1Factory;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_TileMatrixSetLink.
 *
 * <p>
 * 
 * <pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_TileMatrixSetLink" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
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
 *		
 *	  </code>
 * </pre>
 * </p>
 *
 * @generated
 */
public class _TileMatrixSetLinkBinding extends AbstractComplexBinding {

    wmtsv_1Factory factory;

    public _TileMatrixSetLinkBinding(wmtsv_1Factory factory) {
        super();
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WMTS._TileMatrixSetLink;
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
        link.setTileMatrixSetLimits(
                (TileMatrixSetLimitsType) node.getChildValue("TileMatrixSetLimits"));
        return link;
    }

}