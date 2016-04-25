package cz.uhk.fim.kikm.pgrf2.swrenderer.ui.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * UI section: Help - main container.
 * Created by Jakub Kol on 3/3/16.
 */
public final class DialogHelp extends JDialog {
    private static final String DIALOG_TITLE = "Help - Essential key bindings";
    private final int width;
    private final int height;
    private final JPanel pnlCenter = new JPanel();
    private final JLabel lblMovementW = new JLabel("<html> W - move camera forward </html>");
    private final JLabel lblMovementS = new JLabel("<html> S - move camera backward </html>");
    private final JLabel lblMovementA = new JLabel("<html> A - move camera left </html>");
    private final JLabel lblMovementD = new JLabel("<html> D - move camera right </html>");
    private final JLabel lblCamRotMouse = new JLabel("<html> Mouse + Left / Right button pressed - camera rotation </html>");
    private final JLabel lblCamZoomMouse = new JLabel("<html> Mousewheel - zoom </html>");
    private final JLabel lblProjectionP = new JLabel("<html> P - switch between perspective / orthogonal projection </html>");
    private final JLabel lblCoordAxesX = new JLabel("<html> X - turn coordinate axes ON / OFF </html>");
    private final JLabel lblModelSPACE = new JLabel("<html> SPACE - switch between wireframe / solid model </html>");
    private final JLabel lblRebuildR = new JLabel("<html> R - reset the scene </html>");
    private final JLabel lblHidePanelH = new JLabel("<html> H - hide the control panel </html>");
    private final JLabel[] labels = {lblMovementW, lblMovementS, lblMovementA, lblMovementD, lblCamRotMouse, lblCamZoomMouse, lblProjectionP,
                                     lblCoordAxesX, lblModelSPACE, lblRebuildR, lblHidePanelH};

    public DialogHelp(JFrame owner, boolean isModal, int width, int height) {
        super(owner, isModal);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(DIALOG_TITLE);
        this.setResizable(false);
        this.width = width;
        this.height = height;
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(owner);
        this.setLayout(new BorderLayout());
        setLabels();
        setPanels();
        this.setVisible(true);
    }

    private void setPanels() {
        pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
        pnlCenter.add(Box.createVerticalStrut(15));
        for(JComponent label : labels) {
            pnlCenter.add(label);
        }
        pnlCenter.add(Box.createHorizontalStrut(10));
        this.add(pnlCenter, BorderLayout.CENTER);
    }

    private void setLabels() {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        for(JLabel label : labels) {
            label.setFont(font);
        }
    }

}
