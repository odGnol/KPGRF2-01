package model;

import transforms.Col;
import transforms.Point3D;

public class Osy extends Solid {

public Osy() {
    //vrcholy pro osy XYZ(gizmo) 0 - 5
    vertexBuffer.add(new Vertex(new Point3D(), new Col(255, 0, 0)));
    vertexBuffer.add(new Vertex(new Point3D(), new Col(0, 255, 0)));
    vertexBuffer.add(new Vertex(new Point3D(), new Col(0, 0, 255)));
    vertexBuffer.add(new Vertex(new Point3D(1, 0, 0), new Col(255, 0, 0)));
    vertexBuffer.add(new Vertex(new Point3D(0, 1, 0), new Col(0, 255, 0)));
    vertexBuffer.add(new Vertex(new Point3D(0, 0, 1), new Col(0, 0, 255)));

    //osy XYZ
    indexBuffer.add(0);
    indexBuffer.add(3);
    indexBuffer.add(1);
    indexBuffer.add(4);
    indexBuffer.add(2);
    indexBuffer.add(5);

    addParts(new Part(TopologyType.LINE, 0, 6));
}
}
