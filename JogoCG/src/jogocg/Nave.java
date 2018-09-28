package jogocg;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Renan Manoel
 */
public class Nave {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    private List<Municao> municao;
    private int altura, largura;
    private boolean isVisible;

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public Nave(){
        
        ImageIcon referencia = new ImageIcon("res\\NaveJogador.png");
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
        
        municao = new ArrayList<Municao>();
        
        this.x = 295;//605;//1 a 596
        this.y = 605;//295;//1 a 619
    }

    public List<Municao> getMunicao() {
        return municao;
    }
    
    public void atira(){
        this.municao.add(new Municao(x + 30, y - 10));
        
    }
    
    public void movimentacao(){
        x += dx;
        y += dy;
        
        if(this.x < 1){
            x = 1;
        }
        
        if(this.x > 590){
            x = 590;
        }
        
        if(this.y < 1){
            y = 1;
        }
        
        if(this.y > 619){
            y = 619;
        }
            
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Image getImagem(){
        return imagem;
    }
    
    public void keyPressed(KeyEvent tecla){
       int codigo = tecla.getKeyCode();
       
       //controle de posições
       if(codigo == KeyEvent.VK_UP)
       {
           dy = -1;
       }
       if(codigo == KeyEvent.VK_DOWN)
       {
           dy = 1;
       }
       if(codigo == KeyEvent.VK_LEFT)
       {
           dx = -1;
       }
       if(codigo == KeyEvent.VK_RIGHT)
       {
           dx = 1;
       }
       
       if(codigo == KeyEvent.VK_SPACE){
            atira();  
       }
       if(codigo == KeyEvent.VK_R){
            new nivel();  
       }
    }
    
    public void keyReleased(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        
        if(codigo == KeyEvent.VK_UP)
       {
           dy = 0;
       }
       if(codigo == KeyEvent.VK_DOWN)
       {
           dy = 0;
       }
       if(codigo == KeyEvent.VK_LEFT)
       {
           dx = 0;
       }
       if(codigo == KeyEvent.VK_RIGHT)
       {
           dx = 0;
       }
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }
    
}
