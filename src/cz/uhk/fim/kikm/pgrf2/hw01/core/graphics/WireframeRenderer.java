package cz.uhk.fim.kikm.pgrf2.hw01.core.graphics;

import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.CoordinateAxes;
import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.Renderable;
import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.Sphere;
import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.TopologyType;
import cz.uhk.fim.kikm.pgrf2.hw01.ui.canvas.Canvas;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;

/**
 * Basic renderer used for rendering a wireframe model of a particular 3D object.
 * Created by Jakub Kol on 2/23/16.
 */
public final class WireframeRenderer extends AbstractRenderer {
    private static final double W_MIN = 1E-10; // Magical constant.
    private static final TopologyType objectTopology = TopologyType.LINES;

    public WireframeRenderer(Canvas canvas, Mat4 sceneMatrix) {
        super(canvas, sceneMatrix);
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
        Vec3D vecA = aClipped.dehomog();
        Vec3D vecB = bClipped.dehomog();
        vecA = vecA.mul(super.switchCoordsVec).add(super.translateVec).mul(super.transfVec);
        vecB = vecB.mul(super.switchCoordsVec).add(super.translateVec).mul(super.transfVec);
        canvas.drawLine((int)vecA.x, (int)vecA.y, (int)vecB.x, (int)vecB.y, color);
    }

    private void renderTriangle(Point3D a, Point3D b, Point3D c, Color color, Mat4 transformMatrix) {
        renderLine(a, b, color, transformMatrix);
        renderLine(b, c, color, transformMatrix);
        renderLine(c, a, color, transformMatrix);
    }

    @Override
    public void refreshZBuffer() {
        // Z-buffering is not implemented (not necessary in a wireframe model).
        return;
    }

    @Override
    public void renderObject(Renderable renderableObject) {
        if(renderableObject instanceof CoordinateAxes) {
            int colorIndex = 0;
            for (int i = 0; i < renderableObject.getIndexBufferSize(TopologyType.LINES); i += 2) {
                renderLine(renderableObject.getVertex(renderableObject.getIndex(TopologyType.LINES, i)),
                        renderableObject.getVertex(renderableObject.getIndex(TopologyType.LINES, i + 1)),
                        renderableObject.getColor(colorIndex),
                        renderableObject.getTransformMatrix()
                );
                colorIndex++;
            }
        }
        else if(renderableObject instanceof Sphere) {
            for (int i = 0; i < renderableObject.getIndexBufferSize(TopologyType.TRIANGLES); i += 3) {
                renderTriangle(renderableObject.getVertex(renderableObject.getIndex(TopologyType.TRIANGLES, i)),
                               renderableObject.getVertex(renderableObject.getIndex(TopologyType.TRIANGLES, i + 1)),
                               renderableObject.getVertex(renderableObject.getIndex(TopologyType.TRIANGLES, i + 2)),
                               renderableObject.getColor(0),
                               renderableObject.getTransformMatrix()
                );
            }
        }
        else {
            for (int i = 0; i < renderableObject.getIndexBufferSize(objectTopology); i += 2) {
                renderLine(renderableObject.getVertex(renderableObject.getIndex(objectTopology, i)),
                        renderableObject.getVertex(renderableObject.getIndex(objectTopology, i + 1)),
                        renderableObject.getColor(0),
                        renderableObject.getTransformMatrix()
                );
            }
        }
    }
}

