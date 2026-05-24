import java.util.ArrayList;

public class PlanosCasa {

    public static Nivel construirPlantaBaja() {
        Nivel n = new Nivel();
        
        // ==========================================
        // PLANTA BAJA
        // ==========================================
        
        // 1. PAREDES EXTERIORES (Perímetro)
        n.muros.add(new Muro(-180f,  250f,  10f,  250f));  // Frontal izquierda
        n.muros.add(new Muro(50f,  250f,  180f,  250f));  // Frontal derecha
        n.muros.add(new Muro(-100f, -200f,  100f, -250f));  // Trasera diagonal
        n.muros.add(new Muro(-100f,  250f, -100f, -200f));  // Izquierda
        n.muros.add(new Muro( 100f,  250f,  100f, -250f));  // Derecha

        // 2. PAREDES INTERIORES (Planta Baja)
        // ENTRADA Y FUENTE
        n.muros.add(new Muro(10f, 250f, 10f, 225f)); // izquierda
        n.muros.add(new Muro(50f, 250f, 50f, 225f)); // derecha
        n.muros.add(new Muro(40f, 160f, 100f, 160f)); // pared inferior
        n.muros.add(new Muro(40f,  225f,  50f,  225f)); //bodoque frontal junto a puerta
        // OFICINA / ESCRITORIO
        n.muros.add(new Muro(-100f, 160f, -25f, 160f)); // pared inferior
        n.muros.add(new Muro(10f, 160f, -5f,  130f)); // pared diagonal entrada
        n.muros.add(new Muro(-20f,  130f, -5f,  130f));//bodoque derecho
        n.muros.add(new Muro(-100f,  80f, -20f,  80f)); // pared superior
        n.muros.add(new Muro(-20f,  130f, -20f,  0f)); // pared derecha tmb de cocina y comedor
        // COMEDOR Y COCINA
        n.muros.add(new Muro(-100f, -70f, -20f, -70f)); // pared superior junto a baño
        //baño junto a comedor
        n.muros.add(new Muro(-100f, -105f, -20f, -105f)); // pared superior junto a recamara
        n.muros.add(new Muro(-20f,  -85f, -20f,  -65f)); //derecha junto a comedor
        // RECÁMARA Planta baja
        n.muros.add(new Muro(-20f, -145f, -20f, -200f)); // pared derecha arriba
        n.muros.add(new Muro(-20f, -105f, -20f, -120f)); // pared derecha junto a armario
        n.muros.add(new Muro(-100f, -200f, -20f, -200f)); // pared superior junto a patio
        // SALA DE ESTAR
        n.muros.add(new Muro(30f,  80f, 100f,  80f)); // pared inferior
        // ESCALERAS Y BAÑO
        n.muros.add(new Muro(30f,  45f, 100f,  45f));
        n.muros.add(new Muro(30f,  10f, 100f,  10f));
        // GYM
        n.muros.add(new Muro(30f,  -70f, 40f,  -70f)); // pared inferior izq junto a fuente doble altura
        n.muros.add(new Muro(70f,  -70f, 100f,  -70f)); // pared inferior der junto a fuente doble altura
        n.muros.add(new Muro( 30f,  -125f,  30f, -200f)); // pared izquierda
        n.muros.add(new Muro( 30f, -200f, 100f, -200f)); // pared superior
        n.muros.add(new Muro(30f,  -70f, 30f,  -100f)); //izq junto a pasillo y fuente doble altura

        // SUELO PISO 0: La planta baja suele ser completamente sólida
        n.losas.add(new Losa(-100f, 250f, 100f, -250f)); // Cubre todo el perímetro estándar
        return n;
    }

    public static Nivel construirSegundaPlanta() {
        Nivel n = new Nivel();
        
        // ==========================================
        // SEGUNDA PLANTA
        // ==========================================
        
        // 1. PAREDES EXTERIORES
        n.muros.add(new Muro(-20f, -200f,  -20f, -220f));     //bodoque enmedio
        n.muros.add(new Muro(-20f, -220f,  100f, -250f));  // Trasera diagonal
        n.muros.add(new Muro(-100f, -200f,  -20f, -200f));    //trasera habitacion 
        n.muros.add(new Muro(-100f,  235f, -100f, 35f));  // Izquierda superior
        n.muros.add(new Muro(-100f,  10f, -100f, -200f));  // Izquierda inferior
        //HACIA ENFRENTE DE LA CASA
        n.muros.add(new Muro(60f,  225f,  -30f,  225f)); // Frontal derecha balcón
        n.muros.add(new Muro(-100f,  235f,  -70f,  235f));  // Frontal izquierda balcón
        n.muros.add(new Muro( 60f,  160f,  60f, 225f)); //derecha entre senior y fuente

        n.muros.add(new Muro( 100f,  160f,  100f, -145f));  // Derecha mas larga
        n.muros.add(new Muro( 60f,  160f,  100f, 160f));//bodoque entre derechas junto a baño SENIOR
        
        n.muros.add(new Muro( 50f,  -170f,  50f, -235f)); // hacia balcón en area de lavado
        //balcones//
        n.muros.add(new Muro( 100f, -145f,  100f, -250f).aMitad()); //tendedero derecho
        n.muros.add(new Muro( 100f, 155f,  100f, 250f).aMitad()); //frontal derecho junto a torre
        n.muros.add(new Muro( -100f, 10f,  -100f, 35f).aMitad()); //izquierdo junto a macetas
        //DETALLES DE BALCON 
        n.muros.add(new Muro(-30f, 235f,  -30f, 225f)); //bodoque entre balcon y recamara senior
        n.muros.add(new Muro(50f,  250f,  100f,  250f));  // Frontal derecha torre
        n.muros.add(new Muro(50f, 225f,  50f, 250f));  // bodoque torre junto a entrada principal

        //PRIMERA RECAMARA
        n.muros.add(new Muro(-20f, -145f, -20f, -200f)); // pared derecha arriba
        n.muros.add(new Muro(-20f, -105f, -20f, -120f)); // pared derecha junto a armario
        n.muros.add(new Muro(-100f, -105f, -20f, -105f)); // pared inferior junto a baño
        n.muros.add(new Muro(-20f, -145f, -15f, -145f)); //bodoque junto a cuarto de lavado
        //SEGUNDA RECAMARA
        n.muros.add(new Muro(30f,  -70f, 100f,  -70f)); //pared inferior junto a patio doble altura
        n.muros.add(new Muro(20f, -145f, 100f, -145f)); //pared superior junto a lavabo
        n.muros.add(new Muro(30f, -70f, 30f, -120f)); //pared izquierda junto a pasillo
        //BAÑO 
        n.muros.add(new Muro(-100f, -70f, -20f, -70f)); // pared superior junto a recamara
        n.muros.add(new Muro(-20f,  -85f, -20f,  -70f)); //derecha junto a comedor
        //SALA Y MACETAS
        n.muros.add(new Muro(-100f,  10f, -10f,  10f)); // superior junto a sala de estar
        n.muros.add(new Muro(-100f,  35f, -10f,  35f)); //inferior junto a tercera recamara
        //TERCERA RECAMARA
        n.muros.add(new Muro(-100f,  155f, -20f,  155f)); //inferior junto a senior
        n.muros.add(new Muro(-10f,  35f, -10f,  45f)); //derecha corta superior
        n.muros.add(new Muro(-10f,  70f, -10f,  100f)); //derecha enmedio de derechas
        n.muros.add(new Muro(-20f,  100f, -10f,  100f)); //pared inferior bodoque
        n.muros.add(new Muro(-20f,  155f, -20f,  100f)); //derecha larga inferior
        //ARMARIOS
        n.muros.add(new Muro(30f,  80f, 100f,  80f)); // inferior junto a escaleras
        n.muros.add(new Muro(30f,  80f, 30f,  95f)); //izquierda primera
        n.muros.add(new Muro(30f,  110f, 30f,  140f)); //izquierda segunda comaprte con baño
        //BAÑO SENIOR
        n.muros.add(new Muro(30f,  125f, 100f,  125f)); //superior junto a armarios  -------- mover si no cabe BAÑERA
        n.muros.add(new Muro(30f,  155f, 30f,  180f)); //izquierda junto a pasillo
        n.muros.add(new Muro(30f,  180f, 60f,  180f)); //INFERIOR junto a SENIOR
        
        // SUELOS SEGUNDA PLANTA
        n.losas.add(new Losa(-100f, 250f, 10f, -200f));  // Toda la mitad izquierda de la casa de lado a lado
        n.losas.add(new Losa(10f, 225f, 30f, -200f)); // Franja central del pasillo
        n.losas.add(new Losa(30f, 160f, 100f, 45f));    // Parte delantera derecha (sala, escaleras)
        n.losas.add(new Losa(30f, 225f, 60f, 160f));    // PARTE delantera derecha fuente
        n.losas.add(new Losa(30f, -70f, 100f, -250f));  // Franja trasera derecha del patio trasero
        
        n.triangulos.add(new LosaTriangular(-100f, -200f,  100f, -200f,  100f, -250f));
        
        return n;
    }

    public static Nivel construirTerceraPlanta() {
        Nivel n = new Nivel();
        
        // ============================================================
        // TERCERA PLANTA
        // ============================================================
        
        // 1. PAREDES EXTERIORES (Todas convertidas a balcones con .aMitad())
        n.muros.add(new Muro(-20f, -200f,  -20f, -220f).aMitad());     // bodoque enmedio
        n.muros.add(new Muro(-20f, -220f,  30f, -233f).aMitad());  // Trasera diagonal
        n.muros.add(new Muro(-100f, -200f,  -20f, -200f).aMitad());    // trasera habitacion 
        n.muros.add(new Muro(-100f,  225f, -100f, 35f).aMitad());  // Izquierda superior
        n.muros.add(new Muro(-100f,  10f, -100f, -200f).aMitad());  // Izquierda inferior
        
        // HACIA ENFRENTE DE LA CASA
        n.muros.add(new Muro(-100f,  225f,  60f,  225f).aMitad()); // Frontal derecha balcón
        n.muros.add(new Muro( 60f,  160f,  60f, 225f).aMitad()); // derecha entre senior y fuente

        n.muros.add(new Muro( 100f,  160f,  100f, -145f).aMitad());  // Derecha mas larga
        n.muros.add(new Muro( 60f,  160f,  100f, 160f).aMitad()); // bodoque entre derechas junto a baño SENIOR
        
        n.muros.add(new Muro( 30f,  -200f,  30f, -233f).aMitad()); // derecha ultima junto a cuadro de mesa de eventos
        
        // SUELO TERCERA PLANTA
        // Se respetan los mismos huecos y doble altura del piso 2
        n.losas.add(new Losa(-100f, 235f, -30f, 225f)); //izquierda de balcón frontal
        n.losas.add(new Losa(-100f, 225f, 10f, 35f));  //izquierda segunda
        n.losas.add(new Losa(-100f, 10f, 10f, -200f));  //izquierda tercera
        
        n.losas.add(new Losa(10f, 225f, 30f, -200f));    // Franja central del pasillo
        n.losas.add(new Losa(30f, 225f, 60f, 160f));     // PARTE delantera derecha fuente (primera)
        n.losas.add(new Losa(30f, 160f, 100f, 80f));     // Parte delantera derecha (segunda)
        n.losas.add(new Losa(30f, -70f, 100f, -200f));   // Franja trasera derecha del patio trasero
        
        n.triangulos.add(new LosaTriangular(-100f, -200f,  50f, -200f,  50f, -238f));
        
        return n;
    }
}