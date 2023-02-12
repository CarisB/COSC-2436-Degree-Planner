package UI;

import java.io.IOException;
import java.util.Scanner;

public class Scene {
    public void Test() throws IOException, InterruptedException {
        System.out.println("TESTING");
        System.out.println("TESTING2");
        Scanner scn = new Scanner(System.in);
        int test = scn.nextInt();
        Clear();
        scn.nextInt();
    }

    public void Clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
