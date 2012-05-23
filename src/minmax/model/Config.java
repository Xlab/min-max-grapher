package minmax.model;

import hse.kcvc.jminmaxgd.Monomial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Config {

    public final Monomial[] vertex;

    public Config(int g, int d) {
        vertex = new Monomial[]{new Monomial(g, d)};
    }

    public Config(ArrayList<Monomial> list) {
        vertex = list.toArray(new Monomial[]{});
    }

    private Config(Monomial[] set) {
        vertex = set;
    }

    public Config() {
        vertex = new Monomial[]{new Monomial()};
    }

    public Iterable<Monomial> getVertex() {
        return new Iterable<Monomial>() {

            @Override
            public Iterator<Monomial> iterator() {
                return new Iterator<Monomial>() {

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
                    public Monomial next() {
                        return vertex[state++];
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    public Monomial getVertex(int i)
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
    protected Object clone() {
        Monomial[] set = new Monomial[this.getVertexCount()];
        int i = 0;
        for (Monomial v : this.getVertex()) {
            set[i++] = (new Monomial(v.getGamma(), v.getDelta()));
        }

        return new Config(set);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Config other = (Config) obj;
        if (!Arrays.deepEquals(this.vertex, other.vertex)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Arrays.deepHashCode(this.vertex);
        return hash;
    }

    /*
     * //Groovy linkage begin public Config getAt(int power) { return
     * this.power(power); }
     *
     * public Config call() { return this.star(); }
     *
     * public Config call(int times) { if (times == Constants.INFINITY) { return
     * this.star(); } else { return this; } }
     *
     * public Config multiply(Config b) { return this.times(b); }
     *
     * public Config positive() { return this; }
     *
     * public Config multiply(int[] f) { if (f.length > 1) { return
     * this.times(new Config(f[0], f[1])); } else { return this; } } //Groovy
     * linkage end
     */
}
