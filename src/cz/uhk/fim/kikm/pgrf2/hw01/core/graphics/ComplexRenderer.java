package cz.uhk.fim.kikm.pgrf2.hw01.core.graphics;

import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.CoordinateAxes;
import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.Renderable;
import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.TopologyType;
import cz.uhk.fim.kikm.pgrf2.hw01.ui.canvas.Canvas;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;
import java.awt.*;

/**
 * Advanced renderer used for rendering 3D objects defined by triangle meshes.
 * Created by Jakub Kol on 2/24/16.
 */
public final class ComplexRenderer extends AbstractRenderer {
    private static final TopologyType objectTopology = TopologyType.TRIANGLES;
    private static final double W_MIN = 0.5;
    private double[][] zBuffer;
    private static final double Z_BUFFER_MAX_VAL = 1.0;

    public ComplexRenderer(Canvas canvas, Mat4 sceneMatrix) {
        super(canvas, sceneMatrix);
        this.zBuffer = new double[canvas.getWidth()][canvas.getHeight()];
        refreshZBuffer();
    }

    /*-------------------------------------------------------------------------
     * Math notes - Linear interpolation
     * ------------------------------------------------------------------------
     * Endpoints: A[x0, y0], B[x1, y1]
     *
     * t = (x - x0) / (x1 - x0)
     * y = (1 - t) * y0 + t * y1
     *
     * s = (y - y0) / (y1 - y0)
     * x = (1 - s) * x0 + s * x1
     *-------------------------------------------------------------------------
     */

    private void renderTriangle(Point3D a, Point3D b, Point3D c, Color color, Mat4 transformMatrix) {
        a = a.mul(transformMatrix.mul(sceneMatrix));
        b = b.mul(transformMatrix.mul(sceneMatrix));
        c = c.mul(transformMatrix.mul(sceneMatrix));
        // We need to sort points by the value of w-coord
        // Such that a.w >= b.w >= c.w
        if(b.w > a.w) { // A - B comparison
            Point3D tmpB = b;
            b = a;
            a = tmpB;
        }
        if(c.w > b.w) { // B - C comparison
            Point3D tmpC = c;
            c = b;
            b = tmpC;
        }
        if(b.w > a.w) { // (B - C) - A comparison
            Point3D tmpA = a;
            a = b;
            b = tmpA;
        }
        if(a.w < W_MIN) { // Triangle is not visible
            return;
        }
        else if(c.w > W_MIN) { // Whole triangle is visible
            rasterizeTriangle(a, b, c, color);
        }
        else if(b.w > W_MIN) { // Triangle is partially visible
            double t = (b.w - W_MIN) / (b.w - c.w);
            Point3D vA = b.mul(1.0 - t).add(c.mul(t));
            t = (a.w - W_MIN) / (a.w - c.w);
            Point3D vB = a.mul(1.0 - t).add(c.mul(t));
            rasterizeTriangle(a, b, vA, color);
            rasterizeTriangle(a, vA, vB, color);
        }
        else { // Triangle is partially visible
            double t = (a.w - W_MIN) / (a.w - b.w);
            Point3D vA = a.mul(1.0 - t).add(b.mul(t));
            t = (a.w - W_MIN) / (a.w - c.w);
            Point3D vB = a.mul(1.0 - t).add(c.mul(t));
            rasterizeTriangle(a, vA, vB, color);
        }
    }

    private void rasterizeTriangle(Point3D a, Point3D b, Point3D c, Color color) {
        Vec3D vecA = a.dehomog();
        Vec3D vecB = b.dehomog();
        Vec3D vecC = c.dehomog();
        vecA = vecA.mul(super.switchCoordsVec).add(super.translateVec).mul(super.transfVec);
        vecB = vecB.mul(super.switchCoordsVec).add(super.translateVec).mul(super.transfVec);
        vecC = vecC.mul(super.switchCoordsVec).add(super.translateVec).mul(super.transfVec);
        // We need to sort points by the value of y-coord.
        // Such that a.y < b.y < c.y
        if(vecA.y > vecB.y) {
            Vec3D tmpVec = vecA;
            vecA = vecB;
            vecB = tmpVec;
        }
        if(vecB.y > vecC.y) {
            Vec3D tmpVec = vecB;
            vecB = vecC;
            vecC = tmpVec;
        }
        if(vecA.y > vecB.y) {
            Vec3D tmpVec = vecA;
            vecA = vecB;
            vecB = tmpVec;
        }
        rasterizePartially(vecA, vecC, vecA, vecB, color); // A -> B
        rasterizePartially(vecA, vecC, vecB, vecC, color); // B -> C
    }

    private void rasterizePartially(Vec3D vecA, Vec3D vecC, Vec3D upperBound, Vec3D lowerBound, Color color) {
        for(int y = Math.max((int)upperBound.y + 1, 0); y <= Math.min(lowerBound.y, canvas.getHeight() - 1); y++) {
            double s1 = (y - upperBound.y) / (lowerBound.y - upperBound.y);
            double x1 = upperBound.x * (1.0 - s1) + lowerBound.x * s1;
            double z1 = upperBound.z * (1.0 - s1) + lowerBound.z * s1;
            double s2 = (y - vecA.y) / (vecC.y - vecA.y);
            double x2 = vecA.x * (1.0 - s2) + vecC.x * s2;
            double z2 = vecA.z * (1.0 - s2) + vecC.z * s2;
            // x1 must be smaller than x2
            // If x2 is greater than x1, switch them (+ associated z)
            if(x1 > x2) {
                double tmpX1 = x1;
                x1 = x2;
                x2 = tmpX1;
                double tmpZ1 = z1;
                z1 = z2;
                z2 = tmpZ1;
            }
            double t;
            double z;
            for(int x = Math.max((int)x1 + 1, 0); x <= Math.min(x2, canvas.getWidth() - 1); x++) {
                t = (x - x1) / (x2 - x1);
                z = z1 * (1.0 - t) + z2 * t;
                // Visibility check
                if(z < zBuffer[x][y] && z >= 0.0) {
                    zBuffer[x][y] = z;
                    canvas.drawPixelAt(x, y, color.getRGB());
                }
            }
        }
    }

    private void renderLine(Point3D a, Point3D b, Color color, Mat4 transformMatrix) {
        a = a.mul(transformMatrix.mul(sceneMatrix));
        b = b.mul(transformMatrix.mul(sceneMatrix));
        if (a.w <= 0 || b.w <= 0) return; // End points are behind the camera => return.
        Point3D aClipped;
        Point3D bClipped;
        double tmp;
        if(a.w < W_MIN) { // Let's clip the endpoint a.
            tmp = (W_MIN - a.w / (b.w - a.w));
            aClipped = a.mul(1 - tmp).add(b.mul(tmp));
        } else {
            aClipped = a;
        }
        if(b.w < W_MIN) { // And the endpoint b.
            tmp = (W_MIN - a.w / (b.w - a.w));
            bClipped = b.mul(1 - tmp).add(b.mul(tmp));
        } else {
            bClipped = b;
        }
        rasterizeLine(aClipped, bClipped, color);
    }

    private void rasterizeLine(Point3D a, Point3D b, Color color) {
        Vec3D vecA = a.dehomog();
        Vec3D vecB = b.dehomog();
        vecA = vecA.mul(super.switchCoordsVec).add(super.translateVec).mul(super.transfVec);
        vecB = vecB.mul(super.switchCoordsVec).add(super.translateVec).mul(super.transfVec);
        // Main axis check
        if(Math.abs(vecA.x - vecB.x) > Math.abs(vecA.y - vecB.y)) { // X-axis
            if(vecA.x > vecB.x) {
                Vec3D tmpVec = vecA;
                vecA = vecB;
                vecB = tmpVec;
            }
            double t;
            double z;
            int y;
            for(int x = Math.max((int)vecA.x + 1, 0); x < Math.min(vecB.x, canvas.getWidth() - 1); x++) {
                t = (x - vecA.x) / (vecB.x - vecA.x);
                y = (int)((1.0 - t) * vecA.y + t * vecB.y);
                z = vecA.z * (1.0 - t) + vecB.z * t;
                if(y > 0 && (y < canvas.getHeight() - 1) && z < zBuffer[x][y]) {
                    zBuffer[x][y] = z;
                    canvas.drawPixelAt(x, y, color.getRGB());
                }
            }
        } else { // Y-axis
            if(vecA.y > vecB.y) {
                Vec3D tmpVec = vecA;
                vecA = vecB;
                vecB = tmpVec;
            }
            double s;
            double z;
            int x;
            for(int y = Math.max((int)vecA.y + 1, 0); y < Math.min(vecB.y, canvas.getHeight() - 1); y++) {
                s = (y - vecA.y) / (vecB.y - vecA.y);
                z = vecA.z * (1.0 - s) + vecB.z * s;
                x = (int)((1.0 - s) * vecA.x + s * vecB.x);
                if(x > 0 && (x < canvas.getWidth() - 1) && z < zBuffer[x][y]) {
                    zBuffer[x][y] = z;
                    canvas.drawPixelAt(x, y, color.getRGB());
                }
            }
        }
    }

    @Override
    public void refreshZBuffer() {
        // Performance improvement - iterate over columns first.
        for(int y = 0; y < zBuffer[0].length; y++) {
            for(int x = 0; x < zBuffer.length; x++) {
                zBuffer[x][y] = Z_BUFFER_MAX_VAL;
            }
        }
    }

    @Override
    public void renderObject(Renderable renderableObject) {
        int colorIndex = 0;
        if(renderableObject instanceof CoordinateAxes) {
            for (int i = 0; i < renderableObject.getIndexBufferSize(TopologyType.LINES); i += 2) {
                renderLine(renderableObject.getVertex(renderableObject.getIndex(TopologyType.LINES, i)),
                        renderableObject.getVertex(renderableObject.getIndex(TopologyType.LINES, i + 1)),
                        renderableObject.getColor(colorIndex),
                        renderableObject.getTransformMatrix()
                );
                colorIndex++;
            }
        } else {
            for (int i = 0; i < renderableObject.getIndexBufferSize(objectTopology); i += 3) {
                renderTriangle(renderableObject.getVertex(renderableObject.getIndex(objectTopology, i)),
                        renderableObject.getVertex(renderableObject.getIndex(objectTopology, i + 1)),
                        renderableObject.getVertex(renderableObject.getIndex(objectTopology, i + 2)),
                        renderableObject.getColor(colorIndex),
                        renderableObject.getTransformMatrix()
                );
                colorIndex++;
            }
        }
    }
}
