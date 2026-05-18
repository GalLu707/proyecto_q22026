/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public  class soldado extends Pieza{

     public soldado(String color, int fila, int columna) {
         String colorFormateado = color.equalsIgnoreCase("ROJO") ? "Red" : "Black";
        super(color, fila, columna, "Soldado", "/Imagenes/English-Pawn-" + colorFormateado + ".png");
    }

   
     @Override
    public boolean movimientoValido(int dFila, int dCol, Pieza[][] tablero) {
        int difFila = dFila - this.fila;
        int difCol = Math.abs(dCol - this.columna);

       
        if (color.equals("ROJO")) {
           
            if (this.fila >= 5) { 
                return difFila == -1 && difCol == 0;
            } else { 
                return (difFila == -1 && difCol == 0) || (difFila == 0 && difCol == 1);
            }
        } else {
            
            return true; 
        }
    }
    

    
}
