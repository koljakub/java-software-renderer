package cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d;

import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 3D sphere representation (icosphere mesh).
 * Created by Jakub Kol on 2/29/16.
 */
public final class Sphere extends Solid {
    private final int smoothness;

    public Sphere(int smoothness) {
        super();
        this.smoothness = smoothness;
        buildIcosahedron();
        createSphere(smoothness);
    }

    public Sphere(List<Color> colorList, int smoothness) {
        super(colorList);
        this.smoothness = smoothness;
        buildIcosahedron();
        createSphere(smoothness);
    }

    public int getSmoothness() {
        return smoothness;
    }

    private void buildIcosahedron() {
        // Vertex buffer
        double t = (1.0 + Math.sqrt(5.0)) / 2.0;
        vertexBuffer.add(new Point3D(-1,  t,  0));
        vertexBuffer.add(new Point3D( 1,  t,  0));
        vertexBuffer.add(new Point3D(-1, -t,  0));
        vertexBuffer.add(new Point3D( 1, -t,  0));
        vertexBuffer.add(new Point3D( 0, -1,  t));
        vertexBuffer.add(new Point3D( 0,  1,  t));
        vertexBuffer.add(new Point3D( 0, -1, -t));
        vertexBuffer.add(new Point3D( 0,  1, -t));
        vertexBuffer.add(new Point3D( t,  0, -1));
        vertexBuffer.add(new Point3D( t,  0,  1));
        vertexBuffer.add(new Point3D(-t,  0, -1));
        vertexBuffer.add(new Point3D(-t,  0,  1));
        // Index buffer
        indexBufferTriangles.add(0); indexBufferTriangles.add(11);indexBufferTriangles.add(5);
        indexBufferTriangles.add(0); indexBufferTriangles.add(5); indexBufferTriangles.add(1);
        indexBufferTriangles.add(0); indexBufferTriangles.add(1); indexBufferTriangles.add(7);
        indexBufferTriangles.add(0); indexBufferTriangles.add(7); indexBufferTriangles.add(10);
        indexBufferTriangles.add(0); indexBufferTriangles.add(10);indexBufferTriangles.add(11);

        indexBufferTriangles.add(1); indexBufferTriangles.add(5); indexBufferTriangles.add(9);
        indexBufferTriangles.add(5); indexBufferTriangles.add(11);indexBufferTriangles.add(4);
        indexBufferTriangles.add(11);indexBufferTriangles.add(10);indexBufferTriangles.add(2);
        indexBufferTriangles.add(10);indexBufferTriangles.add(7); indexBufferTriangles.add(6);
        indexBufferTriangles.add(7); indexBufferTriangles.add(1); indexBufferTriangles.add(8);

        indexBufferTriangles.add(3); indexBufferTriangles.add(9); indexBufferTriangles.add(4);
        indexBufferTriangles.add(3); indexBufferTriangles.add(4); indexBufferTriangles.add(2);
        indexBufferTriangles.add(3); indexBufferTriangles.add(2); indexBufferTriangles.add(6);
        indexBufferTriangles.add(3); indexBufferTriangles.add(6); indexBufferTriangles.add(8);
        indexBufferTriangles.add(3); indexBufferTriangles.add(8); indexBufferTriangles.add(9);

        indexBufferTriangles.add(4); indexBufferTriangles.add(9); indexBufferTriangles.add(5);
        indexBufferTriangles.add(2); indexBufferTriangles.add(4); indexBufferTriangles.add(11);
        indexBufferTriangles.add(6); indexBufferTriangles.add(2); indexBufferTriangles.add(10);
        indexBufferTriangles.add(8); indexBufferTriangles.add(6); indexBufferTriangles.add(7);
        indexBufferTriangles.add(9); indexBufferTriangles.add(8); indexBufferTriangles.add(1);
    }

    private void createSphere(int iterCount) {
        // Every triangle in the icosahedron is divided into 4 smaller ones
        // "Smoothness" of the sphere is defined by the number of iterations
        List<Point3D> tmpVertexBuffer;
        List<Integer> tmpIndexBuffer;
        for(int i = 0; i < iterCount; i ++) {
            tmpVertexBuffer = new ArrayList<Point3D>();
            tmpIndexBuffer = new ArrayList<Integer>();
            for(int j = 0; j < indexBufferTriangles.size() - 2; j += 3) {
                // We need to split every single triangle from the triangle mesh into 4 smaller ones
                Point3D midpoint1 = getMidpoint(getVertex(getIndex(TopologyType.TRIANGLES, j)),
                                                getVertex(getIndex(TopologyType.TRIANGLES, j + 1))
                );
                Point3D midpoint2 = getMidpoint(getVertex(getIndex(TopologyType.TRIANGLES, j + 1)),
                                                getVertex(getIndex(TopologyType.TRIANGLES, j + 2))
                );
                Point3D midpoint3 = getMidpoint(getVertex(getIndex(TopologyType.TRIANGLES, j + 2)),
                                                getVertex(getIndex(TopologyType.TRIANGLES, j ))
                );
                // Triangle #1
                tmpIndexBuffer.add(addToTmpBuffer(getVertex(getIndex(TopologyType.TRIANGLES, j)), tmpVertexBuffer));
                tmpIndexBuffer.add(addToTmpBuffer(midpoint1, tmpVertexBuffer));
                tmpIndexBuffer.add(addToTmpBuffer(midpoint3, tmpVertexBuffer));
                // Triangle #2
                tmpIndexBuffer.add(addToTmpBuffer(getVertex(getIndex(TopologyType.TRIANGLES, j + 1)), tmpVertexBuffer));
                tmpIndexBuffer.add(addToTmpBuffer(midpoint2, tmpVertexBuffer));
                tmpIndexBuffer.add(addToTmpBuffer(midpoint1, tmpVertexBuffer));
                // Triangle #3
                tmpIndexBuffer.add(addToTmpBuffer(getVertex(getIndex(TopologyType.TRIANGLES, j + 2)), tmpVertexBuffer));
                tmpIndexBuffer.add(addToTmpBuffer(midpoint3, tmpVertexBuffer));
                tmpIndexBuffer.add(addToTmpBuffer(midpoint2, tmpVertexBuffer));
                // Triangle #4
                tmpIndexBuffer.add(addToTmpBuffer(midpoint1, tmpVertexBuffer));
                tmpIndexBuffer.add(addToTmpBuffer(midpoint2, tmpVertexBuffer));
                tmpIndexBuffer.add(addToTmpBuffer(midpoint3, tmpVertexBuffer));
            }
            // Add smoother mesh to the solid's vertex and index buffer
            vertexBuffer = new ArrayList<>(tmpVertexBuffer);
            indexBufferTriangles = new ArrayList<>(tmpIndexBuffer);
        }
    }

    // Utility functions
    private int addToTmpBuffer(Point3D vertex, List<Point3D> tmpVertexBuffer) {
        if(!tmpVertexBuffer.contains(vertex)) {
            double length = Math.sqrt(vertex.x * vertex.x + vertex.y * vertex.y + vertex.z * vertex.z);
            tmpVertexBuffer.add(new Point3D(vertex.x / length, vertex.y / length, vertex.z / length));
            return tmpVertexBuffer.size() - 1;
        }
        return tmpVertexBuffer.indexOf(vertex);
    }

    private Point3D getMidpoint(Point3D a, Point3D b) {
        double x = (a.x + b.x) / 2.0;
        double y = (a.y + b.y) / 2.0;
        double z = (a.z + b.z) / 2.0;
        return new Point3D(x, y, z);
    }
}
