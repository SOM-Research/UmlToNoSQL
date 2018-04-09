/**
 */
package postgres;

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
 *   <li>{@link postgres.Database#getTables <em>Tables</em>}</li>
 * </ul>
 *
 * @see postgres.PostgresPackage#getDatabase()
 * @model
 * @generated
 */
public interface Database extends Named {
	/**
	 * Returns the value of the '<em><b>Tables</b></em>' reference list.
	 * The list contents are of type {@link postgres.Table}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tables</em>' reference list.
	 * @see postgres.PostgresPackage#getDatabase_Tables()
	 * @model
	 * @generated
	 */
	EList<Table> getTables();

} // Database
