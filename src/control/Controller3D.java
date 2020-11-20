package control;

import model3d.Cube;
import model3d.Scene;
import rasterize.Raster;
import renderer.GPURenderer;
import renderer.Renderer3D;
import transforms.*;
import view.Panel;

public class Controller3D implements Controller {

    private GPURenderer renderer;
    private Raster raster;

    private Mat4 projection;
    private Camera camera;

    private Scene mainScene, axisScene;

    public Controller3D(Panel panel) {
        renderer = new Renderer3D(panel.getRaster());
        raster = panel.getRaster();
        initListeners(panel);

        camera = new Camera()
                .withPosition(new Vec3D(1, -5, 2))
                .withAzimuth(Math.toRadians(90))
                .withZenith(Math.toRadians(-15));

        projection = new Mat4PerspRH(
                Math.PI / 3,
                raster.getHeight() / (float) raster.getWidth(),
                0.1, 50
        );

//        new Mat4OrthoRH(20, 20, 0.1, 50);

        mainScene = new Scene();
        mainScene.getSolids().add(new Cube());
    }

    private void display() {
        raster.clear();

//        renderer.setModel();
        renderer.setView(camera.getViewMatrix());
        renderer.setProjection(projection);
        renderer.draw(mainScene);

//        renderer.setModel(new Mat4Identity());
//        renderer.draw(axisScene);
    }

    @Override
    public void initListeners(Panel panel) {
        // TODO
    }

}
