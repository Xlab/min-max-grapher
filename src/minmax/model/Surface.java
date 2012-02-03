package minmax.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Surface {

    private final ArrayList<Layer> layers;
    private int currentLayer;
    private Layer tempLayer;

    public Surface() {
        layers = new ArrayList();
        tempLayer = new Layer();
        currentLayer = 0;
    }

    private void placePiece(Piece p) {
        tempLayer.placePiece(p.getLocation().getX() + tempLayer.getDimension() / 2,
                p.getLocation().getY() + tempLayer.getDimension() / 2, p);
    }

    private void sealLayer() {
        layers.add(tempLayer);
        tempLayer = new Layer();
        ++currentLayer;
    }

    public int getCurrentLayer() {
        return currentLayer;
    }

    public int getLayersCount() {
        return layers.size();
    }

    public Layer getLayer(int n) {
        return layers.get(n);
    }

    public void project(Config config, Color color) {
        Piece p;

        for (float i = 0; i < tempLayer.getDimension(); ++i) {
            final float event = i - tempLayer.getDimension() / 2;
            for (float j = 0; j < tempLayer.getDimension(); ++j) {
                final float time = j - tempLayer.getDimension() / 2;
                for (ZUinfPoint slope : config.getVertex()) {
                    if (slope.getX() == event && slope.getY() == time) {
                        p = new Piece(event, time, Piece.Type.VERTEX, color);
                        placePiece(p);
                    } else if (slope.getX() == event && time < slope.getY()) {
                        p = new Piece(event, time, Piece.Type.LEFT, color);
                        placePiece(p);
                    } else if (slope.getY() == time && event > slope.getX()) {
                        p = new Piece(event, time, Piece.Type.TOP, color);
                        placePiece(p);
                    }
                }
                
                for (ZUinfPoint slope : config.getVertex()) {
                    if (event > slope.getX() && time < slope.getY()) {
                        p = new Piece(event, time, Piece.Type.REGULAR, color);
                        placePiece(p);
                    }
                }
            }
        }
        
        sealLayer();
    }
}
