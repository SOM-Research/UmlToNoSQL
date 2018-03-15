/**
 */
package mongodb.impl;

import mongodb.MongodbPackage;
import mongodb.UmlToNoSQLIDReference;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uml To No SQLID Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mongodb.impl.UmlToNoSQLIDReferenceImpl#getReferencedType <em>Referenced Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UmlToNoSQLIDReferenceImpl extends TypeImpl implements UmlToNoSQLIDReference {
	/**
	 * The default value of the '{@link #getReferencedType() <em>Referenced Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedType()
	 * @generated
	 * @ordered
	 */
	protected static final String REFERENCED_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferencedType() <em>Referenced Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedType()
	 * @generated
	 * @ordered
	 */
	protected String referencedType = REFERENCED_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UmlToNoSQLIDReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MongodbPackage.Literals.UML_TO_NO_SQLID_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReferencedType() {
		return referencedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedType(String newReferencedType) {
		String oldReferencedType = referencedType;
		referencedType = newReferencedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MongodbPackage.UML_TO_NO_SQLID_REFERENCE__REFERENCED_TYPE, oldReferencedType, referencedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MongodbPackage.UML_TO_NO_SQLID_REFERENCE__REFERENCED_TYPE:
				return getReferencedType();
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
			case MongodbPackage.UML_TO_NO_SQLID_REFERENCE__REFERENCED_TYPE:
				setReferencedType((String)newValue);
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
			case MongodbPackage.UML_TO_NO_SQLID_REFERENCE__REFERENCED_TYPE:
				setReferencedType(REFERENCED_TYPE_EDEFAULT);
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
			case MongodbPackage.UML_TO_NO_SQLID_REFERENCE__REFERENCED_TYPE:
				return REFERENCED_TYPE_EDEFAULT == null ? referencedType != null : !REFERENCED_TYPE_EDEFAULT.equals(referencedType);
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
		result.append(" (referencedType: ");
		result.append(referencedType);
		result.append(')');
		return result.toString();
	}

} //UmlToNoSQLIDReferenceImpl
