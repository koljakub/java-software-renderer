package cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d;

import transforms.Mat4;
import transforms.Point3D;

import java.awt.Color;
import java.util.List;

/**
 * Abstract representation of an object which can be rendered to the canvas.
 * Created by Jakub Kol on 2/21/16.
 */
public interface Renderable {

    public void setColorList(List<Color> colorList);

    public List<Color> getColorList();

    public Color getColor(int index);

    public Point3D getVertex(int index);

    public int getIndex(TopologyType topology, int index);

    public int getVertexBufferSize();

    public int getIndexBufferSize(TopologyType topology);

    public void setTransformMatrix(Mat4 transformMatrix);

    public Mat4 getTransformMatrix();

}
