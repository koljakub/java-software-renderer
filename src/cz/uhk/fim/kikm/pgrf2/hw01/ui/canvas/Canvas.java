package cz.uhk.fim.kikm.pgrf2.hw01.ui.canvas;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Wrapper class for BufferedImage class.
 * Created by Jakub Kol on 2/20/16.
 */
public final class Canvas extends JPanel {
    private final BufferedImage out;
    private final int width;
    private final int height;
    private final Graphics2D g2d;
    private Color bgColor;

    public Canvas(int width, int height, Color bgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.setSize(width, height);
        out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = out.createGraphics();
        setBgColor(bgColor);
    }

    public void setBgColor(Color color) {
        g2d.setPaint(bgColor);
        g2d.fillRect(0, 0, width, height);
    }

    public Color getBgColor() {
        return bgColor;
    }

    public int getRGBAt(int x, int y) {
        return out.getRGB(x, y);
    }

    public void drawPixelAt(int x, int y, int color) {
        out.setRGB(x, y, color);
    }

    public void drawStringAt(String str, int x, int y, Color color) {
        g2d.setFont(new Font("Serif", Font.PLAIN, 15));
        g2d.setPaint(color);
        g2d.drawString(str, x, y);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(2)); // Line thickness
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void drawOval(int x, int y, int width, int height, Color color) {
        g2d.setPaint(color);
        g2d.drawOval(x, y, width, height);
    }

    public void drawRectangle(int x, int y, int width, int height, @NotNull Color color) {
        g2d.setPaint(color);
        g2d.drawRect(x, y, width, height);
    }

    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints, Color color) {
        g2d.setPaint(color);
        g2d.fillPolygon(xPoints, yPoints,nPoints);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(out, 0, 0, null);
    }
}
