package sistemaempleados;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal del sistema con interfaz gráfica.
 * Permite registrar.*/
public class FormularioEmpleados extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtHijos;
    private JComboBox<String> cmbTipoEmpleado;

    private JTextField txtCampoExtra1;
    private JTextField txtCampoExtra2;
    private JLabel lblExtra1;
    private JLabel lblExtra2;

    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;

    private Empleado[] listaEmpleados = new Empleado[100];
    private int contadorEmpleados = 0;

    public FormularioEmpleados() {
        setTitle("Sistema de Gestión de Empleados");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponentsCustom();
        configurarTabla();
        configurarEventos();
    }

    private void initComponentsCustom() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 100, 25);
        panelPrincipal.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 30, 150, 25);
        panelPrincipal.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 70, 100, 25);
        panelPrincipal.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(140, 70, 150, 25);
        panelPrincipal.add(txtApellido);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(30, 110, 100, 25);
        panelPrincipal.add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(140, 110, 150, 25);
        panelPrincipal.add(txtCedula);

        JLabel lblHijos = new JLabel("Cant. Hijos:");
        lblHijos.setBounds(30, 150, 100, 25);
        panelPrincipal.add(lblHijos);

        txtHijos = new JTextField();
        txtHijos.setBounds(140, 150, 150, 25);
        panelPrincipal.add(txtHijos);

        JLabel lblTipo = new JLabel("Tipo Empleado:");
        lblTipo.setBounds(330, 30, 110, 25);
        panelPrincipal.add(lblTipo);

        String[] tipos = {"Por Hora", "Temporal", "Permanente"};
        cmbTipoEmpleado = new JComboBox<>(tipos);
        cmbTipoEmpleado.setBounds(450, 30, 150, 25);
        panelPrincipal.add(cmbTipoEmpleado);

        lblExtra1 = new JLabel("Horas Trabajadas:");
        lblExtra1.setBounds(330, 70, 120, 25);
        panelPrincipal.add(lblExtra1);

        txtCampoExtra1 = new JTextField();
        txtCampoExtra1.setBounds(450, 70, 150, 25);
        panelPrincipal.add(txtCampoExtra1);

        lblExtra2 = new JLabel("");
        lblExtra2.setBounds(330, 110, 120, 25);
        panelPrincipal.add(lblExtra2);

        txtCampoExtra2 = new JTextField();
        txtCampoExtra2.setBounds(450, 110, 150, 25);
        txtCampoExtra2.setVisible(false);
        lblExtra2.setVisible(false);
        panelPrincipal.add(txtCampoExtra2);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(630, 30, 160, 30);
        panelPrincipal.add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(630, 75, 160, 30);
        panelPrincipal.add(btnLimpiar);

        btnEliminar = new JButton("Eliminar Seleccionado");
        btnEliminar.setBounds(630, 120, 160, 30);
        panelPrincipal.add(btnEliminar);

        tablaEmpleados = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setBounds(30, 200, 760, 320);
        panelPrincipal.add(scrollPane);

        add(panelPrincipal);
    }

    private void configurarTabla() {
        String[] columnas = {"Tipo", "Nombre", "Apellido", "Cédula", "Hijos", "Sueldo (Bs.)"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEmpleados.setModel(modeloTabla);
    }

    private void configurarEventos() {
        cmbTipoEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCamposDinamicos();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                        FormularioEmpleados.this,
                        "¿Desea guardar la información?",
                        "Confirmación de Guardado",
                        JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    try {
                        guardarEmpleado();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormularioEmpleados.this, "Error en los datos ingresados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                        FormularioEmpleados.this,
                        "¿Está seguro de limpiar los datos?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    limpiarFormulario();
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaEmpleados.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    for (int i = filaSeleccionada; i < contadorEmpleados - 1; i++) {
                        listaEmpleados[i] = listaEmpleados[i + 1];
                    }
                    listaEmpleados[contadorEmpleados - 1] = null;
                    contadorEmpleados--;

                    modeloTabla.removeRow(filaSeleccionada);
                    JOptionPane.showMessageDialog(FormularioEmpleados.this, "Empleado eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(FormularioEmpleados.this, "Por favor, seleccione un empleado de la tabla para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void actualizarCamposDinamicos() {
        String seleccionado = (String) cmbTipoEmpleado.getSelectedItem();
        txtCampoExtra2.setText("");

        if (seleccionado.equals("Por Hora")) {
            lblExtra1.setText("Horas Trabajadas:");
            lblExtra2.setVisible(false);
            txtCampoExtra2.setVisible(false);
        } else if (seleccionado.equals("Temporal")) {
            lblExtra1.setText("Fecha Ingreso:");
            lblExtra2.setText("Fecha Fin Contrato:");
            lblExtra2.setVisible(true);
            txtCampoExtra2.setVisible(true);
        } else if (seleccionado.equals("Permanente")) {
            lblExtra1.setText("Antigüedad (Años):");
            lblExtra2.setVisible(false);
            txtCampoExtra2.setVisible(false);
        }
    }

    private void guardarEmpleado() {
        if (contadorEmpleados >= listaEmpleados.length) {
            JOptionPane.showMessageDialog(this, "Capacidad máxima de empleados alcanzada.", "Límite", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String cedula = txtCedula.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos básicos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cantidadHijos = Integer.parseInt(txtHijos.getText().trim());
        String tipo = (String) cmbTipoEmpleado.getSelectedItem();
        Empleado nuevoEmpleado = null;

        if (tipo.equals("Por Hora")) {
            double horas = Double.parseDouble(txtCampoExtra1.getText().trim());
            nuevoEmpleado = new EmpleadoPorHora(nombre, apellido, cedula, cantidadHijos, horas);
        } else if (tipo.equals("Temporal")) {
            String fechaIng = txtCampoExtra1.getText().trim();
            String fechaFin = txtCampoExtra2.getText().trim();
            nuevoEmpleado = new EmpleadoTemporal(nombre, apellido, cedula, cantidadHijos, fechaIng, fechaFin);
        } else if (tipo.equals("Permanente")) {
            int antiguedad = Integer.parseInt(txtCampoExtra1.getText().trim());
            nuevoEmpleado = new EmpleadoPermanente(nombre, apellido, cedula, cantidadHijos, antiguedad);
        }

        listaEmpleados[contadorEmpleados] = nuevoEmpleado;
        contadorEmpleados++;

        Object[] fila = {
                nuevoEmpleado.obtenerTipoEmpleado(),
                nuevoEmpleado.getNombre(),
                nuevoEmpleado.getApellido(),
                nuevoEmpleado.getCedula(),
                nuevoEmpleado.getCantidadHijos(),
                String.format("%.2f", nuevoEmpleado.calcularSueldo())
        };
        modeloTabla.addRow(fila);

        limpiarFormularioCampos();
        JOptionPane.showMessageDialog(this, "Datos almacenados correctamente.");
    }

    private void limpiarFormularioCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtHijos.setText("");
        txtCampoExtra1.setText("");
        txtCampoExtra2.setText("");
        cmbTipoEmpleado.setSelectedIndex(0);
    }

    private void limpiarFormulario() {
        limpiarFormularioCampos();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioEmpleados().setVisible(true);
            }
        });
    }
}
