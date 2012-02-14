package math;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import java.util.HashMap;
import javax.script.*;
import minmax.model.Config;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Calculator {

    /**
     * @param args
     */
    public Config calc(String input) {
        Binding binding = new Binding();
        binding.setVariable("a", new Config(1, 2));
        binding.setVariable("b", new Config(4, 3));
        binding.setProperty("c", new Config(8, 6));
        binding.setVariable("inf", Float.POSITIVE_INFINITY);
        GroovyShell shell = new GroovyShell(binding);

        Config result = (Config) shell.evaluate(input);

        return result;
    }
}
