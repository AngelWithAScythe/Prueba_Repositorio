import java.util.ArrayList;
import java.util.List;

public class GestorRecetas {
    private List<Receta> recetas = new ArrayList<>();
    
    public void agregarReceta(Receta receta) {
        recetas.add(receta);
    }
    
    public List<Receta> obtenerRecetas() {
        return recetas;
    }



    public void eliminarReceta(Receta receta) {
        recetas.remove(receta);
        System.out.println("Se ha eliminado la receta");
    }

    public Receta buscarRecetaPorNombre(String nombre) {
        for (Receta receta : recetas) {
            if (receta.getNombre().equalsIgnoreCase(nombre)) {
                return receta; 
            }
        }
        return null; 
    }

}


