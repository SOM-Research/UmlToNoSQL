package som.umltonosql.generator.util;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.xtext.generator.GeneratorUtil;

import region.Region;

public abstract class AbstractRegionGeneratorUtil {

	protected String basePackage;

	protected Region region;

	private Model pimModel;

	/**
	 * Constructs a new {@link GeneratorUtil}.
	 * <p>
	 * This constructor is <i>protected</i>, subclasses should provide a static way
	 * to access a singleton instance.
	 */
	protected AbstractRegionGeneratorUtil() {

	}

	/**
	 * Sets the {@code basePackage} of this {@link GeneratorUtil}.
	 * 
	 * @param basePackage
	 *            the String representing the base package associated to this helper
	 */
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	/**
	 * Returns a String representing the base package of this helper.
	 * 
	 * @return a String representing the base package of this helper
	 */
	public String getBasePackage() {
		return this.basePackage;
	}

	/**
	 * Sets the {@code region} of this {@link GeneratorUtil}.
	 * 
	 * @param region
	 *            the {@link Region} associated to this helper
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * Returns the {@link Region} associated to this helper.
	 * 
	 * @return the {@link Region} associated to this helper
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Sets the {@code pimModel} of this {@link GeneratorUtil}
	 * 
	 * @param pimModel
	 *            the {@link Model} associated to this helper
	 */
	public void setPimModel(Model pimModel) {
		this.pimModel = pimModel;
	}

	/**
	 * Returns the {@link Model} associated to this helper.
	 * 
	 * @return the {@link Model} associated to this helper
	 */
	public Model getPimModel() {
		return pimModel;
	}

}
