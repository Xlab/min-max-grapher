package minmax.model;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class ZUinfPoint {

    public ZUinfPoint(float time, float event) {
        this.x = event;
        this.y = time;
    }

    private float x;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    private float y;

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public float getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(float y) {
        this.y = y;
    }

}
