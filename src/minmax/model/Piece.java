package minmax.model;

import java.awt.Point;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public interface Piece {
    public enum Type
    {
        VERTEX,
        SHADOW
    }

    Point getLocation();
    Type getPieceType();
}
