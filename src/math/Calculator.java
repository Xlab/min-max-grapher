package math;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import hse.kcvc.jminmaxgd.Monomial;
import hse.kcvc.jminmaxgd.Polynomial;
import hse.kcvc.jminmaxgd.Series;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import minmax.model.Config;
import minmax.model.Layer;
import org.codehaus.groovy.control.CompilationFailedException;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Calculator {

    private final Binding binding;
    private final GroovyShell shell;
    private final ArrayList<Layer> ready;

    public Calculator() {
        ready = new ArrayList<Layer>();
        binding = new Binding();
        binding.setProperty("inf", Float.POSITIVE_INFINITY);
        binding.setProperty("red", Color.red);
        binding.setProperty("orange", Color.orange);
        binding.setProperty("yellow", Color.yellow);
        binding.setProperty("green", Color.green);
        binding.setProperty("cyan", Color.cyan);
        binding.setProperty("blue", Color.blue);
        binding.setProperty("magenta", Color.magenta);
        binding.setProperty("gray", Color.gray);
        binding.setProperty("lightGray", Color.lightGray);
        binding.setProperty("darkGray", Color.darkGray);
        binding.setProperty("black", Color.black);
        binding.setProperty("__hiddenCalculator", this);
        shell = new GroovyShell(ClassLoader.getSystemClassLoader(), binding);
    }

    public Map eval(String input) {
        shell.evaluate(
                "hse.kcvc.jminmaxgd.Monomial m(int g, int d){ return new hse.kcvc.jminmaxgd.Monomial(g, d); }; "
                + "hse.kcvc.jminmaxgd.Polynomial p(java.util.List<hse.kcvc.jminmaxgd.Monomial> monomials){ return new hse.kcvc.jminmaxgd.Polynomial(monomials); }; "
                + "hse.kcvc.jminmaxgd.Series s(hse.kcvc.jminmaxgd.Polynomial p,hse.kcvc.jminmaxgd.Polynomial q, hse.kcvc.jminmaxgd.Monomial r){ return new hse.kcvc.jminmaxgd.Series(g, d); }; "
                + "void display(hse.kcvc.jminmaxgd.Monomial var, java.awt.Color c){ __hiddenCalculator.display(var, c); }; "
                + "void draft(hse.kcvc.jminmaxgd.Monomial var, java.awt.Color c){ __hiddenCalculator.draft(var, c); }; "
                + "void display(hse.kcvc.jminmaxgd.Polynomial var, java.awt.Color c){ __hiddenCalculator.display(var, c); }; "
                + "void draft(hse.kcvc.jminmaxgd.Polynomial var, java.awt.Color c){ __hiddenCalculator.draft(var, c); }; "
                + "void display(hse.kcvc.jminmaxgd.Series var, java.awt.Color c){ __hiddenCalculator.display(var, c); }; "
                + "void draft(hse.kcvc.jminmaxgd.Series var, java.awt.Color c){ __hiddenCalculator.draft(var, c); }; "
                + input);
        return binding.getVariables();
    }

    public boolean checkLine(String line) {
        try {
            shell.parse(line);
            return true;
        } catch (CompilationFailedException e) {
            return false;
        }
    }

    void display(Monomial var, Color c) {
        Config config = new Config(var.getGamma(), var.getDelta());
        ready.add(new Layer(config, c, true));
    }

    void draft(Monomial var, Color c) {
        Config config = new Config(var.getGamma(), var.getDelta());
        ready.add(new Layer(config, c, false));
    }

    void display(Polynomial var, Color c) {
        Config config = new Config(var.getData());
        ready.add(new Layer(config, c, true));
    }

    void draft(Polynomial var, Color c) {
        Config config = new Config(var.getData());
        ready.add(new Layer(config, c, false));
    }

    void display(Series var, Color c) {
        //TODO: !!!
        //ready.add(new Layer(var, c, true));
    }

    void draft(Series var, Color c) {
        //TODO: !!!
        //ready.add(new Layer(var, c, false));
    }

    public ArrayList<Layer> getReady() {
        return ready;
    }

    public Binding getBinding() {
        return binding;
    }

    public GroovyShell getShell() {
        return shell;
    }
}
