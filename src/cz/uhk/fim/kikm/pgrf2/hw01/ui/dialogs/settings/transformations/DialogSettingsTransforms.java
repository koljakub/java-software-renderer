package cz.uhk.fim.kikm.pgrf2.hw01.ui.dialogs.settings.transformations;

import cz.uhk.fim.kikm.pgrf2.hw01.ui.controllers.ControlPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * UI section: Settings (Transformations) - main container.
 * Created by Jakub Kol on 3/3/16.
 */
public final class DialogSettingsTransforms extends JDialog{
    private static final String DEFAULT_TITLE = "Settings - Transformations";
    private final int width;
    private final int height;
    private final ControlPanel controlPanel;
    private final JPanel pnlCenter = new JPanel();
    private final JLabel lblTranslRate = new JLabel("Translation rate");
    private final JLabel lblRotRate = new JLabel("Rotation rate");
    private final JLabel lblScalRate = new JLabel("Scaling rate");
    private final JLabel[] labels = {lblTranslRate, lblRotRate, lblScalRate};
    private final JSlider sliderTransl = new JSlider(0, 200);
    private final JSlider sliderRot = new JSlider(0, 20);
    private final JSlider sliderScale = new JSlider(100, 150);
    private final JButton btnReset = new JButton("Restore defaults");

    public DialogSettingsTransforms(JFrame owner, boolean isModal, int width, int height, ControlPanel controlPanel) {
        super(owner, isModal);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(DEFAULT_TITLE);
        this.setResizable(false);
        this.width = width;
        this.height = height;
        this.controlPanel = controlPanel;
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(owner);
        this.setLayout(new BorderLayout());
        setLabels();
        setSliders();
        setPanels();
        setChangeListeners();
        setActionListeners();
        this.setVisible(true);
    }

    private void setLabels() {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        for (JLabel label : labels) {
            label.setFont(font);
        }
    }

    private void setSliders() {
        Hashtable<Integer, JLabel> lblsSliderTransl = new Hashtable<Integer, JLabel>(2);
        lblsSliderTransl.put(sliderTransl.getMinimum(), new JLabel("Low"));
        lblsSliderTransl.put(sliderTransl.getMaximum(), new JLabel("High"));
        sliderTransl.setMinorTickSpacing(5);
        sliderTransl.setLabelTable(lblsSliderTransl);
        sliderTransl.setPaintLabels(true);
        sliderTransl.setPaintTicks(true);
        sliderTransl.setValue((int)(controlPanel.getTranslationRate() * 10));
        Hashtable<Integer, JLabel> lblsSliderRot = new Hashtable<Integer, JLabel>(2);
        lblsSliderRot.put(sliderRot.getMinimum(), new JLabel("Low"));
        lblsSliderRot.put(sliderRot.getMaximum(), new JLabel("High"));
        sliderRot.setMinorTickSpacing(1);
        sliderRot.setLabelTable(lblsSliderRot);
        sliderRot.setPaintLabels(true);
        sliderRot.setPaintTicks(true);
        sliderRot.setValue((int)(controlPanel.getRotationRate() * 10));
        Hashtable<Integer, JLabel> lblsSliderScale = new Hashtable<Integer, JLabel>(2);
        lblsSliderScale.put(sliderScale.getMinimum(), new JLabel("Low"));
        lblsSliderScale.put(sliderScale.getMaximum(), new JLabel("High"));
        sliderScale.setMinorTickSpacing(1);
        sliderScale.setLabelTable(lblsSliderScale);
        sliderScale.setPaintLabels(true);
        sliderScale.setPaintTicks(true);
        sliderScale.setValue((int)(controlPanel.getScalingRate() * 100));
    }

    private void setPanels() {
        pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
        pnlCenter.add(Box.createVerticalStrut(25));
        pnlCenter.add(lblTranslRate);
        pnlCenter.add(Box.createVerticalStrut(10));
        pnlCenter.add(sliderTransl);
        pnlCenter.add(Box.createVerticalStrut(25));
        pnlCenter.add(lblRotRate);
        pnlCenter.add(Box.createVerticalStrut(10));
        pnlCenter.add(sliderRot);
        pnlCenter.add(Box.createVerticalStrut(25));
        pnlCenter.add(lblScalRate);
        pnlCenter.add(Box.createVerticalStrut(10));
        pnlCenter.add(sliderScale);
        pnlCenter.add(Box.createVerticalStrut(10));
        pnlCenter.add(btnReset);
        lblTranslRate.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRotRate.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblScalRate.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(pnlCenter, BorderLayout.CENTER);
    }

    private void setChangeListeners() {
        sliderTransl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                double value = source.getValue() / 10.0;
                controlPanel.setTranslationRate(value);
            }
        });

        sliderRot.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                double value = source.getValue() / 10.0;
                controlPanel.setRotationRate(value);
            }
        });

        sliderScale.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                double value = source.getValue() / 100.0;
                controlPanel.setScalingRate(value);
            }
        });
    }

    private void setActionListeners() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanel.setTranslationRate(ControlPanel.DEFAULT_TRANSLATION_RATE);
                sliderTransl.setValue((int)(ControlPanel.DEFAULT_TRANSLATION_RATE * 10));
                controlPanel.setRotationRate(ControlPanel.DEFAULT_ROTATION_RATE);
                sliderRot.setValue((int)(ControlPanel.DEFAULT_ROTATION_RATE * 10));
                controlPanel.setScalingRate(ControlPanel.DEFAULT_SCALING_RATE);
                sliderScale.setValue((int)(ControlPanel.DEFAULT_SCALING_RATE * 100));
            }
        });
    }
}
