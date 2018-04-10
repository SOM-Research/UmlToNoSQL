/**
 */
package postgres.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import postgres.Column;
import postgres.PostgresPackage;
import postgres.Table;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link postgres.impl.TableImpl#getCol <em>Col</em>}</li>
 *   <li>{@link postgres.impl.TableImpl#getPrimaryKeys <em>Primary Keys</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TableImpl extends NamedImpl implements Table {
	/**
	 * The cached value of the '{@link #getCol() <em>Col</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCol()
	 * @generated
	 * @ordered
	 */
	protected EList<Column> col;

	/**
	 * The cached value of the '{@link #getPrimaryKeys() <em>Primary Keys</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<Column> primaryKeys;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PostgresPackage.Literals.TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Column> getCol() {
		if (col == null) {
			col = new EObjectContainmentWithInverseEList<Column>(Column.class, this, PostgresPackage.TABLE__COL, PostgresPackage.COLUMN__OWNER);
		}
		return col;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Column> getPrimaryKeys() {
		if (primaryKeys == null) {
			primaryKeys = new EObjectResolvingEList<Column>(Column.class, this, PostgresPackage.TABLE__PRIMARY_KEYS);
		}
		return primaryKeys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PostgresPackage.TABLE__COL:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCol()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PostgresPackage.TABLE__COL:
				return ((InternalEList<?>)getCol()).basicRemove(otherEnd, msgs);
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
			case PostgresPackage.TABLE__COL:
				return getCol();
			case PostgresPackage.TABLE__PRIMARY_KEYS:
				return getPrimaryKeys();
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
			case PostgresPackage.TABLE__COL:
				getCol().clear();
				getCol().addAll((Collection<? extends Column>)newValue);
				return;
			case PostgresPackage.TABLE__PRIMARY_KEYS:
				getPrimaryKeys().clear();
				getPrimaryKeys().addAll((Collection<? extends Column>)newValue);
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
			case PostgresPackage.TABLE__COL:
				getCol().clear();
				return;
			case PostgresPackage.TABLE__PRIMARY_KEYS:
				getPrimaryKeys().clear();
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
			case PostgresPackage.TABLE__COL:
				return col != null && !col.isEmpty();
			case PostgresPackage.TABLE__PRIMARY_KEYS:
				return primaryKeys != null && !primaryKeys.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TableImpl
