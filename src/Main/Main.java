package Main;

import FractalPattern.FractalPattern;
import FractalPattern.NewtonRaphsonMethod;
import FractalPattern.FractalImage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        createImage();
        displayFractal();
    }

    private static void displayFractal() {
        setupDisplay(new NewtonRaphsonMethod(0.005));
    }

    private static void createImage() {
        FractalPattern fractalPattern = new NewtonRaphsonMethod(0.0001);
        fractalPattern.setParameters(16000, 9000);
        FractalImage fractalImage = new FractalImage(16000, 9000, fractalPattern);
        fractalImage.createImage();
    }

    private static void setupDisplay(FractalPattern fractalPattern) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("FractalPattern");

        Display display = new Display(fractalPattern);
        window.add(display);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
    }
}