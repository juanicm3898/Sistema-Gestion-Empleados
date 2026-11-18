package sistemaempleados;

/**
 * Clase base abstracta que define los atributos y comportamientos comunes
 * para todos los tipos de empleados de la empresa.
 * @author Juan
 */
public abstract class Empleado {
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected int cantidadHijos;

    public Empleado(String nombre, String apellido, String cedula, int cantidadHijos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.cantidadHijos = cantidadHijos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(int cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    public abstract double calcularSueldo();

    public abstract String obtenerTipoEmpleado();
}