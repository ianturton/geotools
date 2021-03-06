/**
 */
package net.opengis.gml311.impl;

import java.util.Collection;

import net.opengis.gml311.DirectedFacePropertyType;
import net.opengis.gml311.Gml311Package;
import net.opengis.gml311.TopoSurfaceType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Topo Surface Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.opengis.gml311.impl.TopoSurfaceTypeImpl#getDirectedFace <em>Directed Face</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TopoSurfaceTypeImpl extends AbstractTopologyTypeImpl implements TopoSurfaceType {
    /**
     * The cached value of the '{@link #getDirectedFace() <em>Directed Face</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDirectedFace()
     * @generated
     * @ordered
     */
    protected EList<DirectedFacePropertyType> directedFace;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TopoSurfaceTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Gml311Package.eINSTANCE.getTopoSurfaceType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DirectedFacePropertyType> getDirectedFace() {
        if (directedFace == null) {
            directedFace = new EObjectContainmentEList<>(DirectedFacePropertyType.class, this, Gml311Package.TOPO_SURFACE_TYPE__DIRECTED_FACE);
        }
        return directedFace;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case Gml311Package.TOPO_SURFACE_TYPE__DIRECTED_FACE:
                return ((InternalEList<?>)getDirectedFace()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Gml311Package.TOPO_SURFACE_TYPE__DIRECTED_FACE:
                return getDirectedFace();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case Gml311Package.TOPO_SURFACE_TYPE__DIRECTED_FACE:
                getDirectedFace().clear();
                getDirectedFace().addAll((Collection<? extends DirectedFacePropertyType>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case Gml311Package.TOPO_SURFACE_TYPE__DIRECTED_FACE:
                getDirectedFace().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case Gml311Package.TOPO_SURFACE_TYPE__DIRECTED_FACE:
                return directedFace != null && !directedFace.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //TopoSurfaceTypeImpl
