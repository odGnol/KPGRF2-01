package rasterize;

public class DashedLineRasterizer extends LineRasterizer {

    public DashedLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        /*
        k = 0

        k < 5
        setPixel(..);
        k++
        k > 10 - k = 0

        */
    }

}
