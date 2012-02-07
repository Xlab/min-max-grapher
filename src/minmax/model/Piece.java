package minmax.model;

import java.awt.Color;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Piece {
    private final ZUinfPoint location;
    private final Type type;
    private final Color color;

    public Piece(float event, float time, Type type, Color color) {
        this.type = type;
        this.location = new ZUinfPoint(event, time);
        this.color = color;
    }

    public ZUinfPoint getLocation() {
        return location;
    }
    
    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
    
    static public enum Type {
        VERTEX,
        BOTTOM,
        LEFT,
        REGULAR
    };

    @Override
    public String toString() {
        return location.toTeXString();
    }
}