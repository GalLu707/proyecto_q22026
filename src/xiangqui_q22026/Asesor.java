/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public class Asesor extends Pieza {
    public Asesor(String color, int fila, int columna) {
        String colorFormateado = color.equalsIgnoreCase("ROJO") ? "Red" : "Black";
        
        super(color, fila, columna, "Asesor", "/Imagenes/English-Advisor-" + colorFormateado + ".png");
    }

    @Override
    public boolean movimientoValido(int dFila, int dCol, Pieza[][] tablero) {
        // 1. Validar movimiento diagonal exacto de 1 casilla
        int difFila = Math.abs(dFila - this.fila);
        int difCol = Math.abs(dCol - this.columna);
        if (difFila != 1 || difCol != 1) return false;

        // 2. Validar límites del Palacio (Columnas de la 3 a la 5)
        if (dCol < 3 || dCol > 5) return false;
        if (this.color.equals("ROJO") && (dFila < 7 || dFila > 9)) return false;
        if (this.color.equals("NEGRO") && (dFila < 0 || dFila > 2)) return false;

        // 3. RECURSIVIDAD: Validar que la casilla destino no tenga un aliado
        return validarDestinoRecursivo(dFila, dCol, tablero);
    }

    private boolean validarDestinoRecursivo(int dFila, int dCol, Pieza[][] tablero) {
        Pieza destino = tablero[dFila][dCol];
        if (destino == null) return true; 
       // Casilla vacia
        return !destino.getColor().equals(this.color); 
       // Permitido si es enemigo
    }
}
