package minmax.model;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Piece {
    private final ZUinfPoint location;
    private final Type type;

    public Piece(float event, float time, Type type) {
        this.type = type;
        this.location = new ZUinfPoint(event, time);
    }

    public ZUinfPoint getLocation() {
        return location;
    }
    
    public Type getType() {
        return type;
    }
    
    static public enum Type {
        VERTEX,
        TOP,
        LEFT,
        REGULAR
    };
}