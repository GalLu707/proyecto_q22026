/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class VentanaReportes extends JFrame{
    private JTextArea txtLogs; 
     private PlayerManager manager;

    public VentanaReportes() {
        this.manager = new PlayerManager();
        
        setTitle("Reportes y Estadísticas");
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       
        JTabbedPane pestañas = new JTabbedPane();
        
        pestañas.addTab("Ranking de Jugadores", crearPanelRanking());
        pestañas.addTab("Historial de Partidas", crearPanelLogs());
        
        add(pestañas, BorderLayout.CENTER);

    
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            new VentanaPrincipal().setVisible(true);
            this.dispose();
        });
        add(btnVolver, BorderLayout.SOUTH);
    }

    private JPanel crearPanelRanking() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

     
        String[] columnas = {"Posición", "Usuario", "Puntos"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
     
        ArrayList<Player> ranking = manager.obtenerRanking();
        int posicion = 1;
        for (Player p : ranking) {
            Object[] fila = {posicion++, p.getUsername(), p.getPuntos()};
            modelo.addRow(fila);
        }

        panel.add(new JLabel("Top Jugadores Activos", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel crearPanelLogs() {
          JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    txtLogs = new JTextArea(); 
    txtLogs.setEditable(false);
    
    panel.add(new JScrollPane(txtLogs), BorderLayout.CENTER);
    
    cargarHistorialEnPantalla(); 
    
    return panel;
        

    }
    
    private void cargarHistorialEnPantalla() {
    StringBuilder sb = new StringBuilder();
    sb.append("--- Historial de Partidas Recientes ---\n\n");
    
    java.util.ArrayList<String> logs = PlayerManager.getHistorialPartidas();
    
    if (logs.isEmpty()) {
        sb.append("Aún no se han jugado partidas en esta sesión.");
    } else {
        
        for (String log : logs) {
            sb.append("• ").append(log).append("\n");
        }
    }
    
    txtLogs.setText(sb.toString());
}
    
    
    
    
}
