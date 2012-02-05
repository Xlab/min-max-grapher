package minmax.model;

import java.awt.Color;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Layer {

    private final Config config;
    private final Color color;

    public Layer(Config config, Color color) {
        this.config = config;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Config getConfig() {
        return config;
    }
}
