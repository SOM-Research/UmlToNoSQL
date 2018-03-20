/**
 */
package region.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import region.PostgresDescriptor;
import region.RegionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Postgres Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link region.impl.PostgresDescriptorImpl#getHost <em>Host</em>}</li>
 *   <li>{@link region.impl.PostgresDescriptorImpl#getPort <em>Port</em>}</li>
 *   <li>{@link region.impl.PostgresDescriptorImpl#getDatabaseName <em>Database Name</em>}</li>
 *   <li>{@link region.impl.PostgresDescriptorImpl#getJdbcDriver <em>Jdbc Driver</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PostgresDescriptorImpl extends DrillDescriptorImpl implements PostgresDescriptor {
	/**
	 * The default value of the '{@link #getHost() <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected static final String HOST_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getHost() <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected String host = HOST_EDEFAULT;
	/**
	 * The default value of the '{@link #getPort() <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPort()
	 * @generated
	 * @ordered
	 */
	protected static final int PORT_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getPort() <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPort()
	 * @generated
	 * @ordered
	 */
	protected int port = PORT_EDEFAULT;
	/**
	 * The default value of the '{@link #getDatabaseName() <em>Database Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabaseName()
	 * @generated
	 * @ordered
	 */
	protected static final String DATABASE_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getDatabaseName() <em>Database Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabaseName()
	 * @generated
	 * @ordered
	 */
	protected String databaseName = DATABASE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getJdbcDriver() <em>Jdbc Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJdbcDriver()
	 * @generated
	 * @ordered
	 */
	protected static final String JDBC_DRIVER_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getJdbcDriver() <em>Jdbc Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJdbcDriver()
	 * @generated
	 * @ordered
	 */
	protected String jdbcDriver = JDBC_DRIVER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PostgresDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RegionPackage.Literals.POSTGRES_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHost() {
		return host;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHost(String newHost) {
		String oldHost = host;
		host = newHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RegionPackage.POSTGRES_DESCRIPTOR__HOST, oldHost, host));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPort() {
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPort(int newPort) {
		int oldPort = port;
		port = newPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RegionPackage.POSTGRES_DESCRIPTOR__PORT, oldPort, port));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabaseName(String newDatabaseName) {
		String oldDatabaseName = databaseName;
		databaseName = newDatabaseName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RegionPackage.POSTGRES_DESCRIPTOR__DATABASE_NAME, oldDatabaseName, databaseName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJdbcDriver() {
		return jdbcDriver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJdbcDriver(String newJdbcDriver) {
		String oldJdbcDriver = jdbcDriver;
		jdbcDriver = newJdbcDriver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RegionPackage.POSTGRES_DESCRIPTOR__JDBC_DRIVER, oldJdbcDriver, jdbcDriver));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RegionPackage.POSTGRES_DESCRIPTOR__HOST:
				return getHost();
			case RegionPackage.POSTGRES_DESCRIPTOR__PORT:
				return getPort();
			case RegionPackage.POSTGRES_DESCRIPTOR__DATABASE_NAME:
				return getDatabaseName();
			case RegionPackage.POSTGRES_DESCRIPTOR__JDBC_DRIVER:
				return getJdbcDriver();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RegionPackage.POSTGRES_DESCRIPTOR__HOST:
				setHost((String)newValue);
				return;
			case RegionPackage.POSTGRES_DESCRIPTOR__PORT:
				setPort((Integer)newValue);
				return;
			case RegionPackage.POSTGRES_DESCRIPTOR__DATABASE_NAME:
				setDatabaseName((String)newValue);
				return;
			case RegionPackage.POSTGRES_DESCRIPTOR__JDBC_DRIVER:
				setJdbcDriver((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RegionPackage.POSTGRES_DESCRIPTOR__HOST:
				setHost(HOST_EDEFAULT);
				return;
			case RegionPackage.POSTGRES_DESCRIPTOR__PORT:
				setPort(PORT_EDEFAULT);
				return;
			case RegionPackage.POSTGRES_DESCRIPTOR__DATABASE_NAME:
				setDatabaseName(DATABASE_NAME_EDEFAULT);
				return;
			case RegionPackage.POSTGRES_DESCRIPTOR__JDBC_DRIVER:
				setJdbcDriver(JDBC_DRIVER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RegionPackage.POSTGRES_DESCRIPTOR__HOST:
				return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
			case RegionPackage.POSTGRES_DESCRIPTOR__PORT:
				return port != PORT_EDEFAULT;
			case RegionPackage.POSTGRES_DESCRIPTOR__DATABASE_NAME:
				return DATABASE_NAME_EDEFAULT == null ? databaseName != null : !DATABASE_NAME_EDEFAULT.equals(databaseName);
			case RegionPackage.POSTGRES_DESCRIPTOR__JDBC_DRIVER:
				return JDBC_DRIVER_EDEFAULT == null ? jdbcDriver != null : !JDBC_DRIVER_EDEFAULT.equals(jdbcDriver);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (host: ");
		result.append(host);
		result.append(", port: ");
		result.append(port);
		result.append(", databaseName: ");
		result.append(databaseName);
		result.append(", jdbcDriver: ");
		result.append(jdbcDriver);
		result.append(')');
		return result.toString();
	}

} //PostgresDescriptorImpl
