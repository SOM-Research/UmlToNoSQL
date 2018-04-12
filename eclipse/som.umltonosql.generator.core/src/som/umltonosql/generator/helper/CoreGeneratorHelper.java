package som.umltonosql.generator.helper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.uml2.uml.Class;

import region.Partition;
import region.Region;
import region.StorageKind;

public class CoreGeneratorHelper {

	private Partition partition;
	
	private String projectName;
	
	private Set<Class> beans;
	private Map<Class, Region> regionPerClass;
	
	public CoreGeneratorHelper(Partition partition) {
		this.partition = partition;
		this.projectName = partition.getName();
		
		this.beans = new HashSet<>();
		this.regionPerClass = new HashMap<>();
		for(Region r  : partition.getRegions()) {
			for(Class c : r.getClasses()) {
				beans.add(c);
				regionPerClass.put(c, r);
			}
		}
	}
	
	public String getDatastoreImport(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return "som.umltonosql.core.datastore.store.MongoDatastore";
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return "som.umltonosql.core.datastore.store.PostgresDatastore";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the datastore import for {0}", sKind));
	}
	
	public String getDatastoreType(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return "MongoDatastore";
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return "PostgresDatastore";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the datastore type for {0}", sKind));
	}
	
	public String getDatastoreVariableName(Region region) {
		return region.getName() + "Datastore";
	}
	
	public String getDatastoreInvocationParameters(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return "\"" + ((Partition)region.eContainer()).getName().toLowerCase() + "\"";
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return "\"jdbc:" + "postgresql" + "://" + "127.0.0.1" + ":" + "5432" + "/" + ((Partition)region.eContainer()).getName().toLowerCase() + "\"";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the datastore parameters for {0}", sKind));
	}
	
	public String getProcessorImport(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return "som.umltonosql.core.datastore.query.processor.MongoQueryProcessor";
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return "som.umltonosql.core.datastore.query.processor.DrillQueryProcessor";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the processor import for {0}", sKind));

	}
	
	public String getProcessorType(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return "MongoQueryProcessor";
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return "DrillQueryProcessor";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the processor type for {0}", sKind));
	}
	
	public String getProcessorArgumenst(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return "this," + region.getName() + "Datastore";
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return "this";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the processor arguments for {0}", sKind));
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
		Partition rSet = (Partition) r.eContainer();
		return rSet.getName().toLowerCase() + '.' + r.getName().toLowerCase() + '.' + bean;
		
	}
}
