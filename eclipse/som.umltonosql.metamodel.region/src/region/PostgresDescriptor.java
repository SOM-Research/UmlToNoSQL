/**
 */
package region;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Postgres Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link region.PostgresDescriptor#getHost <em>Host</em>}</li>
 *   <li>{@link region.PostgresDescriptor#getPort <em>Port</em>}</li>
 *   <li>{@link region.PostgresDescriptor#getDatabaseName <em>Database Name</em>}</li>
 *   <li>{@link region.PostgresDescriptor#getJdbcDriver <em>Jdbc Driver</em>}</li>
 * </ul>
 *
 * @see region.RegionPackage#getPostgresDescriptor()
 * @model
 * @generated
 */
public interface PostgresDescriptor extends DrillDescriptor {

	/**
	 * Returns the value of the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host</em>' attribute.
	 * @see #setHost(String)
	 * @see region.RegionPackage#getPostgresDescriptor_Host()
	 * @model
	 * @generated
	 */
	String getHost();

	/**
	 * Sets the value of the '{@link region.PostgresDescriptor#getHost <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' attribute.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(String value);

	/**
	 * Returns the value of the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' attribute.
	 * @see #setPort(int)
	 * @see region.RegionPackage#getPostgresDescriptor_Port()
	 * @model
	 * @generated
	 */
	int getPort();

	/**
	 * Sets the value of the '{@link region.PostgresDescriptor#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(int value);

	/**
	 * Returns the value of the '<em><b>Database Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database Name</em>' attribute.
	 * @see #setDatabaseName(String)
	 * @see region.RegionPackage#getPostgresDescriptor_DatabaseName()
	 * @model
	 * @generated
	 */
	String getDatabaseName();

	/**
	 * Sets the value of the '{@link region.PostgresDescriptor#getDatabaseName <em>Database Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database Name</em>' attribute.
	 * @see #getDatabaseName()
	 * @generated
	 */
	void setDatabaseName(String value);

	/**
	 * Returns the value of the '<em><b>Jdbc Driver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jdbc Driver</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jdbc Driver</em>' attribute.
	 * @see #setJdbcDriver(String)
	 * @see region.RegionPackage#getPostgresDescriptor_JdbcDriver()
	 * @model
	 * @generated
	 */
	String getJdbcDriver();

	/**
	 * Sets the value of the '{@link region.PostgresDescriptor#getJdbcDriver <em>Jdbc Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jdbc Driver</em>' attribute.
	 * @see #getJdbcDriver()
	 * @generated
	 */
	void setJdbcDriver(String value);
} // PostgresDescriptor
