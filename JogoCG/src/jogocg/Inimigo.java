package jogocg;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Renan Manoel
 */
public class Inimigo {
    
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisible;
    
    private int contador = 0;
    
    //768
    
    //private static final int ALTURA_TELA = 1;
    public static int VELOCIDADE = 1;
    
    public Inimigo(int x, int y){
        this.x = x;
        this.y = y;
        
        ImageIcon referencia;
        if(contador ++ %3 == 0)
        {
           referencia = new ImageIcon("res\\InimigoPadrao2.png"); 
        }
        else
        {
            referencia = new ImageIcon("res\\InimigoModerado2.png"); 
        }
           
         
        imagem = referencia.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        
        isVisible = true;
    }
    
    public void movimentacaoInimigo(){
        
//        if(this.y < 0){
//            this.y = ALTURA_TELA;
//        }
//        else
//        {
            this.y += VELOCIDADE; 
//        }
        
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
