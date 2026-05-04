/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqui_q22026;

import java.util.Date;

/**
 *
 * @author USER
 */
public class Player {
    private String username;
    private String password;
    private int puntos;
    private Date fechaIngreso;
    private boolean activo;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
        this.fechaIngreso = new Date();
        this.activo = true;
    }

    
     public String getUsername() {
         return username;
     }
     
    public String getPassword() { 
        return password; 
    }
    
    public void setPassword(String password) { 
        this.password = password;
    }
    
    public int getPuntos() { 
        return puntos; 
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
    
    
}
