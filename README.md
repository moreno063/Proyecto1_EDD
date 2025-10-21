Proyecto1_BDD
Sistema de Gestión y Priorización de Tareas
Este proyecto implementa un gestor de tareas con prioridades utilizando Java y estructuras de datos. Permite registrar tareas, asignarles una prioridad (1 a 5), editarlas, eliminarlas y deshacer cambios.
Cuenta con una interfaz gráfica.

Tecnologías utilizadas
Lenguaje: Java (JDK 17 o superior)
Entorno recomendado: VSCode
Estructuras de datos utilizadas: - Arreglo dinámico: Almacena todas las tareas. - Pila: Guarda las acciones realizadas. - Cola con prioridad: Almacena las tareas Pendientes.
Preparación del proyecto
Entrar a VS code (Con JDK 8 instalado) y abrir la carpeta del proyecto.
Compilar y ejecutar el archivo main.java.
Se abrirá una ventana de nombre "Gestor de Tareas"
Inicio del programa
La interfaz tiene dos secciones principales:

Panel central: Tabla con las columnas: "ID", "Título", "Descripción", "Entrega", "Prioridad" y "Estado" donde se pueden ver todas las tareas.
Panel inferior: Botones para poder realizar las siguientes acciones:
Para agregar una tarea:
Presiona "Agregar"
En el campo "Tarea:" se escribe la descripción de la Tarea (Ejemplo: "Informe laboratorio")
Escribe el nombre y descripción de la tarea, se escoge la fecha de entrega, su prioridad y su estado.
Presionar "OK".
En la pantalla será visible la tarea agregada.
Para editar una tarea
En la tabla se escoge la tarea a editar.
Saldrá una ventana para seleccionar el campo a editar.
Edita el campo, presiona "Ok" y la actualización se verá reflejada en la pantalla.
Para elimninar una tarea
Selecciona la tarea a eliminar y presiona el botón "Eliminar" la tarea ya no aparece en la tabla.
Para deshacer la última acción
Presiona "Deshacer"
Saldrá una ventana para confirmar.
La última acción se deshizo.
Guardar Cambios
Con el botón "Guardar Cambios" se guardarán todas las tareas que aparezcan en la tabla.
Ver todas las tareas o ver solo las pendientes.
Con los botones "Pendientes" y "Mostrar Todo" se puede decidir si quiere ver todas las tareas o únicamente las pendientes, ordenadas por prioridad.
Autores
** Sara Sepúlveda ** ** Antonio Moreno ** ** Samuel Mejía ** Estructuras de Datos Universidad Nacional de Colombia

Licencia
Proyecto de carácter educativo.
Libre para uso académico o personal, con atribución al autor original.
