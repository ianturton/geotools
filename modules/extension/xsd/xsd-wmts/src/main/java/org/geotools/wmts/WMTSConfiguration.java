package org.geotools.wmts;

import org.eclipse.xsd.util.XSDSchemaLocationResolver;
import org.geotools.wmts.bindings.*;
import org.geotools.xml.Configuration;
import org.picocontainer.MutablePicoContainer;

/**
 * Parser configuration for the http://www.opengis.net/wmts/1.0 schema.
 *
 * @generated
 */
public class WMTSConfiguration extends Configuration {

    /**
     * Creates a new configuration.
     * 
     * @generated
     */     
    public WMTSConfiguration() {
       super(WMTS.getInstance());
       
       //TODO: add dependencies here
    }
    
    /**
     * Registers the bindings for the configuration.
     *
     * @generated
     */
    protected final void registerBindings( MutablePicoContainer container ) {
        //Types
        container.registerComponentImplementation(WMTS.AcceptedFormatsType,AcceptedFormatsTypeBinding.class);
        container.registerComponentImplementation(WMTS.ContentsType,ContentsTypeBinding.class);
        container.registerComponentImplementation(WMTS.GetCapabilitiesValueType,GetCapabilitiesValueTypeBinding.class);
        container.registerComponentImplementation(WMTS.GetFeatureInfoValueType,GetFeatureInfoValueTypeBinding.class);
        container.registerComponentImplementation(WMTS.GetTileValueType,GetTileValueTypeBinding.class);
        container.registerComponentImplementation(WMTS.LayerType,LayerTypeBinding.class);
        container.registerComponentImplementation(WMTS.RequestServiceType,RequestServiceTypeBinding.class);
        container.registerComponentImplementation(WMTS.SectionsType,SectionsTypeBinding.class);
        container.registerComponentImplementation(WMTS.URLTemplateType,URLTemplateTypeBinding.class);
        container.registerComponentImplementation(WMTS.VersionType,VersionTypeBinding.class);
        container.registerComponentImplementation(WMTS._BinaryPayload,_BinaryPayloadBinding.class);
        container.registerComponentImplementation(WMTS._Capabilities,_CapabilitiesBinding.class);
        container.registerComponentImplementation(WMTS._Dimension,_DimensionBinding.class);
        container.registerComponentImplementation(WMTS._DimensionNameValue,_DimensionNameValueBinding.class);
        container.registerComponentImplementation(WMTS._FeatureInfoResponse,_FeatureInfoResponseBinding.class);
        container.registerComponentImplementation(WMTS._GetCapabilities,_GetCapabilitiesBinding.class);
        container.registerComponentImplementation(WMTS._GetFeatureInfo,_GetFeatureInfoBinding.class);
        container.registerComponentImplementation(WMTS._GetTile,_GetTileBinding.class);
        container.registerComponentImplementation(WMTS._LegendURL,_LegendURLBinding.class);
        container.registerComponentImplementation(WMTS._Style,_StyleBinding.class);
        container.registerComponentImplementation(WMTS._TextPayload,_TextPayloadBinding.class);
        container.registerComponentImplementation(WMTS._Theme,_ThemeBinding.class);
        container.registerComponentImplementation(WMTS._Themes,_ThemesBinding.class);
        container.registerComponentImplementation(WMTS._TileMatrix,_TileMatrixBinding.class);
        container.registerComponentImplementation(WMTS._TileMatrixLimits,_TileMatrixLimitsBinding.class);
        container.registerComponentImplementation(WMTS._TileMatrixSet,_TileMatrixSetBinding.class);
        container.registerComponentImplementation(WMTS._TileMatrixSetLimits,_TileMatrixSetLimitsBinding.class);
        container.registerComponentImplementation(WMTS._TileMatrixSetLink,_TileMatrixSetLinkBinding.class);

        //Elements
        container.registerComponentImplementation(WMTS.BinaryPayload,BinaryPayloadBinding.class);
        container.registerComponentImplementation(WMTS.Capabilities,CapabilitiesBinding.class);
        container.registerComponentImplementation(WMTS.Dimension,DimensionBinding.class);
        container.registerComponentImplementation(WMTS.DimensionNameValue,DimensionNameValueBinding.class);
        container.registerComponentImplementation(WMTS.FeatureInfoResponse,FeatureInfoResponseBinding.class);
        container.registerComponentImplementation(WMTS.GetCapabilities,GetCapabilitiesBinding.class);
        container.registerComponentImplementation(WMTS.GetFeatureInfo,GetFeatureInfoBinding.class);
        container.registerComponentImplementation(WMTS.GetTile,GetTileBinding.class);
        container.registerComponentImplementation(WMTS.Layer,LayerBinding.class);
        container.registerComponentImplementation(WMTS.LegendURL,LegendURLBinding.class);
        container.registerComponentImplementation(WMTS.Style,StyleBinding.class);
        container.registerComponentImplementation(WMTS.TextPayload,TextPayloadBinding.class);
        container.registerComponentImplementation(WMTS.Theme,ThemeBinding.class);
        container.registerComponentImplementation(WMTS.Themes,ThemesBinding.class);
        container.registerComponentImplementation(WMTS.TileMatrix,TileMatrixBinding.class);
        container.registerComponentImplementation(WMTS.TileMatrixLimits,TileMatrixLimitsBinding.class);
        container.registerComponentImplementation(WMTS.TileMatrixSet,TileMatrixSetBinding.class);
        container.registerComponentImplementation(WMTS.TileMatrixSetLimits,TileMatrixSetLimitsBinding.class);
        container.registerComponentImplementation(WMTS.TileMatrixSetLink,TileMatrixSetLinkBinding.class);

    
    }
} 