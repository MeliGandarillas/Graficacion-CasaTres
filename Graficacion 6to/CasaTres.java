import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CasaTres extends JFrame {

    private GLJPanel      glPanel;
    private FPSAnimator   animator;
    private CasaRenderer  renderer;

    public CasaTres() {
        setTitle("Casa 3 Pisos - Vista Panorámica y Modo Jugador");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        GLProfile      profile      = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        capabilities.setSampleBuffers(true);
        capabilities.setNumSamples(8);

        glPanel  = new GLJPanel(capabilities);
        renderer = new CasaRenderer();
        glPanel.addGLEventListener(renderer);
        glPanel.setFocusable(true);

        glPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int tecla = e.getKeyCode();
                
                // ELEVADOR: Solo funciona si la tecla NO estaba presionada antes
                if (tecla == KeyEvent.VK_E && !renderer.teclasPresionadas.contains(tecla)) {
                    renderer.jugador.subirPiso();
                }
                if (tecla == KeyEvent.VK_Q && !renderer.teclasPresionadas.contains(tecla)) {
                    renderer.jugador.bajarPiso();
                }
                // VISUALIZADOR DE COLISIONES
                if (tecla == KeyEvent.VK_V && !renderer.teclasPresionadas.contains(tecla)) {
                    renderer.verColisiones = !renderer.verColisiones;
                }
                
                // MODO JUGADOR / CÁMARA LIBRE
                if (tecla == KeyEvent.VK_P && !renderer.teclasPresionadas.contains(tecla)) {
                    renderer.modoJugador = !renderer.modoJugador;
                }

                // Guardamos la tecla para movimiento (W, A, S, D) y para bloquear repetición
                renderer.teclasPresionadas.add(tecla);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Liberamos la tecla para que pueda volver a usarse
                renderer.teclasPresionadas.remove(e.getKeyCode());
            }
        });

        final int[] lastMousePos = new int[2];

        glPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMousePos[0] = e.getX();
                lastMousePos[1] = e.getY();
            }
        });

        glPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!renderer.modoJugador) {
                    renderer.rotY += (e.getX() - lastMousePos[0]) * 0.5f;
                    renderer.rotX += (e.getY() - lastMousePos[1]) * 0.5f;

                    if (renderer.rotX < 10.0f) renderer.rotX = 10.0f;
                    if (renderer.rotX > 85.0f) renderer.rotX = 85.0f;
                } else {
                    renderer.jugador.angulo -= (e.getX() - lastMousePos[0]) * 0.4f;
                    renderer.lookY -= (e.getY() - lastMousePos[1]) * 0.04f;

                    if (renderer.lookY < -3.0f) renderer.lookY = -3.0f;
                    if (renderer.lookY >  3.0f) renderer.lookY =  3.0f;
                }
                lastMousePos[0] = e.getX();
                lastMousePos[1] = e.getY();
            }
        });

        glPanel.addMouseWheelListener(e -> {
            if (!renderer.modoJugador) {
                renderer.distCamara += e.getWheelRotation() * 10.0f;
                if (renderer.distCamara <  50.0f) renderer.distCamara =  50.0f;
                if (renderer.distCamara > 600.0f) renderer.distCamara = 600.0f;
            }
        });

        add(glPanel, BorderLayout.CENTER);
        animator = new FPSAnimator(glPanel, 60, true);
        animator.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CasaTres().setVisible(true));
    }
}