import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Caso casoActual;

    public static void main(String[] args){
        try{
            while (casoActual == null){
                try{
                    crearNuevoCaso();
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            int opcion;
            do{
                mostrarMenu();
                opcion = leerEntero("Seleccione una opción: ");
                try{
                    switch (opcion){
                        case 1:
                            crearNuevoCaso();
                            break;
                        case 2:
                            registrarUbicacion();
                            break;
                        case 3:
                            System.out.println(casoActual.consultarUbicaciones());
                            break;
                        case 4:
                            consultarUbicacion();
                            break;
                        case 5:
                            modificarUbicacion();
                            break;
                        case 6:
                            descartarUbicacion();
                            break;
                        case 7:
                            registrarPista();
                            break;
                        case 8:
                            System.out.println(casoActual.consultarPistas());
                            break;
                        case 9:
                            buscarPista();
                            break;
                        case 10:
                            modificarPista();
                            break;
                        case 11:
                            eliminarPista();
                            break;
                        case 12:
                            System.out.println(casoActual.generarReporte());
                            break;
                        case 13:
                            System.out.println("Programa terminado.");
                            break;
                        default:
                            System.out.println("Seleccione una opción de 1 a 13.");
                    }
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                } catch (Espacioinvalido e){
                    System.out.println(e.getMessage());
                } catch (Noencontrado e){
                    System.out.println(e.getMessage());
                } catch (NullPointerException e){
                    System.out.println(e.getMessage());
                }
            } while (opcion != 13);
        } finally{
            scanner.close();
        }
    }

    private static int leerEntero(String mensaje){
        while (true){
            System.out.print(mensaje);
            try{
                int numero = scanner.nextInt();
                scanner.nextLine();
                return numero;
            } catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    private static String leerTexto(String mensaje){
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static void mostrarMenu(){
        System.out.println("\nCASO: " + casoActual.getNombre());
        System.out.println("1. Nuevo caso");
        System.out.println("2. Registrar ubicación");
        System.out.println("3. Consultar ubicaciones");
        System.out.println("4. Consultar una ubicación");
        System.out.println("5. Modificar ubicación");
        System.out.println("6. Descartar ubicación");
        System.out.println("7. Registrar pista");
        System.out.println("8. Consultar pistas");
        System.out.println("9. Buscar pista");
        System.out.println("10. Modificar pista");
        System.out.println("11. Eliminar pista");
        System.out.println("12. Mostrar reporte de investigación");
        System.out.println("13. Salir");
    }

    private static void crearNuevoCaso(){
        String nombre = leerTexto("Nombre del caso: ");
        String codigo = leerTexto("Código del caso: ");
        String detective = leerTexto("Detective responsable: ");
        casoActual = new Caso(nombre, codigo, detective);
        System.out.println("Caso creado correctamente.");
    }

    private static void registrarUbicacion(){
        int posicion = leerEntero("Posición de la ubicación (0 a 4): ");
        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        String direccion = leerTexto("Dirección: ");
        int riesgo = leerEntero("Riesgo (1 a 10): ");
        String estado = leerTexto("Estado: ");
        Ubicacion ubicacion = new Ubicacion(codigo, nombre, direccion, riesgo, estado);
        casoActual.registrarUbicacion(posicion, ubicacion);
        System.out.println("Ubicación registrada correctamente.");
    }

    private static void consultarUbicacion(){
        int posicion = leerEntero("Posición de la ubicación (0 a 4): ");
        System.out.println(casoActual.consultarUbicacion(posicion));
    }

    private static void modificarUbicacion(){
        int posicion = leerEntero("Posición de la ubicación (0 a 4): ");
        System.out.println(casoActual.consultarUbicacion(posicion));
        System.out.println("1. Modificar riesgo");
        System.out.println("2. Modificar estado");
        int opcion = leerEntero("Seleccione una opción: ");
        switch (opcion){
            case 1:
                int riesgo = leerEntero("Nuevo riesgo (1 a 10): ");
                casoActual.modificarUbicacionRiesgo(posicion, riesgo);
                break;
            case 2:
                String estado = leerTexto("Nuevo estado: ");
                casoActual.modificarUbicacionEstado(posicion, estado);
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }
        System.out.println("Ubicación modificada correctamente.");
    }

    private static void descartarUbicacion(){
        int posicion = leerEntero("Posición de la ubicación (0 a 4): ");
        casoActual.descartarUbicacion(posicion);
        System.out.println("Ubicación descartada correctamente.");
    }

    private static void registrarPista(){
        String codigo = leerTexto("Código: ");
        String descripcion = leerTexto("Descripción: ");
        String evidencia = leerTexto("Tipo de evidencia: ");
        int importancia = leerEntero("Importancia (1 a 10): ");
        int confiabilidad = leerEntero("Confiabilidad (0 a 100): ");
        Pista pista = new Pista(codigo, descripcion, evidencia, importancia, confiabilidad);
        casoActual.registrarPista(pista);
        System.out.println("Pista registrada correctamente.");
    }

    private static void buscarPista(){
        String codigo = leerTexto("Código de la pista: ");
        System.out.println(casoActual.consultarPista(codigo));
    }

    private static void modificarPista(){
        String codigo = leerTexto("Código de la pista a modificar: ");
        System.out.println("1. Modificar código");
        System.out.println("2. Modificar descripción");
        System.out.println("3. Modificar tipo de evidencia");
        System.out.println("4. Modificar importancia");
        System.out.println("5. Modificar confiabilidad");
        int opcion = leerEntero("Seleccione una opción: ");
        switch (opcion){
            case 1:
                String newcodigo = leerTexto("Nuevo código: ");
                casoActual.modificarPistacodigo(codigo, newcodigo);
                break;
            case 2:
                String descripcion = leerTexto("Nueva descripción: ");
                casoActual.modificarPistaDescripcion(codigo, descripcion);
                break;
            case 3:
                String evidencia = leerTexto("Nuevo tipo de evidencia: ");
                casoActual.modificarPistaEvidencia(codigo, evidencia);
                break;
            case 4:
                int importancia = leerEntero("Nueva importancia (1 a 10): ");
                casoActual.modificarPistaImportancia(codigo, importancia);
                break;
            case 5:
                int confiabilidad = leerEntero("Nueva confiabilidad (0 a 100): ");
                casoActual.modificarPistaConfiabilidad(codigo, confiabilidad);
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }
        System.out.println("Pista modificada correctamente.");
    }

    private static void eliminarPista(){
        String codigo = leerTexto("Código de la pista a eliminar: ");
        casoActual.eliminarPista(codigo);
        System.out.println("Pista eliminada correctamente.");
    }
}