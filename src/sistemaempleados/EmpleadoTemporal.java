package sistemaempleados;

/**
 * Representa a un empleado bajo modalidad temporal.
 * Su sueldo base incluye bonificación por cantidad de hijos.
 */
public class EmpleadoTemporal extends Empleado {
    private String fechaIngreso;
    private String fechaFinContrato;

    public EmpleadoTemporal(String nombre, String apellido, String cedula, int cantidadHijos, String fechaIngreso, String fechaFinContrato) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.fechaIngreso = fechaIngreso;
        this.fechaFinContrato = fechaFinContrato;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(String fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    @Override
    public double calcularSueldo() {
        return 18000.0 + (this.cantidadHijos * 1000.0);
    }

    @Override
    public String obtenerTipoEmpleado() {
        return "Temporal";
    }
}