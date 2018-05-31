import javax.swing.*;
import java.awt.*;
import java.util.Stack;
/**************************************
 *  Tomasz Walis-Walisiak
 *  ID w1507569
 *  Last update: 02-04-2017
 **************************************/
public class PrintPath{
    static double distance;
    static int row, column, startPoint, endPoint;



    /**
     * Put all the points
     * Draw the line from current Node to the next node
     * Check the cost of the distance
     * Stack peek raturn value at the top of the stack
     * push() adds the Points to the stack
     * pop() removes pre previous points that have been already drawn
     * peek() to look at the object in the top of this stack without removing it from the stack.
     */
    public static void printPath(int type) throws InterruptedException {

        distance = 0;
        Stack<Point> pointStack = new Stack<>();
        int x = (endPoint - 1) / column;
        int y = (endPoint - 1) % column;
        Node current = Grid.grid[x][y];

        drawStartEndPointAndFilledSquare((endPoint - 1) / Grid.N, (endPoint - 1) % Grid.N, 1);
        pointStack.push(new Point((endPoint - 1) / Grid.N, (endPoint - 1) % Grid.N));
        while (!current.sourceNode) {

            int c = current.parent;
            x = (c - 1) / column;
            y = (c - 1) % column;
            drawStartEndPointAndFilledSquare(x, y, 1);
            pointStack.push(new Point(x, y));
            current = Grid.grid[x][y];
        }
        Point previous = pointStack.pop();
        drawStartEndPointAndFilledSquare(previous.x, previous.y, 2);
        while (!pointStack.isEmpty()) {
            drawAlgorithmLine(previous.x, previous.y, pointStack.peek().x, pointStack.peek().y);
            if (previous.x != pointStack.peek().x && previous.y != pointStack.peek().y && type != 2) {
                if (PathFindingOnSquaredGrid.randomGrid[previous.x][pointStack.peek().y])
                    drawStartEndPointAndFilledSquare(previous.x, pointStack.peek().y, 1);
                else if (PathFindingOnSquaredGrid.randomGrid[pointStack.peek().x][previous.y])
                    drawStartEndPointAndFilledSquare(pointStack.peek().x, previous.y, 1);
                if (type == 1)
                    distance += 2; // manhattan
                if (type == 3)
                    distance += 1;
            } else {
                distance += 1; // all same
            }
            previous = pointStack.pop();
        }
        drawStartEndPointAndFilledSquare(previous.x, previous.y, 3);


        /*option panel do display the cost of the algorithm*/
        if (type == 1){

            JOptionPane.showMessageDialog(null, "Manhattan Distance: " + distance +" "+ " Cost: " + Grid.cost );
        }
        if (type == 2){
            double euclideanDistance = distance*Math.sqrt(2);
            euclideanDistance = Math.round(euclideanDistance * 10);
            JOptionPane.showMessageDialog(null, "Euclidean Distance: " + euclideanDistance/10 +" "+ " Cost: " + Grid.cost);
        }

        if (type == 3){
            JOptionPane.showMessageDialog(null, "Chebyshev Distance: " + distance +" "+ "  Cost: " + Grid.cost);
        }

    }


    /**
     * Draw the algorithm path
     * @param y This is the first parameter of the drawStartEndPointAndFilledSquare
     * @param x This is the first parameter of the drawStartEndPointAndFilledSquare method
     * @param algorithmType This is the first parameter of the drawStartEndPointAndFilledSquare method
     */
    public static void drawStartEndPointAndFilledSquare(int y, int x, int algorithmType) {
        y = Grid.N - 1 - y;
        switch (algorithmType) {
            case 1:
                StdDraw.setPenColor(Color.ORANGE);
                StdDraw.filledRectangle(x, y, 0.5, 0.5);
                break;
            case 2: // start circle
                StdDraw.setPenColor(Color.red);
                StdDraw.filledCircle(x, y, 0.3);
                break;
            case 3: // end cross
                StdDraw.setPenColor(Color.red);
                StdDraw.setFont(new Font("Arial", Font.BOLD, 40));
                StdDraw.text(x, y, "x");
                break;
        }
    }

    /**
     * Draw the algorithm line
     * @param y1 is the first parameter of the drawStartEndPointAndFilledSquare
     * @param x1 This is the first parameter of the drawAlgorithmLine method
     * @param y2 This is the first parameter of the drawAlgorithmLine method
     * @param x2 This is the first parameter of the drawAlgorithmLine method
     */
    public static void drawAlgorithmLine(int y1, int x1, int y2, int x2) {
        y1 = Grid.N - y1 - 1;
        y2 = Grid.N - y2 - 1;
        StdDraw.setPenColor(Color.red);
        StdDraw.line(x1, y1, x2, y2);
    }
}
