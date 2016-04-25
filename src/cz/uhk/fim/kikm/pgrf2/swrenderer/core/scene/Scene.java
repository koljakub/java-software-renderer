package cz.uhk.fim.kikm.pgrf2.swrenderer.core.scene;

import cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d.*;
import cz.uhk.fim.kikm.pgrf2.swrenderer.core.graphics.*;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.canvas.Canvas;
import transforms.*;

import java.util.*;
import java.util.List;

/**
 * 3D scene representation.
 * Consists of transformable 3D objects (+ non-transformable coordinate axes).
 * Created by Jakub Kol on 2/20/16.
 */
public final class Scene {
    private static final Vec3D CAM_VEC = new Vec3D(24.5,26.3,15.9);
    public static final double CAM_AZIMUTH = -2.289722062236285;
    public static final double CAM_ZENITH = -0.5276894128883556;
    public static final int CAM_RADIUS = 55;
    private static final boolean CAM_FIRST_PERSON = false;
    private Renderer mainRenderer;
    private final WireframeRenderer wireframeRenderer;
    private final ComplexRenderer complexRenderer;
    private final List<Renderable> renderableObjects = new ArrayList<Renderable>(5);
    private boolean coordAxesEnabled = true;
    private final Camera camera = new Camera();
    private Mat4 viewMatrix;
    private final Mat4 perspMatrix = new Mat4PerspRH(Math.PI/4.0,0.75, 0.1, 200.0);
    private final Mat4 orthoMatrix;
    private Mat4 projectionMatrix = perspMatrix;
    private Mat4 sceneMatrix = new Mat4Identity();
    private final Canvas canvas;

    public Scene(Canvas canvas) {
        this.canvas = canvas;
        this.orthoMatrix = new Mat4OrthoRH(100, 100, 0.1, 200);
        this.mainRenderer = new ComplexRenderer(canvas, sceneMatrix);
        this.wireframeRenderer = new WireframeRenderer(canvas, sceneMatrix);
        this.complexRenderer = new ComplexRenderer(canvas, sceneMatrix);
        setupCamera(CAM_VEC, CAM_AZIMUTH, CAM_ZENITH, CAM_RADIUS, CAM_FIRST_PERSON);
    }

    public void setupCamera(Vec3D vec, double azimuth, double zenith, int radius, boolean firstPerson) {
        camera.setPosition(vec);
        camera.setAzimuth(azimuth);
        camera.setZenith(zenith);
        camera.setRadius(radius);
        camera.setFirstPerson(firstPerson);
        viewMatrix = camera.getViewMatrix();
    }

    public void redrawCoordAxes() {
        coordAxesEnabled = !coordAxesEnabled;
        redraw();
    }

    public void switchRenderingMode() {
        if(mainRenderer instanceof ComplexRenderer) {
            wireframeRenderer.setSceneMatrix(mainRenderer.getSceneMatrix());
            mainRenderer = wireframeRenderer;
        } else {
            complexRenderer.setSceneMatrix(mainRenderer.getSceneMatrix());
            mainRenderer = complexRenderer;
        }
        redraw();
    }

    public void switchProjectionType() {
        if(projectionMatrix instanceof Mat4PerspRH) {
            this.projectionMatrix = orthoMatrix;
        } else {
            this.projectionMatrix = perspMatrix;
        }
        redraw();
    }

    public void redraw() {
        mainRenderer.clearCanvas();
        viewMatrix = camera.getViewMatrix();
        sceneMatrix = viewMatrix.mul(projectionMatrix);
        mainRenderer.setSceneMatrix(sceneMatrix);
        for(Renderable renderableObject : renderableObjects) {
            if(coordAxesEnabled && renderableObject instanceof CoordinateAxes) {
                mainRenderer.renderObject(renderableObject);
            }
            else if(!coordAxesEnabled && renderableObject instanceof CoordinateAxes) continue;
            else {
                mainRenderer.renderObject(renderableObject);
            }
        }
        canvas.repaint();
        mainRenderer.refreshZBuffer();
    }

    public void addObject(Renderable renderableObject) {
        renderableObjects.add(renderableObject);
    }

    public void removeObject(Renderable renderableObject) {
        renderableObjects.remove(renderableObject);
    }

    public void clear() {
        renderableObjects.clear();
        redraw();
    }

    public Camera getCamera() {
        return camera;
    }

}
