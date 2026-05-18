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

        // Validar que el movimiento total configure una "L" (2 en un eje y 1 en el otro)
        if (!((difFila == 2 && difCol == 1) || (difFila == 1 && difCol == 2))) return false;

        // Calculamos la dirección de la primera casilla recta del movimiento
        int pasoF = 0;
        int pasoC = 0;

        if (difFila == 2) {
            pasoF = calcularPaso(this.fila, dFila); // Se mueve 2 vertical, el primer paso es vertical
        } else {
            pasoC = calcularPaso(this.columna, dCol); // Se mueve 2 horizontal, el primer paso es horizontal
        }

        // RECURSIVIDAD: Evaluamos secuencialmente si la pata del caballo está bloqueada
        if (verificarPataBloqueada(this.fila + pasoF, this.columna + pasoC, tablero)) {
            return false; 
        }

        // Validar casilla destino
        Pieza destino = tablero[dFila][dCol];
        return destino == null || !destino.getColor().equals(this.color);
    }

    // FUNCIÓN RECURSIVA: Evalúa el punto de apoyo inicial del caballo de manera aislada
    private boolean verificarPataBloqueada(int fPata, int cPata, Pieza[][] tablero) {
        // Base recursiva simple: si la casilla de apoyo inmediata tiene un obstáculo, bloquea el camino
        return tablero[fPata][cPata] != null;
    }
}
