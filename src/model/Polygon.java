package model;

import rasterize.LineRasterizer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private final List<Point> points;
    private final int color;

    public Polygon() {
        this(new ArrayList<>());
    }

    public Polygon(List<Point> points) {
        this(points, Color.MAGENTA.getRGB());
    }

    // konstruktor, který umí nastavit jen barvu a výchozí bude prázdný seznam

    public Polygon(List<Point> points, int color) {
        this.points = points;
        this.color = color;
    }

    public void addPoints(Point... pointsToAdd) { // varargs java
//        points.addAll(pointsToAdd.)
    }

//    public void rasterize(LineRasterizer lineRasterizer) {
    //
//    }

    public void addPoints(List<Point> pointsToAdd) {
        points.addAll(pointsToAdd);
    }

    // clear pro vymazání seznamu vrcholů

    public List<Point> getPoints() {
        return points;
    }

    public int getColor() {
        return color;
    }

}
