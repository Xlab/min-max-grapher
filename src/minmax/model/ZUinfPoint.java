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
        if (((ZUinfPoint) o).getX() < this.getX()) {
            return -1;
        } else if (((ZUinfPoint) o).getX() > this.getX()) {
            return 1;
        } else {
            if (((ZUinfPoint) o).getY() < this.getY()) {
                return -1;
            } else if (((ZUinfPoint) o).getY() > this.getY()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZUinfPoint other = (ZUinfPoint) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Float.floatToIntBits(this.x);
        hash = 41 * hash + Float.floatToIntBits(this.y);
        return hash;
    }

    public String toTeXString() {
        if (this.equals(new ZUinfPoint())) {
            return "\\epsilon";
        } else if (this.equals(new ZUinfPoint(0, 0))) {
            return "e";
        }

        final String event = "\\delta";
        final String time = "t";

        final String eventView = ((this.x == Float.POSITIVE_INFINITY) ? "\\inf" : ((this.x == Float.NEGATIVE_INFINITY) ? "-\\inf" : "" + (int) this.x));
        final String timeView = ((this.y == Float.POSITIVE_INFINITY) ? "\\inf" : ((this.y == Float.NEGATIVE_INFINITY) ? "-\\inf" : "" + (int) this.y));;

        if (this.x == 0.0f) {
            return time + "^{" + timeView + "}";
        } else if (this.y == 0.0f) {
            return event + "^{" + eventView + "}";
        } else {
            return event + "^{" + eventView + "}" + time + "^{" + timeView + "}";
        }
    }
}
