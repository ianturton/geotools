/**
 */
package net.opengis.wmts.v_11.impl;

import net.opengis.wmts.v_11.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class wmts11FactoryImpl extends EFactoryImpl implements wmts11Factory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static wmts11Factory init() {
        try {
            wmts11Factory thewmts11Factory = (wmts11Factory)EPackage.Registry.INSTANCE.getEFactory(wmts11Package.eNS_URI);
            if (thewmts11Factory != null) {
                return thewmts11Factory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new wmts11FactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public wmts11FactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case wmts11Package.BINARY_PAYLOAD_TYPE: return createBinaryPayloadType();
            case wmts11Package.CAPABILITIES_TYPE: return createCapabilitiesType();
            case wmts11Package.CONTENTS_TYPE: return createContentsType();
            case wmts11Package.DIMENSION_NAME_VALUE_TYPE: return createDimensionNameValueType();
            case wmts11Package.DIMENSION_TYPE: return createDimensionType();
            case wmts11Package.DOCUMENT_ROOT: return createDocumentRoot();
            case wmts11Package.FEATURE_INFO_RESPONSE_TYPE: return createFeatureInfoResponseType();
            case wmts11Package.GET_CAPABILITIES_TYPE: return createGetCapabilitiesType();
            case wmts11Package.GET_FEATURE_INFO_TYPE: return createGetFeatureInfoType();
            case wmts11Package.GET_TILE_TYPE: return createGetTileType();
            case wmts11Package.LAYER_TYPE: return createLayerType();
            case wmts11Package.LEGEND_URL_TYPE: return createLegendURLType();
            case wmts11Package.STYLE_TYPE: return createStyleType();
            case wmts11Package.TEXT_PAYLOAD_TYPE: return createTextPayloadType();
            case wmts11Package.THEMES_TYPE: return createThemesType();
            case wmts11Package.THEME_TYPE: return createThemeType();
            case wmts11Package.TILE_MATRIX_LIMITS_TYPE: return createTileMatrixLimitsType();
            case wmts11Package.TILE_MATRIX_SET_LIMITS_TYPE: return createTileMatrixSetLimitsType();
            case wmts11Package.TILE_MATRIX_SET_LINK_TYPE: return createTileMatrixSetLinkType();
            case wmts11Package.TILE_MATRIX_SET_TYPE: return createTileMatrixSetType();
            case wmts11Package.TILE_MATRIX_TYPE: return createTileMatrixType();
            case wmts11Package.URL_TEMPLATE_TYPE: return createURLTemplateType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case wmts11Package.GET_CAPABILITIES_VALUE_TYPE:
                return createGetCapabilitiesValueTypeFromString(eDataType, initialValue);
            case wmts11Package.GET_FEATURE_INFO_VALUE_TYPE:
                return createGetFeatureInfoValueTypeFromString(eDataType, initialValue);
            case wmts11Package.GET_TILE_VALUE_TYPE:
                return createGetTileValueTypeFromString(eDataType, initialValue);
            case wmts11Package.REQUEST_SERVICE_TYPE:
                return createRequestServiceTypeFromString(eDataType, initialValue);
            case wmts11Package.RESOURCE_TYPE_TYPE:
                return createResourceTypeTypeFromString(eDataType, initialValue);
            case wmts11Package.VERSION_TYPE:
                return createVersionTypeFromString(eDataType, initialValue);
            case wmts11Package.ACCEPTED_FORMATS_TYPE:
                return createAcceptedFormatsTypeFromString(eDataType, initialValue);
            case wmts11Package.GET_CAPABILITIES_VALUE_TYPE_OBJECT:
                return createGetCapabilitiesValueTypeObjectFromString(eDataType, initialValue);
            case wmts11Package.GET_FEATURE_INFO_VALUE_TYPE_OBJECT:
                return createGetFeatureInfoValueTypeObjectFromString(eDataType, initialValue);
            case wmts11Package.GET_TILE_VALUE_TYPE_OBJECT:
                return createGetTileValueTypeObjectFromString(eDataType, initialValue);
            case wmts11Package.REQUEST_SERVICE_TYPE_OBJECT:
                return createRequestServiceTypeObjectFromString(eDataType, initialValue);
            case wmts11Package.RESOURCE_TYPE_TYPE_OBJECT:
                return createResourceTypeTypeObjectFromString(eDataType, initialValue);
            case wmts11Package.SECTIONS_TYPE:
                return createSectionsTypeFromString(eDataType, initialValue);
            case wmts11Package.TEMPLATE_TYPE:
                return createTemplateTypeFromString(eDataType, initialValue);
            case wmts11Package.VERSION_TYPE_OBJECT:
                return createVersionTypeObjectFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case wmts11Package.GET_CAPABILITIES_VALUE_TYPE:
                return convertGetCapabilitiesValueTypeToString(eDataType, instanceValue);
            case wmts11Package.GET_FEATURE_INFO_VALUE_TYPE:
                return convertGetFeatureInfoValueTypeToString(eDataType, instanceValue);
            case wmts11Package.GET_TILE_VALUE_TYPE:
                return convertGetTileValueTypeToString(eDataType, instanceValue);
            case wmts11Package.REQUEST_SERVICE_TYPE:
                return convertRequestServiceTypeToString(eDataType, instanceValue);
            case wmts11Package.RESOURCE_TYPE_TYPE:
                return convertResourceTypeTypeToString(eDataType, instanceValue);
            case wmts11Package.VERSION_TYPE:
                return convertVersionTypeToString(eDataType, instanceValue);
            case wmts11Package.ACCEPTED_FORMATS_TYPE:
                return convertAcceptedFormatsTypeToString(eDataType, instanceValue);
            case wmts11Package.GET_CAPABILITIES_VALUE_TYPE_OBJECT:
                return convertGetCapabilitiesValueTypeObjectToString(eDataType, instanceValue);
            case wmts11Package.GET_FEATURE_INFO_VALUE_TYPE_OBJECT:
                return convertGetFeatureInfoValueTypeObjectToString(eDataType, instanceValue);
            case wmts11Package.GET_TILE_VALUE_TYPE_OBJECT:
                return convertGetTileValueTypeObjectToString(eDataType, instanceValue);
            case wmts11Package.REQUEST_SERVICE_TYPE_OBJECT:
                return convertRequestServiceTypeObjectToString(eDataType, instanceValue);
            case wmts11Package.RESOURCE_TYPE_TYPE_OBJECT:
                return convertResourceTypeTypeObjectToString(eDataType, instanceValue);
            case wmts11Package.SECTIONS_TYPE:
                return convertSectionsTypeToString(eDataType, instanceValue);
            case wmts11Package.TEMPLATE_TYPE:
                return convertTemplateTypeToString(eDataType, instanceValue);
            case wmts11Package.VERSION_TYPE_OBJECT:
                return convertVersionTypeObjectToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BinaryPayloadType createBinaryPayloadType() {
        BinaryPayloadTypeImpl binaryPayloadType = new BinaryPayloadTypeImpl();
        return binaryPayloadType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CapabilitiesType createCapabilitiesType() {
        CapabilitiesTypeImpl capabilitiesType = new CapabilitiesTypeImpl();
        return capabilitiesType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ContentsType createContentsType() {
        ContentsTypeImpl contentsType = new ContentsTypeImpl();
        return contentsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DimensionNameValueType createDimensionNameValueType() {
        DimensionNameValueTypeImpl dimensionNameValueType = new DimensionNameValueTypeImpl();
        return dimensionNameValueType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DimensionType createDimensionType() {
        DimensionTypeImpl dimensionType = new DimensionTypeImpl();
        return dimensionType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureInfoResponseType createFeatureInfoResponseType() {
        FeatureInfoResponseTypeImpl featureInfoResponseType = new FeatureInfoResponseTypeImpl();
        return featureInfoResponseType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetCapabilitiesType createGetCapabilitiesType() {
        GetCapabilitiesTypeImpl getCapabilitiesType = new GetCapabilitiesTypeImpl();
        return getCapabilitiesType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetFeatureInfoType createGetFeatureInfoType() {
        GetFeatureInfoTypeImpl getFeatureInfoType = new GetFeatureInfoTypeImpl();
        return getFeatureInfoType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetTileType createGetTileType() {
        GetTileTypeImpl getTileType = new GetTileTypeImpl();
        return getTileType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayerType createLayerType() {
        LayerTypeImpl layerType = new LayerTypeImpl();
        return layerType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LegendURLType createLegendURLType() {
        LegendURLTypeImpl legendURLType = new LegendURLTypeImpl();
        return legendURLType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StyleType createStyleType() {
        StyleTypeImpl styleType = new StyleTypeImpl();
        return styleType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextPayloadType createTextPayloadType() {
        TextPayloadTypeImpl textPayloadType = new TextPayloadTypeImpl();
        return textPayloadType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ThemesType createThemesType() {
        ThemesTypeImpl themesType = new ThemesTypeImpl();
        return themesType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ThemeType createThemeType() {
        ThemeTypeImpl themeType = new ThemeTypeImpl();
        return themeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TileMatrixLimitsType createTileMatrixLimitsType() {
        TileMatrixLimitsTypeImpl tileMatrixLimitsType = new TileMatrixLimitsTypeImpl();
        return tileMatrixLimitsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TileMatrixSetLimitsType createTileMatrixSetLimitsType() {
        TileMatrixSetLimitsTypeImpl tileMatrixSetLimitsType = new TileMatrixSetLimitsTypeImpl();
        return tileMatrixSetLimitsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TileMatrixSetLinkType createTileMatrixSetLinkType() {
        TileMatrixSetLinkTypeImpl tileMatrixSetLinkType = new TileMatrixSetLinkTypeImpl();
        return tileMatrixSetLinkType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TileMatrixSetType createTileMatrixSetType() {
        TileMatrixSetTypeImpl tileMatrixSetType = new TileMatrixSetTypeImpl();
        return tileMatrixSetType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TileMatrixType createTileMatrixType() {
        TileMatrixTypeImpl tileMatrixType = new TileMatrixTypeImpl();
        return tileMatrixType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URLTemplateType createURLTemplateType() {
        URLTemplateTypeImpl urlTemplateType = new URLTemplateTypeImpl();
        return urlTemplateType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetCapabilitiesValueType createGetCapabilitiesValueTypeFromString(EDataType eDataType, String initialValue) {
        GetCapabilitiesValueType result = GetCapabilitiesValueType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGetCapabilitiesValueTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetFeatureInfoValueType createGetFeatureInfoValueTypeFromString(EDataType eDataType, String initialValue) {
        GetFeatureInfoValueType result = GetFeatureInfoValueType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGetFeatureInfoValueTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetTileValueType createGetTileValueTypeFromString(EDataType eDataType, String initialValue) {
        GetTileValueType result = GetTileValueType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGetTileValueTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RequestServiceType createRequestServiceTypeFromString(EDataType eDataType, String initialValue) {
        RequestServiceType result = RequestServiceType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertRequestServiceTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceTypeType createResourceTypeTypeFromString(EDataType eDataType, String initialValue) {
        ResourceTypeType result = ResourceTypeType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertResourceTypeTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VersionType createVersionTypeFromString(EDataType eDataType, String initialValue) {
        VersionType result = VersionType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertVersionTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String createAcceptedFormatsTypeFromString(EDataType eDataType, String initialValue) {
        return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertAcceptedFormatsTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetCapabilitiesValueType createGetCapabilitiesValueTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createGetCapabilitiesValueTypeFromString(wmts11Package.Literals.GET_CAPABILITIES_VALUE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGetCapabilitiesValueTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertGetCapabilitiesValueTypeToString(wmts11Package.Literals.GET_CAPABILITIES_VALUE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetFeatureInfoValueType createGetFeatureInfoValueTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createGetFeatureInfoValueTypeFromString(wmts11Package.Literals.GET_FEATURE_INFO_VALUE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGetFeatureInfoValueTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertGetFeatureInfoValueTypeToString(wmts11Package.Literals.GET_FEATURE_INFO_VALUE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GetTileValueType createGetTileValueTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createGetTileValueTypeFromString(wmts11Package.Literals.GET_TILE_VALUE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertGetTileValueTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertGetTileValueTypeToString(wmts11Package.Literals.GET_TILE_VALUE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RequestServiceType createRequestServiceTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createRequestServiceTypeFromString(wmts11Package.Literals.REQUEST_SERVICE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertRequestServiceTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertRequestServiceTypeToString(wmts11Package.Literals.REQUEST_SERVICE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceTypeType createResourceTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createResourceTypeTypeFromString(wmts11Package.Literals.RESOURCE_TYPE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertResourceTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertResourceTypeTypeToString(wmts11Package.Literals.RESOURCE_TYPE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String createSectionsTypeFromString(EDataType eDataType, String initialValue) {
        return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertSectionsTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String createTemplateTypeFromString(EDataType eDataType, String initialValue) {
        return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTemplateTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VersionType createVersionTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createVersionTypeFromString(wmts11Package.Literals.VERSION_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertVersionTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertVersionTypeToString(wmts11Package.Literals.VERSION_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public wmts11Package getwmts11Package() {
        return (wmts11Package)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static wmts11Package getPackage() {
        return wmts11Package.eINSTANCE;
    }

} //wmts11FactoryImpl
