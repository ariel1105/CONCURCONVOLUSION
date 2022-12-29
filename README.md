# CONCURCONVOLUSION
El proyecto consta de un programa en Java que aplica filtros
convolucionales de manera concurrente a una imagen.
Para llevar a cabo esta tarea, en la clase Main te pide mediante inputs
ingresar el tamaño del Buffer en el cual se cargaran tareas con las
información de los píxeles que los workers irán tomando para ejecutar, la
cantidad de Threads a iniciar, la ruta en la que se encuentra la imagen que
quiere procesar y el filtro que quiere aplicar, y al finalizar devuelve en una
imagen de nombre “salida.jpg” el resultado.

##Explicación de las clases creadas en el programa:

● El Buffer, es un recurso compartido entre los threads generados y lo
creamos como un monitor. De esta forma, nos aseguramos el mutex
tanto en la escritura(evitando que se “pisen” datos) como en la lectura
de datos (evitando la pérdida de tareas para analizar). La estructura
del buffer es similar a la de un buffer circular, es decir, funciona con
algoritmo FIFO pero teniendo una capacidad limitada para agregar
datos, teniendo que esperar, si este está lleno, a que se libere alguna
posición para insertar uno nuevo. En este Buffer se irán cargando un
cantidad de tareas equivalente a la cantidad de pixeles de la foto
eliminando los bordes siempre respetando la capacidad del Buffer y
además al final se agregaran PoisonPills equivalentes a la cantidad de
Workers para que dejen de trabajar en cuanto las consuman.

● El FilterWorker extiende de Thread, representa cada uno de los
Threads que ingresamos por input al iniciar el programa y es el
encargado de leer una tarea del Buffer y ejecutarla.

● Las PoisonPills se encargan de finalizar el trabajo del FilterWorker y
aumentar el WorkerCounter en uno.

● El ProductorThreadPool en base a la cantidad de píxeles de la imagen
original, le quita los bordes, crea una cantidad de tareas equivalente a
los píxeles restantes y las carga en el Buffer. Luego de cargar todas,
crea PoisonPills equivalentes a la cantidad de Workers y las carga en
el buffer también.

● Cada Task (tarea) tiene toda la información necesaria para leer el
pixel, aplicarle el filtro y replicar el resultado en la imagen de salida.

● El WorkerCounter está implementado como un monitor ya que también
es un recurso compartido, y es el encargado de ir controlando que el
Main no finalice hasta que todos los Workers terminen, por lo que va
llevando un conteo de los workers que van terminando y ,una vez
terminados todos, hace el stop.

● El ThreadPool crea cada uno de los FilterWolkers en base al valor
ingresado por input y comienza la ejecución de cada uno, también
crea el ProductorThreadPool y lo ejecuta.

● Contamos también con una clase UserPool que solo lanza el
ThreadPool y guarda el momento en el que se inició y una clase
PoolStopper que ejecuta el stop del WorkerCounter, crea el archivo de
imagen resultado de aplicar los filtros (salida.jpg) y compara el
momento en que inició el programa guardado en el UserPool con el
momento en que terminó para mostrar el tiempo que tardó la
ejecución.

● Por último tenemos los filtros guardados como un Enum Filtro que
poseen un nombre y la matriz para aplicarlo, permitiendo así
fácilmente meter nuevos simplemente agregando un nuevo valor en el
código fuente.

##CÓMO EJECUTAR EL PROGRAMA:
Para ejecutar por CLI:

1. Tener instalado JDK-19, ubicarte en la carpeta src desde la consola y
lanzar el comando “java Main”.
2. El programa iniciará y te pedirá que indiques el tamaño del Buffer,
ingresar el valor deseado y presionar enter.
3. Ahora te pedirá la cantidad de Workers a iniciar, ingresar el número
deseado y presionar enter.
4. Seguido a eso te pedirá la ruta absoluta de la imagen que quieras
procesar(Ejemplo:
C:\Users\Administrator\Desktop\TP_Concurrente\michi.jpg), ingresar
la ruta y presionar enter.
5. Por último pedirá el filtro que quieras aplicarle, debe ingresar alguno
de los siguientes valores sin comillas “blur”, “sharpen” , “sobel
vertical”, “sobel horizontal” , “laplacian” y “emboss”. Presionar enter.
6. Esperar a que finalice la ejecución y revisar el archivo salida.jpg
ubicado en la misma carpeta src.

##Para ejecutar por intelliJ:

1. Configurar la SDK del proyecto con openjdk-19 de Oracle
2. Correr el Main.
3. El programa iniciará y te pedirá que indiques el tamaño del Buffer,
ingresar el valor deseado y presionar enter.
4. Ahora te pedirá la cantidad de Workers a iniciar, ingresar el número
deseado y presionar enter.
5. Seguido a eso te pedirá la ruta absoluta de la imagen que quieras
procesar(Ejemplo:
C:\Users\Administrator\Desktop\TP_Concurrente\michi.jpg), ingresar
la ruta y presionar enter.
6. Por último pedirá el filtro que quieras aplicarle, debe ingresar alguno
de los siguientes valores sin comillas “blur”, “sharpen” , “sobel
vertical”, “sobel horizontal” , “laplacian” y “emboss”. Presionar enter.
7. Esperar a que finalice la ejecución y revisar el archivo salida.jpg
ubicado en la misma carpeta del proyecto “TP_Concurrente”.
