/**
 *
 * @author Pardo
 */
import java.util.*;

public class main {

    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int nCasillas;
        char[] tablero;
        String[][] jugador;
        
        System.out.println("Ingrese la cantidad de jugadores:");
        jugador = new String[sca.nextInt()][3];
        
        
        
        
        System.out.println("Ingrese el numero de casillas del tablero (no menor a 20): ");        
        nCasillas = sca.nextInt();
        while(nCasillas < 20){
            System.out.println("El numero de casillas no puede ser menor a 20");
            nCasillas = sca.nextInt();
        }
        tablero = generarTablero(nCasillas);
       
    }
    
    public static int lanzarDados(){
        int resultado = (int)((Math.random()*6+1)+(Math.random()*6+1));
        return resultado;
    }
    
    public static char[] generarTablero(int nCasillas){
        char[] tablero = new char[nCasillas];
        for(int i=0;i<tablero.length;i++){
            tablero[i] = 'x';
        }
        int especial = (int)(Math.random()*5);
        
        return tablero;
    }
    
   
}
