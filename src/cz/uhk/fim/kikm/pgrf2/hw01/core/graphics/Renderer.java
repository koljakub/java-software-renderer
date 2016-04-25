package cz.uhk.fim.kikm.pgrf2.hw01.core.graphics;

import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.Renderable;
import transforms.Mat4;

/**
 * Abstract representation of a software renderer.
 * Created by Jakub Kol on 2/21/16.
 */
public interface Renderer {

    public void renderObject(Renderable renderableObject);

    public void refreshZBuffer();

    public void clearCanvas();

    public void setSceneMatrix(Mat4 sceneMatrix);

    public Mat4 getSceneMatrix();

}
