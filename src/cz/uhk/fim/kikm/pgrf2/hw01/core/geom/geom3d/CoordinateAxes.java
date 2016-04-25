package cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d;

import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 3D coordinate axes representation.
 * Created by Jakub Kol on 2/21/16.
 */
public final class CoordinateAxes implements Renderable {
    private final List<Point3D> vertexBuffer = new ArrayList<Point3D>(4);
    private final List<Integer> indexBuffer = new ArrayList<Integer>();
    private List<Color> colorList;
    private final Mat4 transformMatrix = new Mat4Identity();
    private final int axisLength;

    public CoordinateAxes(int axisLength) {
        this.axisLength = axisLength;
        initAxes();
    }

    public CoordinateAxes(int axisLength, List<Color> colorList) {
        this.colorList = new ArrayList<>(colorList);
        this.axisLength = axisLength;
        initAxes();
    }

    @Override
    public void setColorList(List<Color> colorList) {
        this.colorList = new ArrayList<>(colorList);
    }

    @Override
    public List<Color> getColorList() {
        return new ArrayList<Color>(colorList);
    }

    private void initAxes() {
        // Vertex buffer
        // Central point
        vertexBuffer.add(new Point3D(0.0f,0.0f,0.0f));
        // X-axis end point
        vertexBuffer.add(new Point3D(axisLength, 0.0f, 0.0f));
        // Y-axis end point
        vertexBuffer.add(new Point3D(0.0f, axisLength, 0.0f));
        // Z-axis end point
        vertexBuffer.add(new Point3D(0.0f, 0.0f, axisLength));
        // Index buffer
        for(int i=1; i <= 3; i++) {
            indexBuffer.add(0);
            indexBuffer.add(i);
        }
    }

    @Override
    public Color getColor(int index) {
        return colorList.get(index);
    }

    @Override
    public Point3D getVertex(int index) {
        return vertexBuffer.get(index);
    }

    @Override
    public int getIndex(TopologyType topology, int index) {
        return indexBuffer.get(index);
    }

    @Override
    public int getVertexBufferSize() {
        return vertexBuffer.size();
    }

    @Override
    public int getIndexBufferSize(TopologyType topology) {
        return indexBuffer.size();
    }

    @Override
    public void setTransformMatrix(Mat4 transformMatrix) {
        // Coordinate axes are static (non-transformable) -> do nothing.
    }

    @Override
    public Mat4 getTransformMatrix() {
        return new Mat4(transformMatrix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordinateAxes that = (CoordinateAxes) o;

        if (axisLength != that.axisLength) return false;
        if (vertexBuffer != null ? !vertexBuffer.equals(that.vertexBuffer) : that.vertexBuffer != null) return false;
        if (indexBuffer != null ? !indexBuffer.equals(that.indexBuffer) : that.indexBuffer != null) return false;
        if (colorList != null ? !colorList.equals(that.colorList) : that.colorList != null) return false;
        return transformMatrix != null ? transformMatrix.equals(that.transformMatrix) : that.transformMatrix == null;

    }

    @Override
    public int hashCode() {
        int result = vertexBuffer != null ? vertexBuffer.hashCode() : 0;
        result = 31 * result + (indexBuffer != null ? indexBuffer.hashCode() : 0);
        result = 31 * result + (colorList != null ? colorList.hashCode() : 0);
        result = 31 * result + (transformMatrix != null ? transformMatrix.hashCode() : 0);
        result = 31 * result + axisLength;
        return result;
    }
}
