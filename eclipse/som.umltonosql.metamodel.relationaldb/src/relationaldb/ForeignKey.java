/**
 */
package relationaldb;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relationaldb.ForeignKey#getReferencedColumn <em>Referenced Column</em>}</li>
 * </ul>
 *
 * @see relationaldb.RelationaldbPackage#getForeignKey()
 * @model
 * @generated
 */
public interface ForeignKey extends Column {
	/**
	 * Returns the value of the '<em><b>Referenced Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Column</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Column</em>' reference.
	 * @see #setReferencedColumn(Column)
	 * @see relationaldb.RelationaldbPackage#getForeignKey_ReferencedColumn()
	 * @model
	 * @generated
	 */
	Column getReferencedColumn();

	/**
	 * Sets the value of the '{@link relationaldb.ForeignKey#getReferencedColumn <em>Referenced Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Column</em>' reference.
	 * @see #getReferencedColumn()
	 * @generated
	 */
	void setReferencedColumn(Column value);

} // ForeignKey
