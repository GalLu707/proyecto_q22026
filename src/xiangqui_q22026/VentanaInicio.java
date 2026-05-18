/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author USER
 */
public class VentanaInicio extends JFrame{
    private final PlayerManager manager;
    
  public VentanaInicio() {
          manager = new PlayerManager();
        setTitle("Xiangqi - Inicio");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        PanelImagen fondo = new PanelImagen("/Imagenes/Login.jpg");
       this.setContentPane(fondo);
        setLayout(new GridLayout(5, 1, 10, 20)); 


        Border bordeElegante = BorderFactory.createLineBorder(new Color(184, 134, 11), 3, true);
        
    
        JButton btnLogin = new JButton("LOG IN");
        JButton btnCrear = new JButton("CREAR JUGADOR");
        JButton btnSalir = new JButton("SALIR");

       
        estilizarBoton(btnLogin, bordeElegante);
        estilizarBoton(btnCrear, bordeElegante);
        estilizarBoton(btnSalir, bordeElegante);

       
        JLabel titulo = new JLabel("BIENVENIDO A XIANGQI", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(Color.white);
        add(titulo);
        add(btnLogin);
        add(btnCrear);
        add(btnSalir);

      btnLogin.addActionListener(e -> mostrarLogin() );
        btnCrear.addActionListener(e -> mostrarRegistro());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void mostrarRegistro() {
        try {
            String user = JOptionPane.showInputDialog(this, "Nuevo Usuario:");
            if (user == null || user.isEmpty()) return;

            String pass = JOptionPane.showInputDialog(this, "Contraseña (5 caracteres):");
            if (pass == null || pass.length() != 5) {
                throw new Exception("la contraseña debe tener exactamente 5 caracteres");
            }

            if (manager.guardarUsuario(new Player(user, pass))) {
                JOptionPane.showMessageDialog(this, "Usuario creado con éxito");
            } else {
                JOptionPane.showMessageDialog(this, "El username ya existe :c");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }  
    
    
    private void estilizarBoton(JButton boton, Border borde) {
        boton.setBorder(borde);
        boton.setFocusPainted(false); 
        boton.setBackground(new Color(245, 245, 220)); 
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        
        
        boton.setMargin(new Insets(10, 20, 10, 20));
    }
    private void mostrarLogin() {
    String user = JOptionPane.showInputDialog(this, "Username:");
    String pass = JOptionPane.showInputDialog(this, "Password:");

    
    Player p = manager.buscarUsuario(user); 

    if (p != null && p.getPassword().equals(pass)) {
        
        if (!p.isActivo()) {
            JOptionPane.showMessageDialog(this, "Esta cuenta ha sido desactivada.");
            return;
        }

      
        PlayerManager.setUsuarioLogueado(p); 

        
        new VentanaPrincipal().setVisible(true);
        this.dispose(); 
    } else {
       
        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
    }
}
    
}
