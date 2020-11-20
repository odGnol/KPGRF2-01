package model3d;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private final List<Solid> solids;

    public Scene() {
        this(new ArrayList<>());
    }

    public Scene(List<Solid> solids) {
        this.solids = solids;
    }

    public List<Solid> getSolids() {
        return solids;
    }

}
