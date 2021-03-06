package math;

/**
 * Создал: Максим Куприянов,
 * Факультет Бизнес-информатики
 * Отделение Программной инженерии
 * 2 курс, группа 272ПИ, НИУ-ВШЭ
 *
 * Проект: Курсовая работа 2011-2012гг
 *
 * Тема: "Программа выполнения операций в
 * идемпотентном полукольце конус-ограниченных
 * множеств."
 *
 * Программа: MinMaxGrapher
 *
 * Связь: me@kc.vc
 */

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import hse.kcvc.jminmaxgd.Constants;
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
        binding.setProperty("inf", Constants.INFINITY);
        binding.setProperty("_inf", Constants._INFINITY);
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
                + "hse.kcvc.jminmaxgd.Polynomial p(hse.kcvc.jminmaxgd.Monomial m){ return new hse.kcvc.jminmaxgd.Polynomial(m); }; "
                + "hse.kcvc.jminmaxgd.Polynomial p(hse.kcvc.jminmaxgd.Polynomial p){ return new hse.kcvc.jminmaxgd.Polynomial(p); }; "
                + "hse.kcvc.jminmaxgd.Series s(hse.kcvc.jminmaxgd.Polynomial p,hse.kcvc.jminmaxgd.Polynomial q, hse.kcvc.jminmaxgd.Monomial r){ return new hse.kcvc.jminmaxgd.Series(p, q, r); }; "
                + "hse.kcvc.jminmaxgd.Series s(hse.kcvc.jminmaxgd.Polynomial p){ return new hse.kcvc.jminmaxgd.Series(p); }; "
                + "hse.kcvc.jminmaxgd.Series s(hse.kcvc.jminmaxgd.Series s){ return new hse.kcvc.jminmaxgd.Series(s); }; "
                + "hse.kcvc.jminmaxgd.Monomial m(){ return new hse.kcvc.jminmaxgd.Monomial(); }; "
                + "hse.kcvc.jminmaxgd.Polynomial p(){ return new hse.kcvc.jminmaxgd.Polynomial(); }; "
                + "hse.kcvc.jminmaxgd.Series s(){ return new hse.kcvc.jminmaxgd.Series(); }; "
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
        Config r = new Config(var.getR().getGamma(), var.getR().getDelta());
        Polynomial rStar = r.star();
        Polynomial result = var.getP().oplus(var.getQ().otimes(rStar));
        Config config = new Config(result.getData());
        ready.add(new Layer(config, c, true));
    }

    void draft(Series var, Color c) {
        Config r = new Config(var.getR().getGamma(), var.getR().getDelta());
        Polynomial rStar = r.star();
        Polynomial result = var.getP().oplus(var.getQ().otimes(rStar));
        Config config = new Config(result.getData());
        ready.add(new Layer(config, c, false));
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
