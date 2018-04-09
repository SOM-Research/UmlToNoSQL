/**
 */
package postgres.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import postgres.Column;
import postgres.PostgresPackage;
import postgres.Table;
import postgres.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link postgres.impl.ColumnImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link postgres.impl.ColumnImpl#getKeyOf <em>Key Of</em>}</li>
 *   <li>{@link postgres.impl.ColumnImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ColumnImpl extends NamedImpl implements Column {
	/**
	 * The cached value of the '{@link #getKeyOf() <em>Key Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyOf()
	 * @generated
	 * @ordered
	 */
	protected Table keyOf;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PostgresPackage.Literals.COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getOwner() {
		if (eContainerFeatureID() != PostgresPackage.COLUMN__OWNER) return null;
		return (Table)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(Table newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, PostgresPackage.COLUMN__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(Table newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != PostgresPackage.COLUMN__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, PostgresPackage.TABLE__COL, Table.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PostgresPackage.COLUMN__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getKeyOf() {
		if (keyOf != null && keyOf.eIsProxy()) {
			InternalEObject oldKeyOf = (InternalEObject)keyOf;
			keyOf = (Table)eResolveProxy(oldKeyOf);
			if (keyOf != oldKeyOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PostgresPackage.COLUMN__KEY_OF, oldKeyOf, keyOf));
			}
		}
		return keyOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetKeyOf() {
		return keyOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKeyOf(Table newKeyOf, NotificationChain msgs) {
		Table oldKeyOf = keyOf;
		keyOf = newKeyOf;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PostgresPackage.COLUMN__KEY_OF, oldKeyOf, newKeyOf);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeyOf(Table newKeyOf) {
		if (newKeyOf != keyOf) {
			NotificationChain msgs = null;
			if (keyOf != null)
				msgs = ((InternalEObject)keyOf).eInverseRemove(this, PostgresPackage.TABLE__KEY, Table.class, msgs);
			if (newKeyOf != null)
				msgs = ((InternalEObject)newKeyOf).eInverseAdd(this, PostgresPackage.TABLE__KEY, Table.class, msgs);
			msgs = basicSetKeyOf(newKeyOf, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PostgresPackage.COLUMN__KEY_OF, newKeyOf, newKeyOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Type newType, NotificationChain msgs) {
		Type oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PostgresPackage.COLUMN__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PostgresPackage.COLUMN__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PostgresPackage.COLUMN__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PostgresPackage.COLUMN__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PostgresPackage.COLUMN__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((Table)otherEnd, msgs);
			case PostgresPackage.COLUMN__KEY_OF:
				if (keyOf != null)
					msgs = ((InternalEObject)keyOf).eInverseRemove(this, PostgresPackage.TABLE__KEY, Table.class, msgs);
				return basicSetKeyOf((Table)otherEnd, msgs);
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
			case PostgresPackage.COLUMN__OWNER:
				return basicSetOwner(null, msgs);
			case PostgresPackage.COLUMN__KEY_OF:
				return basicSetKeyOf(null, msgs);
			case PostgresPackage.COLUMN__TYPE:
				return basicSetType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case PostgresPackage.COLUMN__OWNER:
				return eInternalContainer().eInverseRemove(this, PostgresPackage.TABLE__COL, Table.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PostgresPackage.COLUMN__OWNER:
				return getOwner();
			case PostgresPackage.COLUMN__KEY_OF:
				if (resolve) return getKeyOf();
				return basicGetKeyOf();
			case PostgresPackage.COLUMN__TYPE:
				return getType();
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
			case PostgresPackage.COLUMN__OWNER:
				setOwner((Table)newValue);
				return;
			case PostgresPackage.COLUMN__KEY_OF:
				setKeyOf((Table)newValue);
				return;
			case PostgresPackage.COLUMN__TYPE:
				setType((Type)newValue);
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
			case PostgresPackage.COLUMN__OWNER:
				setOwner((Table)null);
				return;
			case PostgresPackage.COLUMN__KEY_OF:
				setKeyOf((Table)null);
				return;
			case PostgresPackage.COLUMN__TYPE:
				setType((Type)null);
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
			case PostgresPackage.COLUMN__OWNER:
				return getOwner() != null;
			case PostgresPackage.COLUMN__KEY_OF:
				return keyOf != null;
			case PostgresPackage.COLUMN__TYPE:
				return type != null;
		}
		return super.eIsSet(featureID);
	}

} //ColumnImpl