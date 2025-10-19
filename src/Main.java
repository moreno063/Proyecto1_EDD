import java.time.LocalDate;

//Ejemplo provisional generado con IA para verificar el funcionamiento
public class Main {
    public static void main(String[] args) {
        // Crear gestor de tareas
        GestorTareas gestor = new GestorTareas();

        // Crear algunas tareas de prueba
        Tarea t1 = new Tarea(1, "Proyecto Java", "Completar gestor de tareas", LocalDate.now(), LocalDate.of(2025, 10, 20), 2, "Pendiente");
        Tarea t2 = new Tarea(2, "Estudiar", "Repasar fundamentos de electricidad y magnetismo",LocalDate.now(), LocalDate.of(2025, 10, 15), 1, "Pendiente");

        // Agregar tareas al gestor
        gestor.agregarTarea(t1);
        gestor.agregarTarea(t2);

        // Mostrar todas las tareas
        System.out.println("=== LISTA DE TAREAS ===");
        for (Tarea t : gestor.getListaTareas()) {
            System.out.println(t);
        }

        // Actualizar titulo de una tarea
        gestor.actualizarTitulo(1, "Proyecto Java - Actualizado");

        // Mostrar tarea actualizada
        System.out.println("\n=== TAREA ACTUALIZADA ===");
        System.out.println(gestor.buscarTarea(1));

        // Eliminar tarea 2
        gestor.EliminarTarea(2);

        // Mostrar lista final de tareas
        System.out.println("\n=== LISTA FINAL ===");
        for (Tarea t : gestor.getListaTareas()) {
            System.out.println(t);
        }

        // Guardar en archivo
        Persistencia persistencia = new Persistencia("tareas.txt");
        persistencia.guardarTareas(gestor.getListaTareas());

        // Cargar desde archivo en un nuevo gestor
        GestorTareas gestor2 = new GestorTareas();
        gestor2.getListaTareas().addAll(persistencia.cargarTareas());

        // Mostrar tareas cargadas
        System.out.println("\n=== TAREAS CARGADAS DESDE ARCHIVO ===");
        for (Tarea t : gestor2.getListaTareas()) {
            System.out.println(t);
        }
    }
}
