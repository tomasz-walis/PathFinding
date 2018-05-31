/**************************************
 *  Tomasz Walis-Walisiak
 *  ID w1507569
 *  Last update: 02-04-2017
 **************************************/
public class Node {

        //variables
        boolean blockedNode = false;
        boolean sourceNode = false;
        boolean destinationNode = false;
        int numOfNodes = 0;
        int heuristicCost = 0;
        int i = 0;
        int j = 0;
        int finalNodeCost = 0;
        int distanceFromStartNode = 0;
        int parent = 0;

        /**
         * Iterate trough the white squares and
         * find the less expensive white square
         */
        public static Node pickMinimumNodeCost() {
                Node minimum = Path.whiteSquares.getFirst();
                int minimumF = minimum.finalNodeCost;


                for (Node node : Path.whiteSquares) {
                        if (node.finalNodeCost < minimumF){
                                minimumF = node.finalNodeCost;
                        }

                }
                for (Node node : Path.whiteSquares) {
                        if (node.finalNodeCost == minimumF) {
                                minimum = node;
                                break;
                        }

                }

                return minimum;
        }


    }

