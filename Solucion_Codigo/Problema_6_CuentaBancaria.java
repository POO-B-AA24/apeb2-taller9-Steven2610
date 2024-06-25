public class Problema_6_CuentaBancaria {
        public static void main(String[] args) {

        CuentaBancaria cuentaCheques = new CuentaCheques(123, "Juan Perez");
        CuentaBancaria cuentaAhorros = new CuentaAhorros(456, "Maria Lopez");
        CuentaBancaria cuentaPlatino = new CuentaPlatino(789, "Luis Gomez");

        cuentaCheques.depositar(4240);
        cuentaAhorros.depositar(300);
        cuentaPlatino.depositar(31300);
        System.out.println("Retiro de cuenta de cheques " + cuentaCheques.retirar(50310));
        System.out.println("Retiro de cuenta de ahorros" + cuentaAhorros.retirar(100130));
        System.out.println("Retiro de cuenta de ahorros " + cuentaAhorros.retirar(20300));
        System.out.println("Retiro de cuenta platino : " + cuentaPlatino.retirar(2535));

        ((CuentaAhorros) cuentaAhorros).calcularInteres();
        ((CuentaPlatino) cuentaPlatino).calcularInteres();

        System.out.println(cuentaCheques);
        System.out.println(cuentaAhorros);
        System.out.println(cuentaPlatino);
    }
}
class CuentaBancaria {
    protected int numeroCuenta;
    protected String nombreCliente;
    protected double balance;

    public CuentaBancaria(int numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0.0;
    }

    public void depositar(double cantidad) {
        balance += cantidad;
    }

    public boolean retirar(double cantidad) {
        balance -= cantidad;
        return true;
    }
    public double obtenerBalance() {
        return balance;
    }
    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta=" + numeroCuenta +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", balance=" + balance +
                '}';
    }
}
class CuentaCheques extends CuentaBancaria {
    public CuentaCheques(int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }
    @Override
    public boolean retirar(double cantidad) {
        balance -= cantidad;  // Permite sobregiros
        return true;
    }
}
class CuentaAhorros extends CuentaBancaria {
    double taza_Interes = 0.02;

    public CuentaAhorros(int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public boolean retirar(double cantidad) {
        if (balance >= cantidad) {
            balance -= cantidad;
            return true;
        } else {
            return false;
        }
    }

    public void calcularInteres() {
        balance += balance * taza_Interes;
    }
}
class CuentaPlatino extends CuentaBancaria {
    double TASA_INTERES = 0.10;

    public CuentaPlatino(int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public boolean retirar(double cantidad) {
        balance -= cantidad;  // Permite sobregiros
        return true;
    }

    public void calcularInteres() {
        balance += balance * TASA_INTERES;
    }
}

