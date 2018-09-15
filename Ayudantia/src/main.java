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
        
        System.out.println("Ingrese el numero de casillas del tablero (no menor a 20): ");        
        nCasillas = sca.nextInt();
        while(nCasillas < 20){
            System.out.println("El numero de casillas no puede ser menor a 20");
            nCasillas = sca.nextInt();
        }
        tablero = generarTablero(nCasillas);
        
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
            jugador[i] = new Jugador(sca.next(), nCasillas-1);
        }
        boolean ganador = false;
        do{
            mostrarTablero(tablero);
            for(int i=0;i<jugador.length;i++){
                mostarJugadores(jugador);
                System.out.println(jugador[i].getNombre()+": Presiona una tecla para lanzar dados");
                sca.next();
                int dados = lanzarDados();
                System.out.println("El resultado de los dados es: "+dados);
                jugador[i].setPosicion(dados);
                if(jugador[i].getPosicion() == (nCasillas-1) ){
                    System.out.println(jugador[i].getNombre()+" es el Ganador");
                    ganador = true;
                    break;
                }
                else{
                    if(tablero[jugador[i].getPosicion()] != 'x'){
                        if(tablero[jugador[i].getPosicion()] == 'P'){
                            portal(jugador, i, tablero);
                        }
                        if(tablero[jugador[i].getPosicion()] == 'S'){
                            vida(jugador, i);
                        }
                      if(tablero[jugador[i].getPosicion()] == 'D'){
                         desafio(jugador, i);
                        }
                     }
                }
            } 
        }while(!ganador);
        
    }
    
    public static int lanzarDados(){
        int resultado = (int)((Math.random()*6+1)+(Math.random()*6+1));
        return resultado;
    }
    
    public static char[] generarTablero(int nCasillas){
        char[] tablero = new char[nCasillas];
        tablero[0] = 'I';
        tablero[nCasillas-1] = 'F';
        
        for(int i=1;i<tablero.length-1;i++){
            tablero[i] = 'x';
        }
        
        for(int i=3;i<tablero.length;i++){
            if(i%4 == 0){
                if(i<tablero.length){
                    tablero[i] = 'P';
                }
                i++;
                if(i<tablero.length){
                    tablero[i] = 'S';
                }
                i = i+2;
                if(i<tablero.length){
                    tablero[i] = 'D';
                }
                i = i+4;
            }
        }
        
        return tablero;
    }
    
    public static void desafio(Jugador[] jugador, int indice){
        int aleatorio = (int)(Math.random()*2);
        int signo = (int)(Math.random()*2);
        switch (aleatorio){
            case 0:
                aleatorio = (int)(Math.random()*5)+1;
                if(signo == 0){
                    jugador[indice].setPosicion(-aleatorio);
                }
                else{
                    jugador[indice].setPosicion(aleatorio);
                }
                break;
                
            case 1:
                aleatorio = (int)(Math.random()*4)+1;
                if(signo == 0){
                    for(int i=0;i<jugador.length;i++){
                        jugador[i].setVida(-aleatorio);
                    }
                }
                else{
                    for(int i=0;i<jugador.length;i++){
                        jugador[i].setVida(aleatorio);
                    }
                }
                break;
        }
    }
    
    public static void portal(Jugador[] jugador, int indice, char[] tablero){
        int aleatorio = (int)Math.random()*2;
        switch (aleatorio){
            case 0:
                for(int i=0;i<tablero.length;i++){
                    if(tablero[i] == 'P' && i<jugador[indice].getPosicion()){
                        jugador[indice].setAbsolutePosicion(i);
                        break;
                    }
                }
                break;
            case 1:
                for(int i=0;i<tablero.length;i++){
                    if(tablero[i] == 'P' && i>jugador[indice].getPosicion()){
                        jugador[indice].setAbsolutePosicion(i);
                        break;
                    }
                }
                break;
        }
    }
    
    public static void vida(Jugador[] jugador, int indice){
        int aleatorio = (int)(Math.random()*4+1);
        int signo = (int)(Math.random()*2+1);
        switch (signo){
            case 0:
                jugador[indice].setVida(-aleatorio);
                break;
            case 1:
                jugador[indice].setVida(aleatorio);
                break;
        }
    }
    
    public static void mostrarTablero(char[] tablero){
        for(int i=0;i<tablero.length;i++){
            System.out.print(tablero[i]+" ");
        }
        System.out.println("");
    }
    
    public static void mostarJugadores(Jugador[] jugador){
        
        for(int i=0;i<jugador.length;i++){
            System.out.println(jugador[i].getNombre()+"\t"+jugador[i].getVida()+"\t"+jugador[i].getPosicion());
        }
    }
    
}

