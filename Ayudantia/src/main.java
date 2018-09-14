/**
 *
 * @author Pardo
 */
public class main {

    public static void main(String[] args) {
        
        for(int i=0;i<50;i++){
            System.out.println(lanzarDados());
        }
        
    }
    
    public static int lanzarDados(){
        int resultado = (int)(2*(Math.random()*6+1));
        return resultado;
    }
    
    public static char[] generarTablero(int nCasillas){
        char[] tablero = new char[nCasillas];
        return tablero;
    }

}
