/**
 *
 * @author Pardo
 */
public class Jugador {
    
    private String nombre;
    private int vida;
    private int posicion;
    private int max;
    
    public Jugador(String nombre, int max){
        this.nombre = nombre;
        this.vida = 15;
        this.posicion = 0;
        this.max = max;
    }
    
    public void setVida(int cant){
        this.vida = this.vida + cant;
        if(this.vida > 15){
            this.vida = 15;
        }
    }
    public void setPosicion(int posicion){
        this.posicion = this.posicion + posicion;
        if(this.posicion < 0){
            this.posicion = 0;
        }
        if(this.posicion > this.max){
            this.posicion = this.max;
        }
    }
    
    public void setAbsolutePosicion(int posicion){
        this.posicion = posicion;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public int getVida(){
        return this.vida;
    }
    public int getPosicion(){
        return this.posicion;
    }
}
