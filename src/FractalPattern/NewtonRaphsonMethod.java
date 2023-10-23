package FractalPattern;

import Main.CMath;
import Main.ColorSet;

import java.awt.*;
import java.util.ArrayList;

public class NewtonRaphsonMethod extends FractalPattern {
    private int screenHeight;
    private int screenWidth;
    private final double scaleFactor;
    private final double tol;
    private ColorSet colorSet;

    private ArrayList<double[]> roots = new ArrayList<>();

    public NewtonRaphsonMethod(double scaleFactor) {
        super("NewtonRaphson");
        this.scaleFactor = scaleFactor;
        tol = 0.01;
        getRoots();
        colorSet = new ColorSet(roots.size(), new Color(247, 131, 185));
        colors = colorSet.generateAnalogousColors();
    }

    @Override
    public void setParameters(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    // TODO: put all roots of function into roots arraylist
    private void getRoots() {
        roots.add(new double[]{1.0, 0.0});
        roots.add(new double[]{-1.0, 0.0});
        roots.add(new double[]{0, 1.0});
        roots.add(new double[]{0, -1.0});
        roots.add(new double[]{Math.pow(2, 0.5), Math.pow(2, 0.5)});
        roots.add(new double[]{Math.pow(2, 0.5), -Math.pow(2, 0.5)});
        roots.add(new double[]{-Math.pow(2, 0.5), Math.pow(2, 0.5)});
        roots.add(new double[]{-Math.pow(2, 0.5), -Math.pow(2, 0.5)});
    }

    private double[] func(double real, double complex) {
        // y = x^3 - 1
//        return Main.CMath.add(Main.CMath.pow(real, complex, 3), -1, 0);
        // p = z^8 + 15z^4 - 16
        return CMath.add(CMath.add(CMath.pow(real, complex, 8), CMath.multiply(CMath.pow(real, complex, 4), 15, 0)), -16, 0);
    }

    private double[] derv(double real, double complex) {
        // y' = 3x^2
//        return Main.CMath.multiply(Main.CMath.pow(real, complex, 2), 3, 0);
        // p' = 8z^7 + 60z^3
        return CMath.add(CMath.multiply(CMath.pow(real, complex, 7), 8, 0), CMath.multiply(CMath.pow(real, complex, 3), 60, 0));
    }

    private double[] func(double[] n) {
        return func(n[0], n[1]);
    }

    private double[] derv(double[] n) {
        return derv(n[0], n[1]);
    }

    public double[] toComplexPlane(double x, double y) {
        double r = (x - (screenWidth / 2.0)) * scaleFactor;
        double c = ((screenHeight / 2.0) - y) * scaleFactor;
        return new double[]{r, c};
    }

    @Override
    public Color getColor(double x, double y) {
        double[] z = newtonRaphsonMethod(toComplexPlane(x, y));
        for(int i = 0; i < roots.size(); i++) {
            if(withinTolerance(z, roots.get(i))) {
                return colors.get(i);
            }
        }
        // Return black if point does not converge to a root of polynomial
        return Color.BLACK;
    }

    public boolean withinTolerance(double[] p, double[] r) {
        // Guess that within a 1% tolerance of root is acceptable
        if(p[0] == r[0] && p[1] == r[1]) {
            return true;
        }
        if(Math.abs((p[0] - r[0]) / r[0]) < tol && p[1] == r[1]) {
            return true;
        }
        if(p[0] == r[0] && Math.abs((p[1] - r[1]) / r[1]) < tol) {
            return true;
        }
        return Math.abs((p[0] - r[0]) / r[0]) < tol && Math.abs((p[1] - r[1]) / r[1]) < tol;
    }

    public boolean withinTolerance(double[] p) {
        // Guess that within a 1% tolerance of root is acceptable
        for(double[] r: roots) {
            if(p[0] == r[0] && p[1] == r[1]) {
                return true;
            }
            if(Math.abs((p[0] - r[0]) / r[0]) < tol && p[1] == r[1]) {
                return true;
            }
            if(p[0] == r[0] && Math.abs((p[1] - r[1]) / r[1]) < tol) {
                return true;
            }
            if(Math.abs((p[0] - r[0]) / r[0]) < tol && Math.abs((p[1] - r[1]) / r[1]) < tol) {
                return true;
            }
        }
        return false;
    }

    private double[] newtonRaphsonMethod(double[] p) {
        int i = 0;
        while(!withinTolerance(p) && i < 300) {
            p = CMath.add(p, CMath.multiply(CMath.divide(func(p), derv(p)), -1, 0));
            i++;
        }
        return p;
    }
}
