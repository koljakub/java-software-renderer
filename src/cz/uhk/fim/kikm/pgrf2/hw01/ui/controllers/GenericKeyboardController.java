package cz.uhk.fim.kikm.pgrf2.hw01.ui.controllers;

import cz.uhk.fim.kikm.pgrf2.hw01.core.scene.Scene;
import cz.uhk.fim.kikm.pgrf2.hw01.ui.frame.MainFrame;
import cz.uhk.fim.kikm.pgrf2.hw01.ui.frame.MainScene;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Keyboard controller. Handles key bindings.
 * Created by Jakub Kol on 2/22/16.
 */
public final class GenericKeyboardController {
    private final JPanel mfContentPane;
    private final MainFrame mainFrame;
    private final MainScene mainScene;
    public static final double DEFAULT_CAM_SPEED = 1.0F;
    private double cameraSpeed;

    public GenericKeyboardController(MainScene mainScene, double cameraSpeed, MainFrame mainFrame) {
        this.mainScene = mainScene;
        this.cameraSpeed = cameraSpeed;
        this.mainFrame = mainFrame;
        this.mfContentPane = (JPanel)mainFrame.getContentPane();
    }

    public void init() {
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "camForward");
        mfContentPane.getActionMap().put("camForward",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainScene.getScene().getCamera().forward(cameraSpeed);
                        mainScene.getScene().redraw();
                    }
                }
        );
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "camBackward");
        mfContentPane.getActionMap().put("camBackward",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainScene.getScene().getCamera().backward(cameraSpeed);
                        mainScene.getScene().redraw();
                    }
                }
        );
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "camLeft");
        mfContentPane.getActionMap().put("camLeft",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainScene.getScene().getCamera().left(cameraSpeed);
                        mainScene.getScene().redraw();
                    }
                }
        );
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "camRight");
        mfContentPane.getActionMap().put("camRight",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainScene.getScene().getCamera().right(cameraSpeed);
                        mainScene.getScene().redraw();
                    }
                }
        );
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("X"), "redrawAxes");
        mfContentPane.getActionMap().put("redrawAxes",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainScene.getScene().redrawCoordAxes();
                    }
                }
        );
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "switchRenderingMode");
        mfContentPane.getActionMap().put("switchRenderingMode",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainScene.getScene().switchRenderingMode();
                    }
                }
        );
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "rebuildScene");
        mfContentPane.getActionMap().put("rebuildScene",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainScene.rebuildScene();
                    }
                }
        );
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"), "changeProjectionType");
        mfContentPane.getActionMap().put("changeProjectionType",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainScene.getScene().switchProjectionType();
                    }
                }
        );
        mfContentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("H"), "hideControlPanel");
        mfContentPane.getActionMap().put("hideControlPanel",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainFrame.hideControlPanel();
                    }
                }
        );
    }
}
