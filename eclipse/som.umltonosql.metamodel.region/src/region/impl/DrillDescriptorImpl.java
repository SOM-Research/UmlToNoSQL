/**
 */
package region.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import region.DrillDescriptor;
import region.RegionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Drill Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link region.impl.DrillDescriptorImpl#getDrillDriver <em>Drill Driver</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DrillDescriptorImpl extends DatastoreDescriptorImpl implements DrillDescriptor {
	/**
	 * The default value of the '{@link #getDrillDriver() <em>Drill Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDrillDriver()
	 * @generated
	 * @ordered
	 */
	protected static final String DRILL_DRIVER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDrillDriver() <em>Drill Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDrillDriver()
	 * @generated
	 * @ordered
	 */
	protected String drillDriver = DRILL_DRIVER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DrillDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RegionPackage.Literals.DRILL_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDrillDriver() {
		return drillDriver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDrillDriver(String newDrillDriver) {
		String oldDrillDriver = drillDriver;
		drillDriver = newDrillDriver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RegionPackage.DRILL_DESCRIPTOR__DRILL_DRIVER, oldDrillDriver, drillDriver));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RegionPackage.DRILL_DESCRIPTOR__DRILL_DRIVER:
				return getDrillDriver();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RegionPackage.DRILL_DESCRIPTOR__DRILL_DRIVER:
				setDrillDriver((String)newValue);
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
			case RegionPackage.DRILL_DESCRIPTOR__DRILL_DRIVER:
				setDrillDriver(DRILL_DRIVER_EDEFAULT);
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
			case RegionPackage.DRILL_DESCRIPTOR__DRILL_DRIVER:
				return DRILL_DRIVER_EDEFAULT == null ? drillDriver != null : !DRILL_DRIVER_EDEFAULT.equals(drillDriver);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (drillDriver: ");
		result.append(drillDriver);
		result.append(')');
		return result.toString();
	}

} //DrillDescriptorImpl
