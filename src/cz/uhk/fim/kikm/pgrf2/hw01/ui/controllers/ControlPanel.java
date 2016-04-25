package cz.uhk.fim.kikm.pgrf2.hw01.ui.controllers;

import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.Cube;
import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.Pyramid;
import cz.uhk.fim.kikm.pgrf2.hw01.core.geom.geom3d.Sphere;
import cz.uhk.fim.kikm.pgrf2.hw01.ui.frame.MainFrame;
import cz.uhk.fim.kikm.pgrf2.hw01.ui.frame.MainScene;
import transforms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 3D transformations controller.
 * Created by Jakub Kol on 2/29/16.
 */
public final class ControlPanel extends JPanel {
    private final MainFrame mainFrame;
    private final JPanel pnlLeft = new JPanel();
    private final JButton btnTranslateXPlus = new JButton("Translate X+");
    private final JButton btnTranslateXMinus = new JButton("Translate X-");
    private final JButton btnTranslateYPlus = new JButton("Translate Y+");
    private final JButton btnTranslateYMinus = new JButton("Translate Y-");
    private final JButton btnTranslateZPlus = new JButton("Translate Z+");
    private final JButton btnTranslateZMinus = new JButton("Translate Z-");
    private final JPanel pnlLeftCenter = new JPanel();
    private final JPanel pnlCenter = new JPanel();
    private final JCheckBox checkBoxCube = new JCheckBox("Cube:       ");
    private final JCheckBox checkBoxPyramid = new JCheckBox("Pyramid:  ");
    private final JCheckBox checkBoxSphere = new JCheckBox("Sphere:   ");
    private final JPanel pnlRightCenter = new JPanel();
    private final JPanel pnlRight = new JPanel();
    private final JButton btnRotateXPlus = new JButton("Rotate X+");
    private final JButton btnRotateXMinus = new JButton("Rotate X-");
    private final JButton btnRotateYPlus = new JButton("Rotate Y+");
    private final JButton btnRotateYMinus = new JButton("Rotate Y-");
    private final JButton btnRotateZPlus = new JButton("Rotate Z+");
    private final JButton btnRotateZMinus = new JButton("Rotate Z-");
    private final JButton btnScalePlus = new JButton("+");
    private final JButton btnScaleMinus = new JButton("-");
    // 3D objects
    private final MainScene mainScene;
    private Cube cube;
    private Pyramid pyramid;
    private Sphere sphere;
    public static final double DEFAULT_TRANSLATION_RATE = 2;
    private double translationRate;
    public static final double DEFAULT_ROTATION_RATE = 0.3;
    private double rotationRate;
    public static final double DEFAULT_SCALING_RATE = 1.08;
    private double scalingRate;

    public ControlPanel(MainScene mainScene, double translationRate, double rotationRate, double scalingRate, MainFrame mainFrame) {
        this.mainScene = mainScene;
        this.translationRate = translationRate;
        this.rotationRate = rotationRate;
        this.scalingRate = scalingRate;
        this.mainFrame = mainFrame;
        this.setBackground(new Color(224, 224, 224));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        setPanels();
        setButtons();
        setCheckBoxes();
        setActionListeners();
        this.setVisible(true);
    }

    public void setControllableObjects(Cube cube, Pyramid pyramid, Sphere sphere) {
        this.cube = cube;
        this.pyramid = pyramid;
        this.sphere = sphere;
    }

    private void setPanels() {
        pnlLeft.setLayout(new GridLayout(2, 3, 5, 5));
        this.add(pnlLeft);
        pnlLeftCenter.setLayout(new GridLayout(1, 1, 5, 5));
        this.add(pnlLeftCenter);
        pnlCenter.setLayout(new GridLayout(3, 1, 5, 5));
        this.add(pnlCenter);
        pnlRightCenter.setLayout(new GridLayout(1, 1, 5, 5));
        this.add(pnlRightCenter);
        pnlRight.setLayout(new GridLayout(2, 3, 5, 5));
        this.add(pnlRight);
    }

    private void setButtons() {
        JButton[] translateButtons = {btnTranslateXPlus, btnTranslateYPlus, btnTranslateZPlus,
                                      btnTranslateXMinus, btnTranslateYMinus, btnTranslateZMinus};
        for(JButton btn : translateButtons) {
            btn.setFocusable(false);
            btn.setPreferredSize(new Dimension(130,30));
            pnlLeft.add(btn);
        }
        JButton[] rotateButtons = {btnRotateXPlus, btnRotateYPlus, btnRotateZPlus,
                                   btnRotateXMinus, btnRotateYMinus, btnRotateZMinus};
        for(JButton btn : rotateButtons) {
            btn.setFocusable(false);
            btn.setPreferredSize(new Dimension(130,30));
            pnlRight.add(btn);
        }
        btnScalePlus.setPreferredSize(new Dimension(45,45));
        btnScalePlus.setFocusable(false);
        pnlLeftCenter.add(btnScalePlus);
        btnScaleMinus.setPreferredSize(new Dimension(45,45));
        btnScaleMinus.setFocusable(false);
        pnlRightCenter.add(btnScaleMinus);
    }

    private void setCheckBoxes() {
        checkBoxCube.setFocusable(false);
        checkBoxPyramid.setFocusable(false);
        checkBoxSphere.setFocusable(false);
        checkBoxCube.setHorizontalTextPosition(SwingConstants.LEFT);
        checkBoxPyramid.setHorizontalTextPosition(SwingConstants.LEFT);
        checkBoxSphere.setHorizontalTextPosition(SwingConstants.LEFT);
        pnlCenter.add(checkBoxCube);
        pnlCenter.add(checkBoxPyramid);
        pnlCenter.add(checkBoxSphere);
    }

    private void setActionListeners() {
        btnTranslateXPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4Transl(translationRate, 0, 0)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4Transl(translationRate, 0, 0)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4Transl(translationRate, 0, 0)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnTranslateXMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4Transl(-translationRate, 0, 0)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4Transl(-translationRate, 0, 0)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4Transl(-translationRate, 0, 0)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnTranslateYPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4Transl(0, translationRate, 0)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4Transl(0, translationRate, 0)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4Transl(0, translationRate, 0)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnTranslateYMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4Transl(0, -translationRate, 0)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4Transl(0, -translationRate, 0)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4Transl(0, -translationRate, 0)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnTranslateZPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4Transl(0, 0, translationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4Transl(0, 0, translationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4Transl(0, 0, translationRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnTranslateZMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4Transl(0, 0, -translationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4Transl(0, 0, -translationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4Transl(0, 0, -translationRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnRotateXPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4RotX(rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4RotX(rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4RotX(rotationRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnRotateXMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4RotX(-rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4RotX(-rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4RotX(-rotationRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnRotateYPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4RotY(rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4RotY(rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4RotY(rotationRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnRotateYMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4RotY(-rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4RotY(-rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4RotY(-rotationRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnRotateZPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4RotZ(rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4RotZ(rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4RotZ(rotationRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnRotateZMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4RotZ(-rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4RotZ(-rotationRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4RotZ(-rotationRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnScalePlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4Scale(scalingRate, scalingRate, scalingRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4Scale(scalingRate, scalingRate, scalingRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4Scale(scalingRate, scalingRate, scalingRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
        btnScaleMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxCube.isSelected()) {
                    cube.setTransformMatrix(cube.getTransformMatrix().mul(new Mat4Scale(1 / scalingRate, 1 / scalingRate, 1 / scalingRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxPyramid.isSelected()) {
                    pyramid.setTransformMatrix(pyramid.getTransformMatrix().mul(new Mat4Scale(1 / scalingRate, 1 / scalingRate, 1 / scalingRate)));
                    mainScene.getScene().redraw();
                }
                if(checkBoxSphere.isSelected()) {
                    sphere.setTransformMatrix(sphere.getTransformMatrix().mul(new Mat4Scale(1 / scalingRate, 1 / scalingRate, 1 / scalingRate)));
                    mainScene.getScene().redraw();
                }
            }
        });
    }

    public double getTranslationRate() {
        return translationRate;
    }

    public void setTranslationRate(double translationRate) {
        this.translationRate = translationRate;
    }

    public double getRotationRate() {
        return rotationRate;
    }

    public void setRotationRate(double rotationRate) {
        this.rotationRate = rotationRate;
    }

    public double getScalingRate() {
        return scalingRate;
    }

    public void setScalingRate(double scalingRate) {
        this.scalingRate = scalingRate;
    }
}
