package minmax.model;

import java.util.ArrayList;
import java.util.Iterator;

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

    public Config plus(Config b) {
        ArrayList set = new ArrayList();
        
        for (ZUinfPoint v2 : b.getVertex()) {
            for (ZUinfPoint v1 : this.getVertex()) {
                if (v1.getX() <= v2.getX() && v1.getY() >= v2.getY()) {
                    set.add(v1);
                } else if (v1.getX() > v2.getX() && v1.getY() < v2.getY()) {
                    set.add(v2);
                } else {
                    set.add(v1);
                    set.add(v2);
                }
            }
        }

        return new Config(set);
    }
    
    public void project(Surface s)
    {
        
    }
}
