/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class PlayerManager implements IDataManager{
    private static ArrayList<Player> usuarios = new ArrayList<>();
    private static Player usuarioLogueado = null;

    @Override
    public Player buscarUsuario(String username) {
        for (Player p : usuarios) {
            if (p.getUsername().equals(username)) return p;
        }
        return null;
    }
    
    @Override
    public boolean guardarUsuario(Player p) {
        if (buscarUsuario(p.getUsername()) == null) {
            usuarios.add(p);
            return true;
        }
        return false;
    }

    public static void setUsuarioLogueado(Player p) {
        usuarioLogueado = p; 
    }
    public static Player getUsuarioLogueado() { 
        return usuarioLogueado; 
    }

    @Override
    public boolean eliminarUsuario(String username) {
        // calmaos lpm
        throw new UnsupportedOperationException("Not supported yet."); 
    }

   public ArrayList<Player> obtenerRanking() {
    ArrayList<Player> listaFiltrada = new ArrayList<>();
    
    
    for (Player p : usuarios) {
        if (p.isActivo()) {
            listaFiltrada.add(p);
        }
    }
    
    
    listaFiltrada.sort((p1, p2) -> Integer.compare(p2.getPuntos(), p1.getPuntos()));
    
    return listaFiltrada;
}
    
    
    public void finalizarPartida(Player ganador) {
    if (ganador != null) {
        int puntosActuales = ganador.getPuntos();
        ganador.setPuntos(puntosActuales + 3);
        
        JOptionPane.showMessageDialog(null, "¡Felicidades " + ganador.getUsername() + "! Has ganado 3 puntos.");
        
        
        // manager.registrarPartida(jugador1, jugador2, ganador);
    }
    
    new VentanaPrincipal().setVisible(true);
    //this.dispose();
}

    
    
    
    
    
}
