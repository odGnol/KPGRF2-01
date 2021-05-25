package model;

import model.Solid;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    //seznam těles
    private List<Solid> solids;

    public Scene() {
        this(new ArrayList<>());
    }

    public Scene(List<Solid> solids) {
        this.solids = solids;
    }

    public List<Solid> getSolids() {
        return solids;
    }

    public List<Solid> clearSolid() {
        return solids = new ArrayList<Solid>();
    }

}
