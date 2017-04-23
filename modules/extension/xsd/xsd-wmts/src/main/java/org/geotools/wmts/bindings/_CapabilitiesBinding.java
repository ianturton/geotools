package org.geotools.wmts.bindings;


import org.geotools.wmts.WMTS;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;

import net.opengis.ows11.OnlineResourceType;
import net.opengis.ows11.OperationsMetadataType;
import net.opengis.ows11.ServiceIdentificationType;
import net.opengis.ows11.ServiceProviderType;
import net.opengis.wmts.v_11.CapabilitiesType;
import net.opengis.wmts.v_11.ContentsType;
import net.opengis.wmts.v_11.ThemeType;
import net.opengis.wmts.v_11.wmts11Factory;

import java.util.List;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/wmts/1.0:_Capabilities.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_Capabilities" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *  			&lt;complexContent&gt;
 *  				&lt;extension base="ows:CapabilitiesBaseType"&gt;
 *  					&lt;sequence&gt;
 *  						&lt;element minOccurs="0" name="Contents" type="wmts:ContentsType"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;Metadata about the data served by this server. 
 *  								For WMTS, this section SHALL contain data about layers and 
 *  								TileMatrixSets&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  						&lt;element maxOccurs="unbounded" minOccurs="0" ref="wmts:Themes"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;
 *  								Metadata describing a theme hierarchy for the layers
 *  								&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  						&lt;element maxOccurs="unbounded" minOccurs="0" name="WSDL" type="ows:OnlineResourceType"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;Reference to a WSDL resource&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  						&lt;element maxOccurs="unbounded" minOccurs="0" name="ServiceMetadataURL" type="ows:OnlineResourceType"&gt;
 *  							&lt;annotation&gt;
 *  								&lt;documentation&gt;
 *  								Reference to a ServiceMetadata resource on resource 
 *  								oriented architectural style
 *  								&lt;/documentation&gt;
 *  							&lt;/annotation&gt;
 *  						&lt;/element&gt;
 *  					&lt;/sequence&gt;
 *  				&lt;/extension&gt;
 *  			&lt;/complexContent&gt;
 *  		&lt;/complexType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class _CapabilitiesBinding extends AbstractComplexBinding {

	wmts11Factory factory;		
	public _CapabilitiesBinding( wmts11Factory factory ) {
		super();
		this.factory = factory;
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return WMTS._Capabilities;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return CapabilitiesType.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {
            CapabilitiesType capabilities = factory.createCapabilitiesType();
            capabilities.setContents((ContentsType) node.getChildValue(ContentsType.class));
            capabilities.getThemes().addAll(node.getChildren(ThemeType.class));
            capabilities.setOperationsMetadata((OperationsMetadataType) node.getChildValue(OperationsMetadataType.class));
            capabilities.setServiceIdentification((ServiceIdentificationType) node.getChildValue(ServiceIdentificationType.class));
            capabilities.setServiceProvider((ServiceProviderType) node.getChildValue(ServiceProviderType.class));
            capabilities.setUpdateSequence((String) node.getChildValue("UpdateSequence"));
            
            List<Node> children = node.getChildren("ServiceMetadataURL");
            for(Node c:children) {
                capabilities.getServiceMetadataURL().add((OnlineResourceType) c.getValue());
            }
            capabilities.getWSDL().addAll(node.getChildren("WSDL"));
            return capabilities;	}

}