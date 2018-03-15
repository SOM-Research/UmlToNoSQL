/**
 */
package mongodb;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mongodb.Database#getCollections <em>Collections</em>}</li>
 * </ul>
 *
 * @see mongodb.MongodbPackage#getDatabase()
 * @model
 * @generated
 */
public interface Database extends EObject {
	/**
	 * Returns the value of the '<em><b>Collections</b></em>' reference list.
	 * The list contents are of type {@link mongodb.Collection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collections</em>' reference list.
	 * @see mongodb.MongodbPackage#getDatabase_Collections()
	 * @model
	 * @generated
	 */
	EList<Collection> getCollections();

} // Database
