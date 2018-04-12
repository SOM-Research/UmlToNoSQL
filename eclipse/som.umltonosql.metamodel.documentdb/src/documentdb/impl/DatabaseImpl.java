/**
 */
package documentdb.impl;

import documentdb.Collection;
import documentdb.Database;
import documentdb.DatabaseKind;
import documentdb.DocumentdbPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link documentdb.impl.DatabaseImpl#getCollections <em>Collections</em>}</li>
 *   <li>{@link documentdb.impl.DatabaseImpl#getRawDatabase <em>Raw Database</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DatabaseImpl extends MinimalEObjectImpl.Container implements Database {
	/**
	 * The cached value of the '{@link #getCollections() <em>Collections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollections()
	 * @generated
	 * @ordered
	 */
	protected EList<Collection> collections;

	/**
	 * The default value of the '{@link #getRawDatabase() <em>Raw Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRawDatabase()
	 * @generated
	 * @ordered
	 */
	protected static final DatabaseKind RAW_DATABASE_EDEFAULT = DatabaseKind.MONGODB;

	/**
	 * The cached value of the '{@link #getRawDatabase() <em>Raw Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRawDatabase()
	 * @generated
	 * @ordered
	 */
	protected DatabaseKind rawDatabase = RAW_DATABASE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatabaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DocumentdbPackage.Literals.DATABASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Collection> getCollections() {
		if (collections == null) {
			collections = new EObjectContainmentEList<Collection>(Collection.class, this, DocumentdbPackage.DATABASE__COLLECTIONS);
		}
		return collections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseKind getRawDatabase() {
		return rawDatabase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRawDatabase(DatabaseKind newRawDatabase) {
		DatabaseKind oldRawDatabase = rawDatabase;
		rawDatabase = newRawDatabase == null ? RAW_DATABASE_EDEFAULT : newRawDatabase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DocumentdbPackage.DATABASE__RAW_DATABASE, oldRawDatabase, rawDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DocumentdbPackage.DATABASE__COLLECTIONS:
				return ((InternalEList<?>)getCollections()).basicRemove(otherEnd, msgs);
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
			case DocumentdbPackage.DATABASE__COLLECTIONS:
				return getCollections();
			case DocumentdbPackage.DATABASE__RAW_DATABASE:
				return getRawDatabase();
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
			case DocumentdbPackage.DATABASE__COLLECTIONS:
				getCollections().clear();
				getCollections().addAll((java.util.Collection<? extends Collection>)newValue);
				return;
			case DocumentdbPackage.DATABASE__RAW_DATABASE:
				setRawDatabase((DatabaseKind)newValue);
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
			case DocumentdbPackage.DATABASE__COLLECTIONS:
				getCollections().clear();
				return;
			case DocumentdbPackage.DATABASE__RAW_DATABASE:
				setRawDatabase(RAW_DATABASE_EDEFAULT);
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
			case DocumentdbPackage.DATABASE__COLLECTIONS:
				return collections != null && !collections.isEmpty();
			case DocumentdbPackage.DATABASE__RAW_DATABASE:
				return rawDatabase != RAW_DATABASE_EDEFAULT;
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
		result.append(" (rawDatabase: ");
		result.append(rawDatabase);
		result.append(')');
		return result.toString();
	}

} //DatabaseImpl
