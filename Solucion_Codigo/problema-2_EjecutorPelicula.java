import java.util.ArrayList;
import java.util.Arrays;
public class EjecutorPelicula {
    public static void main(String[] args) {
        DVD soporteDvd = new DVD(4.5);
        VHS soporteVhs = new VHS("Cinta XD");
        ArrayList<Pelicula>  listaPeliculas = new ArrayList<Pelicula>(Arrays.asList(
                                              new Pelicula(soporteDvd,"Intesamente", "Pepe Gamer", "2024", "Peruvian"),
                                              new Pelicula(soporteVhs,"JuanPerez", "Diego Gamer", "2012", "Ingles"),
                                              new Pelicula(soporteVhs,"XD", "RDF Gamer", "1999", "Japanese")
                                                ));
        for(Pelicula peli : listaPeliculas){
             System.out.println(peli);
        }       
    }    
}
class Pelicula{
    public Soporte soporte;
    public String titulo;
    public String autor;
    public String anioEdicion;
    public String idioma;
    public double costoAlquiler;

    public Pelicula(Soporte soporte, String titulo, String autor, String anioEdicion, String idioma) {
        this.soporte = soporte;
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
        this.idioma = idioma;
    }
            
   public void calcularCostoAlquiler(String tipo,double costo){
       this.costoAlquiler=costo;
       this.costoAlquiler=("DVD".equals(tipo) )?this.costoAlquiler*1.1 :this.costoAlquiler;
   }
    @Override
    public String toString() {
        return "Pelicula{" + "soporte=" + soporte + ", titulo=" + titulo + ", autor=" + autor + ", anioEdicion=" + anioEdicion + ", idioma=" + idioma + ", costoAlquiler=" + costoAlquiler + '}';
    }   
}
class Soporte{}
class DVD extends Soporte{
    public double capacidadGB;
    public DVD(double capacidadGB) {
        this.capacidadGB = capacidadGB;
    }

    @Override
    public String toString() {
        return "DVD{" + "capacidadGB=" + capacidadGB + '}';
    }
   
}
class VHS extends Soporte{
    public String tipoCintaMagnetica;

    public VHS(String tipoCintaMagnetica) {
        this.tipoCintaMagnetica = tipoCintaMagnetica;
    }

    @Override
    public String toString() {
        return "VHS{" + "tipoCintaMagnetica=" + tipoCintaMagnetica + '}';
    }
}
