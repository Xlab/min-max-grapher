package minmax.model;

import minmax.Settings;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Layer {

    private final Piece[][] grid;

    public Layer() {
        final int d = Settings.defaultDimension;
        grid = new Piece[d][];
        init(d);
    }

    public Layer(int d)
            throws IllegalArgumentException {
        if (d > 0) {
            if (d % 2 != 0) {
                ++d;
            }
            grid = new Piece[d][];
            init(d);
        } else {
            throw new IllegalArgumentException("Cannot create zero-size layer");
        }
    }

    private void init(int d) {
        for (int i = 0; i < d; ++i) {
            grid[i] = new Piece[d];
        }
    }

    public void placeEvent(int x, int y, Piece e)
            throws IllegalArgumentException {
        if (y < grid.length && x < grid.length && e != null) {
            grid[y][x] = e;
        } else {
            throw new IllegalArgumentException("cannot place event(null? "
                    + (e != null) + ") on (" + x + ";" + y + ")");
        }
    }
}
