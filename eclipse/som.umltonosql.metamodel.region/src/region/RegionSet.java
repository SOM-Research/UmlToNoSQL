/**
 */
package region;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link region.RegionSet#getRegions <em>Regions</em>}</li>
 * </ul>
 *
 * @see region.RegionPackage#getRegionSet()
 * @model
 * @generated
 */
public interface RegionSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Regions</b></em>' containment reference list.
	 * The list contents are of type {@link region.Region}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regions</em>' containment reference list.
	 * @see region.RegionPackage#getRegionSet_Regions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Region> getRegions();

} // RegionSet
