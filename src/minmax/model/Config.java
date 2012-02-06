package minmax.model;

import java.util.*;
import minmax.Settings;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Config {

    private final ZUinfPoint[] vertex;

    public Config(float k, float t) {
        vertex = new ZUinfPoint[]{new ZUinfPoint(k, t)};
    }

    private Config(ArrayList<ZUinfPoint> list) {
        vertex = list.toArray(new ZUinfPoint[]{});
    }

    public Iterable<ZUinfPoint> getVertex() {
        return new Iterable<ZUinfPoint>() {

            @Override
            public Iterator<ZUinfPoint> iterator() {
                return new Iterator<ZUinfPoint>() {

                    int state = 0;

                    @Override
                    public boolean hasNext() {
                        if (state < vertex.length) {
                            return true;
                        } else {
                            return false;
                        }
                    }

                    @Override
                    public ZUinfPoint next() {
                        return vertex[state++];
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    public ZUinfPoint getVertex(int i)
            throws IllegalArgumentException {
        if (i < vertex.length) {
            return vertex[i];
        } else {
            throw new IllegalArgumentException("Cannot acces to " + i
                    + "th element of array (size: " + vertex.length + ")");
        }
    }

    public int getVertexCount() {
        return vertex.length;
    }

    @Override
    public String toString() {
        String string = "";
        for (ZUinfPoint point : this.getVertex()) {
            string += point.toString() + "\n";
        }

        return string;
    }

    public Config plus(Config b) {
        if (b.getVertexCount() < 2) {
            if (b.getVertexCount() < 1) {
                return this;
            }

            ArrayList<ZUinfPoint> set = new ArrayList();
            ZUinfPoint taken = b.getVertex(0);

            for (ZUinfPoint my : this.getVertex()) {
                if (taken.getX() >= my.getX() && taken.getY() <= my.getY()) {
                    taken = null; //useless
                    break;
                }
            }

            for (ZUinfPoint my : this.getVertex()) {
                if (taken != null) {
                    if (taken.getX() < my.getX() && taken.getY() > my.getY()) {
                        set.add(taken);
                    } else {
                        set.add(taken);

//                        my = markUseless(set, my);
//
//                        if (my != null) {
                        set.add(my);
//                        }
                    }
                    taken = null;
                } else {
                    my = markUseless(set, my);

                    if (my != null) {
                        set.add(my);
                    }
                }
            }

            Collections.sort(set, new Comparator<ZUinfPoint>() {

                @Override
                public int compare(ZUinfPoint o1, ZUinfPoint o2) {
                    return (int) Math.signum(o2.getX() - o1.getX());
                }
            });
            return new Config(set);
        } else {
            Config c = new Config(new ArrayList<ZUinfPoint>()).plus(this);

            for (ZUinfPoint taken : b.getVertex()) {
                c.plus(new Config(taken.getX(), taken.getY()));
            }

            return c;
        }
    }

    private ZUinfPoint markUseless(ArrayList<ZUinfPoint> set, ZUinfPoint my) {
        for (ZUinfPoint p : set) {
            if (my.getX() >= p.getX() && my.getY() <= p.getY()) {
                my = null; //useless
                break;
            }
        }
        return my;
    }

    public Config times(Config b) {
        Config target = new Config(new ArrayList<ZUinfPoint>());
        for (ZUinfPoint my : this.getVertex()) {
            for (ZUinfPoint taken : b.getVertex()) {
                Config c = new Config(my.getX() + taken.getX(), my.getY() + taken.getY());
                target = target.plus(c);
            }
        }
        return target;
    }

    public Config power(int power) {
        ArrayList<ZUinfPoint> set = new ArrayList();
        for (ZUinfPoint my : this.getVertex()) {
            set.add(new ZUinfPoint(my.getX() * power, my.getY() * power));
        }

        return new Config(set);
    }

    public Config star() {
        Config e = new Config(0, 0);
        for (int i = 1; i < Settings.defaultPrecision; ++i) {
            e = e.plus(this.power(i));
        }

        return e;
    }
}
