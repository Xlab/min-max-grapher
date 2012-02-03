package minmax.slope;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Vertex implements Piece{
    private final Color color;
    private final Point location;
    
    public Vertex(int x, int y, Color color) {
        this.color = color;
        this.location = new Point(x, y);
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Type getPieceType() {
        return Piece.Type.VERTEX;
    }
}
