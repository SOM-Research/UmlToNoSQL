/**
 */
package graphdb;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graphdb.Graph#getVertices <em>Vertices</em>}</li>
 *   <li>{@link graphdb.Graph#getEdges <em>Edges</em>}</li>
 *   <li>{@link graphdb.Graph#getRawDatabase <em>Raw Database</em>}</li>
 * </ul>
 *
 * @see graphdb.GraphdbPackage#getGraph()
 * @model
 * @generated
 */
public interface Graph extends EObject {
	/**
	 * Returns the value of the '<em><b>Vertices</b></em>' containment reference list.
	 * The list contents are of type {@link graphdb.Vertex}.
	 * It is bidirectional and its opposite is '{@link graphdb.Vertex#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertices</em>' containment reference list.
	 * @see graphdb.GraphdbPackage#getGraph_Vertices()
	 * @see graphdb.Vertex#getGraph
	 * @model opposite="graph" containment="true"
	 * @generated
	 */
	EList<Vertex> getVertices();

	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
	 * The list contents are of type {@link graphdb.Edge}.
	 * It is bidirectional and its opposite is '{@link graphdb.Edge#getGraph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see graphdb.GraphdbPackage#getGraph_Edges()
	 * @see graphdb.Edge#getGraph
	 * @model opposite="graph" containment="true"
	 * @generated
	 */
	EList<Edge> getEdges();

	/**
	 * Returns the value of the '<em><b>Raw Database</b></em>' attribute.
	 * The literals are from the enumeration {@link graphdb.DatabaseKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Raw Database</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Raw Database</em>' attribute.
	 * @see graphdb.DatabaseKind
	 * @see #setRawDatabase(DatabaseKind)
	 * @see graphdb.GraphdbPackage#getGraph_RawDatabase()
	 * @model
	 * @generated
	 */
	DatabaseKind getRawDatabase();

	/**
	 * Sets the value of the '{@link graphdb.Graph#getRawDatabase <em>Raw Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Raw Database</em>' attribute.
	 * @see graphdb.DatabaseKind
	 * @see #getRawDatabase()
	 * @generated
	 */
	void setRawDatabase(DatabaseKind value);

} // Graph
