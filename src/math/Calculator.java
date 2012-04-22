package math;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
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
        Config result = (Config) shell.evaluate("minmax.model.Config dt(float event, float time){ return new minmax.model.Config(event, time); }; "
                + "void display(minmax.model.Config var, java.awt.Color c){ __hiddenCalculator.display(var, c); }; "
                + "void draft(minmax.model.Config var, java.awt.Color c){ __hiddenCalculator.draft(var, c); }; "
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
    
    void display(Config var, Color c)
    {
        ready.add(new Layer(var, c, true));
    }
    
    void draft(Config var, Color c)
    {
        ready.add(new Layer(var, c, false));
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

