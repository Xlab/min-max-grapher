package minmax.model;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class ZUinfPoint {

    public ZUinfPoint(float event, float time) {
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

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "["+ this.x +"; "+ this.y +"]";
    }
}
