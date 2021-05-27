package model;

import transforms.Col;
import transforms.Point3D;

public class JehlanPloska extends Solid {

    public JehlanPloska() {
        vertexBuffer.add(new Vertex(new Point3D(5, 5, 5), new Col(140, 25, 10)));
        vertexBuffer.add(new Vertex(new Point3D(-5, -5, 5), new Col(90, 25, 5)));
        vertexBuffer.add(new Vertex(new Point3D(-5, 5, -5), new Col(20, 255, 230)));
        vertexBuffer.add(new Vertex(new Point3D(5, -5, -5), new Col(200, 100, 230)));

        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(2);

        indexBuffer.add(0);
        indexBuffer.add(2);
        indexBuffer.add(3);

        indexBuffer.add(0);
        indexBuffer.add(3);
        indexBuffer.add(1);

        indexBuffer.add(3);
        indexBuffer.add(2);
        indexBuffer.add(1);

        addParts(new Part(TopologyType.TRIANGLE, 0, 4));
    }
}
