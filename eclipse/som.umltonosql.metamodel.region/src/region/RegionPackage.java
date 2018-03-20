/**
 */
package region;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see region.RegionFactory
 * @model kind="package"
 * @generated
 */
public interface RegionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "region";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "som.umltonosql.metamodel.region";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "region";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RegionPackage eINSTANCE = region.impl.RegionPackageImpl.init();

	/**
	 * The meta object id for the '{@link region.impl.RegionSetImpl <em>Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see region.impl.RegionSetImpl
	 * @see region.impl.RegionPackageImpl#getRegionSet()
	 * @generated
	 */
	int REGION_SET = 0;

	/**
	 * The feature id for the '<em><b>Regions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_SET__REGIONS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_SET__NAME = 1;

	/**
	 * The number of structural features of the '<em>Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_SET_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_SET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link region.impl.RegionImpl <em>Region</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see region.impl.RegionImpl
	 * @see region.impl.RegionPackageImpl#getRegion()
	 * @generated
	 */
	int REGION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__CLASSES = 1;

	/**
	 * The feature id for the '<em><b>Associations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__ASSOCIATIONS = 2;

	/**
	 * The feature id for the '<em><b>Datastore Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__DATASTORE_DESCRIPTOR = 3;

	/**
	 * The number of structural features of the '<em>Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link region.impl.DatastoreDescriptorImpl <em>Datastore Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see region.impl.DatastoreDescriptorImpl
	 * @see region.impl.RegionPackageImpl#getDatastoreDescriptor()
	 * @generated
	 */
	int DATASTORE_DESCRIPTOR = 2;

	/**
	 * The number of structural features of the '<em>Datastore Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATASTORE_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Datastore Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATASTORE_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link region.impl.DrillDescriptorImpl <em>Drill Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see region.impl.DrillDescriptorImpl
	 * @see region.impl.RegionPackageImpl#getDrillDescriptor()
	 * @generated
	 */
	int DRILL_DESCRIPTOR = 3;

	/**
	 * The feature id for the '<em><b>Drill Driver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRILL_DESCRIPTOR__DRILL_DRIVER = DATASTORE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Drill Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRILL_DESCRIPTOR_FEATURE_COUNT = DATASTORE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Drill Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRILL_DESCRIPTOR_OPERATION_COUNT = DATASTORE_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link region.impl.MongoDescriptorImpl <em>Mongo Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see region.impl.MongoDescriptorImpl
	 * @see region.impl.RegionPackageImpl#getMongoDescriptor()
	 * @generated
	 */
	int MONGO_DESCRIPTOR = 4;

	/**
	 * The feature id for the '<em><b>Drill Driver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGO_DESCRIPTOR__DRILL_DRIVER = DRILL_DESCRIPTOR__DRILL_DRIVER;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGO_DESCRIPTOR__HOST = DRILL_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGO_DESCRIPTOR__PORT = DRILL_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Database Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGO_DESCRIPTOR__DATABASE_NAME = DRILL_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Mongo Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGO_DESCRIPTOR_FEATURE_COUNT = DRILL_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Mongo Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONGO_DESCRIPTOR_OPERATION_COUNT = DRILL_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link region.impl.PostgresDescriptorImpl <em>Postgres Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see region.impl.PostgresDescriptorImpl
	 * @see region.impl.RegionPackageImpl#getPostgresDescriptor()
	 * @generated
	 */
	int POSTGRES_DESCRIPTOR = 5;

	/**
	 * The feature id for the '<em><b>Drill Driver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTGRES_DESCRIPTOR__DRILL_DRIVER = DRILL_DESCRIPTOR__DRILL_DRIVER;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTGRES_DESCRIPTOR__HOST = DRILL_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTGRES_DESCRIPTOR__PORT = DRILL_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Database Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTGRES_DESCRIPTOR__DATABASE_NAME = DRILL_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Jdbc Driver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTGRES_DESCRIPTOR__JDBC_DRIVER = DRILL_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Postgres Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTGRES_DESCRIPTOR_FEATURE_COUNT = DRILL_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Postgres Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTGRES_DESCRIPTOR_OPERATION_COUNT = DRILL_DESCRIPTOR_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link region.RegionSet <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set</em>'.
	 * @see region.RegionSet
	 * @generated
	 */
	EClass getRegionSet();

	/**
	 * Returns the meta object for the containment reference list '{@link region.RegionSet#getRegions <em>Regions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Regions</em>'.
	 * @see region.RegionSet#getRegions()
	 * @see #getRegionSet()
	 * @generated
	 */
	EReference getRegionSet_Regions();

	/**
	 * Returns the meta object for the attribute '{@link region.RegionSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see region.RegionSet#getName()
	 * @see #getRegionSet()
	 * @generated
	 */
	EAttribute getRegionSet_Name();

	/**
	 * Returns the meta object for class '{@link region.Region <em>Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Region</em>'.
	 * @see region.Region
	 * @generated
	 */
	EClass getRegion();

	/**
	 * Returns the meta object for the attribute '{@link region.Region#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see region.Region#getName()
	 * @see #getRegion()
	 * @generated
	 */
	EAttribute getRegion_Name();

	/**
	 * Returns the meta object for the reference list '{@link region.Region#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Classes</em>'.
	 * @see region.Region#getClasses()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_Classes();

	/**
	 * Returns the meta object for the reference list '{@link region.Region#getAssociations <em>Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Associations</em>'.
	 * @see region.Region#getAssociations()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_Associations();

	/**
	 * Returns the meta object for the containment reference '{@link region.Region#getDatastoreDescriptor <em>Datastore Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Datastore Descriptor</em>'.
	 * @see region.Region#getDatastoreDescriptor()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_DatastoreDescriptor();

	/**
	 * Returns the meta object for class '{@link region.DatastoreDescriptor <em>Datastore Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Datastore Descriptor</em>'.
	 * @see region.DatastoreDescriptor
	 * @generated
	 */
	EClass getDatastoreDescriptor();

	/**
	 * Returns the meta object for class '{@link region.DrillDescriptor <em>Drill Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drill Descriptor</em>'.
	 * @see region.DrillDescriptor
	 * @generated
	 */
	EClass getDrillDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link region.DrillDescriptor#getDrillDriver <em>Drill Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Drill Driver</em>'.
	 * @see region.DrillDescriptor#getDrillDriver()
	 * @see #getDrillDescriptor()
	 * @generated
	 */
	EAttribute getDrillDescriptor_DrillDriver();

	/**
	 * Returns the meta object for class '{@link region.MongoDescriptor <em>Mongo Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mongo Descriptor</em>'.
	 * @see region.MongoDescriptor
	 * @generated
	 */
	EClass getMongoDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link region.MongoDescriptor#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host</em>'.
	 * @see region.MongoDescriptor#getHost()
	 * @see #getMongoDescriptor()
	 * @generated
	 */
	EAttribute getMongoDescriptor_Host();

	/**
	 * Returns the meta object for the attribute '{@link region.MongoDescriptor#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see region.MongoDescriptor#getPort()
	 * @see #getMongoDescriptor()
	 * @generated
	 */
	EAttribute getMongoDescriptor_Port();

	/**
	 * Returns the meta object for the attribute '{@link region.MongoDescriptor#getDatabaseName <em>Database Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Database Name</em>'.
	 * @see region.MongoDescriptor#getDatabaseName()
	 * @see #getMongoDescriptor()
	 * @generated
	 */
	EAttribute getMongoDescriptor_DatabaseName();

	/**
	 * Returns the meta object for class '{@link region.PostgresDescriptor <em>Postgres Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Postgres Descriptor</em>'.
	 * @see region.PostgresDescriptor
	 * @generated
	 */
	EClass getPostgresDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link region.PostgresDescriptor#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host</em>'.
	 * @see region.PostgresDescriptor#getHost()
	 * @see #getPostgresDescriptor()
	 * @generated
	 */
	EAttribute getPostgresDescriptor_Host();

	/**
	 * Returns the meta object for the attribute '{@link region.PostgresDescriptor#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see region.PostgresDescriptor#getPort()
	 * @see #getPostgresDescriptor()
	 * @generated
	 */
	EAttribute getPostgresDescriptor_Port();

	/**
	 * Returns the meta object for the attribute '{@link region.PostgresDescriptor#getDatabaseName <em>Database Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Database Name</em>'.
	 * @see region.PostgresDescriptor#getDatabaseName()
	 * @see #getPostgresDescriptor()
	 * @generated
	 */
	EAttribute getPostgresDescriptor_DatabaseName();

	/**
	 * Returns the meta object for the attribute '{@link region.PostgresDescriptor#getJdbcDriver <em>Jdbc Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jdbc Driver</em>'.
	 * @see region.PostgresDescriptor#getJdbcDriver()
	 * @see #getPostgresDescriptor()
	 * @generated
	 */
	EAttribute getPostgresDescriptor_JdbcDriver();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RegionFactory getRegionFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link region.impl.RegionSetImpl <em>Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see region.impl.RegionSetImpl
		 * @see region.impl.RegionPackageImpl#getRegionSet()
		 * @generated
		 */
		EClass REGION_SET = eINSTANCE.getRegionSet();

		/**
		 * The meta object literal for the '<em><b>Regions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION_SET__REGIONS = eINSTANCE.getRegionSet_Regions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGION_SET__NAME = eINSTANCE.getRegionSet_Name();

		/**
		 * The meta object literal for the '{@link region.impl.RegionImpl <em>Region</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see region.impl.RegionImpl
		 * @see region.impl.RegionPackageImpl#getRegion()
		 * @generated
		 */
		EClass REGION = eINSTANCE.getRegion();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGION__NAME = eINSTANCE.getRegion_Name();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__CLASSES = eINSTANCE.getRegion_Classes();

		/**
		 * The meta object literal for the '<em><b>Associations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__ASSOCIATIONS = eINSTANCE.getRegion_Associations();

		/**
		 * The meta object literal for the '<em><b>Datastore Descriptor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__DATASTORE_DESCRIPTOR = eINSTANCE.getRegion_DatastoreDescriptor();

		/**
		 * The meta object literal for the '{@link region.impl.DatastoreDescriptorImpl <em>Datastore Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see region.impl.DatastoreDescriptorImpl
		 * @see region.impl.RegionPackageImpl#getDatastoreDescriptor()
		 * @generated
		 */
		EClass DATASTORE_DESCRIPTOR = eINSTANCE.getDatastoreDescriptor();

		/**
		 * The meta object literal for the '{@link region.impl.DrillDescriptorImpl <em>Drill Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see region.impl.DrillDescriptorImpl
		 * @see region.impl.RegionPackageImpl#getDrillDescriptor()
		 * @generated
		 */
		EClass DRILL_DESCRIPTOR = eINSTANCE.getDrillDescriptor();

		/**
		 * The meta object literal for the '<em><b>Drill Driver</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRILL_DESCRIPTOR__DRILL_DRIVER = eINSTANCE.getDrillDescriptor_DrillDriver();

		/**
		 * The meta object literal for the '{@link region.impl.MongoDescriptorImpl <em>Mongo Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see region.impl.MongoDescriptorImpl
		 * @see region.impl.RegionPackageImpl#getMongoDescriptor()
		 * @generated
		 */
		EClass MONGO_DESCRIPTOR = eINSTANCE.getMongoDescriptor();

		/**
		 * The meta object literal for the '<em><b>Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONGO_DESCRIPTOR__HOST = eINSTANCE.getMongoDescriptor_Host();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONGO_DESCRIPTOR__PORT = eINSTANCE.getMongoDescriptor_Port();

		/**
		 * The meta object literal for the '<em><b>Database Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONGO_DESCRIPTOR__DATABASE_NAME = eINSTANCE.getMongoDescriptor_DatabaseName();

		/**
		 * The meta object literal for the '{@link region.impl.PostgresDescriptorImpl <em>Postgres Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see region.impl.PostgresDescriptorImpl
		 * @see region.impl.RegionPackageImpl#getPostgresDescriptor()
		 * @generated
		 */
		EClass POSTGRES_DESCRIPTOR = eINSTANCE.getPostgresDescriptor();

		/**
		 * The meta object literal for the '<em><b>Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTGRES_DESCRIPTOR__HOST = eINSTANCE.getPostgresDescriptor_Host();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTGRES_DESCRIPTOR__PORT = eINSTANCE.getPostgresDescriptor_Port();

		/**
		 * The meta object literal for the '<em><b>Database Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTGRES_DESCRIPTOR__DATABASE_NAME = eINSTANCE.getPostgresDescriptor_DatabaseName();

		/**
		 * The meta object literal for the '<em><b>Jdbc Driver</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTGRES_DESCRIPTOR__JDBC_DRIVER = eINSTANCE.getPostgresDescriptor_JdbcDriver();

	}

} //RegionPackage
