/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racegame;


import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 *
 * @author Andrés
 */
public class RaceGame extends JFrame implements KeyListener, ActionListener {
    
    private int xpos=300; //x posicion del car
    private int ypos=700; //y positcion del carro
    private ImageIcon car, car1, car2, car3; // imagen del carro
    
    Random random= new Random(); //generador de numeros aleatorios
   
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private int num1=400; //x posicion de los obstaculos
    private int tree1ypos=100, tree2ypos=-100, tree3ypos=-300, tree4ypos=200, tree5ypos=-200, tree6ypos=400;
    private int roadmove=0; // y posicion de la carretera
    private int carxpos[]={100,200,300,400,500}; //x posiciones
    private int carypos[]={-240,-480,-720,-960,-1200}; //y posicion
    private int cxpos1=0, cxpos2=2, cxpos3=4; // x posiciones del carro
    private int cypos1=random.nextInt(5),cypos2=random.nextInt(5), cypos3=random.nextInt(5); //y posiciones
    // posicion y
    int y1pos=carypos[cypos1];
    int y2pos=carypos[cypos2];
    int y3pos=carypos[cypos3];// actual y psoiciones del carro
    // posicion x
    int x1pos=carxpos[cxpos1];
    int x2pos=carxpos[cxpos2];
    int x3pos=carxpos[cxpos3];
    
    int score=0; //Record por defecto
    int delay=100;// el retraso mueve los carros paso a paso
    int speed=90; // Velocidad del carro
    private ImageIcon tree1, tree2, tree3;//tree images
    
    private boolean gameover=false, paint=false;// la logica del juego
    private boolean moveLeft = false; // Variable para controlar el movimiento a la izquierda
    private boolean moveRight = false; // Variable para controlar el movimiento a la derecha
    

    
    //constructor para inicializar el juego
    
    public RaceGame(String title){
        super(title); // El constructor de la clase madre, el cual ubicará el título del frame
        setBounds(300, 10, 700, 700);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Crea el Layout nulo de la Jframe
        setFocusable(true); // Establecer foco de concentración
        setResizable(false); // Este frame no es de tamaño modificable
        addKeyListener(this);
        offScreenImage = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB); // Inicializar offScreenImage
        initDoubleBuffer();

        // Añadir un WindowListener para manejar el evento de cierre de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false); // Hacer que la ventana solo se haga invisible
            }
        });
        
    }
    
    
    
    // metodo paint para mostarat los graphics  en la pantalla
    @Override
    public void paint(Graphics g){
        // Crear un nuevo offScreenGraphics
        Graphics offScreenGraphics = offScreenImage.getGraphics();

        // Dibujar el pasto
        Color grassGreen = new Color(51, 102, 0); // Un tono más oscuro de verde
        offScreenGraphics.setColor(grassGreen);
        offScreenGraphics.fillRect(0, 0, 700, 700);

        // Dibujar la carretera
        offScreenGraphics.setColor(Color.gray);
        offScreenGraphics.fillRect(90, 0, 10, 700);
        offScreenGraphics.fillRect(600, 0, 10, 700);
        offScreenGraphics.fillRect(100, 0, 500, 700);

        // Dibujar las líneas de la carretera
        if(roadmove==0){
            for(int i=0;i<=700;i+=100){
                offScreenGraphics.setColor(Color.white);
                offScreenGraphics.fillRect(350, i, 10, 70);
            }
            roadmove=1;
        } else if(roadmove==1){
            for (int i=50;i<=700;i+=100){
                offScreenGraphics.setColor(Color.white);
                offScreenGraphics.fillRect(350, i, 10, 70);
            }
            roadmove=0;
        }

        // Actualizar las posiciones de los árboles
        updateTreePositions();

        // Dibujar los árboles
        drawTrees(offScreenGraphics);

        // Dibujar el offScreenImage en el Graphics de la pantalla
        g.drawImage(offScreenImage, 0, 0, this);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    

    // Método para actualizar las posiciones de los árboles
    private void updateTreePositions() {
        // Incrementar las posiciones y de los árboles basado en la velocidad del juego
        tree1ypos += 10; // Ajustar la velocidad según sea necesario
        tree2ypos += 10;
        tree3ypos += 10;
        tree4ypos += 10;
        tree5ypos += 10;
        tree6ypos += 10;

        // Verificar si los árboles han salido de la pantalla y reiniciar su posición
        if(tree1ypos > 700) {
            tree1ypos = -random.nextInt(200) - 200; // Reiniciar la posición y de tree1
        }
        if(tree2ypos > 700) {
           tree2ypos = -random.nextInt(200) - 200; // Reiniciar la posición y de tree1
        }
        if(tree2ypos > 700) {
           tree2ypos = -random.nextInt(200) - 200; // Reiniciar la posición y de tree1
        }
        if(tree3ypos > 700) {
           tree3ypos = -random.nextInt(200) - 200; // Reiniciar la posición y de tree1
        }
        if(tree4ypos > 700) {
           tree4ypos = -random.nextInt(200) - 200; // Reiniciar la posición y de tree1
        }
        if(tree5ypos > 700) {
           tree5ypos = -random.nextInt(200) - 200; // Reiniciar la posición y de tree1
        }
        if(tree6ypos > 700) {
           tree6ypos = -random.nextInt(200) - 200; // Reiniciar la posición y de tree1
        }
    }

    // Método para dibujar los árboles en la pantalla
    private void drawTrees(Graphics g) {
        try {
            tree1 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/A4.png")).getScaledInstance(140, 140, Image.SCALE_DEFAULT));
            tree2 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/A1.png")).getScaledInstance(140, 140, Image.SCALE_DEFAULT));
            tree3 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/a3.png")).getScaledInstance(140, 140, Image.SCALE_DEFAULT));
        } catch(IOException e){
            e.printStackTrace();
        }

        // Dibujar los árboles en sus posiciones actuales
        tree1.paintIcon(this, g, 0, tree1ypos);
        tree2.paintIcon(this, g, 0, tree2ypos);
        tree3.paintIcon(this, g, 0, tree3ypos);
        tree1.paintIcon(this, g ,600, tree4ypos);
        tree2.paintIcon(this, g ,600, tree5ypos);
        tree3.paintIcon(this, g ,600, tree6ypos);

        
       //cargar imagen para el carro
        try {
            car = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/c1.png")).getScaledInstance(100, 200, Image.SCALE_DEFAULT)); //Cargar imagen para el carro
        } catch (IOException e) {
            e.printStackTrace();
        }
        car.paintIcon(this, g, xpos, ypos);// dibuja el carro en la pantalla
        ypos -= 80;
        if (ypos < 500) {
            ypos = 500;
        }
        //Cargar los otros carros
        try {
            car1 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/c2.png")).getScaledInstance(100, 200, Image.SCALE_DEFAULT));
            car2 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/c3.png")).getScaledInstance(100, 200, Image.SCALE_DEFAULT));
            car3 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/c4.png")).getScaledInstance(100, 200, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }

        car1.paintIcon(this, g, x1pos, y1pos);
        car2.paintIcon(this, g, x2pos, y2pos);
        car3.paintIcon(this, g, x3pos, y3pos);
        y1pos+=50;
        y2pos+=50;
        y3pos+=50;
        // Movimiento del carro fuera de la pantalla y resetear la posicion
        if(y1pos>700){
            cxpos1=random.nextInt(5);
            cypos1=random.nextInt(5);
            y1pos=carypos[cypos1];//reseta la posicion y del primero carro en competencia   
        }
        if(y2pos>700){
            cxpos2++;
            if(cxpos2>4){
                cxpos2=0;
            }
            cxpos2=random.nextInt(5);
            cypos2=random.nextInt(5);
            y2pos=carypos[cypos2];
        }
        if(y3pos>700){
            cxpos3++;
            if(cxpos3>4){
                cxpos3=0;
            }
            cxpos3=random.nextInt(5);
            cypos2=random.nextInt(5);
            y3pos=carypos[cypos3];
        }
        
        if(cxpos1==cxpos2 && cypos1>-100 && cypos2>-100){
            cxpos1-=1;
            if(cxpos1<0){
                cxpos1+=2;  
            }
        }
        if(cxpos1==cxpos3 && cypos1>-100 && cypos3>-100){
            cxpos3-=1;
            if(cxpos3<0){
                cxpos3+=2;  
            }
        }
        if(cxpos2==cxpos3 && cypos2>-100 && cypos3>-100){
            cxpos2-=1;
            if(cxpos2<0){
                cxpos2+=2;  
            }
        }
        // mas logica
        if(cxpos1<2 && cxpos2<2 && cxpos3<2){
            if(cxpos1==0 && cxpos2==0 && cxpos3==1){
                cxpos3++;
                cxpos2++;
            }
            else if(cxpos1==0 && cxpos2==1 && cxpos3==0){
                cxpos3++;
                cxpos2++;
            }
             else if(cxpos1==1 && cxpos2==0 && cxpos3==0){
                cxpos1++;
                cxpos2++;
            }
        }
        
        // Logica para gameover
        if(y1pos<ypos && y1pos+175 >ypos && x1pos==xpos){
            gameover=true;
        }
         if(y2pos<ypos && y2pos+175 >ypos && x2pos==xpos){
            gameover=true;
        }
          if(y3pos<ypos && y3pos+175 >ypos && x3pos==xpos){
            gameover=true;
        }
           if(ypos<y1pos && y1pos+175 >y1pos && x1pos==xpos){
            gameover=true;
        }
           if(ypos<y2pos && y2pos+175 >y2pos && x2pos==xpos){
           gameover=true;
        }
           if(ypos<y3pos && y3pos+175 >ypos && x3pos==xpos){
           gameover=true;
        }
           if(gameover){
            g.setColor(Color.black);
            g.fillRect(120, 210, 460, 200);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial narrow", Font.BOLD, 50));
            String gameOverText = "Game Over";
            int gameOverTextWidth = g.getFontMetrics().stringWidth(gameOverText);
            int xGameOver = 120 + (460 - gameOverTextWidth) / 2; // Centrar horizontalmente
            g.setColor(Color.red);
            g.drawString(gameOverText, xGameOver, 270);

            g.setColor(Color.white);
            g.setFont(new Font("Arial narrow", Font.BOLD, 30));
            String restartText = "Press Enter to Restart";
            int restartTextWidth = g.getFontMetrics().stringWidth(restartText);
            int xRestart = 120 + (460 - restartTextWidth) / 2; // Centrar horizontalmente
            g.drawString(restartText, xRestart, 340);

            if(!paint){
                repaint();
                paint=true;
            }
        }
        else{
            repaint();
        }
        //Score
        g.setColor(Color.red); //Borde del rectangulo
        g.fillRect(120, 35, 220, 50);
        g.setColor(Color.black);
        g.fillRect(125, 40, 210, 40); //Actual rectangulo, donde se muestra el score
        //velocidad
        g.setColor(Color.red);
        g.fillRect(385, 35, 180, 50);
        g.setColor(Color.black);
        g.fillRect(390, 40, 170, 40);
        g.setColor(Color.white);
        g.setFont(new Font("Arial Narrow", Font.BOLD, 30));
        g.drawString("Score: "+score, 130, 67);
        g.drawString(speed+" Km/h", 400, 67);
        score++;
        speed++;
        if(speed>140){
            speed=240-delay;
        }
        if(score%50==0){
            delay-=10;
            if(delay<60){
                delay=60; //Reseteear retraso a 60
            }
        }
        try{
            TimeUnit.MICROSECONDS.sleep(delay);
        }catch(InterruptedException e){
            e.printStackTrace();;
        }
        
    }    
    
     private void initDoubleBuffer() {
        offScreenImage = createImage(getWidth(), getHeight());
        offScreenGraphics = offScreenImage.getGraphics();
    }
     
    private void drawDoubleBuffered() {
        if (offScreenImage == null) {
            initDoubleBuffer();
        }

        // Limpiar el búfer
        offScreenGraphics.clearRect(0, 0, getWidth(), getHeight());
        // Dibujar en el búfer
        paint(offScreenGraphics);
        // Obtener el Graphics de la pantalla
        Graphics g = getGraphics();
        if (g != null && offScreenImage != null) {
            // Dibujar el contenido del búfer en la pantalla
            g.drawImage(offScreenImage, 0, 0, null);
            // Liberar el recurso gráfico
            g.dispose();
        }
        // Repintar la pantalla con el contenido del búfer
        repaint();
    }
    

    @Override
    public void keyTyped(KeyEvent e) { 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !gameover) {
            // Si la tecla izquierda es presionada, mover el carro a la posición izquierda
            xpos -= 50;
            if (xpos < 100) {
                xpos = 100; // Llevar el carro a una posición izquierda
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !gameover) {
            // Si la tecla derecha es presionada, mover el carro a la posición derecha
            xpos += 50;
            if (xpos > 500) {
                xpos = 500; // Llevar el carro a una posición derecha
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gameover) {
                // Si el juego ha terminado y se presiona Enter, reiniciar el juego
                gameover = false;
                paint = false;
                cxpos1 = 0;
                cxpos2 = 2;
                cxpos3 = 4;
                cypos1 = random.nextInt(5);
                cypos2 = random.nextInt(5);
                cypos3 = random.nextInt(5);
                y1pos = carypos[cypos1];
                y2pos = carypos[cypos2];
                y3pos = carypos[cypos3];
                x1pos = carxpos[cxpos1];
                x2pos = carxpos[cxpos2];
                x3pos = carxpos[cxpos3];
                speed = 90;
                score = 0;
                delay = 100;
                xpos = 300;
                ypos = 700;
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawDoubleBuffered();
    }
    
     @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            setVisible(false); // Hacer que la ventana solo se haga invisible
        } else {
            super.processWindowEvent(e);
        }
    }
    
}




    
    
    
    
    

