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
        
        System.out.println("Ingrese la cantidad de jugadores:");
        int cantJugadores = sca.nextInt();
        while(cantJugadores < 1){
            System.out.println("La cantidad de jugadores minima es de 1");
            System.out.println("Por favor ingrese nuevamente la cantidad de jugadores");
            cantJugadores = sca.nextInt();
        }
        
        Jugador[] jugador = new Jugador[cantJugadores];
        for(int i=0;i<jugador.length;i++){
            System.out.println("Ingrese el nombre de jugador n°"+(i+1));
            agregarJugador(jugador, sca.next(), i);
        }
        
        
        
        
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
        tablero[0] = 'P';
        tablero[nCasillas-1] = 'F';
        for(int i=0;i<tablero.length-1;i++){
            tablero[i] = 'x';
        }
        
        return tablero;
    }
    
    public static void agregarJugador(Jugador[] lista, String nombre, int pos){
        lista[pos] = new Jugador(nombre);
    }
    
   
}
