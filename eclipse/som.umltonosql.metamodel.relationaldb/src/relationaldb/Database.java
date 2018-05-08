/**
 */
package relationaldb;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relationaldb.Database#getTables <em>Tables</em>}</li>
 *   <li>{@link relationaldb.Database#getRawDatabase <em>Raw Database</em>}</li>
 * </ul>
 *
 * @see relationaldb.RelationaldbPackage#getDatabase()
 * @model
 * @generated
 */
public interface Database extends Named {
	/**
	 * Returns the value of the '<em><b>Tables</b></em>' containment reference list.
	 * The list contents are of type {@link relationaldb.Table}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tables</em>' containment reference list.
	 * @see relationaldb.RelationaldbPackage#getDatabase_Tables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Table> getTables();

	/**
	 * Returns the value of the '<em><b>Raw Database</b></em>' attribute.
	 * The literals are from the enumeration {@link relationaldb.DatabaseKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Raw Database</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Raw Database</em>' attribute.
	 * @see relationaldb.DatabaseKind
	 * @see #setRawDatabase(DatabaseKind)
	 * @see relationaldb.RelationaldbPackage#getDatabase_RawDatabase()
	 * @model
	 * @generated
	 */
	DatabaseKind getRawDatabase();

	/**
	 * Sets the value of the '{@link relationaldb.Database#getRawDatabase <em>Raw Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Raw Database</em>' attribute.
	 * @see relationaldb.DatabaseKind
	 * @see #getRawDatabase()
	 * @generated
	 */
	void setRawDatabase(DatabaseKind value);

} // Database
