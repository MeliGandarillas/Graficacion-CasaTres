import java.util.ArrayList;

public class PlanosCasa {

    public static Nivel construirPlantaBaja() {
        Nivel n = new Nivel();
        
        // 1. PAREDES EXTERIORES
        n.muros.add(new Muro(-180f,  250f,  10f,  250f));  
        n.muros.add(new Muro(50f,  250f,  180f,  250f));  
        n.muros.add(new Muro(-100f, -200f,  100f, -250f));  
        n.muros.add(new Muro(-100f,  250f, -100f, -200f));  
        n.muros.add(new Muro( 100f,  250f,  100f, -250f));  

        // 2. PAREDES INTERIORES
        n.muros.add(new Muro(10f, 250f, 10f, 225f)); 
        n.muros.add(new Muro(50f, 250f, 50f, 225f)); 
        n.muros.add(new Muro(40f, 160f, 100f, 160f)); 
        n.muros.add(new Muro(40f,  225f,  50f,  225f)); 
        n.muros.add(new Muro(-100f, 160f, -25f, 160f)); 
        n.muros.add(new Muro(10f, 160f, -5f,  130f)); 
        n.muros.add(new Muro(-20f,  130f, -5f,  130f));
        n.muros.add(new Muro(-100f,  80f, -20f,  80f)); 
        n.muros.add(new Muro(-20f,  130f, -20f,  0f)); 
        n.muros.add(new Muro(-100f, -70f, -20f, -70f)); 
        n.muros.add(new Muro(-100f, -105f, -20f, -105f)); 
        n.muros.add(new Muro(-20f,  -85f, -20f,  -65f)); 
        n.muros.add(new Muro(-20f, -145f, -20f, -200f)); 
        n.muros.add(new Muro(-20f, -105f, -20f, -120f)); 
        n.muros.add(new Muro(-100f, -200f, -20f, -200f)); 
        n.muros.add(new Muro(30f,  80f, 100f,  80f)); 
        n.muros.add(new Muro(30f,  45f, 100f,  45f));
        n.muros.add(new Muro(30f,  10f, 100f,  10f));
        n.muros.add(new Muro(30f,  -70f, 40f,  -70f)); 
        n.muros.add(new Muro(70f,  -70f, 100f,  -70f)); 
        n.muros.add(new Muro( 30f,  -125f,  30f, -200f)); 
        n.muros.add(new Muro( 30f, -200f, 100f, -200f)); 
        n.muros.add(new Muro(30f,  -70f, 30f,  -100f)); 

        // 3. SUELO
        n.losas.add(new Losa(-100f, 250f, 100f, -250f));
        return n;
    }

    public static Nivel construirSegundaPlanta() {
        Nivel n = new Nivel();
        
        n.muros.add(new Muro(-20f, -200f,  -20f, -220f));     
        n.muros.add(new Muro(-20f, -220f,  100f, -250f));  
        n.muros.add(new Muro(-100f, -200f,  -20f, -200f));    
        n.muros.add(new Muro(-100f,  235f, -100f, 35f));  
        n.muros.add(new Muro(-100f,  10f, -100f, -200f));  
        n.muros.add(new Muro(60f,  225f,  -30f,  225f)); 
        n.muros.add(new Muro(-100f,  235f,  -70f,  235f));  
        n.muros.add(new Muro( 60f,  160f,  60f, 225f)); 
        n.muros.add(new Muro( 100f,  160f,  100f, -145f));  
        n.muros.add(new Muro( 60f,  160f,  100f, 160f));
        n.muros.add(new Muro( 50f,  -170f,  50f, -235f)); 
        n.muros.add(new Muro( 100f, -145f,  100f, -250f).aMitad()); 
        n.muros.add(new Muro( 100f, 155f,  100f, 250f).aMitad()); 
        n.muros.add(new Muro( -100f, 10f,  -100f, 35f).aMitad()); 
        n.muros.add(new Muro(-30f, 235f,  -30f, 225f)); 
        n.muros.add(new Muro(50f,  250f,  100f,  250f));  
        n.muros.add(new Muro(50f, 225f,  50f, 250f));  
        n.muros.add(new Muro(-20f, -145f, -20f, -200f)); 
        n.muros.add(new Muro(-20f, -105f, -20f, -120f)); 
        n.muros.add(new Muro(-100f, -105f, -20f, -105f)); 
        n.muros.add(new Muro(-20f, -145f, -15f, -145f)); 
        n.muros.add(new Muro(30f,  -70f, 100f,  -70f)); 
        n.muros.add(new Muro(20f, -145f, 100f, -145f)); 
        n.muros.add(new Muro(30f, -70f, 30f, -120f)); 
        n.muros.add(new Muro(-100f, -70f, -20f, -70f)); 
        n.muros.add(new Muro(-20f,  -85f, -20f,  -70f)); 
        n.muros.add(new Muro(-100f,  10f, -10f,  10f)); 
        n.muros.add(new Muro(-100f,  35f, -10f,  35f)); 
        n.muros.add(new Muro(-100f,  155f, -20f,  155f)); 
        n.muros.add(new Muro(-10f,  35f, -10f,  45f)); 
        n.muros.add(new Muro(-10f,  70f, -10f,  100f)); 
        n.muros.add(new Muro(-20f,  100f, -10f,  100f)); 
        n.muros.add(new Muro(-20f,  155f, -20f,  100f)); 
        n.muros.add(new Muro(30f,  80f, 100f,  80f)); 
        n.muros.add(new Muro(30f,  80f, 30f,  95f)); 
        n.muros.add(new Muro(30f,  110f, 30f,  140f)); 
        n.muros.add(new Muro(30f,  125f, 100f,  125f)); 
        n.muros.add(new Muro(30f,  155f, 30f,  180f)); 
        n.muros.add(new Muro(30f,  180f, 60f,  180f)); 
        
        n.losas.add(new Losa(-100f, 250f, 10f, -200f));  
        n.losas.add(new Losa(10f, 225f, 30f, -200f)); 
        n.losas.add(new Losa(30f, 160f, 100f, 45f));    
        n.losas.add(new Losa(30f, 225f, 60f, 160f));    
        n.losas.add(new Losa(30f, -70f, 100f, -250f));  
        
        n.triangulos.add(new LosaTriangular(-100f, -200f,  100f, -200f,  100f, -250f));
        return n;
    }

    public static Nivel construirTerceraPlanta() {
        Nivel n = new Nivel();
        
        n.muros.add(new Muro(-20f, -200f,  -20f, -220f).aMitad());     
        n.muros.add(new Muro(-20f, -220f,  30f, -233f).aMitad());  
        n.muros.add(new Muro(-100f, -200f,  -20f, -200f).aMitad());    
        n.muros.add(new Muro(-100f,  225f, -100f, 35f).aMitad());  
        n.muros.add(new Muro(-100f,  10f, -100f, -200f).aMitad());  
        n.muros.add(new Muro(-100f,  225f,  60f,  225f).aMitad()); 
        n.muros.add(new Muro( 60f,  160f,  60f, 225f).aMitad()); 
        n.muros.add(new Muro( 100f,  160f,  100f, -145f).aMitad());  
        n.muros.add(new Muro( 60f,  160f,  100f, 160f).aMitad()); 
        n.muros.add(new Muro( 30f,  -200f,  30f, -233f).aMitad()); 
        
        n.losas.add(new Losa(-100f, 235f, -30f, 225f)); 
        n.losas.add(new Losa(-100f, 225f, 10f, 35f));  
        n.losas.add(new Losa(-100f, 10f, 10f, -200f));  
        n.losas.add(new Losa(10f, 225f, 30f, -200f));    
        n.losas.add(new Losa(30f, 225f, 60f, 160f));     
        n.losas.add(new Losa(30f, 160f, 100f, 80f));     
        n.losas.add(new Losa(30f, -70f, 100f, -200f));   
        
        n.triangulos.add(new LosaTriangular(-100f, -200f,  50f, -200f,  50f, -238f));
        return n;
    }
}