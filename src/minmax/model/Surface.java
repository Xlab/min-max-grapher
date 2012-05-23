package minmax.model;

import hse.kcvc.jminmaxgd.Monomial;
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
        try {
            return layers.get(n);
        } catch (IndexOutOfBoundsException e) {
            return new Layer(new Config(), Color.black);
        }
    }

    public void addLayer(Config config, Color color) {
        layers.add(new Layer(config, color));
    }

    public void addLayer(Config config, Color color, boolean shadow) {
        layers.add(new Layer(config, color, shadow));
    }

    public void addLayer(Layer l) {
        layers.add(l);
    }

    public Piece getPiece(int layer, int i, int j) {
        Config config = getLayer(layer).getConfig();
        Color color = getLayer(layer).getColor();
        final int event = i - dimension / 2;
        final int time = (dimension - j) - dimension / 2;

        for (Monomial slope : config.getVertex()) {
            if (event > slope.getGamma() && time < slope.getDelta()) {
                return new Piece(event, time, Piece.Type.REGULAR, color);
            }
        }

        for (Monomial slope : config.getVertex()) {
            if (slope.getGamma() == event && slope.getDelta() == time) {
                return new Piece(event, time, Piece.Type.VERTEX, color);
            } else if (slope.getGamma() == event && time < slope.getDelta()) {
                return new Piece(event, time, Piece.Type.LEFT, color);
            } else if (slope.getDelta() == time && event > slope.getGamma()) {
                return new Piece(event, time, Piece.Type.BOTTOM, color);
            }
        }

        return null;
    }

    public boolean needShadow(int layer) {
        return getLayer(layer).needShadow();
    }

    public void clear() {
        layers.clear();
    }
}
