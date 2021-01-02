package Challenges.NBodyChallenge;

public class NBody {
    private double duration;
    private double interval;
    private double[][] planets;
    private double radius;
    private String[] names;

    private final double G = 6.67E-11;

    public NBody(double d, double dt, double[][] p, double ur, String[] s) {
        duration = d;
        interval = dt;
        planets = p.clone();
        radius = ur;
        names = s;
    }
    public double getDuration() {
        return duration;
    }
    public double getInterval() {
        return interval;
    }
    public double getDays(int i) {
        return i / 86400.0;
    }
    public double getUniverseRadius() {
        return radius;
    }
    public void setPlanets(int indexI, int indexJ, double value) {
        planets[indexI][indexJ] = value;
    }
    public double getPlanets(int i, int j) {
        return planets[i][j];
    }
    public double[][] getAllPlanets() {
        return planets;
    }
    //Returns force of p2 on p1; NOT p1 on p2
    public double[] pairForce(int planet1, int planet2) {
        int p1 = planet1;
        int p2 = planet2;
        double dx = planets[p2][0] - planets[p1][0];
        double dy = planets[p2][1] - planets[p1][1];

        double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double f = G * planets[p1][4] * planets[p2][4] / (d * d);

        double[] F = {f * dx / d, f * dy / d};
        return F;
    }
    public double[] netForce(int planet) {
        int p = planet;
        double[] nF = {0, 0};
        for (int i = 0; i < 5; i++) {
            if (p != i) {
                double[] f = this.pairForce(p, i);
                nF[0] += f[0];
                nF[1] += f[1];
            }
        }
        return nF;
    }
    public double[] acceleration(int p) {
        double[] a = {0, 0};
        double[] nF = this.netForce(p);
        a[0] = nF[0] / planets[p][4];
        a[1] = nF[1] / planets[p][4];
        return a;
    }

    public void updateAllPositions() {
        for (int i = 0; i < planets.length; i++) {
            double[] a = acceleration(i);
            planets[i][2] = planets[i][2] + a[0] * interval;
            planets[i][3] = planets[i][3] + a[1] * interval;
        }
        for (int i = 0; i < planets.length; i++) {
            planets[i][0] = planets[i][0] + planets[i][2] * interval;
            planets[i][1] = planets[i][1] + planets[i][3] * interval;
        }
    }
    public void printPlanets() {
        int j = planets.length;
        System.out.println(j);
        System.out.println(radius);

        for (int i = 0; i < j; i++) {
            System.out.print(planets[i][0] + "  ");
            System.out.print(planets[i][1] + "  ");
            System.out.print(planets[i][2] + "  ");
            System.out.print(planets[i][3] + "  ");
            System.out.print(planets[i][4] + "  ");
            System.out.println(names[i] + "  ");
        }
    }
    
}
