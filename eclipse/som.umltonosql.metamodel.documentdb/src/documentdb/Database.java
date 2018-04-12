/**
 */
package documentdb;

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
 *   <li>{@link documentdb.Database#getCollections <em>Collections</em>}</li>
 *   <li>{@link documentdb.Database#getRawDatabase <em>Raw Database</em>}</li>
 * </ul>
 *
 * @see documentdb.DocumentdbPackage#getDatabase()
 * @model
 * @generated
 */
public interface Database extends EObject {
	/**
	 * Returns the value of the '<em><b>Collections</b></em>' containment reference list.
	 * The list contents are of type {@link documentdb.Collection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collections</em>' containment reference list.
	 * @see documentdb.DocumentdbPackage#getDatabase_Collections()
	 * @model containment="true"
	 * @generated
	 */
	EList<Collection> getCollections();

	/**
	 * Returns the value of the '<em><b>Raw Database</b></em>' attribute.
	 * The literals are from the enumeration {@link documentdb.DatabaseKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Raw Database</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Raw Database</em>' attribute.
	 * @see documentdb.DatabaseKind
	 * @see #setRawDatabase(DatabaseKind)
	 * @see documentdb.DocumentdbPackage#getDatabase_RawDatabase()
	 * @model
	 * @generated
	 */
	DatabaseKind getRawDatabase();

	/**
	 * Sets the value of the '{@link documentdb.Database#getRawDatabase <em>Raw Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Raw Database</em>' attribute.
	 * @see documentdb.DatabaseKind
	 * @see #getRawDatabase()
	 * @generated
	 */
	void setRawDatabase(DatabaseKind value);

} // Database
