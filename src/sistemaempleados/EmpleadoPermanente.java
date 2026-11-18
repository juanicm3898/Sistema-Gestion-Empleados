package sistemaempleados;

/**
 * Representa a un empleado de planta permanente en la empresa.
 * Considera bonos por hijos y antigüedad en años.
 */
public class EmpleadoPermanente extends Empleado {
    private int antiguedadAnios;

    public EmpleadoPermanente(String nombre, String apellido, String cedula, int cantidadHijos, int antiguedadAnios) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.antiguedadAnios = antiguedadAnios;
    }

    public int getAntiguedadAnios() {
        return antiguedadAnios;
    }

    public void setAntiguedadAnios(int antiguedadAnios) {
        this.antiguedadAnios = antiguedadAnios;
    }

    @Override
    public double calcularSueldo() {
        return 20000.0 + (this.cantidadHijos * 1000.0) + (this.antiguedadAnios * 1000.0);
    }

    @Override
    public String obtenerTipoEmpleado() {
        return "Permanente";
    }
}