package cz.uhk.fim.kikm.pgrf2.swrenderer.ui.frame;

import cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d.*;
import cz.uhk.fim.kikm.pgrf2.swrenderer.core.scene.Scene;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.canvas.Canvas;
import transforms.Mat4Scale;
import transforms.Mat4Transl;
import java.util.*;
import java.awt.*;

/**
 * Static concrete scene. The code must be modified in order to rearrange it.
 * Created by Jakub Kol on 3/4/16.
 */
public final class MainScene {
    private final MainFrame frame;
    private final Scene scene;
    private final Canvas canvas;
    // 3D objects
    private Cube cube;
    private Pyramid pyramid;
    private Sphere sphere;

    public MainScene(MainFrame frame, Canvas canvas) {
        this.frame = frame;
        this.canvas = canvas;
        scene = new Scene(canvas);
    }

    public void buildScene() {
        // 3D objects initialization
        CoordinateAxes coordAxes = new CoordinateAxes(GeomConstants.COORD_AXES_DEFAULT_LEN);
        cube = new Cube(GeomConstants.CUBE_DEFAULT_EDGE_LEN);
        pyramid = new Pyramid(GeomConstants.PYRAMID_DEFAULT_HEIGHT, GeomConstants.PYRAMID_DEFAULT_BASE_EDGE_LEN);
        sphere = new Sphere(GeomConstants.SPHERE_DEFAULT_SMOOTHNESS);
        Renderable[] sceneObjects = {cube, pyramid, sphere};
        // Color lists
        java.util.List<Color> coordAxesColorList = new ArrayList<Color>();
        coordAxesColorList.add(Color.RED);
        coordAxesColorList.add(Color.GREEN);
        coordAxesColorList.add(Color.BLUE);
        coordAxes.setColorList(coordAxesColorList);
        for(Renderable sceneObject : sceneObjects) {
            java.util.List<Color> colorList = new ArrayList<Color>();
            for(int i = 0; i < sceneObject.getIndexBufferSize(TopologyType.TRIANGLES); i++) {
                colorList.add(generateRandomColor(Color.WHITE));
            }
            sceneObject.setColorList(colorList);
        }
        // 3D transformations
        pyramid.setTransformMatrix(new Mat4Transl(15.0, -7.0, 0.0));
        sphere.setTransformMatrix(new Mat4Scale(8,8,8).mul(new Mat4Transl(0.0, 25.0, 8.0)));
        scene.addObject(coordAxes);
        scene.addObject(cube);
        scene.addObject(pyramid);
        scene.addObject(sphere);
        frame.setControllableSolids(cube, pyramid, sphere);
        scene.redraw();
    }

    public void rebuildScene() {
        scene.clear();
        buildScene();
    }

    public Scene getScene() {
        return scene;
    }

    public void setCube(Cube cube) {
        cube.setTransformMatrix(this.cube.getTransformMatrix());
        scene.removeObject(this.cube);
        this.cube = cube;
        scene.addObject(cube);
        frame.setControllableSolids(cube, pyramid, sphere);
        scene.redraw();
    }

    public Cube getCube() {
        return cube;
    }

    public void setPyramid(Pyramid pyramid) {
        pyramid.setTransformMatrix(this.pyramid.getTransformMatrix());
        scene.removeObject(this.pyramid);
        this.pyramid = pyramid;
        scene.addObject(pyramid);
        frame.setControllableSolids(cube, pyramid, sphere);
        scene.redraw();
    }

    public Pyramid getPyramid() {
        return pyramid;
    }

    public void setSphere(Sphere sphere) {
        sphere.setTransformMatrix(this.sphere.getTransformMatrix());
        scene.removeObject(this.sphere);
        this.sphere = sphere;
        scene.addObject(sphere);
        frame.setControllableSolids(cube, pyramid, sphere);
        scene.redraw();
    }

    public Sphere getSphere() {
        return sphere;
    }

    // Utility function
    private Color generateRandomColor(Color mix) {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        if (mix != null) {
            red = (red + mix.getRed()) / 2;
            green = (green + mix.getGreen()) / 2;
            blue = (blue + mix.getBlue()) / 2;
        }
        Color color = new Color(red, green, blue);
        return color;
    }

}
