package som.umltonosql.generator.core;

import region.Region;

public abstract class RegionGeneratorUtil {

	protected String basePackage;

	protected Region region;

	/**
	 * Constructs a new {@link GeneratorUtil}.
	 * <p>
	 * This constructor is <i>protected</i>, subclasses should provide a static way
	 * to access a singleton instance.
	 */
	protected RegionGeneratorUtil() {

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

}
