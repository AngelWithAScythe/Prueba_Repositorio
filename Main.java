import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorRecetas gestor = new GestorRecetas();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=========================");
            System.out.println("   GESTOR DE RECETAS   ");
            System.out.println("=========================");
            System.out.println("1. Agregar Receta");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opcion: ");
            
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero válido.");
                continue;
            }

             switch (opcion) {
                case 1:
                    System.out.println("\n--- Agregar Nueva Receta ---");
                    System.out.print("Ingrese el nombre de la receta: ");
                    String nombre = scanner.nextLine().trim();
                    
                    System.out.print("Ingrese los ingredientes (separados por coma): ");
                    String[] ingredientes = scanner.nextLine().split(",");
                    
                    System.out.print("Ingrese las instrucciones: ");
                    String instrucciones = scanner.nextLine().trim();
                    
                    Receta nuevaReceta = new Receta(nombre, ingredientes, instrucciones);
                    gestor.agregarReceta(nuevaReceta);
                    System.out.println("\n Receta agregada con exito!");
                    break;
                
                case 2:
                    System.out.println("\nSaliendo...");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("\n Opcion no valida. Intente de nuevo.");
            }
        }
    }
}
