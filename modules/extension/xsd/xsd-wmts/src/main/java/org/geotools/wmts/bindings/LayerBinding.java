package org.geotools.wmts.bindings;


import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;

import net.opengis.ows11.CodeType;
import net.opengis.ows20.BoundingBoxType;
import net.opengis.ows20.DatasetDescriptionSummaryBaseType;
import net.opengis.ows20.MetadataType;
import net.opengis.wmts.v_11.DimensionType;
import net.opengis.wmts.v_11.LayerType;
import net.opengis.wmts.v_11.TileMatrixSetLinkType;
import net.opengis.wmts.v_11.URLTemplateType;
import net.opengis.wmts.v_11.wmts11Factory;		

import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/wmts/1.0:Layer.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;element name="Layer" substitutionGroup="ows:DatasetDescriptionSummary" type="wmts:LayerType" xmlns="http://www.w3.org/2001/XMLSchema"/&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class LayerBinding extends AbstractSimpleBinding {

	wmts11Factory factory;		
	public LayerBinding( wmts11Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS.Layer;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	<b>
	 * @generated modifiable
	 */	
	public Class<LayerType> getType() {
		return LayerType.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {
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