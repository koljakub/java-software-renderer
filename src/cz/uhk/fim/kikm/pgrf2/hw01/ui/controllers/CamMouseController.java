package cz.uhk.fim.kikm.pgrf2.hw01.ui.controllers;

import cz.uhk.fim.kikm.pgrf2.hw01.core.scene.Scene;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Mouse controller for the camera.
 * Created by Jakub Kol on 2/22/16.
 */
public final class CamMouseController extends MouseAdapter {
    public static final double DEFAULT_SCR_SPEED = 3.0F;
    private final Scene scene;
    private final int canvasWidth;
    private final int canvasHeight;
    private double scrollSpeed;
    private int oldX = 0;
    private int oldY = 0;
    private int deltaX = 0;
    private int deltaY = 0;
    private boolean isPressed = false;

    public CamMouseController(Scene scene, double scrollSpeed, int canvasWidth, int canvasHeight) {
        this.scene = scene;
        this.scrollSpeed = scrollSpeed;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() == -1) {
            scene.getCamera().forward(scrollSpeed);
            scene.redraw();
        } else {
            scene.getCamera().backward(scrollSpeed);
            scene.redraw();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        oldX = e.getX();
        oldY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        deltaX = oldX - e.getX();
        deltaY = oldY - e.getY();
        scene.getCamera().addAzimuth((Math.PI * deltaX) / canvasWidth);
        scene.getCamera().addZenith((Math.PI * deltaY) / canvasHeight);
        oldX = e.getX();
        oldY = e.getY();
        scene.redraw();
    }
}
