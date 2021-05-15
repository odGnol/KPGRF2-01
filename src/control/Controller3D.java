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
    }

    private void initListeners(Panel panel) {
        panel.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_C) {
                            imageBuffer.setElement(400, 300, Color.YELLOW.getRGB());
                            panel.repaint();
                        } else if (e.getKeyCode() == KeyEvent.VK_E) {
                            partBuffer = new ArrayList<>();
                            indexBuffer = new ArrayList<>();
                            vertexBuffer = new ArrayList<>();
                            display();
                        } else if (e.getKeyCode() == KeyEvent.VK_W) {
                            camera = camera.up(posun);
                            display();
                        } else if (e.getKeyCode() == KeyEvent.VK_S) {
                            camera = camera.down(posun);
                            display();
                        } else if (e.getKeyCode() == KeyEvent.VK_A) {
                            camera = camera.left(posun);
                            display();
                        } else if (e.getKeyCode() == KeyEvent.VK_D) {
                            camera = camera.right(posun);
                            display();
                        }
                    }
                }
        );
    }

    private void initBuffers() {
        vertexBuffer.add(new Vertex(new Point3D(), new Col(255, 0, 0)));
        vertexBuffer.add(new Vertex(new Point3D(1, 0, 0), new Col(255, 0, 0)));  //1
        vertexBuffer.add(new Vertex(new Point3D(0, 1, 0), new Col(0, 255, 0))); //2
        vertexBuffer.add(new Vertex(new Point3D(0, 0, 1), new Col(0, 0, 255))); //3

        //osy
        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(0);
        indexBuffer.add(2);
        indexBuffer.add(0);
        indexBuffer.add(3);

        partBuffer.add(new Part(TopologyType.LINE, 0, 2));

        System.out.println(vertexBuffer);
    }

}