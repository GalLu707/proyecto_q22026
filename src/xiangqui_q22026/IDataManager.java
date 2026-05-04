/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public interface IDataManager {
    boolean guardarUsuario(Player p);
    Player buscarUsuario(String username);
    boolean eliminarUsuario(String username);
}
