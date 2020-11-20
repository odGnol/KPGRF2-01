package control;

import fill.SeedFill;
import model.Point;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterize.Raster;
import rasterize.TrivialLineRasterizer;
import transforms.*;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;
    private final Raster raster;

    private LineRasterizer trivialLineRasterizer;
    private Polygon polygon;
    private SeedFill seedFill;

    private int x, y;

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();
        initObjects(panel.getRaster());
        initListeners(panel);

//        raster.setPixel(50, 50, 0x00ff00);
//        raster.setPixel(60, 60, Color.GREEN.getRGB());
//
//        for (int x = 100; x <= 300; x++) {
//            raster.setPixel(x, x, 0x00ffff);
//        }

//        Point2D p1 = new Point2D(10, 20);
//        Point2D p2 = new Point2D(100, 50);
//        trivialLineRasterizer.rasterize((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY(), Color.RED.getRGB());
//
////        Mat3 mat = new Mat3Transl2D(0, 20);
//        Mat3 mat = new Mat3Transl2D(-10, -20).mul(new Mat3Scale2D(3)).mul(new Mat3Transl2D(10, 20));
//        p1 = p1.mul(mat);
//        p2 = p2.mul(mat);
//        trivialLineRasterizer.rasterize((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY(), Color.GREEN.getRGB());
    }

    private void initObjects(Raster raster) {
        trivialLineRasterizer = new TrivialLineRasterizer(raster);

        polygon = new Polygon();
        polygon.addPoints(new Point(10, 10), new Point(20, 50));

        seedFill = new SeedFill(raster);
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
//                    rasterizer.drawLine(x, y, e.getX(), e.getY());
                    x = e.getX();
                    y = e.getY();
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        seedFill.setSeed(new Point(e.getX(), e.getY()));
                        seedFill.setFillColor(0xffffff);
                        seedFill.fill();
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        //TODO
                    }
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    raster.clear();
                    trivialLineRasterizer.rasterize(x, y, e.getX(), e.getY(), 0xff00ff);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                }
                update();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    //TODO
                }
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void update() {
//        panel.clear();
        //TODO
    }

    private void hardClear() {
        panel.clear();
    }

}
