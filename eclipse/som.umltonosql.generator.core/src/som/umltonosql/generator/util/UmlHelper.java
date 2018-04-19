package som.umltonosql.generator.util;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Property;

public class UmlHelper {

	private Model umlModel;

	public UmlHelper(Model umlModel) {
		this.umlModel = umlModel;
	}

	public Class getClassWithName(String name) {
		Iterator<EObject> it = umlModel.eResource().getAllContents();
		while (it.hasNext()) {
			EObject e = it.next();
			if (e instanceof Class) {
				Class c = (Class) e;
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		throw new RuntimeException("Cannot find the UML class with the provided name: " + name);
	}

	public Association getAssociation(Class from, String endName) {
		return (Association) getAssociationEnd(from, endName).eContainer();
	}
	
	public Property getAssociationEnd(Class from, String endName) {
		for(Association association : from.getAssociations()) {
			Property end = getAssociationEnd(association, endName);
			if(nonNull(end)) {
				return end;
			}
		}
		throw new RuntimeException("Cannot find the association with the end named " + endName + " from the Class " + from.getName());
	}
	
	public Property getAssociationEnd(Association association, String endName) {
		for(Property associationEnd : association.getOwnedEnds()) {
			if(associationEnd.getName().equals(endName)) {
				return associationEnd;
			}
		}
		return null;
	}
	
	public Property getAssociationEnd(Association association, Class end) {
		for(Property associationEnd : association.getOwnedEnds()) {
			if(associationEnd.getType().equals(end)) {
				return associationEnd;
			}
		}
		throw new RuntimeException("Cannot find the association end with the type " + end.getName());
	}

}
