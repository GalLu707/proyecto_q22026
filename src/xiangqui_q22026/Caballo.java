/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public class Caballo extends Pieza{
     public Caballo(String color, int fila, int columna) {
        String colorFormateado = color.equalsIgnoreCase("ROJO") ? "Red" : "Black";
        super(color, fila, columna, "Caballo", "/Imagenes/English-Horse-" + colorFormateado + ".png");
    }

    @Override
    public boolean movimientoValido(int dFila, int dCol, Pieza[][] tablero) {
        int difFila = Math.abs(dFila - this.fila);
        int difCol = Math.abs(dCol - this.columna);

        // Validar que el movimiento total configure una L
        if (!((difFila == 2 && difCol == 1) || (difFila == 1 && difCol == 2))) return false;

        
        int pasoF = 0;
        int pasoC = 0;

        if (difFila == 2) {
            pasoF = calcularPaso(this.fila, dFila); // Se mueve 2 vertical, el primer paso es vertical
        } else {
            pasoC = calcularPaso(this.columna, dCol); // Se mueve 2 horizontal, el primer paso es horizontal
        }

        
        if (verificarPataBloqueada(this.fila + pasoF, this.columna + pasoC, tablero)) {
            return false; 
        }

        // Valida casilla destino
        Pieza destino = tablero[dFila][dCol];
        return destino == null || !destino.getColor().equals(this.color);
    }

  
    private boolean verificarPataBloqueada(int fPata, int cPata, Pieza[][] tablero) {
        return tablero[fPata][cPata] != null;
    }
}
