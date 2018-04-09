/**
 */
package postgres;

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
 * @see postgres.PostgresFactory
 * @model kind="package"
 * @generated
 */
public interface PostgresPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "postgres";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "som.umltonosql.metamodel.postgres";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "postgres";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PostgresPackage eINSTANCE = postgres.impl.PostgresPackageImpl.init();

	/**
	 * The meta object id for the '{@link postgres.impl.NamedImpl <em>Named</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.NamedImpl
	 * @see postgres.impl.PostgresPackageImpl#getNamed()
	 * @generated
	 */
	int NAMED = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link postgres.impl.DatabaseImpl <em>Database</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.DatabaseImpl
	 * @see postgres.impl.PostgresPackageImpl#getDatabase()
	 * @generated
	 */
	int DATABASE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__TABLES = NAMED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_FEATURE_COUNT = NAMED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_OPERATION_COUNT = NAMED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link postgres.impl.TableImpl <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.TableImpl
	 * @see postgres.impl.PostgresPackageImpl#getTable()
	 * @generated
	 */
	int TABLE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>Col</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__COL = NAMED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__KEY = NAMED_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_FEATURE_COUNT = NAMED_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OPERATION_COUNT = NAMED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link postgres.impl.ColumnImpl <em>Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.ColumnImpl
	 * @see postgres.impl.PostgresPackageImpl#getColumn()
	 * @generated
	 */
	int COLUMN = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__OWNER = NAMED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__KEY_OF = NAMED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__TYPE = NAMED_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_FEATURE_COUNT = NAMED_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_OPERATION_COUNT = NAMED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link postgres.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.TypeImpl
	 * @see postgres.impl.PostgresPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 4;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link postgres.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.PrimitiveTypeImpl
	 * @see postgres.impl.PostgresPackageImpl#getPrimitiveType()
	 * @generated
	 */
	int PRIMITIVE_TYPE = 5;

	/**
	 * The number of structural features of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link postgres.impl.VarcharImpl <em>Varchar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.VarcharImpl
	 * @see postgres.impl.PostgresPackageImpl#getVarchar()
	 * @generated
	 */
	int VARCHAR = 6;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARCHAR__LENGTH = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Varchar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARCHAR_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Varchar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARCHAR_OPERATION_COUNT = PRIMITIVE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link postgres.impl.IntegerImpl <em>Integer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.IntegerImpl
	 * @see postgres.impl.PostgresPackageImpl#getInteger()
	 * @generated
	 */
	int INTEGER = 7;

	/**
	 * The number of structural features of the '<em>Integer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Integer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OPERATION_COUNT = PRIMITIVE_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link postgres.impl.UmlToNoSQLIDImpl <em>Uml To No SQLID</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see postgres.impl.UmlToNoSQLIDImpl
	 * @see postgres.impl.PostgresPackageImpl#getUmlToNoSQLID()
	 * @generated
	 */
	int UML_TO_NO_SQLID = 8;

	/**
	 * The number of structural features of the '<em>Uml To No SQLID</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UML_TO_NO_SQLID_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Uml To No SQLID</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UML_TO_NO_SQLID_OPERATION_COUNT = PRIMITIVE_TYPE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link postgres.Named <em>Named</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named</em>'.
	 * @see postgres.Named
	 * @generated
	 */
	EClass getNamed();

	/**
	 * Returns the meta object for the attribute '{@link postgres.Named#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see postgres.Named#getName()
	 * @see #getNamed()
	 * @generated
	 */
	EAttribute getNamed_Name();

	/**
	 * Returns the meta object for class '{@link postgres.Database <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database</em>'.
	 * @see postgres.Database
	 * @generated
	 */
	EClass getDatabase();

	/**
	 * Returns the meta object for the reference list '{@link postgres.Database#getTables <em>Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tables</em>'.
	 * @see postgres.Database#getTables()
	 * @see #getDatabase()
	 * @generated
	 */
	EReference getDatabase_Tables();

	/**
	 * Returns the meta object for class '{@link postgres.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see postgres.Table
	 * @generated
	 */
	EClass getTable();

	/**
	 * Returns the meta object for the containment reference list '{@link postgres.Table#getCol <em>Col</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Col</em>'.
	 * @see postgres.Table#getCol()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Col();

	/**
	 * Returns the meta object for the reference list '{@link postgres.Table#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Key</em>'.
	 * @see postgres.Table#getKey()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Key();

	/**
	 * Returns the meta object for class '{@link postgres.Column <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column</em>'.
	 * @see postgres.Column
	 * @generated
	 */
	EClass getColumn();

	/**
	 * Returns the meta object for the container reference '{@link postgres.Column#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see postgres.Column#getOwner()
	 * @see #getColumn()
	 * @generated
	 */
	EReference getColumn_Owner();

	/**
	 * Returns the meta object for the reference '{@link postgres.Column#getKeyOf <em>Key Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key Of</em>'.
	 * @see postgres.Column#getKeyOf()
	 * @see #getColumn()
	 * @generated
	 */
	EReference getColumn_KeyOf();

	/**
	 * Returns the meta object for the reference '{@link postgres.Column#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see postgres.Column#getType()
	 * @see #getColumn()
	 * @generated
	 */
	EReference getColumn_Type();

	/**
	 * Returns the meta object for class '{@link postgres.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see postgres.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for class '{@link postgres.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Type</em>'.
	 * @see postgres.PrimitiveType
	 * @generated
	 */
	EClass getPrimitiveType();

	/**
	 * Returns the meta object for class '{@link postgres.Varchar <em>Varchar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Varchar</em>'.
	 * @see postgres.Varchar
	 * @generated
	 */
	EClass getVarchar();

	/**
	 * Returns the meta object for the attribute '{@link postgres.Varchar#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see postgres.Varchar#getLength()
	 * @see #getVarchar()
	 * @generated
	 */
	EAttribute getVarchar_Length();

	/**
	 * Returns the meta object for class '{@link postgres.Integer <em>Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer</em>'.
	 * @see postgres.Integer
	 * @generated
	 */
	EClass getInteger();

	/**
	 * Returns the meta object for class '{@link postgres.UmlToNoSQLID <em>Uml To No SQLID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uml To No SQLID</em>'.
	 * @see postgres.UmlToNoSQLID
	 * @generated
	 */
	EClass getUmlToNoSQLID();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PostgresFactory getPostgresFactory();

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
		 * The meta object literal for the '{@link postgres.impl.NamedImpl <em>Named</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.NamedImpl
		 * @see postgres.impl.PostgresPackageImpl#getNamed()
		 * @generated
		 */
		EClass NAMED = eINSTANCE.getNamed();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED__NAME = eINSTANCE.getNamed_Name();

		/**
		 * The meta object literal for the '{@link postgres.impl.DatabaseImpl <em>Database</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.DatabaseImpl
		 * @see postgres.impl.PostgresPackageImpl#getDatabase()
		 * @generated
		 */
		EClass DATABASE = eINSTANCE.getDatabase();

		/**
		 * The meta object literal for the '<em><b>Tables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE__TABLES = eINSTANCE.getDatabase_Tables();

		/**
		 * The meta object literal for the '{@link postgres.impl.TableImpl <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.TableImpl
		 * @see postgres.impl.PostgresPackageImpl#getTable()
		 * @generated
		 */
		EClass TABLE = eINSTANCE.getTable();

		/**
		 * The meta object literal for the '<em><b>Col</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__COL = eINSTANCE.getTable_Col();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__KEY = eINSTANCE.getTable_Key();

		/**
		 * The meta object literal for the '{@link postgres.impl.ColumnImpl <em>Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.ColumnImpl
		 * @see postgres.impl.PostgresPackageImpl#getColumn()
		 * @generated
		 */
		EClass COLUMN = eINSTANCE.getColumn();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN__OWNER = eINSTANCE.getColumn_Owner();

		/**
		 * The meta object literal for the '<em><b>Key Of</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN__KEY_OF = eINSTANCE.getColumn_KeyOf();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN__TYPE = eINSTANCE.getColumn_Type();

		/**
		 * The meta object literal for the '{@link postgres.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.TypeImpl
		 * @see postgres.impl.PostgresPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '{@link postgres.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.PrimitiveTypeImpl
		 * @see postgres.impl.PostgresPackageImpl#getPrimitiveType()
		 * @generated
		 */
		EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

		/**
		 * The meta object literal for the '{@link postgres.impl.VarcharImpl <em>Varchar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.VarcharImpl
		 * @see postgres.impl.PostgresPackageImpl#getVarchar()
		 * @generated
		 */
		EClass VARCHAR = eINSTANCE.getVarchar();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARCHAR__LENGTH = eINSTANCE.getVarchar_Length();

		/**
		 * The meta object literal for the '{@link postgres.impl.IntegerImpl <em>Integer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.IntegerImpl
		 * @see postgres.impl.PostgresPackageImpl#getInteger()
		 * @generated
		 */
		EClass INTEGER = eINSTANCE.getInteger();

		/**
		 * The meta object literal for the '{@link postgres.impl.UmlToNoSQLIDImpl <em>Uml To No SQLID</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see postgres.impl.UmlToNoSQLIDImpl
		 * @see postgres.impl.PostgresPackageImpl#getUmlToNoSQLID()
		 * @generated
		 */
		EClass UML_TO_NO_SQLID = eINSTANCE.getUmlToNoSQLID();

	}

} //PostgresPackage
