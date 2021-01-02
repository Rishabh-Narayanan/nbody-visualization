package Challenges.NBodyChallenge;

import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.introcs.StdDraw;
import java.util.Scanner;

public class NBodyTester2 {
    NBody universe;
    public NBodyTester2(double d, double dt, double[][] p, double ur, String[] n) {
        universe = new NBody(d, dt, p, ur, n);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("NBodyChallenge/Planets/planets.txt");
        Scanner scan = new Scanner(file);
        Scanner console = new Scanner(System.in);

        int numPlanets = scan.nextInt();
        double ur = scan.nextDouble();

        double[][] planets = new double[numPlanets][5];
        String[] planetNames = new String[numPlanets];
        for (int i = 0; i < numPlanets; i++) {
            for (int j = 0; j < 5; j++) {
                planets[i][j] = scan.nextDouble();
            }
            planetNames[i] = scan.next();
        }

        System.out.println("How long? (years)");
        double d = console.nextDouble() * 31536000;
        System.out.println("Interval? (days)");
        double dt = console.nextDouble() * 86400;

        NBodyTester2 n = new NBodyTester2(d, dt, planets, ur, planetNames);

        scan.close();
        console.close();
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setScale(-n.universe.getUniverseRadius(), n.universe.getUniverseRadius());
        for (int i = 0; i < n.universe.getDuration(); i+=n.universe.getInterval()) {
            StdDraw.picture(0.0, 0.0, "Planets/starfield.jpg");
            StdDraw.picture(n.universe.getPlanets(0, 0), n.universe.getPlanets(0, 1), "Planets/earth.gif");
            StdDraw.picture(n.universe.getPlanets(1, 0), n.universe.getPlanets(1, 1), "Planets/mars.gif");
            StdDraw.picture(n.universe.getPlanets(2, 0), n.universe.getPlanets(2, 1), "Planets/mercury.gif");
            StdDraw.picture(n.universe.getPlanets(3, 0), n.universe.getPlanets(3, 1), "Planets/sun.gif");
            StdDraw.picture(n.universe.getPlanets(4, 0), n.universe.getPlanets(4, 1), "Planets/venus.gif");
            StdDraw.show(20);
            n.universe.updateAllPositions();
        }
        n.universe.printPlanets();
    }
}