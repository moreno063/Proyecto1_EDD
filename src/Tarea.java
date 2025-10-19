import java.io.Serializable; //Para la persistencia de datos
import java.time.LocalDate; //Para manejo de fechas

/*
 * Esta clase representa una tarea dentro del sistema.
 * Implementa serializable para poder guardarse en un archivo
 */

public class Tarea implements Serializable {
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    private int prioridad; // 1 = Alta, 2 = Media, 3 = Baja.
    private String estado; // (Pendiente, en progreso o completada) 
    
    //Metodo constructor 

    public Tarea(int id, String titulo, String descripcion, LocalDate fechaCreacion, LocalDate fechaEntrega, int prioridad, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.prioridad = prioridad;
        this.estado = estado;
    }
    
    //Metodos getters para acceder a los atributos de la tarea

    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public String getEstado() {
        return estado;
    }
    
    /*
     * Metodos setters para editar algunos de los atributos (No se incluyen los 
     * atributos ID y FechaCreacion ya que estos deben ser inmutables)
     */

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
    @Override
    public String toString(){
        return "Tarea [ÏD = " + id + ", Titulo = " + titulo + ", Estado =  " 
                + estado + ", Prioridad = " + prioridad + ", Fecha Entrega = " + fechaEntrega + "]";
    }    
}
