import java.security.PublicKey;
import java.util.Objects;

public class Node {
    public Node prior;
    public Point location;
    public int g;
    public int h;
    public int f;


    public Node(Node prior, Point location, int g, int h, int f){
        this.prior = prior;
        this.location = location;
        this.g = g;
        this.h = h;
        this.f = f;
    }

    @Override
    public boolean equals(Object o) {
        //if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        //return g == node.g && h == node.h && f == node.f && Objects.equals(prior, node.prior) && Objects.equals(location, node.location);
        return this.location.equals(node.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prior, location, g, h, f);
    }
}
