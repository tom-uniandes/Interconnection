package model.logic;

import model.data_structures.Country;
import model.data_structures.Edge;
import model.data_structures.GrafoListaAdyacencia;
import model.data_structures.ITablaSimbolos;
import model.data_structures.Landing;
import model.data_structures.PilaEncadenada;

public class Requerimiento3 {
    public static String req3String(GrafoListaAdyacencia grafo, ITablaSimbolos paises, String pais1, String pais2) {
        Country pais11 = (Country) paises.get(pais1);
        Country pais22 = (Country) paises.get(pais2);
        String capital1 = pais11.getCapitalName();
        String capital2 = pais22.getCapitalName();
    
        PilaEncadenada pila = grafo.minPath(capital1, capital2);
    
        float disttotal = 0;
        String fragmento = "Ruta: ";
    
        double longorigen = 0;
        double longdestino = 0;
        double latorigen = 0;
        double latdestino = 0;
        String origennombre = "";
        String destinonombre = "";
    
        while (!pila.isEmpty()) {
            Edge arco = (Edge) pila.pop();
    
            if (arco.getSource().getInfo().getClass().getName().equals("model.data_structures.Landing")) {
                obtenerDatosOrigenDesdeLanding(arco, longorigen, latorigen, origennombre);
            }
            if (arco.getSource().getInfo().getClass().getName().equals("model.data_structures.Country")) {
                obtenerDatosOrigenDesdePais(arco, longorigen, latorigen, origennombre);
            }
            if (arco.getDestination().getInfo().getClass().getName().equals("model.data_structures.Landing")) {
                obtenerDatosDestinoDesdeLanding(arco, longdestino, latdestino, destinonombre);
            }
            if (arco.getDestination().getInfo().getClass().getName().equals("model.data_structures.Country")) {
                obtenerDatosDestinoDesdePais(arco, longdestino, latdestino, destinonombre);
            }
    
            float distancia = Distancia.calcularDistancia(longdestino, latdestino, longorigen, latorigen);
            fragmento += "\n \n Origen: " + origennombre + "  Destino: " + destinonombre + "  Distancia: " + distancia;
            disttotal += distancia;
        }
    
        fragmento += "\n Distancia total: " + disttotal;
    
        return fragmento;
    }
    
    private static void obtenerDatosOrigenDesdeLanding(Edge arco, double longorigen, double latorigen, String origennombre) {
        Landing origen = (Landing) arco.getSource().getInfo();
        longorigen = origen.getLongitude();
        latorigen = origen.getLatitude();
        origennombre = origen.getLandingId();
    }
    
    private static void obtenerDatosOrigenDesdePais(Edge arco, double longorigen, double latorigen, String origennombre) {
        Country origen = (Country) arco.getSource().getInfo();
        longorigen = origen.getLongitude();
        latorigen = origen.getLatitude();
        origennombre = origen.getCapitalName();
    }
    
    private static void obtenerDatosDestinoDesdeLanding(Edge arco, double longdestino, double latdestino, String destinonombre) {
        Landing destino = (Landing) arco.getDestination().getInfo();
        longdestino = destino.getLongitude();
        latdestino = destino.getLatitude();
        destinonombre = destino.getLandingId();
    }
    
    private static void obtenerDatosDestinoDesdePais(Edge arco, double longdestino, double latdestino, String destinonombre) {
        Country destino = (Country) arco.getDestination().getInfo();
        longdestino = destino.getLongitude();
        latdestino = destino.getLatitude();
        destinonombre = destino.getCapitalName();
    }
}
