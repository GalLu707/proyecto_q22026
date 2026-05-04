/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author USER
 */
public class VentanaReportes extends JFrame{
     public VentanaReportes() {
        setTitle("Reportes de Sistema");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        
        add(new JLabel("Sección de Reportes", SwingConstants.CENTER), BorderLayout.NORTH);
        
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e -> {
            new VentanaPrincipal().setVisible(true);
            this.dispose();
        });
        add(btnRegresar, BorderLayout.SOUTH);
    }
}
