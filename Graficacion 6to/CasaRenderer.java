import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CasaRenderer implements GLEventListener {

    private final GLU  glu  = new GLU();
    private final GLUT glut = new GLUT();

    public boolean modoJugador = false;
    public float lookY = 0.0f;

    public final CamaraJugador  jugador = new CamaraJugador(25.0f);
    private final EstructuraCasa casa   = new EstructuraCasa(200.0f, 500.0f, 20.0f, 1.5f);

    public final Set<Integer> teclasPresionadas = new HashSet<>();
    private final List<ColisionObjeto> objetosColision = new ArrayList<>();

    public float rotX      =  60.0f;
    public float rotY      =   0.0f;
    public float distCamara= 220.0f;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glEnable(GL2.GL_NORMALIZE);

        float[] ambient  = { 0.4f, 0.4f, 0.45f, 1.0f };
        float[] diffuse  = { 0.9f, 0.9f, 0.85f, 1.0f };
        float[] lightPos = { 0f, 150f, 50f, 1.0f };

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT,  ambient,  0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE,  diffuse,  0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos, 0);
        
        objetosColision.add(new ColisionObjeto(95f, 200f, 8f, 78f));   // Fuente
        objetosColision.add(new ColisionObjeto(-55f, 200f, 45f, 75f)); // Carro
    }

    private void dibujarJugador(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glTranslatef(jugador.x, jugador.y, jugador.z);
        gl.glRotatef(jugador.angulo, 0.0f, 1.0f, 0.0f);
        gl.glColor3f(0.1f, 0.2f, 1.0f);
        gl.glScalef(8.0f, 12.0f, 8.0f);
        glut.glutSolidCube(1.0f);
        gl.glPopMatrix();
    }

    private boolean hayColisionTotal(float x, float z) {
        float radioJugador = 5.0f;
        if (casa.hayColisionConMuros(x, z, jugador.pisoActual, radioJugador)) {
            return true;
        }
        for (ColisionObjeto obj : objetosColision) {
            if (obj.colisiona(x, z, radioJugador)) return true;
        }
        return false;
    }
    
    private void actualizarJugadorConColisiones() {
        float velocidad = modoJugador ? 1.0f : 3.0f;
        float anguloMovimiento = modoJugador ? jugador.angulo : -rotY;
        float rad = (float) Math.toRadians(anguloMovimiento);

        float movX = 0.0f;
        float movZ = 0.0f;

        if (teclasPresionadas.contains(KeyEvent.VK_W)) {
            movX += Math.sin(rad) * velocidad; movZ -= Math.cos(rad) * velocidad;
        }
        if (teclasPresionadas.contains(KeyEvent.VK_S)) {
            movX -= Math.sin(rad) * velocidad; movZ += Math.cos(rad) * velocidad;
        }
        if (teclasPresionadas.contains(KeyEvent.VK_A)) {
            movX -= Math.cos(rad) * velocidad; movZ -= Math.sin(rad) * velocidad;
        }
        if (teclasPresionadas.contains(KeyEvent.VK_D)) {
            movX += Math.cos(rad) * velocidad; movZ += Math.sin(rad) * velocidad;
        }

        if (!hayColisionTotal(jugador.x + movX, jugador.z)) jugador.x += movX;
        if (!hayColisionTotal(jugador.x, jugador.z + movZ)) jugador.z += movZ;
        
        jugador.actualizarAltura();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.6f, 0.8f, 1.0f, 1.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        actualizarJugadorConColisiones();

        if (modoJugador) {
            float rad = (float) Math.toRadians(jugador.angulo);
            float camX = jugador.x;
            float camY = jugador.y + 6.0f;
            float camZ = jugador.z;

            float miraX = camX + (float) Math.sin(rad);
            float miraY = camY + lookY;
            float miraZ = camZ - (float) Math.cos(rad);

            glu.gluLookAt(camX, camY, camZ, miraX, miraY, miraZ, 0.0f, 1.0f, 0.0f);
        } else {
            gl.glTranslatef(0.0f, 0.0f, -distCamara);
            gl.glRotatef(rotX, 1.0f, 0.0f, 0.0f);
            gl.glRotatef(rotY, 0.0f, 1.0f, 0.0f);
            gl.glTranslatef(-jugador.x, -jugador.y, -jugador.z);
        }

        casa.dibujar(gl, glut, jugador.pisoActual);

        if (!modoJugador) dibujarJugador(gl, glut);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        new GLU().gluPerspective(60.0, (float) width / height, 0.5, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override public void dispose(GLAutoDrawable drawable) {}
}