/**
 */
package net.opengis.wmts.v_11.util;

import java.util.Map;

import net.opengis.wmts.v_11.wmts11Package;


import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class wmts11XMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public wmts11XMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        wmts11Package.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the wmts11ResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new wmts11ResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new wmts11ResourceFactoryImpl());
        }
        return registrations;
    }

} //wmts11XMLProcessor
