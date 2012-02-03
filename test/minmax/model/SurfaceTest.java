/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax.model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class SurfaceTest {
    
    public SurfaceTest() {
    }

    /**
     * Test of toString method, of class Surface.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        Surface surface = new Surface();
        
        Config a = new Config(2, 1);
        Config b = new Config(5, 2);
        Config c = new Config(8, 6);
        Config d = new Config(10, 11);

        
        surface.project(a.plus(b).plus(c).plus(d));
        System.out.print(surface.toString(0));
        
        assertTrue(true);
    }
}
