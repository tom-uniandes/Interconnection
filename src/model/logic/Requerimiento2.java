package model.logic;

import model.data_structures.ILista;
import model.data_structures.Landing;
import model.data_structures.Vertex;
import model.data_structures.PosException;
import model.data_structures.VacioException;
import model.data_structures.ITablaSimbolos;

public class Requerimiento2 {

    public static String req2String(ITablaSimbolos landingidtabla) {
        String fragmento = "";
        ILista lista = landingidtabla.valueSet();

        int cantidad = 0;
        int contador = 0;

        for (int i = 1; i <= lista.size(); i++) {
            try {
                ILista subLista = (ILista) lista.getElement(i);
                
                if (subLista.size() > 1 && contador <= 10) {
                    Landing landing = obtenerLanding(subLista);
                    cantidad = contarArcos(subLista);

                    fragmento += "\n Landing " + "\n Nombre: " + landing.getName() + "\n País: " + landing.getPais()
                            + "\n Id: " + landing.getId() + "\n Cantidad: " + cantidad;

                    contador++;
                }
            } catch (PosException | VacioException e) {
                e.printStackTrace();
            }
        }

        return fragmento;
    }

    private static Landing obtenerLanding(ILista subLista) throws PosException, VacioException {
        return (Landing) ((Vertex) subLista.getElement(1)).getInfo();
    }

    private static int contarArcos(ILista subLista) throws PosException, VacioException {
        int cantidad = 0;
        for (int j = 1; j <= subLista.size(); j++) {
            Vertex vertex = (Vertex) subLista.getElement(j);
            cantidad += vertex.edges().size();
        }
        return cantidad;
    }
}