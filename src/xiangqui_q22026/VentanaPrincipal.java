/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author USER
 */
public class VentanaPrincipal extends JFrame{
    private Player usuarioActual;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

     public VentanaPrincipal() {
     
        this.usuarioActual = PlayerManager.getUsuarioLogueado();
        
        setTitle("Menú Principal - " + usuarioActual.getUsername());
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

     
//        PanelImagen fondo = new PanelImagen("src/Imagenes/FondoPrincipal.png");
//        this.setContentPane(fondo);
        setLayout(new BorderLayout());

      
        Border bordeElegante = BorderFactory.createLineBorder(new Color(184, 134, 11), 3, true);
        Color colorCrema = new Color(245, 245, 220);

    
        JPanel panelDatos = new JPanel(new GridLayout(3, 1, 5, 5));
        panelDatos.setOpaque(false);
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40, 50, 20, 50));

        JLabel lblUser = crearLabelBlanco("BIENVENIDO: " + usuarioActual.getUsername().toUpperCase());
        lblUser.setFont(new Font("Serif", Font.BOLD, 26));
        JLabel lblFecha = crearLabelBlanco("Miembro desde: " + sdf.format(usuarioActual.getFechaIngreso()));
        JLabel lblEstado = crearLabelBlanco("Estado: " + (usuarioActual.isActivo() ? "ACTIVO" : "INACTIVO"));

        panelDatos.add(lblUser);
        panelDatos.add(lblFecha);
        panelDatos.add(lblEstado);

        JPanel contenedorBotones = new JPanel(new GridBagLayout()); 
        contenedorBotones.setOpaque(false);
        
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 0, 15));
        panelBotones.setOpaque(false);
        panelBotones.setPreferredSize(new Dimension(300, 350)); 

        JButton btnJugar = new JButton("1- JUGAR XIANGQI");
        JButton btnReportes = new JButton("2- REPORTES");
        JButton btnCambiarPass = new JButton("3- CAMBIAR CONTRASEÑA");
        JButton btnCerrarCuenta = new JButton("4- CERRAR MI CUENTA");
        JButton btnLogOut = new JButton("5- LOG OUT");

  
        estilizarBoton(btnJugar, bordeElegante, colorCrema);
        estilizarBoton(btnReportes, bordeElegante, colorCrema);
        estilizarBoton(btnCambiarPass, bordeElegante, colorCrema);
        estilizarBoton(btnCerrarCuenta, bordeElegante, colorCrema);
        estilizarBoton(btnLogOut, bordeElegante, new Color(255, 200, 200)); 

        panelBotones.add(btnJugar);
        panelBotones.add(btnReportes);
        panelBotones.add(btnCambiarPass);
        panelBotones.add(btnCerrarCuenta);
        panelBotones.add(btnLogOut);
        
        contenedorBotones.add(panelBotones); 

      
        add(panelDatos, BorderLayout.NORTH);
        add(contenedorBotones, BorderLayout.CENTER);

        
        btnLogOut.addActionListener(e -> {
            PlayerManager.setUsuarioLogueado(null);
            new VentanaInicio().setVisible(true);
            this.dispose();
        });
        
        btnJugar.addActionListener(e -> {
   
    VentanaJuego partida = new VentanaJuego();
  
    if (partida.getTitle() != null && !partida.getTitle().isEmpty()) {
        partida.setVisible(true);
        this.dispose();
    }
});
        btnCambiarPass.addActionListener(e -> cambiarPassword());
         btnCerrarCuenta.addActionListener(e -> cerrarCuenta());
    }

    private void estilizarBoton(JButton boton, Border borde, Color fondo) {
        boton.setBorder(borde);
        boton.setBackground(fondo);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JLabel crearLabelBlanco(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setForeground(Color.BLACK); 
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }
    
   private void cambiarPassword() {
    String actual = JOptionPane.showInputDialog(this, "Ingrese contraseña actual:");
    
   
    if (actual != null && actual.equals(usuarioActual.getPassword())) {
        String nueva = JOptionPane.showInputDialog(this, "Nueva contraseña (exactamente 5 caracteres):");
        
        if (nueva != null && nueva.length() == 5) {
            usuarioActual.setPassword(nueva);
            JOptionPane.showMessageDialog(this, "¡Contraseña actualizada con éxito!");
        } else if (nueva != null) {
            JOptionPane.showMessageDialog(this, "Error: La contraseña DEBE ser de 5 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else if (actual != null) {
        JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
    }
} 
    
   private void cerrarCuenta() {
    int confirm = JOptionPane.showConfirmDialog(this, 
        "¿Estás seguro de que deseas cerrar tu cuenta? Ya no podrás iniciar sesión.", 
        "Confirmar Cierre", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        usuarioActual.setActivo(false); 
        JOptionPane.showMessageDialog(this, "Cuenta cerrada. Volviendo al inicio.");
        
     
        PlayerManager.setUsuarioLogueado(null);
        new VentanaInicio().setVisible(true);
        this.dispose();
    }
}
   
}
