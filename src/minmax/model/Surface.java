package minmax.model;

import java.awt.Color;
import java.util.ArrayList;
import minmax.Settings;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Surface {

    private final ArrayList<Layer> layers;
    private final int dimension;

    public int getDimension() {
        return dimension;
    }

    public Surface() {
        layers = new ArrayList();
        dimension = Settings.defaultDimension;
    }

    public Surface(int dimension) {
        layers = new ArrayList();
        this.dimension = dimension;
    }

    public int getLayersCount() {
        return layers.size();
    }

    private Layer getLayer(int n) {
        return layers.get(n);
    }

    public void addLayer(Config config, Color color) {
        layers.add(new Layer(config, color));
    }
    
    public void addLayer(Config config, Color color, boolean shadow) {
        layers.add(new Layer(config, color, shadow));
    }

    public Piece getPiece(int layer, int i, int j) {
        Config config = getLayer(layer).getConfig();
        Color color = getLayer(layer).getColor();
        final float event = i - dimension / 2;
        final float time = (dimension - j) - dimension / 2;
        
        for (ZUinfPoint slope : config.getVertex()) {
            if (event > slope.getX() && time < slope.getY()) {
                return new Piece(event, time, Piece.Type.REGULAR, color);
            }
        }
        
        for (ZUinfPoint slope : config.getVertex()) {
            if (slope.getX() == event && slope.getY() == time) {
                return new Piece(event, time, Piece.Type.VERTEX, color);
            } else if (slope.getX() == event && time < slope.getY()) {
                return new Piece(event, time, Piece.Type.LEFT, color);
            } else if (slope.getY() == time && event > slope.getX()) {
                return new Piece(event, time, Piece.Type.BOTTOM, color);
            }
        }

        return null;
    }
    
    public boolean needShadow(int layer)
    {
        return getLayer(layer).needShadow();
    }
}
