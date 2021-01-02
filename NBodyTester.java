package Challenges.NBodyChallenge;

//import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class NBodyTester extends JPanel {
    NBody universe;

    public NBodyTester(double dt, double d, double planets[][], double ur, String n[]) {
        universe = new NBody(dt, d, planets, ur, n);
    }

    static int[] r = { 26, // Earth - 0
            20, // Mars - 1
            14, // Mercury - 2
            60, // Sun - 3
            20, // Venus - 4
            800, // Starfield - 5
            25 /* Margin - 6 */ * 2 };
    static int speed = 50; // Higher = slower

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("Challenges/NBodyChallenge/Planets/planets.txt");
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

        scan.close();
        console.close();

        JFrame window = new JFrame();
        window.setSize(r[5], r[5]);
        window.setTitle("Planetary Physics");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setUndecorated(true);

        NBodyTester n = new NBodyTester(d, dt, planets, ur, planetNames);

        JLabel days = new JLabel("Days: " + 0);
        days.setForeground(Color.white);
        n.add(days);
        window.add(n);
        window.setVisible(true);
        for (int i = 0; i < n.universe.getDuration(); i += n.universe.getInterval()) {

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            n.universe.updateAllPositions();
            //System.out.println(Arrays.deepToString(n.universe.getAllPlanets()));
            n.repaint();
            days.setText("Days: " + (int) n.universe.getDays(i));
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        days.setText("End of Simulation");
        // System.out.println("\n\n\n");
        // n.universe.printPlanets();
        // System.out.println("\n\n\n");
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        Image background = new ImageIcon("Challenges/NBodyChallenge/Planets/starfield.jpg").getImage();
        Image earth = new ImageIcon("Challenges/NBodyChallenge/Planets/earth.gif").getImage();
        Image mars = new ImageIcon("Challenges/NBodyChallenge/Planets/mars.gif").getImage();
        Image mercury = new ImageIcon("Challenges/NBodyChallenge/Planets/mercury.gif").getImage();
        Image sun = new ImageIcon("Challenges/NBodyChallenge/Planets/sun.gif").getImage();
        Image venus = new ImageIcon("Challenges/NBodyChallenge/Planets/venus.gif").getImage();
        

        double[][] planets = universe.getAllPlanets();
        //System.out.println(Arrays.deepToString(planets));
        double ur = universe.getUniverseRadius();

        
        //400 - width / 2 + x, 400 - height / 2 - y
        

        g2.drawImage(background, 0, 0, r[5], r[5], null);
        g2.drawImage(earth, r[5] / 2 - r[0] / 2 + (int) ((r[5] - r[6]) / 2 * planets[0][0] / ur), r[5] / 2 - r[0] / 2 - (int) ((r[5] - r[6]) / 2 * planets[0][1] / ur), r[0], r[0], null);
        g2.drawImage(mars, r[5] / 2 - r[1] / 2 + (int) ((r[5] - r[6]) / 2 * planets[1][0] / ur), r[5] / 2 - r[1] / 2 - (int) ((r[5] - r[6]) / 2 * planets[1][1] / ur), r[1], r[1], null);
        g2.drawImage(mercury, r[5] / 2 - r[2] / 2 + (int) ((r[5] - r[6]) / 2 * planets[2][0] / ur), r[5] / 2 - r[2] / 2 - (int) ((r[5] - r[6]) / 2 * planets[2][1] / ur), r[2], r[2], null);
        g2.drawImage(sun, r[5] / 2 - r[3] / 2 + (int) ((r[5] - r[6]) / 2 * planets[3][0] / ur), r[5] / 2 - r[3] / 2 - (int) ((r[5] - r[6]) / 2 * planets[3][1] / ur), r[3], r[3], null);
        g2.drawImage(venus, r[5] / 2 - r[4] / 2 + (int) ((r[5] - r[6]) / 2 * planets[4][0] / ur), r[5] / 2 - r[4] / 2 - (int) ((r[5] - r[6]) / 2 * planets[4][1] / ur), r[4], r[4], null);


    }

}