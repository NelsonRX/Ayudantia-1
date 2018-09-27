/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pardo
 */
public class mainTest {
    
    public mainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() { 
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of lanzarDados method, of class main.
     */
    @Test
    public void testLanzarDados() {
        System.out.println("lanzarDados");
        int result = main.lanzarDados();
        boolean funciona = false;
        if(result>=2 && result<=12){
            funciona = true;
        }
        assertTrue(funciona);
    }

    /**
     * Test of portal method, of class main.
     */
    @Test
    public void testPortal() {
        System.out.println("portal");
        char[] tablero = main.generarTablero(20);
        for(int i=0;i<20;i++){
            System.out.print(tablero[i]+" ");
        }
        Jugador prueba = new Jugador("prueba", 20);
        prueba.setPosicion(10);
        int posInicial = 10;
        main.portal(prueba, tablero);
        System.out.println(prueba.getPosicion());
        int posFinal = prueba.getPosicion();
        assertNotEquals(posInicial, posFinal);
        assertEquals('P', tablero[posFinal]);
        
    }

    /**
     * Test of vida method, of class main.
     */
    @Test
    public void testVida() {
        Jugador prueba = new Jugador("prueba", 20);
        System.out.println("vida");
        prueba.setVida(-10);
        int vidaInicial = prueba.getVida();
        main.vida(prueba);
        int vidaFinal = prueba.getVida();
        if(vidaFinal<0 || vidaFinal>15){
            fail("La vida no se encuentra entre los limites permitidos");
        }
        else{
            assertNotEquals(vidaInicial, vidaFinal);
        }
        
   }
    
}
