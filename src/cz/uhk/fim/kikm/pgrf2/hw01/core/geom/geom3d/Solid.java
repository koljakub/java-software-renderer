package cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d;

import transforms.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Skeletal implementation of Renderable interface.
 * Represents generic 3D object.
 * Created by Jakub Kol on 2/18/16.
 */
public abstract class Solid implements Renderable {
    protected List<Point3D> vertexBuffer = new ArrayList<Point3D>();
    protected List<Integer> indexBufferTriangles = new ArrayList<Integer>();
    protected List<Integer> indexBufferLines = new ArrayList<Integer>();
    protected List<Color> colorList;
    private Mat4 transformMatrix = new Mat4Identity();

    public Solid() {
    }

    public Solid(List<Color> colorList) {
        this.colorList = new ArrayList<Color>(colorList);
    }

    @Override
    public void setColorList(List<Color> colorList) {
        this.colorList = new ArrayList<Color>(colorList);
    }

    @Override
    public List<Color> getColorList() {
        return new ArrayList<Color>(colorList);
    }

    @Override
    public Color getColor(int index) {
        return colorList.get(index);
    }

    @Override
    public Point3D getVertex(int index) {
        return new Point3D(vertexBuffer.get(index));
    }

    @Override
    public int getIndex(TopologyType topology, int index) {
        if(topology == TopologyType.LINES) {
            return indexBufferLines.get(index);
        } else {
            return indexBufferTriangles.get(index);
        }
    }

    @Override
    public int getVertexBufferSize() {
        return vertexBuffer.size();
    }

    @Override
    public int getIndexBufferSize(TopologyType topology) {
        if(topology == TopologyType.LINES) {
            return indexBufferLines.size();
        } else {
            return indexBufferTriangles.size();
        }
    }

    @Override
    public void setTransformMatrix(Mat4 transformMatrix) {
        this.transformMatrix = new Mat4(transformMatrix);
    }

    @Override
    public Mat4 getTransformMatrix() {
        return new Mat4(transformMatrix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solid solid = (Solid) o;

        if (vertexBuffer != null ? !vertexBuffer.equals(solid.vertexBuffer) : solid.vertexBuffer != null) return false;
        if (indexBufferTriangles != null ? !indexBufferTriangles.equals(solid.indexBufferTriangles) : solid.indexBufferTriangles != null)
            return false;
        if (indexBufferLines != null ? !indexBufferLines.equals(solid.indexBufferLines) : solid.indexBufferLines != null)
            return false;
        if (colorList != null ? !colorList.equals(solid.colorList) : solid.colorList != null) return false;
        return transformMatrix != null ? transformMatrix.equals(solid.transformMatrix) : solid.transformMatrix == null;

    }

    @Override
    public int hashCode() {
        int result = vertexBuffer != null ? vertexBuffer.hashCode() : 0;
        result = 31 * result + (indexBufferTriangles != null ? indexBufferTriangles.hashCode() : 0);
        result = 31 * result + (indexBufferLines != null ? indexBufferLines.hashCode() : 0);
        result = 31 * result + (colorList != null ? colorList.hashCode() : 0);
        result = 31 * result + (transformMatrix != null ? transformMatrix.hashCode() : 0);
        return result;
    }
}