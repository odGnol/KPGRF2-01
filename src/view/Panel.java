package view;

import rasterize.Raster;
import rasterize.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private RasterBufferedImage imageBuffer;

    private static final int WIDTH = 800, HEIGHT = 600;

    Panel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        imageBuffer = new RasterBufferedImage(WIDTH, HEIGHT);
        imageBuffer.setClearColor(Color.BLACK.getRGB());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        imageBuffer.repaint(g);
    }

    public void resize() {
        if (this.getWidth() < 1 || this.getHeight() < 1) return;

        RasterBufferedImage newRaster = new RasterBufferedImage(this.getWidth(), this.getHeight());
        newRaster.draw(imageBuffer);
        imageBuffer = newRaster;
    }

    public void clear() {
        imageBuffer.clear();
    }

    public Raster getImageBuffer() {
        return imageBuffer;
    }

}