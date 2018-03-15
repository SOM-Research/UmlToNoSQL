/**
 */
package mongodb;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uml To No SQLID Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mongodb.UmlToNoSQLIDReference#getReferencedType <em>Referenced Type</em>}</li>
 * </ul>
 *
 * @see mongodb.MongodbPackage#getUmlToNoSQLIDReference()
 * @model
 * @generated
 */
public interface UmlToNoSQLIDReference extends Type {
	/**
	 * Returns the value of the '<em><b>Referenced Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Type</em>' attribute.
	 * @see #setReferencedType(String)
	 * @see mongodb.MongodbPackage#getUmlToNoSQLIDReference_ReferencedType()
	 * @model
	 * @generated
	 */
	String getReferencedType();

	/**
	 * Sets the value of the '{@link mongodb.UmlToNoSQLIDReference#getReferencedType <em>Referenced Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Type</em>' attribute.
	 * @see #getReferencedType()
	 * @generated
	 */
	void setReferencedType(String value);

} // UmlToNoSQLIDReference
