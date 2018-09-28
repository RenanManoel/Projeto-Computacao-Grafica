/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogocg;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Renan Manoel
 */
public class JanelasJogo extends JFrame {
    public JanelasJogo(){
        
        add(new nivel());
        setTitle("Projeto CG");
        setSize(682 , 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
 
}


