package math;

import javax.script.Compilable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Calculator {

    ScriptEngine e = new ScriptEngineManager().getEngineByExtension("js");
    Compilable jsComplier = (Compilable) e;
}
