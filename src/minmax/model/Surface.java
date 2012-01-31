package minmax.model;

import java.util.ArrayList;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Surface {
    final ArrayList layers;
    int currentLayer;
    Layer tempLayer;
    
    public Surface() {
        layers = new ArrayList();
        tempLayer = new Layer();
        currentLayer = 0;
    }

    void placeEvent(Piece e)
    {
        tempLayer.placeEvent(e.getLocation().x, e.getLocation().y, e);
    }
    
    void sealLayer()
    {
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
    
    
}
