package cz.uhk.fim.kikm.pgrf2.swrenderer.core.main;

import cz.uhk.fim.kikm.pgrf2.swrenderer.ui.frame.MainFrame;

import javax.swing.*;

/**
 * Main class.
 * Initializes the application.
 * Created by Jakub Kol on 2/18/16.
 */
public final class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mf = new MainFrame(MainFrame.DEFAULT_CANVAS_BGCOLOR);
                mf.init();
            }
        });
    }

}


