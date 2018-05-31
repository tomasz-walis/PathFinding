import java.util.Random;

/**************************************
 *  Tomasz Walis-Walisiak
 *  ID w1507569
 *  Last update: 02-04-2017
 **************************************/
public class Grid {

    //variables
    static Random random = new Random();
    static int N = 9;//  random.nextInt(40 - 10 + 1) + 10;
    static int blockedSquares[];
    static Node grid[][];
    static int cost;


    /**
     * Creates NxN Nodes, dependant on the grid size
     * The first loop numbers the cells
     * The second for loop sets the source node, destination and black squares
     * The third for loop computes the heuristic cost calculation based on the
     * manhattan distance formula, euclidean distance formula, chebyshev distance formula
     * @param algorithmType This is the first parameter of the makeGrid method
     */
    public static void makeGrid(int algorithmType) {
        int destinationRow = 0;
        int destinationCol = 0;

        for (int i = 0; i < PrintPath.row; i++) {
            for (int j = 0; j < PrintPath.column; j++) {
                grid[i][j].i = i;
                grid[i][j].j = j;
                grid[i][j].numOfNodes = (i * PrintPath.column + j) + 1;
            }
        }

        for (int i = 0; i < PrintPath.row; i++) {
            for (int j = 0; j < PrintPath.column; j++) {
                if (grid[i][j].numOfNodes == PrintPath.startPoint){
                    grid[i][j].sourceNode = true;
                }
                if (grid[i][j].numOfNodes == PrintPath.endPoint) {
                    grid[i][j].destinationNode = true;
                    destinationRow = i;
                    destinationCol = j;
                }
                if (isClosed(grid[i][j].numOfNodes)) {
                    grid[i][j].blockedNode = true;
                }
            }
        }
        // calculate heuristicCost
        for (int i = 0; i < PrintPath.row; i++) {
            for (int j = 0; j < PrintPath.column; j++) {
                switch (algorithmType) {
                    case 1: // manhattan  formula
                        grid[i][j].heuristicCost = Math.abs(i - destinationRow) + Math.abs(j - destinationCol);

                        break;
                    case 2: // euclidean formula
                        grid[i][j].heuristicCost = (int) Math.floor(Math.sqrt(Math.abs(i - destinationRow) * Math.abs(i - destinationRow) + Math.abs(j - destinationCol) * Math.abs(j - destinationCol)));

                        break;
                    case 3: // chebyshev formula
                        grid[i][j].heuristicCost = Math.max(Math.abs(i - destinationRow), Math.abs(j - destinationCol));

                        break;
                }
            }
        }
    }

    /**
     * Put all grid I and J points in to new Node
     */
    public static void instantiateGrid() {
        for (int i = 0; i < PrintPath.row; i++) {
            for (int j = 0; j < PrintPath.column; j++) {
                grid[i][j] = new Node();
            }
        }
    }

    /**
     * Put all the white squares in to the arrayList
     * check the cost of the Node
     */
    public static void insertInToOpenNode(int number) {
        int x = (number - 1) / PrintPath.column;
        int y = (number - 1) % PrintPath.column;
        grid[x][y].finalNodeCost = grid[x][y].heuristicCost + grid[x][y].distanceFromStartNode;
        Path.whiteSquares.add(grid[x][y]);
        cost=grid[x][y].finalNodeCost;
    }

    /**
     * Check the black squares
     */
    public static boolean isClosed(int number) {
        for (int i = 0; i < blockedSquares.length; i++) {
            if (number == blockedSquares[i])
                return true;
        }
        return false;
    }
}
