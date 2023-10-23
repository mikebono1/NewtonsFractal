package FractalPattern;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FractalImage {
    private final int height;
    private final int width;
    FractalPattern fractalPattern;
    private final double scaleFactor;
    Color[] colors;

    public FractalImage(int width, int height, FractalPattern fractalPattern) {
        this.fractalPattern = fractalPattern;
        this.height = height;
        this.width = width;
        this.scaleFactor = 0.125;
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

    public void createImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                g2.setColor(fractalPattern.getColor(i * scaleFactor, j * scaleFactor));
                g2.fillRect(i, j, 1, 1);
            }
        }
        g2.dispose();
        File file = new File(getFilename() + ".png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilename() {
        File file = new File("res/img");
        String[] fileNames = file.list();
        int count = 0;
        String[] temp1, temp2;
        assert fileNames != null;
        for(String x: fileNames) {
            temp1 = x.split("\\.");
            temp2 = temp1[0].split("_");
            count = Integer.parseInt(temp2[2]);
        }
        return String.format("res/img/%s_Fractal_%03d", fractalPattern.filename, count + 1);
    }
}
