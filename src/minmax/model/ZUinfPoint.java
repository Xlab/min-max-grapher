package minmax.model;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class ZUinfPoint implements Comparable {

    public ZUinfPoint(float event, float time) {
        this.x = event;
        this.y = time;
    }

    public ZUinfPoint() {
        this.x = Float.POSITIVE_INFINITY;
        this.y = Float.NEGATIVE_INFINITY;
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
        return "[" + this.x + "; " + this.y + "]";
    }

    @Override
    public int compareTo(Object o) {
        if (((ZUinfPoint)o).getX() < this.getX()) {
            return -1;
        } else if (((ZUinfPoint)o).getX() > this.getX()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return ((ZUinfPoint)obj).getX() == this.getX();
    }
    
    
}
