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
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
    private String turnoActual = "ROJO";
    private Pieza piezaSeleccionada = null;
    private Pieza[][] logicaTablero = new Pieza[10][9]; 

    public VentanaJuego() {
        this.manager = new PlayerManager();
        this.jugador1 = PlayerManager.getUsuarioLogueado();
        
     
        if (seleccionarOponente()) {
        
        inicializarPiezasLogicas();
        initVentana();
        generarTablero();
        setVisible(true);
        actualizarTableroGrafico();
        
    } else {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
            this.dispose();
        });
    }
        

    }
    
    private void inicializarPiezasLogicas() {
      for (int f = 0; f < 10; f++) {
        for (int c = 0; c < 9; c++) {
            logicaTablero[f][c] = null;
        }
    }
    
    
    
    logicaTablero[0][0] = new Carro("NEGRO", 0, 0);
    logicaTablero[0][8] = new Carro("NEGRO", 0, 8);
    logicaTablero[0][1] = new Caballo("NEGRO", 0, 1);
    logicaTablero[0][7] = new Caballo("NEGRO", 0, 7);
    
    logicaTablero[0][2] = new Elefante("NEGRO", 0, 2);
    logicaTablero[0][6] = new Elefante("NEGRO", 0, 6);
    
     logicaTablero[0][3] = new Asesor("NEGRO", 0, 3);
     logicaTablero[0][5] = new Asesor("NEGRO", 0, 5);
    
    logicaTablero[0][4] = new General("NEGRO", 0, 4);

    logicaTablero[2][1] = new Canon("NEGRO", 2, 1);
    logicaTablero[2][7] = new Canon("NEGRO", 2, 7);

    for (int i = 0; i < 9; i += 2) {
        logicaTablero[3][i] = new soldado("NEGRO", 3, i);
    }

    logicaTablero[9][0] = new Carro("ROJO", 9, 0);
    logicaTablero[9][8] = new Carro("ROJO", 9, 8);
    
    logicaTablero[9][1] = new Caballo("ROJO", 9, 1);
    logicaTablero[9][7] = new Caballo("ROJO", 9, 7);
    
    logicaTablero[9][2] = new Elefante("ROJO", 9, 2);
    logicaTablero[9][6] = new Elefante("ROJO", 9, 6);
    
     logicaTablero[9][3] = new Asesor("ROJO", 9, 3);
     logicaTablero[9][5] = new Asesor("ROJO", 9, 5);
    
    logicaTablero[9][4] = new General("ROJO", 9, 4);

    logicaTablero[7][1] = new Canon("ROJO", 7, 1);
    logicaTablero[7][7] = new Canon("ROJO", 7, 7);

    for (int i = 0; i < 9; i += 2) {
        logicaTablero[6][i] = new soldado("ROJO", 6, i);
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
            g2.fillRect(0, ch * 5, w, ch);
            

    
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
            
            
            casillas[f][c] = btn; 

            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(colorBordeTenue));
            
            final int fila = f;
            final int col = c;
            btn.addActionListener(e -> manejarClick(fila, col));

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
             
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
              Player ganador = turnoActual.equals("ROJO") ? jugador2 : jugador1;
        
              
              finalizarPartida(ganador);
            
            this.dispose();
        }
    }
    
    //este coso no quitar para verificar que si se toque la pieza
    
    
//    private void manejarClick(int f, int c) {
//    Pieza piezaSeleccionada = logicaTablero[filaOrigen][colOrigen];
//    
//   
//    if (piezaSeleccionada.movimientoValido(f, c, logicaTablero)) {
//        
//    } else {
//        JOptionPane.showMessageDialog(this, "Movimiento inválido para esta pieza.");
//    }
//}
    
    
   private void cambiarTurno() {
    if (turnoActual.equals("ROJO")) {
        turnoActual = "NEGRO";
    } else {
        turnoActual = "ROJO";
    }
    
    
} 
    
  private void manejarClick(int f, int c) {
  
    if (piezaSeleccionada == null) {
        Pieza p = logicaTablero[f][c];
        
        if (p != null && p.getColor().equals(turnoActual)) {
            piezaSeleccionada = p;
            
        } else {
            JOptionPane.showMessageDialog(this, "No es tu turno o casilla vacía.");
        }
    } 

    else {
        if (piezaSeleccionada.movimientoValido(f, c, logicaTablero)) {
            Pieza destino = logicaTablero[f][c];

           
            if (destino instanceof General) {
                logicaTablero[f][c] = piezaSeleccionada;
                logicaTablero[piezaSeleccionada.getFila()][piezaSeleccionada.getColumna()] = null;
                
                Player ganador = turnoActual.equals("ROJO") ? jugador1 : jugador2;
                finalizarPartida(ganador);
                return;
            }

            
            logicaTablero[f][c] = piezaSeleccionada;
            logicaTablero[piezaSeleccionada.getFila()][piezaSeleccionada.getColumna()] = null;
            piezaSeleccionada.setPosicion(f, c);
            
       
            actualizarTableroGrafico();
            
            
            piezaSeleccionada = null;
            cambiarTurno();
            
        } else {
            
            piezaSeleccionada = null;
            JOptionPane.showMessageDialog(this, "Movimiento no permitido.");
        }
    }
}  
    
  private void finalizarPartida(Player ganador) {
    if (ganador != null) {
        
        ganador.setPuntos(ganador.getPuntos() + 3);
        
        JOptionPane.showMessageDialog(this, "¡JUEGO TERMINADO!\nGanador: " + ganador.getUsername());
        
       
        new VentanaPrincipal().setVisible(true);
        this.dispose();
    }
}
        
private void actualizarTableroGrafico() {
    for (int f = 0; f < 10; f++) {
        for (int c = 0; c < 9; c++) {
            Pieza p = logicaTablero[f][c];
            JButton btn = casillas[f][c];

            if (p != null) {
                btn.setText(""); 

                try {
                    java.net.URL imgURL = getClass().getResource(p.getRutaImagen());
                    
                    if (imgURL != null) {
                        ImageIcon iconoOriginal = new ImageIcon(imgURL);
                        
                        int anchoBoton = btn.getWidth();
                        int altoBoton = btn.getHeight();

                        if (anchoBoton <= 0) anchoBoton = 65;
                        if (altoBoton <= 0) altoBoton = 65;

                        int anchoPieza = (int) (anchoBoton * 0.85);
                        int altoPieza = (int) (altoBoton * 0.85);

                        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                                anchoPieza, altoPieza, Image.SCALE_SMOOTH
                        );

                        btn.setIcon(new ImageIcon(imagenEscalada));
                    } else {
                        btn.setText(p.getNombre().substring(0, 2).toUpperCase());
                    }
                } catch (Exception e) {
                    btn.setText(p.getNombre().substring(0, 2).toUpperCase());
                }
            } else {
                btn.setText("");
                btn.setIcon(null);
            }

            if (piezaSeleccionada != null && p == piezaSeleccionada) {
                btn.setBackground(new Color(255, 255, 0, 120)); 
                btn.setContentAreaFilled(true);
            } else {
                btn.setContentAreaFilled(false);
            }
        }
    }
    
    this.repaint();
}

        
        
}
