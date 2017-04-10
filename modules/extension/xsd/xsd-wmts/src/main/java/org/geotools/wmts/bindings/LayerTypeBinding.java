package org.geotools.wmts.bindings;

import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;

import net.opengis.ows11.CodeType;
import net.opengis.ows20.DatasetDescriptionSummaryBaseType;
import net.opengis.ows20.MetadataType;
import net.opengis.wmts.v_11.DimensionType;
import net.opengis.wmts.v_11.LayerType;
import net.opengis.wmts.v_11.TileMatrixSetLinkType;
import net.opengis.wmts.v_11.URLTemplateType;
import net.opengis.wmts.v_11.wmts11Factory;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:LayerType.
 *
 * <p>
 * 
 * <pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="LayerType" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;complexContent&gt;
 *  			&lt;extension base="ows:DatasetDescriptionSummaryBaseType"&gt;
 *  				&lt;sequence&gt;
 *  					&lt;element maxOccurs="unbounded" ref="wmts:Style"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;Metadata about the styles of this layer&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/element&gt;
 *  					&lt;element maxOccurs="unbounded" name="Format" type="ows:MimeType"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;Supported valid output MIME types for a tile&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/element&gt;
 *  					&lt;element maxOccurs="unbounded" minOccurs="0" name="InfoFormat" type="ows:MimeType"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;
 *  							Supported valid output MIME types for a FeatureInfo. 
 *  							If there isn't any, The server do not support FeatureInfo requests
 *  							for this layer.&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/element&gt;
 *  					&lt;element maxOccurs="unbounded" minOccurs="0" ref="wmts:Dimension"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;Extra dimensions for a tile and FeatureInfo requests.&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/element&gt;
 *  					&lt;element maxOccurs="unbounded" ref="wmts:TileMatrixSetLink"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;Reference to a tileMatrixSet and limits&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/element&gt;
 *  					&lt;element maxOccurs="unbounded" minOccurs="0" name="ResourceURL" type="wmts:URLTemplateType"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;
 *  								URL template to a tile or a FeatureInfo resource on 
 *  								resource oriented architectural style
 *  							&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/element&gt;
 *  				&lt;/sequence&gt;
 *  			&lt;/extension&gt;
 *  		&lt;/complexContent&gt;
 *  	&lt;/complexType&gt; 
 *		
 *	  </code>
 * </pre>
 * </p>
 *
 * @generated
 */
public class LayerTypeBinding extends AbstractComplexBinding {

    wmts11Factory factory;

    public LayerTypeBinding(wmts11Factory factory) {
        super();
        this.factory = factory;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return WMTS.LayerType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Class getType() {
        return LayerType.class;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        LayerType layer = factory.createLayerType();

        layer.getAbstract().addAll(node.getChildren("Abstract"));
        layer.getBoundingBox().addAll(node.getChildren("BoundingBox"));
        layer.getWGS84BoundingBox().addAll(node.getChildren("WGS84BoundingBox"));
        layer.getDatasetDescriptionSummary()
                .addAll(node.getChildren(DatasetDescriptionSummaryBaseType.class));
        layer.getDimension().addAll(node.getChildren(DimensionType.class));
        layer.getFormat().addAll(node.getChildren("Format"));
        layer.getInfoFormat().addAll(node.getChildren("InfoFormat"));
        layer.setIdentifier((CodeType) node.getChild("Identifier"));
        layer.getKeywords().addAll(node.getChildren("Keyword"));
        layer.getMetadata().addAll(node.getChildren(MetadataType.class));
        layer.getResourceURL().addAll(node.getChildren(URLTemplateType.class));
        layer.getStyle().addAll(node.getChildren("Style"));
        layer.getTileMatrixSetLink().addAll(node.getChildren(TileMatrixSetLinkType.class));
        layer.getTitle().addAll(node.getChildren("Title"));

        return layer;
    }

}