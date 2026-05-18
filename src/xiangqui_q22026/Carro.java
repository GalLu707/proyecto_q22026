/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public class Carro extends Pieza{
    
    
    public Carro(String color, int fila, int columna) {
        String colorFormateado = color.equalsIgnoreCase("ROJO") ? "Red" : "Black";
        super(color, fila, columna, "Carro", "/Imagenes/English-Rook-" + colorFormateado + ".png");
    }

    @Override
    public boolean movimientoValido(int dFila, int dCol, Pieza[][] tablero) {
        // El Carro solo se mueve en linea recta (misma fila o misma columna)
        if (this.fila != dFila && this.columna != dCol) return false;
        if (this.fila == dFila && this.columna == dCol) return false; // No se movio

        int pasoF = calcularPaso(this.fila, dFila);
        int pasoC = calcularPaso(this.columna, dCol);

      
        int piezasEnMedio = contarPiezasEnLinea(this.fila + pasoF, this.columna + pasoC, dFila, dCol, pasoF, pasoC, tablero);

        if (piezasEnMedio == 0) {
            Pieza destino = tablero[dFila][dCol];
            
            return destino == null || !destino.getColor().equals(this.color);
        }
        
        return false;
    }

    
    private int contarPiezasEnLinea(int fAct, int cAct, int fDest, int cDest, int pasoF, int pasoC, Pieza[][] tablero) {
        // Caso base...llegamos a la casilla de destino, dejamos de contar hacia atrás
        if (fAct == fDest && cAct == cDest) {
            return 0;
        }

        // Si encontramos una pieza en la casilla actual, sumamos 1
        int obstaculoAqui = (tablero[fAct][cAct] != null) ? 1 : 0;

        // avanza a la siguiente casilla de la trayectoria
        return obstaculoAqui + contarPiezasEnLinea(fAct + pasoF, cAct + pasoC, fDest, cDest, pasoF, pasoC, tablero);
    }

}
