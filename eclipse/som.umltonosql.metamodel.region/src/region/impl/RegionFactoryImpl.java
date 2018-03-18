/**
 */
package region.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import region.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RegionFactoryImpl extends EFactoryImpl implements RegionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RegionFactory init() {
		try {
			RegionFactory theRegionFactory = (RegionFactory)EPackage.Registry.INSTANCE.getEFactory(RegionPackage.eNS_URI);
			if (theRegionFactory != null) {
				return theRegionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RegionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RegionFactoryImpl() {
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
			case RegionPackage.REGION_SET: return createRegionSet();
			case RegionPackage.REGION: return createRegion();
			case RegionPackage.MONGO_DESCRIPTOR: return createMongoDescriptor();
			case RegionPackage.POSTGRES_DESCRIPTOR: return createPostgresDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RegionSet createRegionSet() {
		RegionSetImpl regionSet = new RegionSetImpl();
		return regionSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Region createRegion() {
		RegionImpl region = new RegionImpl();
		return region;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MongoDescriptor createMongoDescriptor() {
		MongoDescriptorImpl mongoDescriptor = new MongoDescriptorImpl();
		return mongoDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PostgresDescriptor createPostgresDescriptor() {
		PostgresDescriptorImpl postgresDescriptor = new PostgresDescriptorImpl();
		return postgresDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RegionPackage getRegionPackage() {
		return (RegionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RegionPackage getPackage() {
		return RegionPackage.eINSTANCE;
	}

} //RegionFactoryImpl
