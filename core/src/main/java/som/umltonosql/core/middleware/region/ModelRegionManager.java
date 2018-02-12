package som.umltonosql.core.middleware.region;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModelRegionManager {

    protected Map<String, Region> regions;

    public ModelRegionManager() {
        regions = new HashMap<>();
    }


    public void addRegion(Region region) {
        this.regions.put(region.getId(), region);
    }

    public Region getRegion(String regionId) {
        return regions.get(regionId);
    }

    public Collection<Region> getRegions() {
        return regions.values();
    }

}
