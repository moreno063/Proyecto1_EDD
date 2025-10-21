
//Ejemplo provisional generado con IA para verificar el funcionamiento

import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Persistencia persistencia = new Persistencia("tareas.txt"); //En el archivo tareas.txt se guardan las tareas con su información.
        GestorTareas gestor = new GestorTareas();

        // Cargar tareas desde el archivo
        ArrayList<Tarea> cargadas = persistencia.cargarTareas();
        for (Tarea t : cargadas) {
            gestor.agregarTarea(t);
        }

        SwingUtilities.invokeLater(() -> { //Ejecuta la interfaz
            new GestorTareasUI(gestor, persistencia).setVisible(true);
        });
    }
}
