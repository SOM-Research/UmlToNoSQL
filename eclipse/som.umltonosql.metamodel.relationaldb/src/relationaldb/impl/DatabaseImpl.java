/**
 */
package relationaldb.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import relationaldb.Database;
import relationaldb.DatabaseKind;
import relationaldb.RelationaldbPackage;
import relationaldb.Table;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relationaldb.impl.DatabaseImpl#getTables <em>Tables</em>}</li>
 *   <li>{@link relationaldb.impl.DatabaseImpl#getRawDatabase <em>Raw Database</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DatabaseImpl extends NamedImpl implements Database {
	/**
	 * The cached value of the '{@link #getTables() <em>Tables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTables()
	 * @generated
	 * @ordered
	 */
	protected EList<Table> tables;

	/**
	 * The default value of the '{@link #getRawDatabase() <em>Raw Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRawDatabase()
	 * @generated
	 * @ordered
	 */
	protected static final DatabaseKind RAW_DATABASE_EDEFAULT = DatabaseKind.POSTGRES;

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
		return RelationaldbPackage.Literals.DATABASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Table> getTables() {
		if (tables == null) {
			tables = new EObjectResolvingEList<Table>(Table.class, this, RelationaldbPackage.DATABASE__TABLES);
		}
		return tables;
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
			eNotify(new ENotificationImpl(this, Notification.SET, RelationaldbPackage.DATABASE__RAW_DATABASE, oldRawDatabase, rawDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelationaldbPackage.DATABASE__TABLES:
				return getTables();
			case RelationaldbPackage.DATABASE__RAW_DATABASE:
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
			case RelationaldbPackage.DATABASE__TABLES:
				getTables().clear();
				getTables().addAll((Collection<? extends Table>)newValue);
				return;
			case RelationaldbPackage.DATABASE__RAW_DATABASE:
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
			case RelationaldbPackage.DATABASE__TABLES:
				getTables().clear();
				return;
			case RelationaldbPackage.DATABASE__RAW_DATABASE:
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
			case RelationaldbPackage.DATABASE__TABLES:
				return tables != null && !tables.isEmpty();
			case RelationaldbPackage.DATABASE__RAW_DATABASE:
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
