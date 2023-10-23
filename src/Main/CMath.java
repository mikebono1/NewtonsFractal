package Main;

public class CMath {

    // ADDITION ----------------------------------------------------------
    public static double[] add(double r1, double c1, double r2, double c2) {
        return new double[]{r1 + r2, c1 + c2};
    }

    public static double[] add(double[] n1, double r, double c) {
        return add(n1[0], n1[1], r, c);
    }

    public static double[] add(double[] n1, double[] n2) {
        return add(n1[0], n1[1], n2[0], n2[1]);
    }

    // MULTIPLICATION ----------------------------------------------------
    public static double[] multiply(double r1, double c1, double r2, double c2) {
        return new double[]{(r1 * r2) - (c1 * c2), (r1 * c2) + (r2 * c1)};
    }

    public static double[] multiply(double[] n1, double r, double c) {
        return multiply(n1[0], n1[1], r, c);
    }

    public static double[] multiply(double[] n1, double[] n2) {
        return multiply(n1[0], n1[1], n2[0], n2[1]);
    }

    // DIVISION ----------------------------------------------------------
    public static double[] divide(double r1, double c1, double r2, double c2) {
        double d = (Math.pow(r2, 2) + Math.pow(c2, 2));
        double r = ((r1 * r2) + (c1 * c2)) / d;
        double c = ((c1 * r2) - (r1 * c2)) / d;
        return new double[]{r, c};
    }

    public static double[] divide(double[] n, double r, double c) {
        return divide(n[0], n[1], r, c);
    }

    public static double[] divide(double r, double c, double[] n) {
        return divide(r, c, n[0], n[1]);
    }

    public static double[] divide(double[] n1, double[] n2) {
        return divide(n1[0], n1[1], n2[0], n2[1]);
    }

    // MISC OPERATIONS ---------------------------------------------------
    public static double[] pow(double r, double c, int p) {
        boolean flip = p < 0;
        if(p == 0) {
            return new double[]{1, 0};
        }
        if(p == 1) {
            return new double[]{r, c};
        }

        double[] num = new double[]{r, c};
        for(int i = 0; i < p - 1; i++) {
            num = multiply(num, r, c);
        }
        return flip ? divide(1, 0, num) : num;
    }

    public static double[] pow(double[] n, int p) {
        return pow(n[0], n[1], p);
    }
}
