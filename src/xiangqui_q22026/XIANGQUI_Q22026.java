/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xiangqui_q22026;

/**
 *
 * @author USER
 */
public class XIANGQUI_Q22026 {

  
    public static void main(String[] args) {
           try {
          
            IDataManager datos = new PlayerManager(); 
            
            new VentanaInicio().setVisible(true);
        } catch (Exception e) {
            System.err.println("Error crítico: " + e.getMessage());
        }
    }
    
}
