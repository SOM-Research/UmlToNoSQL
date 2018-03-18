/**
 */
package region.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.uml2.uml.Association;

import region.DatastoreDescriptor;
import region.Region;
import region.RegionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Region</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link region.impl.RegionImpl#getName <em>Name</em>}</li>
 *   <li>{@link region.impl.RegionImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link region.impl.RegionImpl#getAssociations <em>Associations</em>}</li>
 *   <li>{@link region.impl.RegionImpl#getDatastoreDescriptor <em>Datastore Descriptor</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RegionImpl extends MinimalEObjectImpl.Container implements Region {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.uml2.uml.Class> classes;

	/**
	 * The cached value of the '{@link #getAssociations() <em>Associations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> associations;

	/**
	 * The cached value of the '{@link #getDatastoreDescriptor() <em>Datastore Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatastoreDescriptor()
	 * @generated
	 * @ordered
	 */
	protected DatastoreDescriptor datastoreDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RegionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RegionPackage.Literals.REGION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RegionPackage.REGION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<org.eclipse.uml2.uml.Class> getClasses() {
		if (classes == null) {
			classes = new EObjectResolvingEList<org.eclipse.uml2.uml.Class>(org.eclipse.uml2.uml.Class.class, this, RegionPackage.REGION__CLASSES);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAssociations() {
		if (associations == null) {
			associations = new EObjectResolvingEList<Association>(Association.class, this, RegionPackage.REGION__ASSOCIATIONS);
		}
		return associations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatastoreDescriptor getDatastoreDescriptor() {
		return datastoreDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatastoreDescriptor(DatastoreDescriptor newDatastoreDescriptor, NotificationChain msgs) {
		DatastoreDescriptor oldDatastoreDescriptor = datastoreDescriptor;
		datastoreDescriptor = newDatastoreDescriptor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RegionPackage.REGION__DATASTORE_DESCRIPTOR, oldDatastoreDescriptor, newDatastoreDescriptor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatastoreDescriptor(DatastoreDescriptor newDatastoreDescriptor) {
		if (newDatastoreDescriptor != datastoreDescriptor) {
			NotificationChain msgs = null;
			if (datastoreDescriptor != null)
				msgs = ((InternalEObject)datastoreDescriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RegionPackage.REGION__DATASTORE_DESCRIPTOR, null, msgs);
			if (newDatastoreDescriptor != null)
				msgs = ((InternalEObject)newDatastoreDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RegionPackage.REGION__DATASTORE_DESCRIPTOR, null, msgs);
			msgs = basicSetDatastoreDescriptor(newDatastoreDescriptor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RegionPackage.REGION__DATASTORE_DESCRIPTOR, newDatastoreDescriptor, newDatastoreDescriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RegionPackage.REGION__DATASTORE_DESCRIPTOR:
				return basicSetDatastoreDescriptor(null, msgs);
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
			case RegionPackage.REGION__NAME:
				return getName();
			case RegionPackage.REGION__CLASSES:
				return getClasses();
			case RegionPackage.REGION__ASSOCIATIONS:
				return getAssociations();
			case RegionPackage.REGION__DATASTORE_DESCRIPTOR:
				return getDatastoreDescriptor();
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
			case RegionPackage.REGION__NAME:
				setName((String)newValue);
				return;
			case RegionPackage.REGION__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends org.eclipse.uml2.uml.Class>)newValue);
				return;
			case RegionPackage.REGION__ASSOCIATIONS:
				getAssociations().clear();
				getAssociations().addAll((Collection<? extends Association>)newValue);
				return;
			case RegionPackage.REGION__DATASTORE_DESCRIPTOR:
				setDatastoreDescriptor((DatastoreDescriptor)newValue);
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
			case RegionPackage.REGION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RegionPackage.REGION__CLASSES:
				getClasses().clear();
				return;
			case RegionPackage.REGION__ASSOCIATIONS:
				getAssociations().clear();
				return;
			case RegionPackage.REGION__DATASTORE_DESCRIPTOR:
				setDatastoreDescriptor((DatastoreDescriptor)null);
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
			case RegionPackage.REGION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RegionPackage.REGION__CLASSES:
				return classes != null && !classes.isEmpty();
			case RegionPackage.REGION__ASSOCIATIONS:
				return associations != null && !associations.isEmpty();
			case RegionPackage.REGION__DATASTORE_DESCRIPTOR:
				return datastoreDescriptor != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //RegionImpl
