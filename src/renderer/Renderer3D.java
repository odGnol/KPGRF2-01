package renderer;

import rasterize.Raster;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Vec3D;

public class Renderer3D implements GPURenderer {

    private final Raster<Integer> raster;

    private Mat4 model, view, projection;

    public Renderer3D(Raster<Integer> raster) {
        this.raster = raster;

        model = new Mat4Identity();
        view = new Mat4Identity();
        projection = new Mat4Identity();
    }

    @Override
    public void draw() {

    }

    private Vec3D transformToWindow(Vec3D vec) {
        return vec
                .mul(new Vec3D(1, -1, 1)) // Y jde nahoru a my chceme, aby šlo dolů
                .add(new Vec3D(1, 1, 0)) // (0,0) je uprostřed a my chceme, aby bylo vlevo nahoře
                .mul(new Vec3D(raster.getWidth() / 2f, raster.getHeight() / 2f, 1));
                // máme <0;2> -> vynásobíme polovinou velikosti plátna
    }

    @Override
    public void clear() {

    }

    @Override
    public void setModel(Mat4 model) {
        this.model = model;
    }

    @Override
    public void setView(Mat4 view) {
        this.view = view;
    }

    @Override
    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }

}
