import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Muebles {

    private final Map<String, Texture> texturas = new HashMap<>();
    private boolean texturasCargadas = false;
    private final String RUTA_TEXTURAS = "C:\\Users\\ganda_lu02tcb\\Downloads\\Graficacion 6to\\texturas";

    public void cargarTexturas(GL2 gl) {
        if (texturasCargadas) return;
        try {
            texturas.put("arbusto", TextureIO.newTexture(new File(RUTA_TEXTURAS + "\\ParedArbusto.jpg"), true));
            texturas.put("fuente", TextureIO.newTexture(new File(RUTA_TEXTURAS + "\\FuentePared.jpg"), true));
            texturas.put("metalNegro", TextureIO.newTexture(new File(RUTA_TEXTURAS + "\\metalNegro.jpg"), true));
            texturas.put("cristal", TextureIO.newTexture(new File(RUTA_TEXTURAS + "\\cristal.jpg"), true));
            texturas.put("madera", TextureIO.newTexture(new File(RUTA_TEXTURAS + "\\madera.jpg"), true));
        } catch (IOException e) {
            System.out.println("Error cargando texturas: " + e.getMessage());
        }
        texturasCargadas = true;
    }

    private void cuboTexturizado(GL2 gl, Texture textura, float x, float y, float z, float sx, float sy, float sz) {
        if (textura == null) return;
        gl.glEnable(GL2.GL_TEXTURE_2D);
        textura.enable(gl);
        textura.bind(gl);

        gl.glPushMatrix();
            gl.glTranslatef(x, y, z);
            gl.glScalef(sx, sy, sz);
            gl.glColor3f(1f, 1f, 1f);
            gl.glBegin(GL2.GL_QUADS);
            
            // Frente
            gl.glNormal3f(0, 0, 1);
            gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, 0.5f);
            gl.glTexCoord2f(1, 0); gl.glVertex3f( 0.5f, -0.5f, 0.5f);
            gl.glTexCoord2f(1, 1); gl.glVertex3f( 0.5f,  0.5f, 0.5f);
            gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f,  0.5f, 0.5f);
            // Atrás
            gl.glNormal3f(0, 0, -1);
            gl.glTexCoord2f(0, 0); gl.glVertex3f( 0.5f, -0.5f, -0.5f);
            gl.glTexCoord2f(1, 0); gl.glVertex3f(-0.5f, -0.5f, -0.5f);
            gl.glTexCoord2f(1, 1); gl.glVertex3f(-0.5f,  0.5f, -0.5f);
            gl.glTexCoord2f(0, 1); gl.glVertex3f( 0.5f,  0.5f, -0.5f);
            // Derecha
            gl.glNormal3f(1, 0, 0);
            gl.glTexCoord2f(0, 0); gl.glVertex3f(0.5f, -0.5f,  0.5f);
            gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, -0.5f, -0.5f);
            gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f,  0.5f, -0.5f);
            gl.glTexCoord2f(0, 1); gl.glVertex3f(0.5f,  0.5f,  0.5f);
            // Izquierda
            gl.glNormal3f(-1, 0, 0);
            gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, -0.5f);
            gl.glTexCoord2f(1, 0); gl.glVertex3f(-0.5f, -0.5f,  0.5f);
            gl.glTexCoord2f(1, 1); gl.glVertex3f(-0.5f,  0.5f,  0.5f);
            gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f,  0.5f, -0.5f);
            // Arriba
            gl.glNormal3f(0, 1, 0);
            gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, 0.5f,  0.5f);
            gl.glTexCoord2f(1, 0); gl.glVertex3f( 0.5f, 0.5f,  0.5f);
            gl.glTexCoord2f(1, 1); gl.glVertex3f( 0.5f, 0.5f, -0.5f);
            gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, 0.5f, -0.5f);
            // Abajo
            gl.glNormal3f(0, -1, 0);
            gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, -0.5f);
            gl.glTexCoord2f(1, 0); gl.glVertex3f( 0.5f, -0.5f, -0.5f);
            gl.glTexCoord2f(1, 1); gl.glVertex3f( 0.5f, -0.5f,  0.5f);
            gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, -0.5f,  0.5f);
            gl.glEnd();
        gl.glPopMatrix();

        textura.disable(gl);
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }

    public void dibujarTodos(GL2 gl, GLUT glut) {
        cargarTexturas(gl);

        // FUENTE
        gl.glPushMatrix();
            gl.glTranslatef(95f, 0f, 200f);
            gl.glRotatef(90f, 0f, 1f, 0f);
            cuboTexturizado(gl, texturas.get("arbusto"), 0f, 11f, 0f, 78f, 22f, 2f);
            cuboTexturizado(gl, texturas.get("fuente"), 0f, 11f, -2f, 42f, 18f, 2f);
            cuboTexturizado(gl, texturas.get("metalNegro"), 0f, 1f, -5f, 48f, 2f, 8f);
        gl.glPopMatrix();

        // CARRO
        float xC = -55f, yC = 0f, zC = 200f;
        gl.glColor3f(0.85f, 0.05f, 0.03f);
        gl.glPushMatrix();
            gl.glTranslatef(xC, yC + 4f, zC);
            gl.glScalef(45f, 8f, 75f);
            glut.glutSolidCube(1.0f);
        gl.glPopMatrix();

        gl.glColor3f(0.75f, 0.02f, 0.02f);
        gl.glPushMatrix();
            gl.glTranslatef(xC, yC + 12f, zC - 5f);
            gl.glScalef(32f, 10f, 35f);
            glut.glutSolidCube(1.0f);
        gl.glPopMatrix();

        cuboTexturizado(gl, texturas.get("cristal"), xC, yC + 15f, zC - 25f, 24f, 5f, 2f);

        gl.glColor3f(0.02f, 0.02f, 0.02f);
        float[][] llantas = {{-24f, -25f}, {24f, -25f}, {-24f, 25f}, {24f, 25f}};
        for (float[] l : llantas) {
            gl.glPushMatrix();
                gl.glTranslatef(xC + l[0], yC + 3f, zC + l[1]);
                gl.glRotatef(90f, 0, 1, 0);
                glut.glutSolidTorus(2.0f, 5.0f, 12, 24);
            gl.glPopMatrix();
        }
    }
}