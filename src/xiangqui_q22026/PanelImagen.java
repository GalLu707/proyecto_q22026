/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class PanelImagen extends JPanel {
    private Image imagen;
    
    public PanelImagen(String ruta) {
    this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
        setLayout(new BorderLayout()); 
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
    
}
