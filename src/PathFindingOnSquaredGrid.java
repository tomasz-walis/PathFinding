import javax.swing.*;
import java.util.*;

/**************************************
 *  Tomasz Walis-Walisiak
 *  ID w1507569
 *  Last update: 02-04-2017
 **************************************/
public class PathFindingOnSquaredGrid {

    //variables
    static boolean[][] randomGrid;
    static int Ai, Aj, Bi, Bj;
    static boolean isRandomPoint;
    static Random random = new Random();


    /**
     * Given an N-by-N matrix of whiteSquares cells, return an N-by-N matrix
     * of cells reachable from the top
     * @param open This is the first parameter of the flow method
     */
   /* public static boolean[][] flow(boolean[][] open) {
        int N = open.length;
        boolean[][] full = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            flow(open, full, 0, j);
        }
        return full;
    }

     *//**
       * Determine set of whiteSquares/blockedSquares cells using depth first search
       * @param open This is the first parameter of the flow method
       * @param full This is the first parameter of the flow method
       * @param i This is the first parameter of the flow method
       * @param j This is the first parameter of the flow method
     **//*
    public static void flow(boolean[][] open, boolean[][] full, int i, int j) {
        int N = open.length;

        // base cases
        if (i < 0 || i >= N){
            return; // invalid row
        }else if (j < 0 || j >= N){
            return; // invalid column
        }else if (!open[i][j]){
            return; // not an whiteSquares Node
        }else if (full[i][j]){
            return; // already marked as whiteSquares
        }
        full[i][j] = true;
        flow(open, full, i + 1, j); // down
        flow(open, full, i, j + 1); // right
        flow(open, full, i, j - 1); // left
        flow(open, full, i - 1, j); // up
    }

    *//**
     * Check if the system percolate
     * @param open This is the first parameter of the percolates method
     *//*
   public static boolean percolates(boolean[][] open) {
        int N = open.length;
        boolean[][] full = flow(open);
        for (int j = 0; j < N; j++) {
            if (full[N - 1][j])
                return true;
        }

        return false;
    }

    *//**
     * Check if the system vertically or directly
     * @param open This is the first parameter of the percolatesDirect method
     *//*
    public static boolean percolatesDirect(boolean[][] open) {
        int N = open.length;
        boolean[][] full = flow(open);
        int directPerc = 0;
        for (int j = 0; j < N; j++) {
            if (full[N - 1][j]) {
                directPerc = 1;
                int rowabove = N - 2;
                for (int i = rowabove; i >= 0; i--) {
                    if (full[i][j]) {
                        directPerc++;
                    } else
                        break;
                }
            }
        }

        StdOut.println("Direct Percolation is: " + directPerc);
        if (directPerc == N)
            return true;
        else
            return false;
    }*/

    /**
     * Draws the grid and boxes to be black or white
     * Draw the N-by-N boolean matrix to standard draw
     * @param a This is the first parameter of the show method
     * @param which This is the first parameter of the show method
     */
    public static void show(boolean[][] a, boolean which) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (a[i][j] == which){
                    StdDraw.square(j, N - i - 1, .5);
                }else{
                    StdDraw.filledSquare(j, N - i - 1, .5);
                }
            }
        }
    }

    /**
     * Return a random N-by-N boolean matrix, where each entry is
     * true with probability p
     * @param N This is the first parameter of the random method
     * @param p This is the first parameter of the random method
     */
    public static boolean[][] random(int N, double p) {
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                a[i][j] = StdRandom.bernoulli(p);
            }
        }
        return a;
    }


    /**
     * Main method called when program is executed
     * @param args This is the first parameter of the main method
     */
    public static void main(String[] args) {
        randomGrid = random(Grid.N, 0.7);
        StdArrayIO.print(randomGrid);
        System.out.println();
        //System.out.println("The system percolates: " + percolates(randomGrid));
        System.out.println();
        //System.out.println("The system percolates directly: " + percolatesDirect(randomGrid));
        System.out.println();

        //ask the user if the points should be random or input
        int response = JOptionPane.showConfirmDialog(null, "Would you like to generate points randomly?", "POINTS",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            isRandomPoint=true;
        }else if (response == JOptionPane.NO_OPTION){
            isRandomPoint=false;
        }

        /**
         * clear and show the grid to the user
         * randomize the start and anding point acordingly to user choice
         * set the starting and ending point on the grid
         * copy the black squares in to
         * Grid.blockedSquares = Arrays.copyOf(Grid.blockedSquares, Grid.blockedSquares.length + 1);
         */

        while (true) {
            StdDraw.clear();
            //draw grid
            show(randomGrid, true);
            try {
                /*if user presses YES on the JOption panel set the boolean
                isRandomPoint to true and generate random point no the grid*/
                if (isRandomPoint==true){
                    do {
                        Ai = random.nextInt(Grid.N);
                        Aj = random.nextInt(Grid.N);
                    } while (!randomGrid[Ai][Aj]);

                    do {
                        Bi = random.nextInt(Grid.N);
                        Bj = random.nextInt(Grid.N);
                    } while (!randomGrid[Bi][Bj] && !(Ai != Bi && Aj != Bj) && !(Ai != Bj && Aj != Bi));

                    /*if user presses NO on the JOption panel set the boolean
                   isRandomPoint to false and let the user to input points coordinates*/
                }else if (isRandomPoint==false){
                    try{
                        do {
                             /*JOption panel to ask user for the input of Ai, Aj*/
                            String number1 = JOptionPane.showInputDialog("Enter i for A >");
                            if ((number1 != null) && (number1.length() > 0)) {
                                Ai = Integer.parseInt(number1);
                            }else{
                                System.exit(0);
                            }
                            String number2 = JOptionPane.showInputDialog( "Enter j for A > " );
                            if ((number2 != null) && (number2.length() > 0)) {
                                Aj = Integer.parseInt(number2);
                            }else{
                                System.exit(0);
                            }

                        } while (!randomGrid[Ai][Aj]);

                    }catch (Exception e){
                        continue;
                    }

                    try {
                        do {
                            /*JOption panel to ask user for the input of Bi, Bj*/
                            String number3 = JOptionPane.showInputDialog( "Enter i for B > " );
                            if ((number3 != null) && (number3.length() > 0)) {
                                Bi = Integer.parseInt(number3);
                            }else{
                                System.exit(0);
                            }
                            String number4 = JOptionPane.showInputDialog( "Enter j for B > " );
                            if ((number4 != null) && (number4.length() > 0)) {
                                Bj = Integer.parseInt(number4);
                            }else{
                                System.exit(0);
                            }
                        }while (!randomGrid[Bi][Bj] && !(Ai != Bi && Aj != Bj) && !(Ai != Bj && Aj != Bi));
                    }catch (Exception e){
                        continue;
                    }
                }
                PrintPath.row = Grid.N;
                PrintPath.column = Grid.N;
                PrintPath.startPoint = Ai * Grid.N + Aj+1 ;
                PrintPath.endPoint = Bi * Grid.N + Bj+1 ;

                for (int e = 1; e <= 3; e++) {
                    StdDraw.clear();
                    show(randomGrid, true);
                    Grid.blockedSquares = new int[0];
                    for (int i = 0, b = 1; i < randomGrid.length; i++) {
                        for (int j = 0; j < randomGrid[i].length; j++, b++) {
                            if (!randomGrid[i][j]) {
                                Grid.blockedSquares = Arrays.copyOf(Grid.blockedSquares, Grid.blockedSquares.length + 1);
                                Grid.blockedSquares[Grid.blockedSquares.length - 1] = b;
                            }
                        }
                    }
                    Grid.grid = new Node[PrintPath.row][PrintPath.column];
                    Grid.instantiateGrid();
                    Grid.makeGrid(e);
                    Grid.insertInToOpenNode(PrintPath.startPoint);

                    while (Path.findAvailablePath());
                    PrintPath.printPath(e);
                }
            } catch (Exception e) {
                continue;
            }

            break;
        }





        System.out.println();

    }
}
