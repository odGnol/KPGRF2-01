package rasterize;

import java.awt.*;

@Deprecated
public class LineRasterizerGraphics {

    private final RasterBufferedImage imageBuffer;

    public LineRasterizerGraphics(Raster raster) {
        this.imageBuffer = ((RasterBufferedImage) raster);
    }

    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        Graphics g = imageBuffer.getGraphics();
        g.setColor(new Color(color));
        g.drawLine(x1, y1, x2, y2);
    }

}
