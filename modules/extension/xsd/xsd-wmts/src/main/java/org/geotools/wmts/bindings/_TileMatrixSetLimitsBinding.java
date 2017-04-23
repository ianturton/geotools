package org.geotools.wmts.bindings;

import org.geotools.wmts.WMTS;
import org.geotools.xml.*;

import net.opengis.wmts.v_11.TileMatrixLimitsType;
import net.opengis.wmts.v_11.TileMatrixSetLimitsType;
import net.opengis.wmts.v_11.wmts11Factory;

import java.util.List;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_TileMatrixSetLimits.
 *
 * <p>
 * 
 * <pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_TileMatrixSetLimits" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  			&lt;sequence&gt;
 *  				&lt;element maxOccurs="unbounded" ref="wmts:TileMatrixLimits"&gt;
 *  					&lt;annotation&gt;
 *  						&lt;documentation&gt;
 *  							Metadata describing the limits of the TileMatrixSet indices. 
 *  							Multiplicity must be the multiplicity of TileMatrix in this 
 *  							TileMatrixSet.
 *  						&lt;/documentation&gt;
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
public class _TileMatrixSetLimitsBinding extends AbstractComplexBinding {

    wmts11Factory factory;

    public _TileMatrixSetLimitsBinding(wmts11Factory factory) {
        super();
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WMTS._TileMatrixSetLimits;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Class getType() {
        return TileMatrixLimitsType.class;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {

        TileMatrixSetLimitsType limits = factory.createTileMatrixSetLimitsType();

        @SuppressWarnings("unchecked")
        List<Node> children = node.getChildren("TileMatrixLimits");
        for (Node c : children) {

            limits.getTileMatrixLimits().add((TileMatrixLimitsType) c.getValue());
        }
        return limits;
    }

}