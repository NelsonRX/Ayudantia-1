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
        cantCasillas = validar(20, "La cantidad de casillas no puede ser menor  20");
        tablero = generarTablero(cantCasillas);
        
        System.out.println("Ingrese la cantidad de jugadores:");
        cantJugadores = validar(1, "La cantidad minima de jugadores es 1");
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
                opcion = validar(1, 2, "Debes elegir entre 1 y 2");
                switch (opcion){
                    case 1:
                        int dados = lanzarDados();
                        System.out.println("El resultado de los dados es: "+dados);
                        jugador[i].mover(dados);
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
                        meditar(jugador[i]);
                        checkCasilla(jugador, i, tablero);
                    }
                
            }
        }while(!ganador);
        
    }
    
    public static void meditar(Jugador jugador){
        System.out.println("Ingrese la cantidad de casillas que desea moverse: (-X para retroceder X posiciones)");
        if (jugador.getMeditar() > 0){
            jugador.usoMeditar();
            int movimiento = validar(-3, 3, "Solo puedes moverte 3 casillas alrededor de tu posicion actual");
            jugador.mover(movimiento);
            jugador.darVida(1);
        }
        else{
            System.out.println("No le quedan mas \"Meditar\"");
            jugador.darVida(-1);
        }
    }
    
    public static int validar(int condicion, String texto){
        int variable = leerInt();
        while(variable < condicion){
            System.out.println(texto);
            variable = leerInt();
        }
        return variable;
    }
    
    public static int validar(int condicionInferior, int condicionSuperior, String texto){
        int variable = leerInt();
        while(variable < condicionInferior || condicionSuperior < variable){
            System.out.println(texto);
            variable = leerInt();
        }
        return variable;
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
                    jugador[indice].mover(-aleatorio);
                }
                else{
                    jugador[indice].mover(aleatorio);
                }
                break;
                
            case 1:
                aleatorio = (int)(Math.random()*4)+1;
                if(signo == 0){
                    for(int i=0;i<jugador.length;i++){
                        jugador[i].darVida(-aleatorio);
                    }
                }
                else{
                    for(int i=0;i<jugador.length;i++){
                        jugador[i].darVida(aleatorio);
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
                jugador.darVida(-aleatorio);
                break;
            case 1:
                jugador.darVida(aleatorio);
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
        System.out.println("Nombre\tVida\tPosicion\tMeditar");
        for(int i=0;i<jugador.length;i++){
            System.out.println(jugador[i].getNombre()+"\t"+jugador[i].getVida()+"\t"+jugador[i].getPosicion()+"\t\t"+jugador[i].getMeditar());
        }
    }
    
}
