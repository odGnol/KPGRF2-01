package rasterize;

import java.awt.*;

// lze použít pouze v rámci třetí úlohy
public class LineRasterizerGraphics extends LineRasterizer {

    private final RasterBufferedImage rasterBI;

    public LineRasterizerGraphics(Raster raster) {
        super(raster);
        this.rasterBI = ((RasterBufferedImage) raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        Graphics g = rasterBI.getGraphics();
        g.setColor(new Color(color));
        g.drawLine(x1, y1, x2, y2);
    }

}
