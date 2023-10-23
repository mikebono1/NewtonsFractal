package FractalPattern;

import java.awt.*;
import java.util.ArrayList;

public abstract class FractalPattern {
    public final String filename;
    ArrayList<Color> colors;

    public FractalPattern(String filename) {
        this.filename = filename;
    }

    public abstract Color getColor(double x, double y);

    public abstract void setParameters(int screenWidth, int screenHeight);
}
