package minmax.gui;

import java.awt.Color;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Grid {

    private Color[][] grid;
    private int size;

    public Grid(int size) {
        this.size = size;
        grid = new Color[size][];
        for (int i = 0; i < size; ++i) {
            grid[i] = new Color[size];
        }
    }

    Grid() {
        grid = new Color[][]{};
    }

    public boolean exist(int x, int y) {
        x += size / 2;
        y += size / 2;
        return (grid.length > y && grid[y].length > x && grid[y][x] != null);
    }

    public Color getPoint(int x, int y) {
        if (exist(x, y)) {
            x += size / 2;
            y += size / 2;
            return grid[y][x];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setPoint(int x, int y, Color value) {
        x += size / 2;
        y += size / 2;
        if (grid.length > y && grid[y].length > x) {
            grid[y][x] = value;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
