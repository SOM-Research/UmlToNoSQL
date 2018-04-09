/**
 */
package postgres;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Varchar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link postgres.Varchar#getLength <em>Length</em>}</li>
 * </ul>
 *
 * @see postgres.PostgresPackage#getVarchar()
 * @model
 * @generated
 */
public interface Varchar extends PrimitiveType {
	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(int)
	 * @see postgres.PostgresPackage#getVarchar_Length()
	 * @model
	 * @generated
	 */
	int getLength();

	/**
	 * Sets the value of the '{@link postgres.Varchar#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(int value);

} // Varchar
