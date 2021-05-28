package model;

import transforms.Col;
import transforms.Point3D;

public class Kvadr extends Solid {

    public Kvadr() {

        //kvádr
        //horní stěna
        //před
        vertexBuffer.add(new Vertex(new Point3D(2, 2, 2), new Col(140, 25, 230))); //0
        vertexBuffer.add(new Vertex(new Point3D(4, 2, 2), new Col(30, 258, 30))); //1

        //za
        vertexBuffer.add(new Vertex(new Point3D(2, 2, -2), new Col(150, 230, 0))); //2
        vertexBuffer.add(new Vertex(new Point3D(4, 3, -2), new Col(255, 25, 30))); //3

        //dolní stěna
        //před
        vertexBuffer.add(new Vertex(new Point3D(2, 0, 2), new Col(23, 25, 230))); //4
        vertexBuffer.add(new Vertex(new Point3D(4, 0, -2), new Col(200, 258, 30))); //5
        //za
        vertexBuffer.add(new Vertex(new Point3D(2, 2, -2), new Col(55, 230, 0))); //6
        vertexBuffer.add(new Vertex(new Point3D(2, 2, -2), new Col(255, 25, 30))); //7

        //horní stěna
        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(2);

        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(3);

//        //přední
//        indexBuffer.add(0);
//        indexBuffer.add(1);
//        indexBuffer.add(4);
//
//        indexBuffer.add(0);
//        indexBuffer.add(1);
//        indexBuffer.add(5);
//
//        //pravá
//        indexBuffer.add(1);
//        indexBuffer.add(2);
//        indexBuffer.add(5);
//
//        indexBuffer.add(1);
//        indexBuffer.add(2);
//        indexBuffer.add(6);
//
//        //levá
//        indexBuffer.add(0);
//        indexBuffer.add(3);
//        indexBuffer.add(4);
//
//        indexBuffer.add(0);
//        indexBuffer.add(3);
//        indexBuffer.add(7);
//
//        //zadní
//
//        indexBuffer.add(2);
//        indexBuffer.add(3);
//        indexBuffer.add(6);
//
//        indexBuffer.add(2);
//        indexBuffer.add(3);
//        indexBuffer.add(7);
//
//        //podstava
//
//        indexBuffer.add(4);
//        indexBuffer.add(5);
//        indexBuffer.add(6);
//
//        indexBuffer.add(4);
//        indexBuffer.add(5);
//        indexBuffer.add(7);

        addParts(new Part(TopologyType.TRIANGLE, 0, 2));
    }
}
