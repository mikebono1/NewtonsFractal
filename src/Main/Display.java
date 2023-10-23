package Main;

import FractalPattern.FractalPattern;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    private final int screenWidth;
    private final int screenHeight;
    private double zoomFactor;
    private final int pixelSize;
    private final FractalPattern fractalPattern;
    Color[] colors;

    public Display(FractalPattern fractalPattern) {
        zoomFactor = 1;
        pixelSize = 1;
        screenWidth = 1500;
        screenHeight = 750;

        this.fractalPattern = fractalPattern;
        this.fractalPattern.setParameters(screenWidth, screenHeight);

        this.setPreferredSize(new Dimension(screenWidth * pixelSize, screenHeight * pixelSize));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        KeyHandler keyHandler = new KeyHandler(this);
        this.addKeyListener(keyHandler);
        setColors();
    }

    private void setColors() {
        colors = new Color[9];
        colors[0] = new Color(90, 210, 244);
        colors[1] = new Color(98, 190, 193);
        colors[2] = new Color(100, 100, 129);
        colors[3] = new Color(99, 89, 92);
        colors[4] = new Color(71, 137, 120);
        colors[5] = new Color(180, 180, 110);
        colors[6] = new Color(153, 93, 129);
        colors[7] = new Color(235, 130, 120);
        colors[8] = Color.BLACK;
    }

    public void incrementZoom() {
        zoomFactor *= 0.5;
        update();
    }

    public void decrementZoom() {
        zoomFactor /= 0.5;
        update();
    }

    public void update() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        for(int i = 0; i < screenWidth; i++) {
            for(int j = 0; j < screenHeight; j++) {
                g2.setColor(fractalPattern.getColor(i * zoomFactor, j * zoomFactor));
                g2.fillRect(i * pixelSize, j * pixelSize, pixelSize, pixelSize);
            }
        }
        g2.dispose();
        System.out.println("DONE UPDATING DISPLAY");
    }
}
