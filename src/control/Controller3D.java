package control;

import model.*;
import rasterize.Raster;
import renderer.GPURenderer;
import renderer.Renderer3D;
import transforms.*;
import view.Panel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controller3D {

    private final GPURenderer renderer;
    private final Raster<Integer> imageBuffer;
    private final Panel panel;

    private List<Part> partBuffer;
    private List<Integer> indexBuffer;
    private List<Vertex> vertexBuffer;

    private Mat4 model, projection;
    private Camera camera;
    private final double posun = 0.1;
    private boolean perspektiva = false;
    private int aktualniX;
    private int aktualniY;

    private Scene main;
    private Scene wireScene;
    private Scene axisScene;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.imageBuffer = panel.getImageBuffer();
        this.renderer = new Renderer3D(panel.getImageBuffer());

        partBuffer = new ArrayList<>();
        indexBuffer = new ArrayList<>();
        vertexBuffer = new ArrayList<>();

        main = new Scene();
        axisScene = new Scene();
        wireScene = new Scene();

        initMatrices();
        initListeners(panel);
        initBuffers();

    }

    private void display() {
        panel.clear();
        renderer.clear();

        renderer.setModel(model);
        renderer.setView(camera.getViewMatrix());
        renderer.setProjection(projection);
//        renderer.draw(partBuffer, indexBuffer, vertexBuffer);
        renderer.createLineSegment(axisScene);
        renderer.createLineSegment(wireScene);
        renderer.createObject(main);


        // necessary to manually request update of the UI
        panel.repaint();

    }

    private void initMatrices() {
        model = new Mat4Identity();

        Vec3D e = new Vec3D(1, -5, 2);
        camera = new Camera()
                .withPosition(e)
                .withAzimuth(Math.toRadians(90))
                .withZenith(Math.toRadians(-15));

        projection = new Mat4PerspRH(
                Math.PI / 3,
                imageBuffer.getHeight() / (float) imageBuffer.getWidth(),
                0.5,
                50
        );

        perspektiva = true;

    }

    private void initListeners(Panel panel) {
        panel.addKeyListener(new KeyAdapter() {
                                 public void keyPressed(KeyEvent e) {
                                     if (e.getKeyCode() == KeyEvent.VK_C) {
                                         axisScene.getSolids().add(new Osy());

                                     } else if (e.getKeyCode() == KeyEvent.VK_V) {
                                         main.clearSolid();
                                         wireScene.clearSolid();
                                         wireScene.getSolids().add(new Jehlan());

                                     } else if (e.getKeyCode() == KeyEvent.VK_X) {
                                         main.clearSolid();
                                         wireScene.clearSolid();
                                         main.getSolids().add(new Kvadr());

                                     } else if (e.getKeyCode() == KeyEvent.VK_B) {
                                         main.clearSolid();
                                         wireScene.clearSolid();
                                         main.getSolids().add(new JehlanPloska());

                                     } else if (e.getKeyCode() == KeyEvent.VK_E) {
                                         main.clearSolid();
                                         wireScene.clearSolid();

                                     } else if (e.getKeyCode() == KeyEvent.VK_W) {
                                         camera = camera.up(posun);

                                     } else if (e.getKeyCode() == KeyEvent.VK_Q) {
                                         camera = camera.forward(posun);

                                     } else if (e.getKeyCode() == KeyEvent.VK_S) {
                                         camera = camera.down(posun);

                                     } else if (e.getKeyCode() == KeyEvent.VK_Y) {
                                         camera = camera.backward(posun);

                                     } else if (e.getKeyCode() == KeyEvent.VK_A) {
                                         camera = camera.left(posun);

                                     } else if (e.getKeyCode() == KeyEvent.VK_D) {
                                         camera = camera.right(posun);

                                     } else if (e.getKeyCode() == KeyEvent.VK_P) {
                                         if (perspektiva) {
                                             perspektiva = false;
                                             projection = new Mat4OrthoRH(
                                                     10.0,
                                                     10.0 * (imageBuffer.getHeight() / (float) imageBuffer.getWidth()),
                                                     0.5,
                                                     50
                                             );
                                         } else {
                                             perspektiva = true;
                                             projection = new Mat4PerspRH(
                                                     Math.PI / 3,
                                                     imageBuffer.getHeight() / (float) imageBuffer.getWidth(),
                                                     0.5,
                                                     50
                                             );
                                         }
                                     }
                                     display();
                                 }
                             }
        );

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                aktualniX = e.getX();
                aktualniY = e.getY();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (aktualniX < e.getX()) {
                    camera = camera.addAzimuth(-(e.getX() - aktualniX) / 1000.0);
                    aktualniX = e.getX();
                } else if (e.getX() < aktualniX) {
                    camera = camera.addAzimuth((aktualniX - e.getX()) / 1000.0);
                    aktualniX = e.getX();
                }
                if (aktualniY < e.getY()) {
                    camera = camera.addZenith(-(e.getY() - aktualniY) / 1000.0);
                    aktualniY = e.getY();
                } else if (e.getY() < aktualniY) {
                    camera = camera.addZenith((aktualniY - e.getY()) / 1000.0);
                    aktualniY = e.getY();
                }
                display();
            }
        });

        panel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() == -1) {
                    Mat4 mat = new Mat4Scale(1.1, 1.1, 1.1);
                    model = model.mul(mat);

                } else {
                    Mat4 mat = new Mat4Scale(0.9, 0.9, 0.9);
                    model = model.mul(mat);
                }
                display();
            }
        });

    }

    private void initBuffers() {


    }

}