package org.geotools.wmts.bindings;


import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;
import org.opengis.metadata.MetaData;

import net.opengis.ows20.DatasetDescriptionSummaryBaseType;
import net.opengis.ows20.MetadataType;
import net.opengis.wmts.v_11.ContentsType;
import net.opengis.wmts.v_11.TileMatrixSetType;
import net.opengis.wmts.v_11.wmts11Factory;		

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:ContentsType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="ContentsType" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;complexContent&gt;
 *  			&lt;extension base="ows:ContentsBaseType"&gt;
 *  				&lt;sequence&gt;
 *  					&lt;element maxOccurs="unbounded" minOccurs="0" ref="wmts:TileMatrixSet"&gt;
 *  						&lt;annotation&gt;
 *  							&lt;documentation&gt;A description of the geometry of a tile fragmentation&lt;/documentation&gt;
 *  						&lt;/annotation&gt;
 *  					&lt;/element&gt;
 *  				&lt;/sequence&gt;
 *  			&lt;/extension&gt;
 *  		&lt;/complexContent&gt;
 *  	&lt;/complexType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class ContentsTypeBinding extends AbstractComplexBinding {

	wmts11Factory factory;		
	public ContentsTypeBinding( wmts11Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS.ContentsType;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return ContentsTypeBinding.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {
		ContentsType contents = factory.createContentsType();
		contents.getDatasetDescriptionSummary().addAll(node.getChildren(DatasetDescriptionSummaryBaseType.class));
		contents.getTileMatrixSet().addAll(node.getChildren(TileMatrixSetType.class));
		contents.getOtherSource().addAll(node.getChildren(MetadataType.class));
		return contents;
	}

}