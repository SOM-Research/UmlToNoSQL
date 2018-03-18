/**
 */
package region;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Drill Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link region.DrillDescriptor#getDrillDriver <em>Drill Driver</em>}</li>
 * </ul>
 *
 * @see region.RegionPackage#getDrillDescriptor()
 * @model abstract="true"
 * @generated
 */
public interface DrillDescriptor extends DatastoreDescriptor {
	/**
	 * Returns the value of the '<em><b>Drill Driver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Drill Driver</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Drill Driver</em>' attribute.
	 * @see #setDrillDriver(String)
	 * @see region.RegionPackage#getDrillDescriptor_DrillDriver()
	 * @model
	 * @generated
	 */
	String getDrillDriver();

	/**
	 * Sets the value of the '{@link region.DrillDescriptor#getDrillDriver <em>Drill Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Drill Driver</em>' attribute.
	 * @see #getDrillDriver()
	 * @generated
	 */
	void setDrillDriver(String value);

} // DrillDescriptor
