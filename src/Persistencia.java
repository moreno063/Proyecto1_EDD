import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.classfile.BufWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Persistencia {
    
    private String archivo;
        
    //Metodo constructor
    public Persistencia(String archivo) {
        this.archivo = archivo;
    }
     
    //Metodo para guardar las tareas en un archivo de texto
    public void guardarTareas(ArrayList<Tarea> listaTareas){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (Tarea t : listaTareas){
                //Se guarda cada atributo de la tarea separado por ";"
                bw.write(t.getId() + ";" + t.getTitulo() + ";" + t.getDescripcion() + ";" 
                + t.getFechaCreacion()+ ";" + t.getFechaEntrega()+ ";" + t.getPrioridad() 
                + ";" + t.getEstado() + ";");
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la tarea: " + e.getMessage());
        }
    }
    //Metodo para cargar todas las tareas desde el archivo en una nueva lista al iniciar el programa
    // Si el archivo no existe o hay un error, devuelve una lista vacia
    public ArrayList<Tarea> cargarTareas(){
        ArrayList<Tarea> listaTareas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null){
                String [] partes = linea.split (";");
                if (partes.length == 6){
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    String descripcion = partes[2];
                    LocalDate fechaCreacion = LocalDate.parse(partes[3]);
                    LocalDate fechaEntrega = LocalDate.parse(partes[4]);
                    int prioridad = Integer.parseInt(partes[5]);
                    String estado = partes [6   ];
                    
                    listaTareas.add(new Tarea(id, titulo, descripcion, fechaCreacion, fechaEntrega, prioridad, estado));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar tareas: " + e.getMessage());
        }
        return listaTareas;
        }
}
