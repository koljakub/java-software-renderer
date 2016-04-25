package cz.uhk.fim.kikm.pgrf2.swrenderer.ui.dialogs.settings.solids;

import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.frame.MainScene;

import javax.swing.*;
import java.awt.*;

/**
 * UI section: Settings (Solids) - main container.
 * Created by Jakub Kol on 3/3/16.
 */
public final class DialogSettingsSolids extends JDialog {
    private static final String DEFAULT_TITLE = "Settings - Solids";
    private final MainScene mainScene;
    private final int width;
    private final int height;
    private final JTabbedPane mainPane = new JTabbedPane();
    private PanelSolid pnlCube;
    private PanelSolid pnlPyramid;
    private PanelSolid pnlSphere;

    public DialogSettingsSolids(MainScene mainScene, boolean isModal, int width, int height,  JFrame owner) {
        super(owner, isModal);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(DEFAULT_TITLE);
        this.setResizable(false);
        this.width = width;
        this.height = height;
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(owner);
        this.mainScene = mainScene;
        setMainPane();
        this.setVisible(true);
    }

    private void setMainPane() {
        ImageIcon cubeIcon = new ImageIcon(DialogSettingsSolids.class.getResource("/resources/icons/cube_medium.png"));
        ImageIcon pyramidIcon = new ImageIcon(DialogSettingsSolids.class.getResource("/resources/icons/pyramid_medium.png"));
        ImageIcon sphereIcon = new ImageIcon(DialogSettingsSolids.class.getResource("/resources/icons/sphere_medium.png"));
        this.add(mainPane);
        pnlCube = new PanelSolid(mainScene, mainScene.getCube(), width, height, this);
        mainPane.addTab("Cube", cubeIcon, pnlCube);
        pnlPyramid = new PanelSolid(mainScene, mainScene.getPyramid(), width, height, this);
        mainPane.addTab("Pyramid", pyramidIcon, pnlPyramid);
        pnlSphere = new PanelSolid(mainScene, mainScene.getSphere(), width, height, this);
        mainPane.addTab("Sphere", sphereIcon, pnlSphere);
    }
}
