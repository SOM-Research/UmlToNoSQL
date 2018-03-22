package som.umltonosql.generator.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.UMLPackage;

import region.DatastoreDescriptor;
import region.MongoDescriptor;
import region.PostgresDescriptor;
import region.Region;
import region.RegionSet;

public class RegionGeneratorHelper {

	private RegionSet rSet;
	
	private String projectName;
	
	private Set<Class> beans;
	private Map<Class, Region> regionPerClass;
	
	public RegionGeneratorHelper(RegionSet rSet) {
		this.rSet = rSet;
		this.projectName = rSet.getName();
		
		this.beans = new HashSet<>();
		this.regionPerClass = new HashMap<>();
		for(Region r  : rSet.getRegions()) {
			for(Class c : r.getClasses()) {
				beans.add(c);
				regionPerClass.put(c, r);
			}
		}
	}
	
	public String getDatastoreImport(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return "som.umltonosql.core.datastore.store.MongoDatastore";
		}
		if(descriptor instanceof PostgresDescriptor) {
			return "som.umltonosql.core.datastore.store.PostgresDatastore";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the datastore import for {0}", descriptor));
	}
	
	public String getDatastoreType(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return "MongoDatastore";
		}
		if(descriptor instanceof PostgresDescriptor) {
			return "PostgresDatastore";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the datastore type for {0}", descriptor));
	}
	
	public String getDatastoreVariableName(Region region) {
		return region.getName() + "Datastore";
	}
	
	public String getDatastoreInvocationParameters(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return "\"" + ((RegionSet)region.eContainer()).getName().toLowerCase() + "\"";
		}
		if(descriptor instanceof PostgresDescriptor) {
			PostgresDescriptor pDesc = (PostgresDescriptor)descriptor;
			return "\"jdbc:" + pDesc.getJdbcDriver() + "://" + pDesc.getHost() + ":" + pDesc.getPort() + "/" + ((RegionSet)region.eContainer()).getName().toLowerCase() + "\"";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the datastore parameters for {0}", descriptor));
	}
	
	public String getProcessorImport(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return "som.umltonosql.core.datastore.query.processor.MongoQueryProcessor";
		}
		if(descriptor instanceof PostgresDescriptor) {
			return "som.umltonosql.core.datastore.query.processor.DrillQueryProcessor";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the processor import for {0}", descriptor));

	}
	
	public String getProcessorType(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return "MongoQueryProcessor";
		}
		if(descriptor instanceof PostgresDescriptor) {
			return "DrillQueryProcessor";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the processor type for {0}", descriptor));
	}
	
	public String getProcessorArgumenst(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return "this," + region.getName() + "Datastore";
		}
		if(descriptor instanceof PostgresDescriptor) {
			return "this";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the processor arguments for {0}", descriptor));
	}
	
	public String getProcessorVariableName(Region region) {
		return region.getName() + "QueryProcessor";
	}
	
	public List<String> getBeanTypes() {
		List<String> beanTypes = new ArrayList<>();
		for(Class c : beans) {
			beanTypes.add(c.getName());
		}
		return beanTypes;
	}
	
	public Region getRegionForBean(String bean) {
		for(Class c : regionPerClass.keySet()) {
			if(c.getName().equals(bean)) {
				return regionPerClass.get(c);
			}
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the region of bean {0}", bean));
	}
	
	public String getBeanImport(String bean) {
		Region r = getRegionForBean(bean);
		RegionSet rSet = (RegionSet) r.eContainer();
		return rSet.getName().toLowerCase() + '.' + r.getName().toLowerCase() + '.' + bean;
		
	}
}
