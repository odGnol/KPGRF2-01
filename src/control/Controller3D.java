package control;

import model.Part;
import model.TopologyType;
import model.Vertex;
import rasterize.Raster;
import renderer.GPURenderer;
import renderer.Renderer3D;
import transforms.*;
import view.Panel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller3D {

    private final GPURenderer renderer;
    private final Raster<Integer> imageBuffer;
    private final Panel panel;

    private final List<Part> partBuffer;
    private final List<Integer> indexBuffer;
    private final List<Vertex> vertexBuffer;

    private Mat4 model, projection;
    private Camera camera;
    private final int posun = 2;

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

//        // test draw
//        imageBuffer.setElement(50, 50, Color.YELLOW.getRGB());
//        panel.repaint();
    }

    private void display() {
        panel.clear();
        renderer.clear();

        renderer.setModel(model);
        renderer.setView(camera.getViewMatrix());
        renderer.setProjection(projection);

//        renderer.draw();
//        renderer.draw(partBuffer, indexBuffer, vertexBuffer);

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
    }

    private void initListeners(Panel panel) {
        // částečně doplněno
        panel.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_C) {
                            display();
                            renderer.draw(partBuffer, indexBuffer, vertexBuffer);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_E) {
                            display();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_W) {
                            camera = camera.up(posun);
                            display();
                            renderer.draw(partBuffer, indexBuffer, vertexBuffer);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_S) {
                            camera = camera.down(posun);
                            display();
                            renderer.draw(partBuffer, indexBuffer, vertexBuffer);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_A) {
                            camera = camera.left(posun);
                            display();
                            renderer.draw(partBuffer, indexBuffer, vertexBuffer);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_D) {
                            camera = camera.right(posun);
                            display();
                            renderer.draw(partBuffer, indexBuffer, vertexBuffer);
                        }
                    }
                }
        );
    }

    private void initBuffers() {
//        vertexBuffer.add(new Vertex(new Point3D(), new Col(255, 0, 0)));
        vertexBuffer.add(new Vertex(new Point3D(10, 10, 2), new Col(0, 125, 0)));
        vertexBuffer.add(new Vertex(new Point3D(-2, 6, -4), new Col(255, 125, 200)));
        vertexBuffer.add(new Vertex(new Point3D(7, 7, -2), new Col(0, 125, 200)));
//        vertexBuffer.add(new Vertex(new Point3D(8, 10, -90), new Col(0, 40, 180)));

        vertexBuffer.add(new Vertex(new Point3D(0, 0, 0), new Col(255, 255, 255)));
        vertexBuffer.add(new Vertex(new Point3D(2, 0, 0), new Col(255, 0, 0)));
        vertexBuffer.add(new Vertex(new Point3D(0, 2, 0), new Col(0, 255, 0)));
        vertexBuffer.add(new Vertex(new Point3D(0, 0, 2), new Col(0, 0, 255)));


        // 1 trojúhelník
        indexBuffer.add(1);
        indexBuffer.add(2);
        indexBuffer.add(3);

        // 3 a více úseček
//        indexBuffer.add(0);
//        indexBuffer.add(3);
//        indexBuffer.add(0);
//        indexBuffer.add(1);
//        indexBuffer.add(0);
//        indexBuffer.add(4);

        //osy
        indexBuffer.add(0);
        indexBuffer.add(1);

        indexBuffer.add(0);
        indexBuffer.add(2);

        indexBuffer.add(0);
        indexBuffer.add(3);

        partBuffer.add(new Part(TopologyType.TRIANGLE, 0, 1));
//        partBuffer.add(new Part(TopologyType.LINE, 3, 2));
        partBuffer.add(new Part(TopologyType.LINE, 0, 2));
    }

}