import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorRecetas gestor = new GestorRecetas();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese el nombre de la receta:");
        String nombre = scanner.nextLine();
        
        System.out.println("Ingrese los ingredientes (separados por coma):");
        String[] ingredientes = scanner.nextLine().split(",");
        
        System.out.println("Ingrese las instrucciones:");
        String instrucciones = scanner.nextLine();
        
        Receta nuevaReceta = new Receta(nombre, ingredientes, instrucciones);
        gestor.agregarReceta(nuevaReceta);
        
        System.out.println("Receta agregada: " + nuevaReceta.getNombre());
    }
}
