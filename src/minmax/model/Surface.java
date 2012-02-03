package minmax.model;

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

    public void project(Config c) {
        Piece p;

        for (float i = 0; i < tempLayer.getDimension(); ++i) {
            final float event = i - tempLayer.getDimension() / 2;
            for (float j = 0; j < tempLayer.getDimension(); ++j) {
                final float time = j - tempLayer.getDimension() / 2;
                for (ZUinfPoint slope : c.getVertex()) {
                    if (slope.getX() == event && slope.getY() == time) {
                        p = new Piece(event, time, Piece.Type.VERTEX);
                        placePiece(p);
                    } else if (slope.getX() == event && time < slope.getY()) {
                        p = new Piece(event, time, Piece.Type.LEFT);
                        placePiece(p);
                    } else if (slope.getY() == time && event > slope.getX()) {
                        p = new Piece(event, time, Piece.Type.TOP);
                        placePiece(p);
                    }
                }
                
                for (ZUinfPoint slope : c.getVertex()) {
                    if (event > slope.getX() && time < slope.getY()) {
                        p = new Piece(event, time, Piece.Type.REGULAR);
                        placePiece(p);
                    }
                }
            }
        }
        
        sealLayer();
    }

    public String toString(int layer) {
        String tmp = "";
        int d = layers.get(layer).getDimension();
        
        for (float i = d/2 - 1; i > -d/2; --i) {
            for (float j = -d/2 + 1; j < d/2; ++j) {
                Piece p = layers.get(layer).getPiece(j + d/2, i + d/2);
                if (p != null) {
                    switch (p.getType()) {
                        case VERTEX:
                            tmp += "1 ";
                            break;
                        case TOP:
                            tmp += "3 ";
                            break;
                        case LEFT:
                            tmp += "2 ";
                            break;
                        case REGULAR:
                            tmp += "0 ";
                            break;
                    }
                } else {
                    tmp += ". ";
                }
            }
            tmp += "\r\n";
        }

        return tmp;
    }
}
