package cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d;

import transforms.Point3D;
import java.awt.Color;
import java.util.List;

/**
 * 3D cube representation.
 *
 *          4 -------- 5
 *         /|         /|
 *        / |        / |
 *       /  |       /  |
 *      6 -------- 7   |
 *      |   |      |   |
 *      |   0 -----|-- 1
 *      |  /       |  /
 *      | /        | /
 *      |/         |/
 *      3 -------- 2
 *
 * Created by Jakub Kol on 2/21/16.
 */
public final class Cube extends Solid {
    private final int edgeLength;

    public Cube(int edgeLength) {
        super();
        this.edgeLength = edgeLength;
        buildCube();
    }

    public Cube(int edgeLength, List<Color> colorList) {
        super(colorList);
        this.edgeLength = edgeLength;
        buildCube();
    }

    private void buildCube() {
        // Let's build a cube!
        vertexBuffer.add(new Point3D(0.0, 0.0, 0.0));                       // 0
        vertexBuffer.add(new Point3D(0.0, edgeLength, 0.0));                // 1
        vertexBuffer.add(new Point3D(edgeLength, edgeLength, 0.0));         // 2
        vertexBuffer.add(new Point3D(edgeLength, 0.0, 0.0));                // 3
        vertexBuffer.add(new Point3D(0.0, 0.0, edgeLength));                // 4
        vertexBuffer.add(new Point3D(0.0, edgeLength, edgeLength));         // 5
        vertexBuffer.add(new Point3D(edgeLength, 0.0, edgeLength));         // 6
        vertexBuffer.add(new Point3D(edgeLength, edgeLength, edgeLength));  // 7
        // Alert! The following code is ugly! :-)
        // Topology type - triangles
        indexBufferTriangles.add(0); indexBufferTriangles.add(1); indexBufferTriangles.add(2);
        indexBufferTriangles.add(0); indexBufferTriangles.add(3); indexBufferTriangles.add(2);
        indexBufferTriangles.add(3); indexBufferTriangles.add(2); indexBufferTriangles.add(7);
        indexBufferTriangles.add(7); indexBufferTriangles.add(6); indexBufferTriangles.add(3);
        indexBufferTriangles.add(6); indexBufferTriangles.add(3); indexBufferTriangles.add(0);
        indexBufferTriangles.add(0); indexBufferTriangles.add(4); indexBufferTriangles.add(6);
        indexBufferTriangles.add(4); indexBufferTriangles.add(0); indexBufferTriangles.add(1);
        indexBufferTriangles.add(1); indexBufferTriangles.add(5); indexBufferTriangles.add(4);
        indexBufferTriangles.add(1); indexBufferTriangles.add(5); indexBufferTriangles.add(7);
        indexBufferTriangles.add(7); indexBufferTriangles.add(2); indexBufferTriangles.add(1);
        indexBufferTriangles.add(4); indexBufferTriangles.add(5); indexBufferTriangles.add(7);
        indexBufferTriangles.add(7); indexBufferTriangles.add(6); indexBufferTriangles.add(4);
        // Topology type - lines
        indexBufferLines.add(0); indexBufferLines.add(1);
        indexBufferLines.add(1); indexBufferLines.add(2);
        indexBufferLines.add(2); indexBufferLines.add(3);
        indexBufferLines.add(3); indexBufferLines.add(0);
        indexBufferLines.add(0); indexBufferLines.add(4);
        indexBufferLines.add(4); indexBufferLines.add(5);
        indexBufferLines.add(5); indexBufferLines.add(1);
        indexBufferLines.add(5); indexBufferLines.add(7);
        indexBufferLines.add(7); indexBufferLines.add(2);
        indexBufferLines.add(7); indexBufferLines.add(6);
        indexBufferLines.add(6); indexBufferLines.add(3);
        indexBufferLines.add(6); indexBufferLines.add(4);
    }

}
