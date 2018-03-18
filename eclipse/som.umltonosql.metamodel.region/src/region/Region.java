/**
 */
package region;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Association;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link region.Region#getName <em>Name</em>}</li>
 *   <li>{@link region.Region#getClasses <em>Classes</em>}</li>
 *   <li>{@link region.Region#getAssociations <em>Associations</em>}</li>
 *   <li>{@link region.Region#getDatastoreDescriptor <em>Datastore Descriptor</em>}</li>
 * </ul>
 *
 * @see region.RegionPackage#getRegion()
 * @model
 * @generated
 */
public interface Region extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see region.RegionPackage#getRegion_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link region.Region#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' reference list.
	 * @see region.RegionPackage#getRegion_Classes()
	 * @model
	 * @generated
	 */
	EList<org.eclipse.uml2.uml.Class> getClasses();

	/**
	 * Returns the value of the '<em><b>Associations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associations</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associations</em>' reference list.
	 * @see region.RegionPackage#getRegion_Associations()
	 * @model
	 * @generated
	 */
	EList<Association> getAssociations();

	/**
	 * Returns the value of the '<em><b>Datastore Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Datastore Descriptor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Datastore Descriptor</em>' containment reference.
	 * @see #setDatastoreDescriptor(DatastoreDescriptor)
	 * @see region.RegionPackage#getRegion_DatastoreDescriptor()
	 * @model containment="true"
	 * @generated
	 */
	DatastoreDescriptor getDatastoreDescriptor();

	/**
	 * Sets the value of the '{@link region.Region#getDatastoreDescriptor <em>Datastore Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Datastore Descriptor</em>' containment reference.
	 * @see #getDatastoreDescriptor()
	 * @generated
	 */
	void setDatastoreDescriptor(DatastoreDescriptor value);

} // Region
