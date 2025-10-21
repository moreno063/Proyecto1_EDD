import java.time.LocalDate;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * Esta clase permite gestionar el conjunto de tareas.
 * Contiene un Arraylist o arreglo dinamico como contenedor principal.
 */

public class GestorTareas {
    private ArrayList<Tarea> listaTareas; // Arreglo con todas las tareas ordenadas por fecha de creacion
    private PriorityQueue<Tarea> cola_p; // Cola con prioridad solo con tareas pendientes
    private Stack<Accion> historialAc; // Pila para el historial de acciones sobre las tareas

    // Metodo constructor para inicializar la lista vacia
    public GestorTareas() {
        listaTareas = new ArrayList<>();
        cola_p = new PriorityQueue<>();
        historialAc = new Stack<>();
    }

    // Metodo para agreagar nuevas tareas
    public void agregarTarea(Tarea tarea) {
        // Agrega tarea a la lista
        listaTareas.add(tarea);
        // Condicional para incluir tarea en la cola de acuerdo al estado
        if (tarea.getEstado().equalsIgnoreCase("Pendiente")) {
            cola_p.add(tarea);
        }
        historialAc.push(new Accion("agregar", tarea, 0)); // Guardar acción en pila
    }

    // Metodo para eliminar una tarea segun su Id
    public void eliminarTarea(int id) {
        for (Tarea t : listaTareas) {
            if (t.getId() == id) {
                listaTareas.remove(t);
                cola_p.remove(t);
                historialAc.push(new Accion("eliminar", t, 0)); // Guardar acción en pila
                break; // Detiene la iteracion despues de eliminar un elemento
            }
        }
    }

    // Metodo para buscar tareas segun su Id

    public Tarea buscarTarea(int id) {
        for (Tarea t : listaTareas) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    // Actualizar cola cuando cambien estado o prioridad de la tarea
    public void actualizarCola() {
        cola_p.clear();
        for (Tarea t : listaTareas) {
            if (t.getEstado().equalsIgnoreCase("Pendiente")) {
                cola_p.add(t);
            }
        }
    }

    // Metodos para actualizar el título de una tarea usando los setter de la clase
    // Tarea
    public boolean actualizarTitulo(int id, String nuevoTitulo) {
        Tarea t = buscarTarea(id);
        if (t != null) {
            String anterior = t.getTitulo();
            t.setTitulo(nuevoTitulo);
            historialAc.push(new Accion("actualizarTitulo", t, anterior));
            return true;
        }
        return false;
    }

    // Método para editar la descripción de una tarea
    public boolean actualizarDescripcion(int id, String nuevaDescripcion) {
        Tarea t = buscarTarea(id);
        if (t != null) {
            String anterior = t.getDescripcion();
            t.setDescripcion(nuevaDescripcion);
            historialAc.push(new Accion("actualizarDescripcion", t, anterior));
            return true;
        }
        return false;
    }

    // Método para editar la Fecha de entrega de una tarea
    public boolean actualizarFechaEntrega(int id, LocalDate nuevaFecha) {
        Tarea t = buscarTarea(id);
        if (t != null) {
            LocalDate anterior = t.getFechaEntrega();
            t.setFechaEntrega(nuevaFecha);
            historialAc.push(new Accion("actualizarFecha", t, anterior));
            return true;
        }
        return false;
    }

    // Método para cambiar la prioridad de una tarea
    public boolean actualizarPrioridad(int id, int nuevaPrioridad) {
        Tarea t = buscarTarea(id);
        if (t != null) {
            Integer anterior = t.getPrioridad();
            t.setPrioridad(nuevaPrioridad);
            actualizarCola();
            historialAc.push(new Accion("actualizarPrioridad", t, anterior));
            return true;
        }
        return false;
    }

    // Método para cambiar el estado de una tarea
    public boolean actualizarEstado(int id, String nuevoEstado) {
        Tarea t = buscarTarea(id);
        if (t != null) {
            String anterior = t.getEstado();
            t.setEstado(nuevoEstado);
            actualizarCola();
            historialAc.push(new Accion("actualizarEstado", t, anterior));
            return true;
        }
        return false;
    }

    // Metodo para revertir la ultima accion realizada
    public void deshacerUltimaAccion() {
        if (!historialAc.isEmpty()) {
            Accion ultima = historialAc.pop();
            ultima.revertir(this);
            actualizarCola(); // si cambias estado o prioridad
        } else {
            System.out.println("No hay acciones para deshacer.");
        }
    }

    public ArrayList<Tarea> getListaTareas() {
        return listaTareas;
    }

    public PriorityQueue<Tarea> getColaTareas() {
        return cola_p;
    }

    public Stack<Accion> getHistorialAcciones() {
        return historialAc;
    }

    // Metodo para vaciar la lista y cola
    public void limpiarTareas() {
        listaTareas.clear();
        cola_p.clear();
    }

}
