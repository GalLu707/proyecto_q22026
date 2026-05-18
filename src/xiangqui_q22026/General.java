/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public final class General extends Pieza {
    
      public General(String color, int fila, int columna) {
        String colorFormateado = color.equalsIgnoreCase("ROJO") ? "Red" : "Black";
        super(color, fila, columna, "General", "/Imagenes/English-King-" + colorFormateado + ".png");
    }

    @Override
    public boolean movimientoValido(int dFila, int dCol, Pieza[][] tablero) {
        // 1. Validar que se mueva solo 1 casilla ortogonalmente
        int difFila = Math.abs(dFila - this.fila);
        int difCol = Math.abs(dCol - this.columna);
        if ((difFila + difCol) != 1) return false;

        // 2. Validar límites del palacio (Columnas de 3 a 5)
        if (dCol < 3 || dCol > 5) return false;
        if (this.color.equals("ROJO") && (dFila < 7 || dFila > 9)) return false; // Palacio inferior
        if (this.color.equals("NEGRO") && (dFila < 0 || dFila > 2)) return false; // Palacio superior

        // 3. Validar casilla destino (vacia o enemigo)
        Pieza destino = tablero[dFila][dCol];
        if (destino != null && destino.getColor().equals(this.color)) return false;

        // 4. RECURSIVIDAD: Simular movimiento y verificar que los generales no se miren de frente
        // Guardamos estado actual temporalmente
        Pieza piezaDestinoOriginal = tablero[dFila][dCol];
        tablero[this.fila][this.columna] = null;
        tablero[dFila][dCol] = this;

        // Buscamos si hay una línea directa de visión entre generales en la columna del destino
        boolean generalesSeMiran = verificarVisionGenerales(0, dCol, tablero, false);

        // Deshacemos la simulación temporal
        tablero[this.fila][this.columna] = this;
        tablero[dFila][dCol] = piezaDestinoOriginal;

        return !generalesSeMiran; // El movimiento es válido si NO se miran
    }

    // FUNCIÓN RECURSIVA: Recorre toda la columna buscando si los dos generales se ven directamente
    private boolean verificarVisionGenerales(int fAct, int col, Pieza[][] tablero, boolean encontroPrimero) {
        if (fAct > 9) return false; // Fin del tablero

        Pieza p = tablero[fAct][col];
        if (p instanceof General) {
            if (encontroPrimero) {
                return true; // Encontró al segundo General sin obstáculos en medio
            } else {
                // Encontró al primer General, seguimos buscando recursivamente activando la bandera
                return verificarVisionGenerales(fAct + 1, col, tablero, true);
            }
        } else if (p != null && encontroPrimero) {
            // Si ya encontramos un General pero hay cualquier otra pieza interrumpiendo la vista, están a salvo
            return false;
        }

        return verificarVisionGenerales(fAct + 1, col, tablero, encontroPrimero);
    }
}
