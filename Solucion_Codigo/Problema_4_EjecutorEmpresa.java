
import java.util.ArrayList;

public class Problema_4_EjecutorEmpresa {
    public static void main(String[] args) {
        Jefe jefe1 = new Jefe("Ana", "Garcia", "Calle Principal 123", "12345678A", 5000);
        Jefe jefe2 = new Jefe("Anali", "Garcia", "Calle Principal 123", "12345678A", 5000);
        T_Mensual mensual = new T_Mensual(jefe1,"Carlos", "Perez", "Calle Secundaria 456", "87654321B", 2000);
        T_Comisionista comisionista1 = new T_Comisionista(1000,50,jefe2,"Lucia", "Fernandez", "Calle Tercera 789", "11223344C",100);
        T_PorHoras porhoras = new T_PorHoras(1000,50,jefe2,"Lucia", "Fernandez", "Calle Tercera 789", "11223344C",10);
        System.out.println("dar de alta empleados");
        ArrayList<Trabajador> listaTrabajador = new ArrayList<>() ;
        listaTrabajador.add(jefe1);
        listaTrabajador.add(jefe2);
        listaTrabajador.add(mensual);
        
        Empresa empresa=new Empresa(listaTrabajador);
        empresa.darAltaTrabajdor(porhoras);
        empresa.darAltaTrabajdor(comisionista1);
        System.out.println("imprimir empresa");
        System.out.println(empresa);
        System.out.println("imprimir nomina de empleados");
        for (Trabajador emp:listaTrabajador) {
            System.out.println(emp.nombre+" "+emp.apellidos+" "+emp.calcularNomina());
        }
        System.out.println("imprimir empleados");
        for (Trabajador emp:listaTrabajador) {
            System.out.println(emp);
        }
    }  
}
class Empresa{
    public ArrayList<Trabajador> listaTrabajador;

    public Empresa(ArrayList<Trabajador> listaTrabajador) {
        this.listaTrabajador = listaTrabajador;
    }
    public void darAltaTrabajdor(Trabajador trabajador){
        listaTrabajador.add(trabajador);
    }

    @Override
    public String toString() {
        return "Empresa{" + "listaTrabajador=" + listaTrabajador + '}';
    }
    
}
class Trabajador {
    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public double salario;
    public Trabajador(String nombre, String apellidos, String direccion, String dni, double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.salario = salario;
    }
    public double calcularNomina(){
        return this.salario;
    }
    @Override
    public String toString() {
        return "Trabajador{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + ", dni=" + dni + ", salario=" + salario + '}';
    }
}
class T_Mensual extends Trabajador {
    public Jefe jefe;

    public T_Mensual(Jefe jefe, String nombre, String apellidos, String direccion, String dni, double salario) {
        super(nombre, apellidos, direccion, dni, salario);
        this.jefe = jefe;
    }

    @Override
    public String toString() {
        return super.toString()+"T_Mensual{" + "jefe=" + jefe.getIdentidad() + '}';
    }  
}
class T_Comisionista extends Trabajador {
    public double ventas;
    public double porcentajeVentas;
    public Jefe jefe;
    public T_Comisionista(double ventas, double porcentajeVentas, Jefe jefe, String nombre, String apellidos, String direccion, String dni, double salario) {
        super(nombre, apellidos, direccion, dni, salario);
        this.ventas = ventas;
        this.porcentajeVentas = porcentajeVentas;
        this.jefe = jefe;
    }
    @Override
    public double calcularNomina() {
        return this.salario=(ventas * (porcentajeVentas/100));
    }
    @Override
    public String toString() {
        return super.toString()+"Comisionista{" + "ventas=" + ventas + ", porcentajeVentas=" + porcentajeVentas + ", jefe=" + jefe.getIdentidad() + '}';
    }   
}
class T_PorHoras extends Trabajador {
    public double horasTrabajadas;
    public double porcentajeHExtra;
    public Jefe jefe;
    public T_PorHoras(double horasTrabajadas, double porcentajeHExtra, Jefe jefe, String nombre, String apellidos, String direccion, String dni, double salario) {
        super(nombre, apellidos, direccion, dni, salario);
        this.horasTrabajadas = horasTrabajadas;
        this.porcentajeHExtra = porcentajeHExtra;
        this.jefe = jefe;
    }
    @Override
    public double calcularNomina() {
        if (horasTrabajadas <= 40) {
            return horasTrabajadas * salario;
        } else {
            return (40 * this.salario) + ((this.horasTrabajadas - 40) * 1+(this.porcentajeHExtra/100));
        }
    }
    @Override
    public String toString() {
        return super.toString()+ "horasTrabajadas=" + horasTrabajadas + ", porcentajeHExtra=" + porcentajeHExtra + ", jefe=" + jefe.getIdentidad() + '}';
    }    
}
class Jefe extends Trabajador {

    public Jefe(String nombre, String apellidos, String direccion, String dni, double salario) {
        super(nombre, apellidos, direccion, dni, salario);
    }
    public String getIdentidad(){
        return "jefe= "+this.nombre+" "+this.apellidos;
    }
}
