public class CamaraJugador {
    public float x      = 0.0f;
    public float z      = 230.0f;
    public float angulo = 0.0f;
    public float y      = 8.0f;
    public float targetY    = 8.0f;
    public  int   pisoActual = 0;

    private final float ALTURA_PISO;

    public CamaraJugador(float alturaPiso) {
        this.ALTURA_PISO = alturaPiso;
    }

    public void actualizarAltura() {
        float velocidadSubida = 0.8f;
        if (y < targetY) {
            y += velocidadSubida;
            if (y > targetY) y = targetY;
        } else if (y > targetY) {
            y -= velocidadSubida;
            if (y < targetY) y = targetY;
        }
    }

    public void subirPiso() {
        if (pisoActual < 2) {
            pisoActual++;
            targetY = 8.0f + (pisoActual * ALTURA_PISO);
        }
    }

    public void bajarPiso() {
        if (pisoActual > 0) {
            pisoActual--;
            targetY = 8.0f + (pisoActual * ALTURA_PISO);
        }
    }
}