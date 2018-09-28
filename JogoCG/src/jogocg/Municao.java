package jogocg;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Renan Manoel
 */
public class Municao {
    
    private Image imagem;
    private int x, y;
    private int altura, largura;
    private boolean isVisible;
    
    //768
    
    private static final int ALTURA_TELA = 1024;
    private static final int VELOCIDADE = 1;
    
    public Municao(int x, int y){
        this.x = x;
        this.y = y;
        
        
        
        ImageIcon referencia = new ImageIcon("res\\tiro.png");
        imagem = referencia.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        
        isVisible = true;
        
    }
    
    public void movimentacaoTiro(){
        this.y -= VELOCIDADE;
        if(this.y > ALTURA_TELA){
           isVisible = false; 
        }
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }
    
}
