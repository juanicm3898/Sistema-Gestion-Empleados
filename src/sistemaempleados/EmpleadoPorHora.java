package sistemaempleados;

/**
 * Representa a un empleado contratado por hora.
 * Su sueldo depende exclusivamente de las horas trabajadas en el mes.
 */
public class EmpleadoPorHora extends Empleado {
    private double horasTrabajadas;

    public EmpleadoPorHora(String nombre, String apellido, String cedula, int cantidadHijos, double horasTrabajadas) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSueldo() {
        return this.horasTrabajadas * 100.0;
    }

    @Override
    public String obtenerTipoEmpleado() {
        return "Por Hora";
    }
}
