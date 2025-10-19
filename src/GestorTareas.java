import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Esta clase permite gestionar el conjunto de tareas.
 * Contiene un Arraylist o arreglo dinamico como contenedor principal.
 */

public class GestorTareas {
    private ArrayList <Tarea> listaTareas; //Arreglo con todas las tareas ordenadas por fecha de creacion
    
    //Metodo constructor para inicializar la lista vacia
    public GestorTareas(){
        listaTareas = new ArrayList<>();
    }
    
    //Metodo para agreagar nuevas tareas
    public void agregarTarea(Tarea tarea){
        listaTareas.add(tarea);
    }
    
    //Metodo para eliminar una tarea segun su Id 
    public void EliminarTarea(int id){
        for (Tarea t : listaTareas){
            if (t.getId() == id){
                listaTareas.remove(t);
                break; //Detiene la iteracion despues de eliminar un elemento
            }
        }
    }
    
    //Metodo para buscar tareas segun su Id
    
    public Tarea buscarTarea(int id){
        for(Tarea t :listaTareas){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    
    //Metodos para actualizar algun de una tarea usando los setter de la clase Tarea
    public boolean actualizarTitulo(int id, String nuevoTitulo){
        Tarea t = buscarTarea(id);
        if (t != null){
            t.setTitulo(nuevoTitulo);
            return true;
        }
        return false;
    }
    
    public boolean actualizarDescripcion(int id, String nuevaDescripcion){
        Tarea t = buscarTarea(id);
        if (t != null){
            t.setDescripcion(nuevaDescripcion);
            return true;
        }
        return false;
    }
    
    public boolean actualizarFechaEntrega(int id, LocalDate nuevaFecha){
        Tarea t = buscarTarea(id);
        if (t != null){
            t.setFechaEntrega(nuevaFecha);
            return true;
        }
        return false;
    }
    
    public boolean actualizarPrioridad(int id, int nuevaPrioridad){
        Tarea t = buscarTarea(id);
        if (t != null){
            t.setPrioridad(nuevaPrioridad);
            return true;
        }
        return false;
    }
    
    public boolean actualizarEstado (int id, String nuevoEstado){
        Tarea t = buscarTarea(id);
        if (t != null){
            t.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }
    
    public ArrayList<Tarea> getListaTareas(){
        return listaTareas;
    }
    
    //Metodo para vaciar la lista
    public void limpiarTareas(){
        listaTareas.clear();
    }
}
