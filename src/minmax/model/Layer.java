package minmax.model;

import java.awt.Color;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Layer {

    private final Config config;
    private final Color color;
    private final boolean shadow;

    public Layer(Config config, Color color, boolean shadow) {
        this.config = config;
        this.color = color;
        this.shadow = shadow;
    }
    
    public Layer(Config config, Color color) {
        this(config, color, true);
    }

    public Color getColor() {
        return color;
    }

    public Config getConfig() {
        return config;
    }

    public boolean needShadow() {
        return shadow;
    } 
}
