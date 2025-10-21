import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Persistencia {

    private String archivo;

    // Metodo constructor
    public Persistencia(String archivo) {
        this.archivo = archivo;
    }

    // Metodo para guardar las tareas en un archivo de texto
    public void guardarTareas(ArrayList<Tarea> listaTareas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Tarea t : listaTareas) {
                bw.write(t.getId() + ";" + t.getTitulo() + ";" + t.getDescripcion() + ";"
                        + t.getFechaCreacion() + ";" + t.getFechaEntrega() + ";" + t.getPrioridad()
                        + ";" + t.getEstado());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

    // Metodo para cargar todas las tareas desde el archivo en una nueva lista al
    // iniciar el programa
    // Si el archivo no existe o hay un error, devuelve una lista vacia
    public ArrayList<Tarea> cargarTareas() {
        ArrayList<Tarea> listaTareas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 7) {
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    String descripcion = partes[2];
                    LocalDate fechaCreacion = LocalDate.parse(partes[3]);
                    LocalDate fechaEntrega = null;
                    if (partes[4] != null && !partes[4].equals("null") && !partes[4].isEmpty()) {
                        fechaEntrega = LocalDate.parse(partes[4]);
                    }
                    int prioridad = Integer.parseInt(partes[5]);
                    String estado = partes[6];

                    listaTareas.add(new Tarea(id, titulo, descripcion, fechaCreacion, fechaEntrega, prioridad, estado));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo. Se creará uno nuevo al guardar.");
        } catch (IOException e) {
            System.out.println("Error al cargar tareas: " + e.getMessage());
        }

        return listaTareas;
    }
}
