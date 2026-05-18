/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public class Canon extends Pieza {
   public Canon(String color, int fila, int columna) {
        String colorFormateado = color.equalsIgnoreCase("ROJO") ? "Red" : "Black";
        super(color, fila, columna, "Cañon", "/Imagenes/English-Cannon-" + colorFormateado + ".png");
    }

    @Override
    public boolean movimientoValido(int dFila, int dCol, Pieza[][] tablero) {
        if (this.fila != dFila && this.columna != dCol) return false;
        if (this.fila == dFila && this.columna == dCol) return false;

        int pasoF = calcularPaso(this.fila, dFila);
        int pasoC = calcularPaso(this.columna, dCol);

        // Contamos cuántas piezas hay en la trayectoria de manera recursiva
        int piezasEnMedio = contarPiezasEnLinea(this.fila + pasoF, this.columna + pasoC, dFila, dCol, pasoF, pasoC, tablero);
        Pieza destino = tablero[dFila][dCol];

        // Caso 1: Desplazamiento normal (Casilla destino vacía y 0 piezas intermedias)
        if (destino == null && piezasEnMedio == 0) {
            return true;
        }
        
        // Caso 2: Captura (Casilla destino con enemigo y exactamente 1 pieza intermedia para saltar)
        if (destino != null && !destino.getColor().equals(this.color) && piezasEnMedio == 1) {
            return true;
        }

        return false;
    }

    // FUNCIÓN RECURSIVA idéntica que cuenta obstáculos paso a paso
    private int contarPiezasEnLinea(int fAct, int cAct, int fDest, int cDest, int pasoF, int pasoC, Pieza[][] tablero) {
        if (fAct == fDest && cAct == cDest) {
            return 0;
        }
        int obstaculoAqui = (tablero[fAct][cAct] != null) ? 1 : 0;
        return obstaculoAqui + contarPiezasEnLinea(fAct + pasoF, cAct + pasoC, fDest, cDest, pasoF, pasoC, tablero);
    }  
}
