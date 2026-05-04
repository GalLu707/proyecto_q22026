/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 *
 * @author USER
 */
public class VentanaJuego extends JFrame {
    private Player jugador1;
    private Player jugador2;
    private JButton[][] casillas = new JButton[10][9]; 
    private PlayerManager manager;

    public VentanaJuego() {
        this.manager = new PlayerManager();
        this.jugador1 = PlayerManager.getUsuarioLogueado();
        
     
        if (seleccionarOponente()) {
            initVentana();
            generarTablero();
        } else {
   
            SwingUtilities.invokeLater(() -> {
                new VentanaPrincipal().setVisible(true);
                this.dispose();
            });
        }
    }

    private boolean seleccionarOponente() {
        String nombreOponente = JOptionPane.showInputDialog(this, "Nombre del oponente:");
        
        if (nombreOponente == null) return false; 

        Player rival = manager.buscarUsuario(nombreOponente);

       
        if (rival == null) {
            JOptionPane.showMessageDialog(this, "El jugador no existe");
            return false;
        }
        if (!rival.isActivo()) {
            JOptionPane.showMessageDialog(this, "El jugador seleccionado no está activo");
            return false;
        }
        if (rival.getUsername().equals(jugador1.getUsername())) {
            JOptionPane.showMessageDialog(this, "No puedes jugar contra ti mismo");
            return false;
        }

        this.jugador2 = rival;
        JOptionPane.showMessageDialog(this, "Partida Iniciada: " + jugador1.getUsername() + " vs " + jugador2.getUsername());
        return true;
    }

    private void initVentana() {
        setTitle("Xiangqi - Partida en Curso");
        setSize(700, 850); 
        setResizable(false);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        
        
        JPanel panelInfo = new JPanel(new GridLayout(1, 2));
        panelInfo.setBackground(new Color(101, 67, 33)); 
        JLabel lblJ1 = new JLabel("Rojo: " + jugador1.getUsername(), SwingConstants.CENTER);
        JLabel lblJ2 = new JLabel("Negro: " + jugador2.getUsername(), SwingConstants.CENTER);
        lblJ1.setForeground(Color.WHITE);
        lblJ2.setForeground(Color.WHITE);
        panelInfo.add(lblJ1);
        panelInfo.add(lblJ2);
        
        add(panelInfo, BorderLayout.NORTH);

      
        JButton btnRetirarse = new JButton("RETIRARSE");
        btnRetirarse.addActionListener(e -> confirmarRetiro());
        add(btnRetirarse, BorderLayout.SOUTH);
    }

    private void generarTablero() {
        
        
         Color colorBordeTenue = new Color(0, 0, 0, 60); 
    Color colorSeleccion = new Color(255, 255, 150, 150); 

    JPanel panelTablero = new JPanel(new GridLayout(10, 9)) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2));
            
            int w = getWidth();
            int h = getHeight();
            int cw = w / 9;
            int ch = h / 10;

            
            g2.setColor(new Color(173, 216, 230, 150)); 
            g2.fillRect(0, ch * 4, w, ch);
            

    
            g2.setColor(colorBordeTenue);
            
            g2.drawLine(cw * 3, 0, cw * 6, ch * 3);
            g2.drawLine(cw * 6, 0, cw * 3, ch * 3);
            
          
            g2.drawLine(cw * 3, ch * 7, cw * 6, ch * 10);
            g2.drawLine(cw * 6, ch * 7, cw * 3, ch * 10);
            
            g2.setStroke(new BasicStroke(3));
            g2.drawRect(0, 0, w, h);
        }
    };

    panelTablero.setBackground(new Color(245, 222, 179)); 
    
    for (int f = 0; f < 10; f++) {
        for (int c = 0; c < 9; c++) {
            JButton btn = new JButton();
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(colorBordeTenue));
            
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setContentAreaFilled(true);
                    btn.setBackground(colorSeleccion);
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setContentAreaFilled(false);
                }
            });

        
            panelTablero.add(btn);
        }
    }

    add(panelTablero, BorderLayout.CENTER);
        
        
        
        
  
    }

    private void confirmarRetiro() {
        int r = JOptionPane.showConfirmDialog(this, "¿Seguro que desea retirarse? El oponente ganará 3 puntos.", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
     
            JOptionPane.showMessageDialog(this, "Te has retirado. " + jugador2.getUsername() + " gana 3 puntos.");
            new VentanaPrincipal().setVisible(true);
            this.dispose();
        }
    }
}
