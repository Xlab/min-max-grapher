package minmax.model;

import hse.kcvc.jminmaxgd.Monomial;
import java.awt.Color;
import minmax.gui.utils.Utils;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Piece {
    private final Monomial location;
    private final Type type;
    private final Color color;

    public Piece(int g, int d, Type type, Color color) {
        this.type = type;
        this.location = new Monomial(g, d);
        this.color = color;
    }

    public Monomial getLocation() {
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
        return Utils.mLaTeX(location);
    }
}