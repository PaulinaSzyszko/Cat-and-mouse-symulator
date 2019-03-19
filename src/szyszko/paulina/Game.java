package szyszko.paulina;

import szyszko.paulina.board.Board;


public class Game {

    private int boardSize;
    private int numberOfCats;
    private int numberOfMice;
    private int frequency;

    private Board board;

    public Game(int boardSize, int numberOfCats, int numberOfMice, int frequency) {
        this.boardSize = boardSize;
        this.numberOfCats = numberOfCats;
        this.numberOfMice = numberOfMice;
        this.frequency =frequency;
    }


    public void start() {
        createBoard();
        populate();

        System.out.println("\n INITIAL BOARD \n");
        System.out.println(board);

        for (int i = 1;; i++) {
            System.out.println("\n \u001B[33m ROUND: " + i + "\033[0m \n");

            boolean isGameEnded = board.movePlayers();

            if (i %frequency == 0) {
                System.out.println(" \n \u001B[36m UPDATED BOARD \033[0m \n");
                System.out.println(board);
            }
            if (isGameEnded) {
                return;
            }
        }

    }

    private void createBoard() {
        board = new Board(boardSize);
    }

    private void populate() {

        board.add(new Cheese());
        board.add(new Cheese());

        for (int i = 1; i <= numberOfCats; i++) {
            board.add(new Cat(i));
        }

        for (int i = 1; i <= numberOfMice; i++) {
            board.add(new Mouse(i));
        }

    }
}
