package minmax.model;

import java.awt.Point;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Shadow implements Piece {
    private final Point location;
    private final Type type;
    
    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Piece.Type getPieceType() {
        return Piece.Type.SHADOW;
    }
    
    public enum Type {
        TOP_LEFT,
        TOP,
        LEFT
    };

    public Shadow(int x, int y, Type type) {
        this.type = type;
        this.location = new Point(x, y);
    }
}
