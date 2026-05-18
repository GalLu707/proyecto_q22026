/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public abstract class Pieza {
     protected String color; 
    protected String nombre;
    protected int fila, columna;
    protected String rutaImagen;
    

    public Pieza(String color, int fila, int columna, String nombre, String rutaImagen) {
        this.color = color;
        this.fila = fila;
        this.columna = columna;
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        
        
    }
     public String getColor() { return color; }
    public String getNombre() { return nombre; }
    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public String getRutaImagen() { return rutaImagen; }
    
    
      public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
    public abstract boolean movimientoValido(int destinoFila, int destinoCol, Pieza[][] tablero);


    protected int calcularPaso(int origen, int destino) {
    if (origen == destino) return 0;
    return (destino > origen) ? 1 : -1;
}
    
    
    
}
