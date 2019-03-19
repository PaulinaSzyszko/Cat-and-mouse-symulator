package szyszko.paulina.board;

import szyszko.paulina.*;

import java.util.*;

public class Board {

    private int boardSize;
    int boardCenter;
    private Point[][] points;
    private Point[][] tmpPoints;
    private Set<Mouse> deadMouses = new HashSet<>();
    private Set<Cat> deadCats = new HashSet<>();
    private int mouseCount = 0;
    private int catCount = 0;


    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.boardCenter = boardSize / 2;
        this.points = initBoard();
        this.tmpPoints = initBoard();
    }

    private Point[][] initBoard() {
        Point[][] board = new Point[boardSize][boardSize];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Point();
            }
        }

        return board;

    }

    public boolean movePlayers() {
        Mouse winningMouse = null;

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {

                if (points[i][j].hasPlayer()) {

                    for (Player player : points[i][j].getPlayers()) {
                        if (player.canMove()) {

                            Direction direction = player.move();
                            int newFirstPosition = i + direction.getFirstDirection();
                            int newSecondPosition = j + direction.getSecondDirection();

                            if (newFirstPosition < 0) {newFirstPosition = newSecondPosition + 1;}
                            if (newSecondPosition < 0) {newSecondPosition = newSecondPosition + 1;}

                            if (newFirstPosition > points.length -1 ) {
                                newFirstPosition = newFirstPosition - 2;
                            }
                            if (newSecondPosition > points.length -1 ) {newSecondPosition = newSecondPosition - 2;}

                            if (player instanceof Cat && points[newFirstPosition][newSecondPosition].hasMice()) {
                                List<Mouse> mice = points[newFirstPosition][newSecondPosition].getMice();
                                if (mice.size()> 1){
                                    //rzutuje kota na playera
                                    deadCats.add((Cat)player);
                                        printMouseKill(mice, player);
                                }else {
                                    deadMouses.addAll(mice);
                                   // printMove(player, direction);
                                    System.out.println(player + " move from " + " (" + i +", "+ j + " )" + "to " + "( "+ newFirstPosition + "," + newSecondPosition +")");

                                    mice.forEach(mouse -> printKill(player, mouse));
                                }}

                            if (player instanceof Mouse && tmpPoints[newFirstPosition][newSecondPosition].hasCat() && !deadMouses.contains(player)) {

                                deadMouses.add((Mouse) player);
                                printWalkedOnto(player);
                            }

                            if (player instanceof Mouse && newFirstPosition == boardCenter && newSecondPosition == boardCenter && !deadMouses.contains(player)) {
                                printMove(player, direction);
                                tmpPoints[newFirstPosition][newSecondPosition].addPlayer(player);
                                winningMouse = (Mouse) player;
                               // printMove(player, direction);
                                System.out.println(player + " move from " + " (" + i +", "+ j + " )" + "to " + "( "+ newFirstPosition + "," + newSecondPosition +")");
                            }

                            if (player instanceof Mouse && !deadMouses.contains(player) && player != winningMouse) {
                                tmpPoints[newFirstPosition][newSecondPosition].addPlayer(player);
                               // printMove(player, direction);
                                System.out.println(player + " move from " + " (" + i +", "+ j + " )" + "to " + "( "+ newFirstPosition + "," + newSecondPosition +")");
                            }

                            if (player instanceof Cat) {
                                tmpPoints[newFirstPosition][newSecondPosition].addPlayer(player);
                               // printMove(player, direction);
                                System.out.println(player + " move from " + " (" + i +", "+ j + " )" + "to " + "( "+ newFirstPosition + "," + newSecondPosition +")");
                            }

                            if (tmpPoints[newFirstPosition][newFirstPosition].hasPlayer()){

                            }

                        } else {
                            tmpPoints[i][j].addPlayer(player);
                        }
                    }
                }
            }
        }

        points = tmpPoints;

        if (winningMouse != null) {
            System.out.println("\n \u001B[32m " + winningMouse + " GOT THE CHEESE! \033[0m \n");
            System.out.println(" \u001B[31m END GAME \033[0m ");
            return true;
        }

//        if (mouseCount == 2*catCount || mouseCount > 2*catCount){
//            System.out.println("\n \u001B[31m ALL CATS HAVE BEEN EATEN BY MOUSES! \033[0m \n");
//            System.out.println(" \u001B[31m END GAME \033[0m ");
//             return true;
//        }

        if (deadMouses.size() == mouseCount) {
            System.out.println("\n \u001B[31m ALL MOUSES ARE DEAD! \033[0m \n");
            System.out.println(" \u001B[31m END GAME \033[0m ");
            return true;
        }



        tmpPoints = initBoard();
        return false;
    }

    public void add(Player player) {

        if (player instanceof Cheese) {
            putInCenter(player);
        }

        if (player instanceof Mouse) {
            mouseCount++;
            putOnBorder(player);
        }

        if (player instanceof Cat) {
            catCount ++;
            putInsideBorders(player);
        }
    }

    public void putInsideBorders(Player player) {
        Random random = new Random();
        int firstIndex = random.nextInt(boardSize - 1) ;
        int secondIndex = random.nextInt(boardSize - 1) ;

        if (firstIndex != boardCenter && secondIndex != boardCenter) {
            points[firstIndex][secondIndex].addPlayer(player);
        } else {
            putInsideBorders(player);
        }

    }

    public void putInCenter(Player player) {
        if (!points[boardCenter][boardCenter].hasPlayer()) {
            points[boardCenter][boardCenter].addPlayer(player);
        }
    }


    public void putOnBorder(Player player) {

        Random random = new Random();
        int firstRandomPosition = random.nextInt(boardSize - 1);
        int randomIndex = random.nextInt(2);
        int secondPosition = 0;

        if (randomIndex == 1) {
            secondPosition = boardSize-1;
        }
        int verticalOrHorizontal = random.nextInt(2);

        if (verticalOrHorizontal == 0) {
            if (!points[firstRandomPosition][secondPosition].hasPlayer()) {
                points[firstRandomPosition][secondPosition].addPlayer(player);
                return;
            }

            putOnBorder(player);
        } else {

            if (!points[secondPosition][firstRandomPosition].hasPlayer()) {
                points[secondPosition][firstRandomPosition].addPlayer(player);
                return;
            }

            putOnBorder(player);
        }
    }

    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (Point[] row : points) {
            finalString.append(Arrays.toString(row)).append("\n");
        }

        return finalString.toString();
    }

    private void printMove(Player player, Direction direction) {

        if(direction == Direction.STAY){
            System.out.println();
            System.out.println(player + " \u001B[35m  stay in the same place\033[0m" );
        }else {
            System.out.println(player + " \u001B[32m  moved  \033[0m" + direction);
        }
    }

    private void printKill(Player player, Player playerKilled) {
        System.out.println(player + " \u001B[31m killed  \033[0m" + playerKilled);
    }

    //metoda ktora drukuje gdy myszy zabija kota
    private void printMouseKill(List<Mouse>players, Player playerKilled){
        System.out.println(players.toString() + " \u001B[36m killed  \033[0m" + playerKilled);
    }
    private void printWalkedOnto(Player playerLeft) {
        System.out.println(playerLeft + "\u001B[31m walked onto a Cat \033[0m ");
    }

}
