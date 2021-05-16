package control;

import model.Part;
import model.TopologyType;
import model.Vertex;
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

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.imageBuffer = panel.getImageBuffer();
        this.renderer = new Renderer3D(panel.getImageBuffer());

        partBuffer = new ArrayList<>();
        indexBuffer = new ArrayList<>();
        vertexBuffer = new ArrayList<>();

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
        renderer.draw(partBuffer, indexBuffer, vertexBuffer);

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
                                         initBuffers();
                                     } else if (e.getKeyCode() == KeyEvent.VK_E) {
                                         partBuffer = new ArrayList<>();
                                         indexBuffer = new ArrayList<>();
                                         vertexBuffer = new ArrayList<>();

                                     } else if (e.getKeyCode() == KeyEvent.VK_W) {
                                         camera = camera.up(posun);

                                     } else if (e.getKeyCode() == KeyEvent.VK_S) {
                                         camera = camera.down(posun);

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
        vertexBuffer.add(new Vertex(new Point3D(), new Col(255, 0, 0)));
        vertexBuffer.add(new Vertex(new Point3D(1, 0, 0), new Col(255, 0, 0)));  //1
        vertexBuffer.add(new Vertex(new Point3D(0, 1, 0), new Col(0, 255, 0))); //2
        vertexBuffer.add(new Vertex(new Point3D(0, 0, 1), new Col(0, 0, 255))); //3

        //osy XYZ
        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(0);
        indexBuffer.add(2);
        indexBuffer.add(0);
        indexBuffer.add(3);

        partBuffer.add(new Part(TopologyType.LINE, 0, 6));
    }

}