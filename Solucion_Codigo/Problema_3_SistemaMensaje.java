
import java.util.ArrayList;

public class Problema_3_SistemaMensaje {

    public static void main(String[] args) {
        // Crear instancias de Movil
        Movil movil1 = new Movil("0923456789", "Alice");
        Movil movil2 = new Movil("0987654321", "Bob");

        // Crear instancia de SMS
        SMS sms = new SMS(movil1, movil2, "Hola, ¿cómo estás?");

        // Crear instancia de MMS
        MMS mms = new MMS(movil1, movil2, "imagen.png");

        // Enviar y visualizar mensajes
        System.out.println(sms.enviarMensaje());
        System.out.println(sms.visualizarMensaje());

        System.out.println(mms.enviarMensaje());
        System.out.println(mms.visualizarMensaje());

        System.out.println("//agregar mensaje");
        movil1.confirarEnvioMensaje(sms);
        movil1.confirarEnvioMensaje(mms);
        ArrayList<Mensaje> temporal= movil1.obtenerList();
        for(Mensaje men:temporal){
            System.out.println(men);
        }
        System.out.println("//eliminar mensaje");
        movil1.eliminarMensaje(0);
        for(Mensaje men:temporal){
            System.out.println(men);
        }
    }
    
}
class Movil {
    private String numeroTelf;
    private String nombre;
    private ArrayList<Mensaje> listMensaje;

    public Movil(String numeroTelf, String nombre) {
        this.numeroTelf = numeroTelf;
        this.nombre = nombre;
        this.listMensaje = new ArrayList<>();
    }
    public void confirarEnvioMensaje(Mensaje mensaje) {
        listMensaje.add(mensaje);
    }
    public void eliminarMensaje(int idx) {
        listMensaje.remove(idx);
    }
    public ArrayList<Mensaje> obtenerList(){
        return listMensaje;
    }
    @Override
    public String toString() {
        return "Movil{" + "numeroTelf=" + numeroTelf + ", nombre=" + nombre;
    }
}
class Mensaje {
    protected Movil remitente;
    protected Movil destinatario;
    public Mensaje(Movil remitente, Movil destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }
    public  String enviarMensaje(){
        return "Enviando SMS de " + remitente + " a " + destinatario + "...";
    }
    public String visualizarMensaje(){
        return  "SMS de " + remitente + " a " + destinatario + ": \n";
    }
    @Override
    public String toString() {
        return "Mensaje{" + "remitente=" + remitente + ", destinatario=" + destinatario + '}';
    }
    
}
// Clase SMS que hereda de Mensaje
class SMS extends Mensaje {
    private String contenido;

    public SMS(Movil remitente, Movil destinatario, String contenido) {
        super(remitente, destinatario);
        this.contenido = contenido;
    }

    @Override
    public String enviarMensaje() {
        return super.enviarMensaje()+"\nMensaje: " + contenido;
    }

    @Override
    public String visualizarMensaje() {
       return super.visualizarMensaje() + contenido;
    }

    @Override
    public String toString() {
        return super.toString()+"SMS{" + "contenido=" + contenido + '}';
    }   
}
// Clase MMS que hereda de Mensaje
class MMS extends Mensaje {
    private String nombreImagen;
    public MMS(Movil remitente, Movil destinatario, String nombreImagen) {
        super(remitente, destinatario);
        this.nombreImagen = nombreImagen;
    }
    @Override
    public String enviarMensaje() {
        return super.enviarMensaje()+"Imagen: " + nombreImagen;
    }

    @Override
    public String visualizarMensaje() {
        return super.visualizarMensaje()+ nombreImagen;
    }

    @Override
    public String toString() {
       return super.toString()+"MMS{" + "nombreImagen=" + nombreImagen + '}';
    }  
}