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
        // 1 casilla ortogonal (que era ortonormal????)
        int difFila = Math.abs(dFila - this.fila);
        int difCol = Math.abs(dCol - this.columna);
        if ((difFila + difCol) != 1) return false;

        // limites del palacio 
        if (dCol < 3 || dCol > 5) return false;
        if (this.color.equals("ROJO") && (dFila < 7 || dFila > 9)) return false; 
        if (this.color.equals("NEGRO") && (dFila < 0 || dFila > 2)) return false; 

        
        Pieza destino = tablero[dFila][dCol];
        if (destino != null && destino.getColor().equals(this.color)) return false;

        
        Pieza piezaDestinoOriginal = tablero[dFila][dCol];
        tablero[this.fila][this.columna] = null;
        tablero[dFila][dCol] = this;

        
        boolean generalesSeMiran = verificarVisionGenerales(0, dCol, tablero, false);

        
        tablero[this.fila][this.columna] = this;
        tablero[dFila][dCol] = piezaDestinoOriginal;

        return !generalesSeMiran; // El movimiento es válido si NO se miran
    }

  
    private boolean verificarVisionGenerales(int fAct, int col, Pieza[][] tablero, boolean encontroPrimero) {
        if (fAct > 9) return false; 

        Pieza p = tablero[fAct][col];
        if (p instanceof General) {
            if (encontroPrimero) {
                return true; // Encontró al segundo General sin obstáculos en medio
            } else {
                // Encontró al primer General
                return verificarVisionGenerales(fAct + 1, col, tablero, true);
            }
        } else if (p != null && encontroPrimero) {
           
            return false;
        }

        return verificarVisionGenerales(fAct + 1, col, tablero, encontroPrimero);
    }
}
