/**
 */
package postgres.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import postgres.Column;
import postgres.Database;
import postgres.PostgresFactory;
import postgres.PostgresPackage;
import postgres.Table;
import postgres.UmlToNoSQLID;
import postgres.Varchar;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PostgresFactoryImpl extends EFactoryImpl implements PostgresFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PostgresFactory init() {
		try {
			PostgresFactory thePostgresFactory = (PostgresFactory)EPackage.Registry.INSTANCE.getEFactory(PostgresPackage.eNS_URI);
			if (thePostgresFactory != null) {
				return thePostgresFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PostgresFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PostgresFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PostgresPackage.DATABASE: return createDatabase();
			case PostgresPackage.TABLE: return createTable();
			case PostgresPackage.COLUMN: return createColumn();
			case PostgresPackage.VARCHAR: return createVarchar();
			case PostgresPackage.INTEGER: return createInteger();
			case PostgresPackage.UML_TO_NO_SQLID: return createUmlToNoSQLID();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Database createDatabase() {
		DatabaseImpl database = new DatabaseImpl();
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table createTable() {
		TableImpl table = new TableImpl();
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column createColumn() {
		ColumnImpl column = new ColumnImpl();
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Varchar createVarchar() {
		VarcharImpl varchar = new VarcharImpl();
		return varchar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public postgres.Integer createInteger() {
		IntegerImpl integer = new IntegerImpl();
		return integer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmlToNoSQLID createUmlToNoSQLID() {
		UmlToNoSQLIDImpl umlToNoSQLID = new UmlToNoSQLIDImpl();
		return umlToNoSQLID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PostgresPackage getPostgresPackage() {
		return (PostgresPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PostgresPackage getPackage() {
		return PostgresPackage.eINSTANCE;
	}

} //PostgresFactoryImpl
