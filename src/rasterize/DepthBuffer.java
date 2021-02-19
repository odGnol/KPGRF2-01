package rasterize;

import java.util.Arrays;
import java.util.Optional;

public class DepthBuffer implements Raster<Double> {

    private final double[][] zData;
    private final int width;
    private final int height;
    private double clearValue;

    public DepthBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.zData = new double[width][height];

        setClearValue(1d);
        clear();
    }

    @Override
    public void clear() {
//        for (double[] d : data) {
//            for (int i = 0; i < d.length; i++) {
//                d[i] = clearValue;
//            }
//        }
        for (double[] d : zData) {
            Arrays.fill(d, clearValue);
        }
    }

    @Override
    public void setClearValue(Double clearValue) {
        this.clearValue = clearValue;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Optional<Double> getElement(int x, int y) {
        if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
            return Optional.of(zData[x][y]);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void setElement(int x, int y, Double zValue) {
        if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
            zData[x][y] = zValue;
        }
    }

}
