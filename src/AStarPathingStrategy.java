import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AStarPathingStrategy
        implements PathingStrategy
{


    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {
        //compare f values
        Comparator<Node> comparator = Comparator.comparingInt((n1 -> n1.f));
        Queue<Node> openL = new PriorityQueue<>(comparator);

        //for quick lookups
        Map<Point, Node> closedL = new Hashtable<>();
        Map<Point, Node> openHash = new Hashtable<>();

        List<Point> path = new LinkedList<>();


        //take start node
        Node startN = new Node(null, start, 0, heuristic(start, end), heuristic(start, end));

        //add start node to open list
        openL.add(startN);
        //openHash.put(start, startN);

        //loop
        while (openL.size() != 0){

            //mark as current node
            Node currentN = openL.remove();
            Point currentPoint = currentN.location;


            //if goal is within one block away
            if (withinReach.test(currentPoint, end)){
                return backtrack(path, currentN);
            }

            else {
                //filter and add neighbors not in open list
                //pt filters out going further away from target
                List<Point> neighbors = potentialNeighbors.apply(currentPoint)
                        .filter(canPassThrough)
                        .toList();

                //iterate through each valid neighbor
                for (Point neighbor : neighbors) {

                    //not on closed list/hash
                    if (!closedL.containsKey(neighbor)) {

                        //mark as current node
                        Node newN = new Node(currentN, neighbor, currentN.g + 1, heuristic(neighbor, end), currentN.g + 1 + heuristic(neighbor, end));
                        //currentN = newN;
                        //currentPoint = neighbor;
                        //System.out.println(newN.location);

                        //check if already in open list and g value is better
                        if (openHash.containsKey(neighbor)) {
                            //g value of neighbor
                            if (newN.g < openHash.get(neighbor).g) {
                                //replace node in open list
                                openL.remove(newN);
                                openL.add(newN);
                                openHash.replace(neighbor, newN);
                            }
                        }

                        //if not in open list, add to open list
                        else {
                            openL.add(newN);
                            openHash.put(neighbor, newN);
                        }
                        closedL.put(currentPoint, currentN);

                    }

                }
                // openL.remove(currentN);
                // openHash.remove(currentPoint, currentN);

            }
        }

        //steps to implement
        //analyze all VALID ADJACENT NODES NOT ON CLOSED LIST
        //mark as current node
        //determine g value (distance from start node) ---- can add 1 to prior g value
        //if node already in open list: CHECK g value is better(LOWER g value) than previous g value and replace old g value
        //estimate h value(distance of adjacent to end)

        //save prior node of neighbor
        //note: neighbors prior node == current node

        //add node to open list (replacing node if already is one in open list that is better)
        //move current node to closed list ---- when move to closed: remove from open list

        //choose node from open list with smallest f value(total distance) and make current node
        //need to compare f values

        //go back to analyzing neighbors

        //System.out.println(path);
        return path;
    }

    public List<Point> backtrack(List<Point> path, Node node){

        if (node.prior == null){
            return path;
        }
        else{
            path.add(0, node.location);
            return backtrack(path, node.prior);
        }
    }

    public int heuristic(Point start, Point end){
        //estimate distance of node to end point (Manhattan)
        return Math.abs(end.x - start.x) + Math.abs(end.y - start.y);
    }
}
