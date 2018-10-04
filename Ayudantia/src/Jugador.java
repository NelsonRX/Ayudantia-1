/**
 *
 * @author Pardo
 */
public class Jugador {
    
    private String nombre;
    private int vida;
    private int posicion;
    private int pos_max;
    private int meditar;
    
    public Jugador(String nombre, int pos_max){
        this.nombre = nombre;
        this.vida = 15;
        this.posicion = 0;
        this.pos_max = pos_max;
        this.meditar = 5;
    }
    
    public void mover(int posicion){
        this.posicion = this.posicion + posicion;
        if(this.posicion < 0){
            this.posicion = 0;
        }
        if(this.posicion > this.pos_max){
            this.posicion = this.pos_max;
        }
    }
    
    public void darVida(int cantidad){
        this.vida = this.vida + cantidad;
        if(this.vida > 15){
            this.vida = 15;
        }
    }
    
    public void usoMeditar(){
        this.meditar = this.meditar - 1; 
   }
    
    public void setVida(int vida){
        this.vida = vida;
    }
    
    public void setMeditar(int cantidad){
        this.meditar = cantidad;
    }
    
    public void setPosicion(int posicion){
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
    
    public int getMeditar(){
        return this.meditar;
    }
}
