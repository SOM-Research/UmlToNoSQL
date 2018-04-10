/**
 */
package postgres.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import postgres.Column;
import postgres.ForeignKey;
import postgres.PostgresPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link postgres.impl.ForeignKeyImpl#getReferencedColumn <em>Referenced Column</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ForeignKeyImpl extends ColumnImpl implements ForeignKey {
	/**
	 * The cached value of the '{@link #getReferencedColumn() <em>Referenced Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedColumn()
	 * @generated
	 * @ordered
	 */
	protected Column referencedColumn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForeignKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PostgresPackage.Literals.FOREIGN_KEY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column getReferencedColumn() {
		if (referencedColumn != null && referencedColumn.eIsProxy()) {
			InternalEObject oldReferencedColumn = (InternalEObject)referencedColumn;
			referencedColumn = (Column)eResolveProxy(oldReferencedColumn);
			if (referencedColumn != oldReferencedColumn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PostgresPackage.FOREIGN_KEY__REFERENCED_COLUMN, oldReferencedColumn, referencedColumn));
			}
		}
		return referencedColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column basicGetReferencedColumn() {
		return referencedColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedColumn(Column newReferencedColumn) {
		Column oldReferencedColumn = referencedColumn;
		referencedColumn = newReferencedColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PostgresPackage.FOREIGN_KEY__REFERENCED_COLUMN, oldReferencedColumn, referencedColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PostgresPackage.FOREIGN_KEY__REFERENCED_COLUMN:
				if (resolve) return getReferencedColumn();
				return basicGetReferencedColumn();
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
			case PostgresPackage.FOREIGN_KEY__REFERENCED_COLUMN:
				setReferencedColumn((Column)newValue);
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
			case PostgresPackage.FOREIGN_KEY__REFERENCED_COLUMN:
				setReferencedColumn((Column)null);
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
			case PostgresPackage.FOREIGN_KEY__REFERENCED_COLUMN:
				return referencedColumn != null;
		}
		return super.eIsSet(featureID);
	}

} //ForeignKeyImpl
