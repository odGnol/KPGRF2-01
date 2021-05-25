package model;

import transforms.Point3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solid {
    
    //seznam vrcholů - Point3D
    final List<Vertex> vertexBuffer = new ArrayList<>();

    //seznam vrcholů - Vertex
    public List<Point3D> points3D = new ArrayList<>();

    //seznam indexovaných vrcholů
    final List<Integer> indexBuffer = new ArrayList<>();

    private List<Part> partBuffer = new ArrayList<>();
    
    protected  int color;
    protected int colorX;
    protected int colorY;
    protected int colorZ;

    //přidej indexy, předefinování na list, žádný předek nezasáhne do této funkce
    final void addParts(Part... parts) {
        partBuffer.addAll(Arrays.asList(parts));
    }


    public List<Point3D> getPoints3D() {
        return points3D;
    }

    public List<Vertex> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }


    public int getColor() {
        return color;
    }

    public int getColorX() {
        return colorX;
    }

    public int getColorY() {
        return colorY;
    }

    public int getColorZ() {
        return colorZ;
    }

}
