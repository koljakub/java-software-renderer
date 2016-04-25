package cz.uhk.fim.kikm.pgrf2.swrenderer.ui.frame;

import cz.uhk.fim.kikm.pgrf2.swrenderer.core.geom.geom3d.*;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.canvas.Canvas;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.controllers.GenericKeyboardController;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.controllers.CamMouseController;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.controllers.ControlPanel;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.dialogs.DialogAbout;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.dialogs.DialogHelp;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.dialogs.settings.solids.DialogSettingsSolids;
import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.dialogs.settings.transformations.DialogSettingsTransforms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * UI section: Frame.
 * Created by Jakub Kol on 2/18/16.
 */
public final class MainFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 1024;
    public static final int DEFAULT_HEIGHT = 768;
    public static final Color DEFAULT_CANVAS_BGCOLOR = Color.WHITE;
    private static final String FRAME_TITLE = "Java Software Renderer";
    private static final String MAIN_MENU_TITLE = "Main menu";
    private final Canvas canvas;
    private final MainScene mainScene;
    private final Dimension screenSize;
    private ControlPanel controlPanel;
    private boolean isControlPanelHidden = false;

    public MainFrame(Color color) {
        screenSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.canvas = new Canvas(screenSize.width, screenSize.height, color);
        this.mainScene = new MainScene(this, canvas);
        setFrameProperties();
        setMainMenu();
        setControlPanel();
        setControllers();
    }

    private void setFrameProperties() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(FRAME_TITLE);
        setResizable(false);
        setSize(screenSize);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        setVisible(true);
        setFocusable(true);
    }

    private void setControlPanel() {
        canvas.setLayout(new BorderLayout());
        controlPanel = new ControlPanel(mainScene, ControlPanel.DEFAULT_TRANSLATION_RATE, ControlPanel.DEFAULT_ROTATION_RATE,
                ControlPanel.DEFAULT_SCALING_RATE, this);
        controlPanel.setFocusable(true);
        canvas.add(controlPanel, BorderLayout.SOUTH);
    }

    private void setControllers() {
        CamMouseController cmc = new CamMouseController(mainScene.getScene(), CamMouseController.DEFAULT_SCR_SPEED, canvas.getWidth(), canvas.getHeight());
        this.addMouseWheelListener(cmc);
        this.addMouseMotionListener(cmc);
        this.addMouseListener(cmc);
        GenericKeyboardController gkc = new GenericKeyboardController(mainScene, GenericKeyboardController.DEFAULT_CAM_SPEED, this);
        gkc.init();
    }

    private void setMainMenu() {
        //Main menu
        JMenuBar mainMenuBar = new JMenuBar();
        JMenu mainMenu = new JMenu(MAIN_MENU_TITLE);
        ImageIcon infoIcon = new ImageIcon(MainFrame.class.getResource("/resources/icons/info.png"));
        ImageIcon exitIconMenu = new ImageIcon(MainFrame.class.getResource("/resources/icons/exit_small.png"));
        ImageIcon exitIconDialog = new ImageIcon(MainFrame.class.getResource("/resources/icons/exit_medium.png"));
        ImageIcon helpIcon = new ImageIcon(MainFrame.class.getResource("/resources/icons/help.png"));
        // Submenu ----------------------------------------------------
        JMenu settingsMenu = new JMenu("Settings");
        ImageIcon solidsIcon = new ImageIcon(MainFrame.class.getResource("/resources/icons/cube.png"));
        ImageIcon transformsIcon = new ImageIcon(MainFrame.class.getResource("/resources/icons/transforms.png"));
        JMenuItem subItemSolids = new JMenuItem("Solids", solidsIcon);
        JMenuItem subItemTransforms = new JMenuItem("Transformations", transformsIcon);
        settingsMenu.add(subItemSolids);
        settingsMenu.add(subItemTransforms);
        mainMenu.add(settingsMenu);
        //-------------------------------------------------------------
        JMenuItem itemHelp = new JMenuItem("Help", helpIcon);
        mainMenu.add(itemHelp);
        JMenuItem itemAbout = new JMenuItem("About", infoIcon);
        mainMenu.add(itemAbout);
        JMenuItem itemExit = new JMenuItem("Exit", exitIconMenu);
        mainMenu.add(itemExit);
        mainMenuBar.add(mainMenu);
        this.setJMenuBar(mainMenuBar);
        // Listeners
        subItemSolids.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogSettingsSolids(mainScene, false, MainFrame.this.getWidth() / 2 + 150, MainFrame.this.getHeight() / 2 + 150, MainFrame.this);
                mainScene.getScene().redraw();
            }
        });

        subItemTransforms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogSettingsTransforms(MainFrame.this, false, MainFrame.this.getWidth() / 2 + 100, MainFrame.this.getHeight() / 2, controlPanel);
            }
        });

        itemHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogHelp(MainFrame.this, false, MainFrame.this.getWidth() / 2, MainFrame.this.getHeight() / 2 );
            }
        });

        itemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogAbout(MainFrame.this, true, MainFrame.this.getWidth() / 2, MainFrame.this.getHeight() / 2);
            }
        });

        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int decision = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION, 0, exitIconDialog);
                if(decision == 0) {
                    MainFrame.this.dispose();
                }
            }
        });
    }

    public void hideControlPanel() {
        if(isControlPanelHidden) {
            controlPanel.setVisible(true);
            isControlPanelHidden = false;
        } else {
            controlPanel.setVisible(false);
            isControlPanelHidden = true;
        }
    }

    public void setControllableSolids(Cube cube, Pyramid pyramid, Sphere sphere) {
        controlPanel.setControllableObjects(cube, pyramid, sphere);
    }

    public void init() {
        mainScene.buildScene();
    }
}
