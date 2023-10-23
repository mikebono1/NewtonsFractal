package Main;

import java.awt.*;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorSet {
    private final int numColors;
    private final Color initialColor;
    private final int degPerStep;

    private int baseColorValue;
    private int maxColorValue;
    private int initialAngle;
    private int incPerDeg;

    public ColorSet(int numColors, Color initialColor) {
        this.numColors = numColors;
        this.initialColor = initialColor;
        degPerStep = 180 / this.numColors;
        setParameters();
    }

    // Pulls starting point data from initial color given
    private void setParameters() {
        int[] rgb = new int[]{initialColor.getRed(), initialColor.getGreen(), initialColor.getBlue()};
        int[] sortedRGB = Arrays.copyOf(rgb, 3);
        Arrays.sort(sortedRGB);
        maxColorValue = 255;
        baseColorValue = sortedRGB[0];
        incPerDeg = (int)Math.round((maxColorValue - baseColorValue) / 60.0);
        initialAngle = getInitialAngle(rgb, sortedRGB);
    }

    private int getInitialAngle(int[] rgb, int[] sortedRGB) {
        int middleValue = sortedRGB[1];
        int middleColor = -1;
        for(int i = 0; i < rgb.length; i++) {
            if(rgb[i] == middleValue) {
                middleColor = i;
            }
        }
        try {
            // Middle color is red
            if(middleColor == 0) {
                return ((middleValue - baseColorValue) / incPerDeg) + 60;
            }
            // Middle color is green
            if(middleColor == 1) {
                return ((middleValue - baseColorValue) / incPerDeg);
            }
            // Middle color is blue
            if(middleColor == 2) {
                return ((middleValue - baseColorValue) / incPerDeg) + 120;
            }
            throw new UnexpectedException("Cannot find middle color value, I think its " + middleColor);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Color getColorFromWheel(int deg) {
        // 0 deg = red, rotates around from there
        while(deg > 359) {
            deg -= 360;
        }
        // Increase G
        if(deg >= 0 && deg < 60) {
            return new Color(maxColorValue, baseColorValue + (incPerDeg * deg), baseColorValue);
        }
        // Decrease R
        if(deg >= 60 && deg < 120) {
            deg -= 60;
            return new Color(maxColorValue - (incPerDeg * deg), maxColorValue, baseColorValue);
        }
        // Increase B
        if(deg >= 120 && deg < 180) {
            deg -= 120;
            return new Color(baseColorValue, maxColorValue, baseColorValue + (incPerDeg * deg));
        }
        // Decrease G
        if(deg >= 180 && deg < 240) {
            deg -= 180;
            return new Color(baseColorValue, maxColorValue - (incPerDeg * deg), maxColorValue);
        }
        // Increase R
        if(deg >= 240 && deg < 300) {
            deg -= 240;
            return new Color(baseColorValue + (incPerDeg * deg), baseColorValue, maxColorValue);
        }
        // Decrease B
        if(deg >= 300) {
            deg -= 300;
            return new Color(maxColorValue, baseColorValue, maxColorValue - (incPerDeg * deg));
        }
        return null;
    }

    // Generates analogous color palette
    public ArrayList<Color> generateAnalogousColors() {
        ArrayList<Color> colors = new ArrayList<>();
        for(int i = 0; i < numColors; i++) {
            colors.add(getColorFromWheel(initialAngle + (i * degPerStep)));
        }
        colors.add(Color.BLACK);
        return colors;
    }

    // TODO: implement greyscale color method
    public ArrayList<Color> generateGreyscaleColors() {
        return null;
    }
}
