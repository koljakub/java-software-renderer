package cz.uhk.fim.kikm.pgrf2.swrenderer.ui.dialogs.settings.solids;

import cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d.*;
import cz.uhk.fim.kikm.pgrf2.swrenderer.core.scene.Scene;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.canvas.Canvas;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.frame.MainScene;
import transforms.Mat4Scale;
import transforms.Mat4Transl;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * UI section: Settings (Solids) - subpanel.
 * Created by Jakub Kol on 3/3/16.
 */
public final class PanelSolid extends JPanel {
    private final DialogSettingsSolids owner;
    private Canvas canvas;
    private Scene scene;
    private final Renderable solid;
    private Renderable solidPreview;
    private int sphereSmoothness;
    private List<Color> selectedColor;
    private final JPanel pnlBottom = new JPanel();
    private final JPanel pnlSettings = new JPanel();
    private final MainScene mainScene;
    private final JLabel lblModelType = new JLabel("Preview model type: ");
    private final JLabel lblColor = new JLabel("Color of the solid: ");
    private final JLabel lblSmoothness = new JLabel("Smoothness: ");
    private final JButton btnModelWire = new JButton("Wireframe");
    private final JButton btnModelSolid = new JButton("Solid");
    private final JButton btnSolidColor = new JButton("Select a color");
    private final JButton btnRandomColor = new JButton("Random colors");
    private final JButton btnApplyChanges = new JButton("Apply");
    private final JButton btnClose= new JButton("Cancel");
    private final String[] cmbBoxVals = {"0  ", "1", "2", "3", "4", "5"};
    private final JComboBox cmbBoxSmoothness = new JComboBox<String>(cmbBoxVals);

    public PanelSolid(MainScene mainScene, Renderable solid, int width, int height, DialogSettingsSolids owner) {
        this.mainScene = mainScene;
        this.solid = solid;
        this.setSize(new Dimension(width, height));
        this.owner = owner;
        if(solid instanceof Cube) {
            solidPreview = new Cube(GeomConstants.CUBE_DEFAULT_EDGE_LEN, solid.getColorList());
        }
        else if(solid instanceof Pyramid) {
            solidPreview = new Pyramid(GeomConstants.PYRAMID_DEFAULT_HEIGHT, GeomConstants.PYRAMID_DEFAULT_BASE_EDGE_LEN, solid.getColorList());
        }
        else if(solid instanceof Sphere){
            int smoothness = ((Sphere)solid).getSmoothness();
            sphereSmoothness = smoothness;
            solidPreview = new Sphere(solid.getColorList(), sphereSmoothness);
            if(sphereSmoothness > 0) {
                solidPreview.setTransformMatrix(new Mat4Scale(12, 12, 12).mul(new Mat4Transl(4, 0, 0)));
            } else if(sphereSmoothness == 0) {
                solidPreview.setTransformMatrix(new Mat4Scale(7,7,7).mul(new Mat4Transl(4, 0, 0)));
            }
        }
        this.setLayout(new BorderLayout());
        setLabels();
        setButtons();
        setPanels();
        setScene();
        setActionListeners();
        this.setVisible(true);
    }

    private void setPanels() {
        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.add(btnApplyChanges);
        pnlBottom.add(btnClose);
        pnlBottom.add(Box.createVerticalStrut(100));
        this.add(pnlBottom, BorderLayout.SOUTH);
        pnlSettings.setLayout(new BoxLayout(pnlSettings, BoxLayout.Y_AXIS));
        pnlSettings.add(Box.createVerticalStrut(50));
        pnlSettings.add(lblModelType);
        pnlSettings.add(Box.createVerticalStrut(10));
        pnlSettings.add(btnModelWire);
        pnlSettings.add(Box.createVerticalStrut(10));
        pnlSettings.add(btnModelSolid);
        pnlSettings.add(Box.createVerticalStrut(25));
        pnlSettings.add(lblColor);
        pnlSettings.add(Box.createVerticalStrut(10));
        pnlSettings.add(btnSolidColor);
        pnlSettings.add(Box.createVerticalStrut(10));
        pnlSettings.add(btnRandomColor);
        pnlSettings.add(Box.createVerticalStrut(25));
        if(solidPreview instanceof Sphere) {
            pnlSettings.add(lblSmoothness);
            pnlSettings.add(Box.createVerticalStrut(10));
            JPanel pnlCmbBox = new JPanel(new FlowLayout());
            pnlCmbBox.add(cmbBoxSmoothness);
            cmbBoxSmoothness.setSelectedIndex(sphereSmoothness);
            pnlSettings.add(pnlCmbBox);
        }
        pnlSettings.add(Box.createHorizontalStrut(220));
        pnlSettings.setVisible(true);
        this.add(pnlSettings, BorderLayout.WEST);
    }

    private void setScene() {
        this.add(pnlSettings, BorderLayout.WEST);
        canvas = new Canvas(this.getWidth() / 2 + 80, this.getHeight() - 200, pnlSettings.getBackground());
        this.add(canvas, BorderLayout.CENTER);
        scene = new Scene(canvas);
        scene.setupCamera(new Vec3D(-8, -8, -5), Scene.CAM_AZIMUTH, Scene.CAM_ZENITH, Scene.CAM_RADIUS, false);
        scene.addObject(solidPreview);
        scene.redraw();
    }

    private void setLabels() {
        lblModelType.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        lblColor.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
    }

    private void setButtons() {
        btnModelWire.setMaximumSize(new Dimension(140, 40));
        btnModelSolid.setMaximumSize(new Dimension(140, 40));
        btnModelSolid.setEnabled(false);
        btnSolidColor.setMaximumSize(new Dimension(140, 40));
        btnRandomColor.setMaximumSize(new Dimension(140, 40));
        btnApplyChanges.setPreferredSize(new Dimension(100, 25));
        btnClose.setPreferredSize(new Dimension(100, 25));
    }


    private void setActionListeners() {
        btnModelWire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnModelWire.setEnabled(false);
                btnModelSolid.setEnabled(true);
                if(solidPreview instanceof Sphere) {
                    centerPreviewSphere(((Sphere) solidPreview).getSmoothness());
                }
                scene.switchRenderingMode();
            }
        });

        btnModelSolid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnModelWire.setEnabled(true);
                btnModelSolid.setEnabled(false);
                if(solidPreview instanceof Sphere) {
                    centerPreviewSphere(((Sphere) solidPreview).getSmoothness());
                }
                scene.switchRenderingMode();
            }
        });

        btnSolidColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(PanelSolid.this, "Please select a color", null);
                if(color != null) {
                    List<Color> colorList = new ArrayList<Color>();
                    for (int i = 0; i < solidPreview.getIndexBufferSize(TopologyType.TRIANGLES); i++) {
                        colorList.add(color);
                    }
                    selectedColor = colorList;
                    solidPreview.setColorList(colorList);
                    scene.redraw();
                }
            }
        });

        btnRandomColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Color> colorList = new ArrayList<Color>();
                for(int i = 0; i < solidPreview.getIndexBufferSize(TopologyType.TRIANGLES); i ++) {
                    colorList.add(generateRandomColor(Color.WHITE));
                }
                selectedColor = colorList;
                solidPreview.setColorList(colorList);
                scene.redraw();
            }
        });

        btnApplyChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(solidPreview instanceof Cube) {
                    mainScene.setCube((Cube)solidPreview);
                }
                else if(solidPreview instanceof Pyramid) {
                    mainScene.setPyramid((Pyramid)solidPreview);
                } else {
                    mainScene.setSphere((Sphere)solidPreview);
                }
                owner.dispose();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.dispose();
            }
        });

        cmbBoxSmoothness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int smoothness = cb.getSelectedIndex();
                Sphere spherePreview = new Sphere(smoothness);
                List<Color> colorList = new ArrayList<Color>();
                if(solidPreview.getColor(0) == solidPreview.getColor(1)) {
                    for (int i = 0; i < spherePreview.getIndexBufferSize(TopologyType.TRIANGLES); i++) {
                        colorList.add(solidPreview.getColor(0));
                    }
                } else {
                    for(int i = 0; i < spherePreview.getIndexBufferSize(TopologyType.TRIANGLES); i ++) {
                        colorList.add(generateRandomColor(Color.WHITE));
                    }
                }
                spherePreview.setColorList(colorList);
                scene.removeObject(solidPreview);
                solidPreview = spherePreview;
                centerPreviewSphere(spherePreview.getSmoothness());
                scene.addObject(solidPreview);
                scene.redraw();
            }
        });

    }

    private void centerPreviewSphere(int sphereSmoothness) {
        if(sphereSmoothness == 0) {
            solidPreview.setTransformMatrix(new Mat4Scale(7, 7, 7).mul(new Mat4Transl(6, 0, 0)));
        } else {
            solidPreview.setTransformMatrix(new Mat4Scale(12, 12, 12).mul(new Mat4Transl(6, 0, 0)));
        }
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
