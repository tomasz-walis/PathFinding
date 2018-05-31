import java.util.LinkedList;

/**************************************
 *  Tomasz Walis-Walisiak
 *  ID w1507569
 *  Last update: 02-04-2017
 **************************************/
public class Path {
    static LinkedList<Node> whiteSquares = new LinkedList<>();
    static LinkedList<Node> blackSquares = new LinkedList<>();



    /**
     * If statement to check if the white squares Linked list in empty
     * compute distanceFromStartNode of neighbours of minimumNodeFCost
     * the vector stores all the positions that it can go to, diagonally and vertically,
     * the for loop iterates through vector i and j to check where movement is allowed,
     * the cMinF checks all the neighbours to determinate which neighbour movement costs less
     * Check the possible movements around the
     * black squares and searches the minimum cost movement.
     */
    public static boolean findAvailablePath() {
        if (whiteSquares.isEmpty()){
            return false;
        }

        Node minimumNodeF = Node.pickMinimumNodeCost();
        whiteSquares.remove(minimumNodeF);
        blackSquares.add(minimumNodeF);
        int x = minimumNodeF.i;
        int y = minimumNodeF.j;
        int vector[][] = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < 8; i++) {
            if (x + vector[i][0] >= 0 && x + vector[i][0] <PrintPath.row && y + vector[i][1] >= 0 && y + vector[i][1] < PrintPath.column) {
                int nearbyX = x + vector[i][0];
                int nearbyY = y + vector[i][1];
                if (Grid.grid[nearbyX][nearbyY].distanceFromStartNode == 0 && Grid.grid[nearbyX][nearbyY].finalNodeCost == 0 && !Grid.grid[nearbyX][nearbyY].blockedNode) {
                    if (i < 4) {
                        Grid.grid[nearbyX][nearbyY].distanceFromStartNode = Grid.grid[x][y].distanceFromStartNode + 14;
                    }else {
                        Grid.grid[nearbyX][nearbyY].distanceFromStartNode = Grid.grid[x][y].distanceFromStartNode + 10;
                    }
                    Grid.grid[nearbyX][nearbyY].parent = Grid.grid[x][y].numOfNodes;
                    Grid.insertInToOpenNode(Grid.grid[nearbyX][nearbyY].numOfNodes);
                }

            }
        }
        return true;

    }
}
