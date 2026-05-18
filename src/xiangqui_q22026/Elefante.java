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
        //  diagonal exacto de 2 casillas
        int difFila = Math.abs(dFila - this.fila);
        int difCol = Math.abs(dCol - this.columna);
        if (difFila != 2 || difCol != 2) return false;

        //elefante no cruza el río
        if (this.color.equals("ROJO") && dFila < 6) return false;  // El Rojo se queda abajo
            if (this.color.equals("NEGRO") && dFila > 3) return false; // El Negro se queda arriba

        // Valida que el punto intermedio de la diagonal esté vacío
        int pasoF = calcularPaso(this.fila, dFila);
        int pasoC = calcularPaso(this.columna, dCol);
        
        if (verificarBloqueoDiagonal(this.fila + pasoF, this.columna + pasoC, dFila, dCol, pasoF, pasoC, tablero)) {
            return false;
        }

      //ally
        Pieza destino = tablero[dFila][dCol];
        return destino == null || !destino.getColor().equals(this.color);
    }

    
    private boolean verificarBloqueoDiagonal(int fAct, int cAct, int fDest, int cDest, int pasoF, int pasoC, Pieza[][] tablero) {
        // si ya escaneamos el punto medio, llegamos a la frontera del destino, no hubo bloqueo en el trayecto ;D
        if (fAct == fDest && cAct == cDest) {
            return false;
        }
        // bloq
        if (tablero[fAct][cAct] != null) {
            return true;
        }
        // destino?
        return verificarBloqueoDiagonal(fAct + pasoF, cAct + pasoC, fDest, cDest, pasoF, pasoC, tablero);
    }
}
