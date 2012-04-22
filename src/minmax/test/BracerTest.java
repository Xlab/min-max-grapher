package minmax.test;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */

import java.text.ParseException;
import net.sourceforge.bracer.BracerParser;

public class BracerTest {
    public static String doMath(String input) throws ParseException
    {
        BracerParser bp = new BracerParser(3);
        bp.parse(input);
        return bp.evaluate(); //the output will be: -3.854 + 27.017I 
    }
}