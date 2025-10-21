import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

//Clase que controla la interfaz y sus eventos

public class GestorTareasUI extends JFrame {

    private GestorTareas gestor;
    private Persistencia persistencia;
    private JTable tablaTareas;
    private DefaultTableModel modeloTabla;

    public GestorTareasUI(GestorTareas gestor, Persistencia persistencia) {
        this.gestor = gestor;
        this.persistencia = persistencia;

        setTitle("Gestor de Tareas");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabla
        modeloTabla = new DefaultTableModel(
                new Object[] { "ID", "Título", "Descripción", "Entrega", "Prioridad", "Estado" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda será editable
            }
        };

        tablaTareas = new JTable(modeloTabla);
        cargarTareasEnTabla();

        JScrollPane scroll = new JScrollPane(tablaTareas);
        add(scroll, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnDeshacer = new JButton("Deshacer");
        JButton btnGuardar = new JButton("Guardar cambios");
        JButton btnPendientes = new JButton("Pendientes");
        JButton btnTodo = new JButton("Mostrar Todo");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnDeshacer);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnPendientes);
        panelBotones.add(btnTodo);

        add(panelBotones, BorderLayout.SOUTH);

        // --- ACCIONES ---
        btnAgregar.addActionListener(e -> agregarTarea());
        btnEditar.addActionListener(e -> editarTarea());
        btnEliminar.addActionListener(e -> eliminarTarea());
        btnDeshacer.addActionListener(e -> deshacerAccion());
        btnGuardar.addActionListener(e -> guardarCambios());
        btnPendientes.addActionListener(e -> elegirPendientes());
        btnTodo.addActionListener(e -> cargarTareasEnTabla());
    }

    private void cargarTareasEnTabla() {
        modeloTabla.setRowCount(0);
        for (Tarea t : gestor.getListaTareas()) {
            modeloTabla.addRow(new Object[] {
                    t.getId(), t.getTitulo(), t.getDescripcion(),
                    t.getFechaEntrega(), t.getPrioridad(), t.getEstado()
            });
        }
    }

    private void agregarTarea() {
        String titulo = JOptionPane.showInputDialog(this, "Título:");
        String desc = JOptionPane.showInputDialog(this, "Descripción:");
        String fechaStr = JOptionPane.showInputDialog(this, "Fecha de entrega (YYYY-MM-DD):");

        try {

            int prioridad = -1;
            while (prioridad < 1 || prioridad > 5) {
                String p = JOptionPane.showInputDialog(this, "Prioridad (1-5):");
                if (p == null)
                    return; // cancelar
                try {
                    prioridad = Integer.parseInt(p);
                } catch (NumberFormatException e) {
                    prioridad = -1;
                }
                if (prioridad < 1 || prioridad > 5)
                    JOptionPane.showMessageDialog(this, "Ingresa un número entre 1 y 5.");
            }

            LocalDate fecha = LocalDate.parse(fechaStr);
            int id = gestor.getListaTareas().size() + 1;

            Tarea t = new Tarea(id, titulo, desc, LocalDate.now(), fecha, prioridad, "Pendiente");
            gestor.agregarTarea(t);
            modeloTabla.addRow(new Object[] { id, titulo, desc, fecha, prioridad, "Pendiente" });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar tarea: " + ex.getMessage());
        }
    }

    private void editarTarea() {
        int fila = tablaTareas.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para editar.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        Tarea t = gestor.buscarTarea(id);
        if (t == null)
            return;

        String[] campos = { "Título", "Descripción", "Fecha de entrega", "Prioridad", "Estado" };
        String campoSeleccionado = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona el campo a editar:",
                "Editar Tarea",
                JOptionPane.QUESTION_MESSAGE,
                null,
                campos,
                campos[0]);

        if (campoSeleccionado == null)
            return; // Usuario canceló

        // --- Editar según campo seleccionado ---
        switch (campoSeleccionado) {
            case "Título":
                String nuevoTitulo = JOptionPane.showInputDialog(this, "Nuevo título:", t.getTitulo());
                if (nuevoTitulo != null && !nuevoTitulo.isEmpty()) {
                    gestor.actualizarTitulo(id, nuevoTitulo);
                }
                break;

            case "Descripción":
                String nuevaDesc = JOptionPane.showInputDialog(this, "Nueva descripción:", t.getDescripcion());
                if (nuevaDesc != null && !nuevaDesc.isEmpty()) {
                    gestor.actualizarDescripcion(id, nuevaDesc);
                }
                break;

            case "Fecha de entrega":
                String nuevaFechaStr = JOptionPane.showInputDialog(this, "Nueva fecha (YYYY-MM-DD):",
                        t.getFechaEntrega());
                if (nuevaFechaStr != null && !nuevaFechaStr.isEmpty()) {
                    try {
                        LocalDate nuevaFecha = LocalDate.parse(nuevaFechaStr);
                        gestor.actualizarFechaEntrega(id, nuevaFecha);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto");
                    }
                }
                break;

            case "Prioridad":
                String nuevaPrioridadStr = JOptionPane.showInputDialog(this, "Nueva prioridad (1-5):",
                        t.getPrioridad());
                if (nuevaPrioridadStr != null && !nuevaPrioridadStr.isEmpty()) {
                    try {
                        int nuevaPrioridad = Integer.parseInt(nuevaPrioridadStr);
                        if (nuevaPrioridad < 1 || nuevaPrioridad > 5) {
                            JOptionPane.showMessageDialog(this, "El valor " + nuevaPrioridad + " no es válido.");
                        } else {
                            gestor.actualizarPrioridad(id, nuevaPrioridad);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Prioridad inválida");
                    }
                }
                break;

            case "Estado":
                String nuevoEstado = JOptionPane.showInputDialog(this,
                        "Nuevo estado (Pendiente/En progreso/Completada):",
                        t.getEstado());
                if (nuevoEstado != null && !nuevoEstado.isEmpty()) {
                    if (!nuevoEstado.equals("Pendiente") && !nuevoEstado.equals("En progreso")
                            && !nuevoEstado.equals("Completada")) {
                        JOptionPane.showMessageDialog(this, "El estado " + nuevoEstado + " no es válido.");
                    } else {
                        gestor.actualizarEstado(id, nuevoEstado);
                    }
                }
                break;
        }

        cargarTareasEnTabla(); //
        persistencia.guardarTareas(gestor.getListaTareas());
    }

    private void eliminarTarea() {
        int fila = tablaTareas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para eliminar.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        gestor.eliminarTarea(id);
        modeloTabla.removeRow(fila);
    }

    private void deshacerAccion() {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "La acción no se recupera. ¿Deseas continuar?",
                "Advertencia",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            gestor.deshacerUltimaAccion();

            cargarTareasEnTabla();
            JOptionPane.showMessageDialog(this, "Última acción deshecha.");
            System.out.println("Continuando...");
        } else {
        }
    }

    private void guardarCambios() {
        persistencia.guardarTareas(gestor.getListaTareas());
        JOptionPane.showMessageDialog(this, "Tareas guardadas correctamente.");
    }

    private void elegirPendientes() {
        modeloTabla.setRowCount(0);
        for (Tarea t : gestor.getColaTareas()) {
            modeloTabla.addRow(new Object[] {
                    t.getId(), t.getTitulo(), t.getDescripcion(),
                    t.getFechaEntrega(), t.getPrioridad(), t.getEstado()
            }); // Toma las tareas guardadas en la cola con prioridad, solo están las que están
                // pendientes y ordenadas por prioridad.
        }

    }

}