package szyszko.paulina;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Provide number of mice");

        int mice = Integer.valueOf(br.readLine());

        System.out.println("Provide number of provide number of cats");

        int cats = Integer.valueOf(br.readLine());
        System.out.println("Provide board size");
        int boardSize = Integer.valueOf(br.readLine());

        System.out.println("Provide frequency");
        int frequency = Integer.valueOf(br.readLine());

        new Game(boardSize, cats, mice, frequency).start();



    }
}
