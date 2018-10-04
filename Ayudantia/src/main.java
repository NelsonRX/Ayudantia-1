/**
 *
 * @author Pardo
 */
import java.util.*;

public class main {

    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int cantCasillas, cantJugadores, opcion;
        char[] tablero;
        
        System.out.println("Ingrese el numero de casillas del tablero (no menor a 20): ");        
        cantCasillas = leerInt();
        while(cantCasillas < 20){
            System.out.println("El numero de casillas no puede ser menor a 20");
            cantCasillas = leerInt();
        }
        tablero = generarTablero(cantCasillas);
        
        System.out.println("Ingrese la cantidad de jugadores:");
        cantJugadores = leerInt();
        while(cantJugadores < 1){
            System.out.println("La cantidad de jugadores minima es de 1");
            System.out.println("Por favor ingrese nuevamente la cantidad de jugadores");
            cantJugadores = leerInt();
        }
        Jugador[] jugador = new Jugador[cantJugadores];
        for(int i=0;i<jugador.length;i++){
            System.out.println("Ingrese el nombre de jugador n°"+(i+1));
            jugador[i] = new Jugador(sca.next(), cantCasillas-1);
        }
        
        boolean ganador = false;
        do{
            mostrarTablero(tablero);
            for(int i=0;i<jugador.length;i++){
                mostarJugadores(jugador);
                System.out.println(jugador[i].getNombre()+": Elija una opcion\n1.- Tirar dados\n2.-Meditar");
                opcion = leerInt();
                while(opcion<1 || opcion>2){
                    System.out.println("Debe elegir entre 1 y 2");
                    opcion = leerInt();
                }
                switch (opcion){
                    case 1:
                        int dados = lanzarDados();
                        System.out.println("El resultado de los dados es: "+dados);
                        jugador[i].avanzar(dados);
                        if(jugador[i].getPosicion() == (cantCasillas-1) ){
                            System.out.println(jugador[i].getNombre()+" es el Ganador");
                            ganador = true;
                            break;
                        }
                        else{
                            checkCasilla(jugador, i, tablero);
                        }
                        break;
                        
                    case 2:
                        System.out.println("Ingrese 1.- para avanzar y 2.- para retroceder");
                        
                    }
                
            }
        }while(!ganador);
        
    }
    
    public static void checkCasilla(Jugador[] jugador, int indice, char[] tablero){
        if(tablero[jugador[indice].getPosicion()] != 'x'){
            if(tablero[jugador[indice].getPosicion()] == 'P'){
                portal(jugador[indice], tablero);
            }
            if(tablero[jugador[indice].getPosicion()] == 'S'){
                vida(jugador[indice]);
            }
            if(tablero[jugador[indice].getPosicion()] == 'D'){
                desafio(jugador, indice);
            }
        }
    }
   
    private static int leerInt(){
        int numero = 0;
        boolean lectura = false;
        do{
            try{
                Scanner leer = new Scanner(System.in);
                numero = leer.nextInt();
                lectura = true;
            }
            catch(InputMismatchException e){
                lectura = false;
                System.out.println("Debes ingresar un numero entero");
            }
        }while(!lectura);
        return numero;
    }
    
    public static int lanzarDados(){
        int resultado1 = (int)(Math.random()*6+1);
        int resultado2 = (int)(Math.random()*6+1);
        return resultado1+resultado2;
    }
    
    public static char[] generarTablero(int cantCasillas){
        char[] tablero = new char[cantCasillas];
        tablero[0] = 'I';
        tablero[cantCasillas-1] = 'F';
        
        for(int i=1;i<tablero.length-1;i++){
            tablero[i] = casillaRandom();
        }
        
        return tablero;
    }
    
    private static char casillaRandom(){
        char casillas[] = {'x','P','S','D'};
        int aleatorio = (int)(Math.random()*4);
        
        return casillas[aleatorio];
    }
    
    public static void desafio(Jugador[] jugador, int indice){
        int aleatorio = (int)(Math.random()*2);
        int signo = (int)(Math.random()*2);
        switch (aleatorio){
            case 0:
                aleatorio = (int)(Math.random()*5)+1;
                if(signo == 0){
                    jugador[indice].avanzar(-aleatorio);
                }
                else{
                    jugador[indice].avanzar(aleatorio);
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
    
    public static void portal(Jugador jugador, char[] tablero){
        int aleatorio = (int)(Math.random()*2);
        switch (aleatorio){ 
            case 0:
                for(int i=jugador.getPosicion();i>0;i--){
                    if(tablero[i] == 'P' && i<jugador.getPosicion()){
                        jugador.setPosicion(i);
                        break;
                    }
                }
                break;
            case 1:
                for(int i=jugador.getPosicion();i<tablero.length;i++){
                    if(tablero[i] == 'P' && i>jugador.getPosicion()){
                        jugador.setPosicion(i);
                        break;
                    }
                }
                break;
        }
    }
    
    public static void vida(Jugador jugador){
        int aleatorio = (int)(Math.random()*4+1);
        int signo = (int)(Math.random()*2);
        switch (signo){
            case 0:
                jugador.setVida(-aleatorio);
                break;
            case 1:
                jugador.setVida(aleatorio);
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
        System.out.println("Nombre\tVida\tPosicion");
        for(int i=0;i<jugador.length;i++){
            System.out.println(jugador[i].getNombre()+"\t"+jugador[i].getVida()+"\t"+jugador[i].getPosicion());
        }
    }
    
}

