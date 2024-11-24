package model.logic;

import model.data_structures.GrafoListaAdyacencia;
import model.data_structures.ITablaSimbolos;
import model.data_structures.ILista;
import model.data_structures.PosException;
import model.data_structures.VacioException;
import model.data_structures.Vertex;

public class Requerimiento1 {
    public static String req1String(GrafoListaAdyacencia grafo, ITablaSimbolos nombrecodigo, 
                                     ITablaSimbolos landingidtabla, String punto1, String punto2) {
        ITablaSimbolos tabla = grafo.getSSC(); 
        ILista lista = tabla.valueSet();
        int max = 0;

        for (int i = 1; i <= lista.size(); i++) {
            try {
                max = actualizarMaximo((int) lista.getElement(i), max);
            } catch (PosException | VacioException e) {
                System.out.println(e.toString());
            }
        }

        String fragmento = "La cantidad de componentes conectados es: " + max;

        try {
            String codigo1 = (String) nombrecodigo.get(punto1);
            String codigo2 = (String) nombrecodigo.get(punto2);

            Vertex vertice1 = (Vertex) ((ILista) landingidtabla.get(codigo1)).getElement(1);
            Vertex vertice2 = (Vertex) ((ILista) landingidtabla.get(codigo2)).getElement(1);
            int elemento1 = (int) tabla.get(vertice1.getId());
            int elemento2 = (int) tabla.get(vertice2.getId());

            if (elemento1 == elemento2) {
                fragmento += "\n Los landing points pertenecen al mismo clúster";
            } else {
                fragmento += "\n Los landing points no pertenecen al mismo clúster";
            }
        } catch (PosException | VacioException e) {
            e.printStackTrace();
        }
        return fragmento;
    }

    private static int actualizarMaximo(int valor, int max) {
        if (valor > max) {
            max = valor;
        }
        return max;
    }
}