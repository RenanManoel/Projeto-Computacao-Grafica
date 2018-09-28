package jogocg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Renan Manoel
 */
public class nivel extends JPanel implements ActionListener {
    private Image fundo;
    private Nave nave;
    private Timer timer;
    private Random rand = new Random();
    private int count;
    
    private boolean emJogo;
    
    private List<Inimigo> inimigos;
    
    private int[][] coordenadas = { 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000 , rand.nextInt(300) +1000   }, 
        { rand.nextInt(300) +1000 , rand.nextInt(300) +1000   },
        
        
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000   }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000   }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000   }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000   },
        
        
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  },
        
        
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  },
        
        
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  },
        
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  },
        
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000  }, 
        { rand.nextInt(300) +1000  , rand.nextInt(300) +1000 } };
    
    
    public nivel(){
        
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(new TecladoAdapter());
        
        ImageIcon referencia = new ImageIcon("res\\fundo.jpg");
        fundo = referencia.getImage();
        nave = new Nave();
        
        emJogo = true;
        
        inicializaInimigos();
        
        timer = new Timer(3, this);
        timer.start();
    }
    

    
    public void inicializaInimigos(){
        inimigos = new ArrayList<>();
       
        for(int i = 0; i < 40; i++){
            rand = new Random();
            Inimigo a = new Inimigo((rand.nextInt(682))+0,-(rand.nextInt(i+700)+800));
            inimigos.add(a);
        }
    }
    
    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        
        if(emJogo)
        {
            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

            List<Municao> tiro = nave.getMunicao();

            for(int i = 0; i < tiro.size(); i++)
            {
                Municao m = (Municao)tiro.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for(int i = 0; i < inimigos.size(); i++)
            {
                Inimigo in = inimigos.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            
            graficos.setColor(Color.WHITE);
            graficos.drawString("Inimigos: " + inimigos.size(), 1, 12);
            
        }
        else
        {
            ImageIcon end = new ImageIcon("res\\gameover.png");
            graficos.drawImage(end.getImage(), 0, 0, null);
        }
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(inimigos.size() == 0){
           emJogo = false; 
        }
        
        List<Municao> tiros = nave.getMunicao();
        
        for(int i = 0; i < tiros.size(); i++ )
        {
            Municao m = (Municao)tiros.get(i);
            
            if(m.isIsVisible())
            {
                m.movimentacaoTiro();
            }
            else
            {
                tiros.remove(i);
            }
        }
        
        for(int i = 0; i < inimigos.size(); i++ )
        {
            Inimigo in = inimigos.get(i);
            
            if(in.isIsVisible())
            {
                in.movimentacaoInimigo();
            }
            else
            {
                inimigos.remove(i);
            }
        }
        
        nave.movimentacao();
        checarColisoes();
        repaint();
    }
    
       
    public void checarColisoes(){
        
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMunicao;
        
        for(int i = 0; i < inimigos.size(); i++){
            
            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();
            
            if(formaNave.intersects(formaInimigo)){
                nave.setIsVisible(false);
                tempInimigo.setIsVisible(false);
                emJogo = false;
            }
            
        }
        
        List<Municao> tiros = nave.getMunicao();
        
        for (int i = 0; i < tiros.size(); i++) {
            
           Municao tempMunicao = tiros.get(i);
           formaMunicao = tempMunicao.getBounds();
           
            for (int j = 0; j < inimigos.size(); j++) {
                Inimigo tempInimigo = inimigos.get(j);
                formaInimigo = tempInimigo.getBounds();
                
                if(formaMunicao.intersects(formaInimigo)){
                    tempInimigo.setIsVisible(false);
                    tempMunicao.setIsVisible(false);
                }
            }
           
        }
        
    }
    
    public void Reset(){
        this.inimigos= new ArrayList<>();
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(new TecladoAdapter());
        
        Inimigo.VELOCIDADE = 1;
        ImageIcon referencia = new ImageIcon("res\\fundo.jpg");
        fundo = referencia.getImage();
        nave = new Nave();
        
        emJogo = true;   
        
        inicializaInimigos();
        
        timer = new Timer(3, this);
        timer.start();
       }
       
    
    
    private class TecladoAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            nave.keyPressed(e);
       if(e.getKeyCode() == KeyEvent.VK_ENTER){
          Reset();
        }
        }
        @Override
        public void keyReleased(KeyEvent e){
            nave.keyReleased(e);
        }
    }
    
}
