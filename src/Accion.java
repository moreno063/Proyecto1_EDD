import java.time.LocalDate;

public class Accion {
    private String tipo;
    private Tarea tarea; // referencia directa
    private Object valorAnterior;

    public Accion(String tipo, Tarea tarea, Object valorAnterior) {
        this.tipo = tipo;
        this.tarea = tarea;
        this.valorAnterior = valorAnterior;
    }

    // Método para revertir la acción
    public void revertir(GestorTareas gestor) {

        switch (tipo) {
            case "agregar":
                gestor.getListaTareas().remove(tarea);
                break;
            case "eliminar":
                gestor.getListaTareas().add(tarea);
                break;
            case "actualizarTitulo":
                tarea.setTitulo((String) valorAnterior);
                break;
            case "actualizarDescripcion":
                tarea.setDescripcion((String) valorAnterior);
                break;
            case "actualizarFecha":
                tarea.setFechaEntrega((LocalDate) valorAnterior);
                break;
            case "actualizarPrioridad":
                tarea.setPrioridad((Integer) valorAnterior);
                break;
            case "actualizarEstado":
                tarea.setEstado((String) valorAnterior);
                gestor.actualizarCola();
                break;
            default:
                System.out.println("Acción desconocida: " + tipo);
        }
    }

}
