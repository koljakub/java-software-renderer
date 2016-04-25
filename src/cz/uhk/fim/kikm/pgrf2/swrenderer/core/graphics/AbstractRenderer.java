package cz.uhk.fim.kikm.pgrf2.swrenderer.core.graphics;

import cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d.Renderable;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.canvas.Canvas;
import transforms.Mat4;
import transforms.Vec3D;

/**
 * Skeletal implementation of Renderer interface.
 * Created by Jakub Kol on 2/23/16.
 */
public abstract class AbstractRenderer implements Renderer {
    protected final Canvas canvas;

    /* Contract: Subclasses must not mutate this field.
     *           Non-package members can access this field via public getter
     *           where deep copy of the object is returned.
     */
    protected Mat4 sceneMatrix;

    // Contract: Subclasses must not mutate following fields.
    protected final Vec3D switchCoordsVec = new Vec3D(1.0, -1.0, 1.0);
    protected final Vec3D translateVec = new Vec3D(1.0, 1.0, 0.0);
    protected final Vec3D transfVec;

    public AbstractRenderer(Canvas canvas, Mat4 sceneMatrix) {
        this.canvas = canvas;
        transfVec = new Vec3D((canvas.getWidth()-1) / 2.0,(canvas.getHeight()-1) / 2.0, 1.0);
        this.sceneMatrix = new Mat4(sceneMatrix);
    }

    @Override
    abstract public void renderObject(Renderable renderableObject);

    @Override
    abstract public void refreshZBuffer();

    @Override
    public void clearCanvas() {
        canvas.setBgColor(canvas.getBgColor());
    }

    @Override
    public void setSceneMatrix(Mat4 sceneMatrix) {
        this.sceneMatrix = new Mat4(sceneMatrix);
    }

    @Override
    public Mat4 getSceneMatrix() {
        return new Mat4(sceneMatrix);
    }
}
