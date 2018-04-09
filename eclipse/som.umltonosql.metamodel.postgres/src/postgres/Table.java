/**
 */
package postgres;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link postgres.Table#getCol <em>Col</em>}</li>
 *   <li>{@link postgres.Table#getKey <em>Key</em>}</li>
 * </ul>
 *
 * @see postgres.PostgresPackage#getTable()
 * @model
 * @generated
 */
public interface Table extends Named {
	/**
	 * Returns the value of the '<em><b>Col</b></em>' containment reference list.
	 * The list contents are of type {@link postgres.Column}.
	 * It is bidirectional and its opposite is '{@link postgres.Column#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Col</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Col</em>' containment reference list.
	 * @see postgres.PostgresPackage#getTable_Col()
	 * @see postgres.Column#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<Column> getCol();

	/**
	 * Returns the value of the '<em><b>Key</b></em>' reference list.
	 * The list contents are of type {@link postgres.Column}.
	 * It is bidirectional and its opposite is '{@link postgres.Column#getKeyOf <em>Key Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' reference list.
	 * @see postgres.PostgresPackage#getTable_Key()
	 * @see postgres.Column#getKeyOf
	 * @model opposite="keyOf" ordered="false"
	 * @generated
	 */
	EList<Column> getKey();

} // Table
