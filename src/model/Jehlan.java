package model;

import transforms.Col;
import transforms.Point3D;

public class Jehlan extends Solid{

    public Jehlan() {

        vertexBuffer.add(new Vertex(new Point3D(5, 5, 5), new Col(140, 25, 230)));
        vertexBuffer.add(new Vertex(new Point3D(-5, -5, 5), new Col(140, 25, 230)));
        vertexBuffer.add(new Vertex(new Point3D(-5, 5, -5), new Col(140, 25, 230)));
        vertexBuffer.add(new Vertex(new Point3D(5, -5, -5), new Col(140, 25, 230)));

        indexBuffer.add(0);
        indexBuffer.add(1);

        indexBuffer.add(0);
        indexBuffer.add(2);

        indexBuffer.add(0);
        indexBuffer.add(3);

        indexBuffer.add(1);
        indexBuffer.add(2);

        indexBuffer.add(1);
        indexBuffer.add(3);

        indexBuffer.add(2);
        indexBuffer.add(3);

        addParts(new Part(TopologyType.LINE, 0, 6));
    }
}
