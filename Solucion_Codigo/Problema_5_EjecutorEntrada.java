import java.util.ArrayList;
import java.util.List;

public class Problema_5_EjecutorEntrada {

    private static List<Zona> zonas1 = new ArrayList<>();
    private static int nextId1 = 1;

    public static void main(String[] args) {
        // Inicializar zonas
        Zona zona1 = new Zona("Principal", 200, 25.0, 17.5);
        Zona zona2 = new Zona("PalcoB", 40, 70.0, 40.0);
        Zona zona3 = new Zona("Central", 400, 20.0, 14.0);
        Zona zona4 = new Zona("Lateral", 100, 15.0, 10.0);

        // Agregar zonas
        zonas1.add(zona1);
        zonas1.add(zona2);
        zonas1.add(zona3);
        zonas1.add(zona4);

        // Vender entradas
        venderEntrada("Principal", "Juan Perez", "normal");
        venderEntrada("Principal", "Maria Lopez", "abonado");
        venderEntrada("PalcoB", "Luis Gomez", "reducida");

        // Consultar entradas
        consultarEntrada(1);
        consultarEntrada(2);
        consultarEntrada(3);
        consultarEntrada(4);
    }

    public static void venderEntrada(String zonaNombre, String nombreComprador, String tipo) {
        for (Zona zona : zonas1) {
            if (zona.getNombre().equals(zonaNombre)) {
                if (zona.getLocalidadesDisponibles() > 0) {
                    Entrada entrada = zona.venderEntrada(tipo, nextId1, nombreComprador);
                    if (entrada != null) {
                        System.out.println("Entrada vendida: " + entrada);
                        nextId1++;
                    }
                } else {
                    System.out.println("La zona " + zonaNombre + " está completa.");
                }
                return;
            }
        }
        System.out.println("La zona " + zonaNombre + " no existe.");
    }

    public static void consultarEntrada(int id) {
        for (Zona zona : zonas1) {
            Entrada entrada = zona.getEntradaById(id);
            if (entrada != null) {
                System.out.println("Entrada encontrada: " + entrada);
                return;
            }
        }
        System.out.println("No existe ninguna entrada con el identificador " + id + ".");
    }
}

class Zona {
    private String nombre;
    private int numLocalidades;
    private double precioNormal;
    private double precioAbonado;
    private List<Entrada> entradasVendidas;

    public Zona(String nombre, int numLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.numLocalidades = numLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.entradasVendidas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getLocalidadesDisponibles() {
        return numLocalidades - entradasVendidas.size();
    }

    public Entrada venderEntrada(String tipo, int id, String nombreComprador) {
        Entrada entrada = null;
        switch (tipo) {
            case "normal":
                entrada = new EntradaNormal(id, this, nombreComprador);
                break;
            case "reducida":
                entrada = new EntradaReducida(id, this, nombreComprador);
                break;
            case "abonado":
                entrada = new EntradaAbonado(id, this, nombreComprador);
                break;
        }
        if (entrada != null) {
            entradasVendidas.add(entrada);
        }
        return entrada;
    }

    public Entrada getEntradaById(int id) {
        for (Entrada entrada : entradasVendidas) {
            if (entrada.getId() == id) {
                return entrada;
            }
        }
        return null;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public double getPrecioAbonado() {
        return precioAbonado;
    }
}

abstract class Entrada {
    protected int id;
    protected Zona zona;
    protected String nombreComprador;

    public Entrada(int id, Zona zona, String nombreComprador) {
        this.id = id;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
    }

    public int getId() {
        return id;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public String getZonaNombre() {
        return zona.getNombre();
    }

    public double getPrecio() {
        return zona.getPrecioNormal();
    }

    @Override
    public String toString() {
        return "Entrada{id=" + id + ", zona=" + zona.getNombre() + ", nombreComprador='" + nombreComprador + "', precio=" + getPrecio() + "}";
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() * 0.85;
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double getPrecio() {
        return zona.getPrecioAbonado();
    }
}
