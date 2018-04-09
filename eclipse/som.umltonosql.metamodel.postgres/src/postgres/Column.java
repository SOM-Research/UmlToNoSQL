/**
 */
package postgres;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link postgres.Column#getOwner <em>Owner</em>}</li>
 *   <li>{@link postgres.Column#getKeyOf <em>Key Of</em>}</li>
 *   <li>{@link postgres.Column#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see postgres.PostgresPackage#getColumn()
 * @model
 * @generated
 */
public interface Column extends Named {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link postgres.Table#getCol <em>Col</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(Table)
	 * @see postgres.PostgresPackage#getColumn_Owner()
	 * @see postgres.Table#getCol
	 * @model opposite="col" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Table getOwner();

	/**
	 * Sets the value of the '{@link postgres.Column#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(Table value);

	/**
	 * Returns the value of the '<em><b>Key Of</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link postgres.Table#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Of</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Of</em>' reference.
	 * @see #setKeyOf(Table)
	 * @see postgres.PostgresPackage#getColumn_KeyOf()
	 * @see postgres.Table#getKey
	 * @model opposite="key" ordered="false"
	 * @generated
	 */
	Table getKeyOf();

	/**
	 * Sets the value of the '{@link postgres.Column#getKeyOf <em>Key Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key Of</em>' reference.
	 * @see #getKeyOf()
	 * @generated
	 */
	void setKeyOf(Table value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Type)
	 * @see postgres.PostgresPackage#getColumn_Type()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link postgres.Column#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

} // Column
