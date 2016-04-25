package cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d;

import transforms.Point3D;

import java.awt.*;
import java.util.List;

/**
 * 3D pyramid representation.
 *
 *             4
 *             \
 *            / \ \
 *           /   \  \
 *          /     \   \
 *         /       \    \
 *      0 /---------\-----\ 1
 *       /           \    /
 *      /             \  /
 *   3 ----------------\/ 2
 *
 * Created by Jakub Kol on 2/20/16.
 */
public final class Pyramid extends Solid {
    private final int height;
    private final int edgeLength;

    public Pyramid(int height, int edgeLength) {
        super();
        this.height = height;
        this.edgeLength = edgeLength;
        buildPyramid();
    }

    public Pyramid(int height, int edgeLength, List<Color> colorList) {
        super(colorList);
        this.height = height;
        this.edgeLength = edgeLength;
        buildPyramid();
    }

    public void buildPyramid() {
        // Let's build a pyramid! (Just like old Egyptians.)                              // Vertex - index in VB
        vertexBuffer.add(new Point3D(0, 0, 0));                                     // 0 - 0
        vertexBuffer.add(new Point3D(0, edgeLength, 0));                            // 1 - 1
        vertexBuffer.add(new Point3D(edgeLength, edgeLength, 0));                   // 2 - 2
        vertexBuffer.add(new Point3D(edgeLength, 0, 0));                            // 3 - 3
        vertexBuffer.add(new Point3D(edgeLength / 2.0, edgeLength / 2.0, height));  // 4 - 4
        // TopologyType - triangles
        indexBufferTriangles.add(0); indexBufferTriangles.add(1); indexBufferTriangles.add(2);
        indexBufferTriangles.add(0); indexBufferTriangles.add(3); indexBufferTriangles.add(2);
        indexBufferTriangles.add(1); indexBufferTriangles.add(0); indexBufferTriangles.add(4);
        indexBufferTriangles.add(0); indexBufferTriangles.add(3); indexBufferTriangles.add(4);
        indexBufferTriangles.add(3); indexBufferTriangles.add(2); indexBufferTriangles.add(4);
        indexBufferTriangles.add(2); indexBufferTriangles.add(1); indexBufferTriangles.add(4);
        // TopologyType - lines
        indexBufferLines.add(0); indexBufferLines.add(1);
        indexBufferLines.add(1); indexBufferLines.add(2);
        indexBufferLines.add(2); indexBufferLines.add(3);
        indexBufferLines.add(3); indexBufferLines.add(0);
        indexBufferLines.add(0); indexBufferLines.add(4);
        indexBufferLines.add(1); indexBufferLines.add(4);
        indexBufferLines.add(2); indexBufferLines.add(4);
        indexBufferLines.add(3); indexBufferLines.add(4);
    }
}
