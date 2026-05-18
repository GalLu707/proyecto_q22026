/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public class Elefante extends Pieza{
     public Elefante(String color, int fila, int columna) {
        String colorFormateado = color.equalsIgnoreCase("ROJO") ? "Red" : "Black";
        super(color, fila, columna, "Elefante", "/Imagenes/English-Elephant-" + colorFormateado + ".png");
    }

    @Override
    public boolean movimientoValido(int dFila, int dCol, Pieza[][] tablero) {
        // 1. Validar salto diagonal exacto de 2 casillas
        int difFila = Math.abs(dFila - this.fila);
        int difCol = Math.abs(dCol - this.columna);
        if (difFila != 2 || difCol != 2) return false;

        // 2. Restricción del Río: El elefante jamás cruza el río
        if (this.color.equals("ROJO") && dFila < 5) return false;  // El Rojo se queda abajo
        if (this.color.equals("NEGRO") && dFila > 4) return false; // El Negro se queda arriba

        // 3. RECURSIVIDAD: Validar que el punto intermedio de la diagonal esté vacío
        int pasoF = calcularPaso(this.fila, dFila);
        int pasoC = calcularPaso(this.columna, dCol);
        
        if (verificarBloqueoDiagonal(this.fila + pasoF, this.columna + pasoC, dFila, dCol, pasoF, pasoC, tablero)) {
            return false; // Si hay una pieza bloqueando el centro, movimiento inválido
        }

        // 4. Validar que la casilla final no tenga un aliado
        Pieza destino = tablero[dFila][dCol];
        return destino == null || !destino.getColor().equals(this.color);
    }

    // FUNCIÓN RECURSIVA: Escanea el camino intermedio hasta el destino para verificar bloqueos
    private boolean verificarBloqueoDiagonal(int fAct, int cAct, int fDest, int cDest, int pasoF, int pasoC, Pieza[][] tablero) {
        // Base: si ya escaneamos el punto medio y llegamos a la frontera del destino, no hubo bloqueo en el trayecto
        if (fAct == fDest && cAct == cDest) {
            return false;
        }
        // Si la casilla intermedia tiene una pieza, está bloqueado
        if (tablero[fAct][cAct] != null) {
            return true;
        }
        // Llamada recursiva hacia el destino
        return verificarBloqueoDiagonal(fAct + pasoF, cAct + pasoC, fDest, cDest, pasoF, pasoC, tablero);
    }
}
