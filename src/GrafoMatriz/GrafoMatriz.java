/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafoMatriz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Datos.Usuario;

/**
 *
 * @author elmot
 */
public class GrafoMatriz {
    int numVerts = 20;
    static int maxVerts = 20;
    Vertice[] verts;
    int[][] matAd;

    public GrafoMatriz() {
        this(maxVerts);
    }

    public GrafoMatriz(int mx) {
        matAd = new int[mx][mx];
        verts = new Vertice[mx];
        for (int i = 0; i < mx; i++) {
            for (int j = 0; j < mx; j++) {
                matAd[i][j] = 0;
            }
        }
        numVerts = 0;
    }

    public void nuevoVertice(Usuario nom) {
        boolean esta = numVertice(nom) >= 0;
        if (!esta) {
            Vertice v = new Vertice(nom);
            v.asigVert(numVerts);
            verts[numVerts++] = v;
        }
    }

    public int numVertice(Usuario vs) {
        Vertice v = new Vertice(vs);
        boolean encontrado = false;
        int i = 0;
        for (; (i < numVerts) && !encontrado;) {
            encontrado = verts[i].equals(v);
            if (!encontrado) {
                i++;
            }
        }
        return (i < numVerts) ? i : -1;
    }

    public boolean adyacente(Usuario a, Usuario b) throws Exception {
        int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0)
            throw new Exception("Vértice no existe");
        return matAd[va][vb] == 1;
    }

    public boolean adyacente(int va, int vb) throws Exception {
        if (va < 0 || vb < 0)
            throw new Exception("Vértice no existe");
        return matAd[va][vb] == 1;
    }

    public void borrarArco(Usuario a, Usuario b) throws Exception {
        int va = numVertice(a);
        int vb = numVertice(b);
        if (va < 0 || vb < 0)
            throw new Exception("Vértice no existe");
        matAd[va][vb] = 0;
    }

    public void borrarArco(int va, int vb) throws Exception {
        if (va < 0 || vb < 0)
            throw new Exception("Vértice no existe");
        matAd[va][vb] = 0;
    }

    public int gradoSalidaVertice(Usuario nombreVertice) throws Exception {
        int indice = numVertice(nombreVertice);
        if (indice < 0)
            throw new Exception("Vértice no existe");

        int gradoSalida = 0;
        for (int i = 0; i < numVerts; i++) {
            if (matAd[indice][i] == 1) { // Contar los arcos que salen del vértice
                gradoSalida++;
            }
        }
        return gradoSalida;
    }

    public int gradoEntradaVertice(Usuario nombreVertice) throws Exception {
        int indice = numVertice(nombreVertice);
        if (indice < 0)
            throw new Exception("Vértice no existe");

        int gradoEntrada = 0;
        for (int i = 0; i < numVerts; i++) {
            if (matAd[i][indice] == 1) { // Contar los arcos que llegan al vértice
                gradoEntrada++;
            }
        }
        return gradoEntrada;
    }

    public void borrarVertice(Usuario nombreVertice) throws Exception {
        int indice = numVertice(nombreVertice);
        if (indice < 0)
            throw new Exception("Vértice no existe");

        // Eliminar el vértice del arreglo de vértices
        for (int i = indice; i < numVerts - 1; i++) {
            verts[i] = verts[i + 1];
        }
        verts[numVerts - 1] = null;
        numVerts--;

        // Eliminar todas las conexiones asociadas al vértice en la matriz de adyacencia
        for (int i = indice; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                matAd[i][j] = matAd[i + 1][j];
            }
        }
        for (int j = indice; j < numVerts; j++) {
            for (int i = 0; i < numVerts; i++) {
                matAd[i][j] = matAd[i][j + 1];
            }
        }
    }

    //Lista de solicitudes
    public List<Usuario> verticesConArcoApuntandoHacia(Usuario nombreVertice) throws Exception {
        int indice = numVertice(nombreVertice);
        if (indice < 0)
            throw new Exception("Vértice no existe");

        List<Usuario> verticesConArco = new ArrayList<>();
        for (int i = 0; i < numVerts; i++) {
            if (matAd[i][indice] == 1) { // Verificar si hay un arco apuntando hacia el vértice dado
                verticesConArco.add(verts[i].info);
            }
        }
        return verticesConArco;
    }

    //Lista de amigos
    public List<Usuario> verticesConArcoIdaYVuelta(Usuario nombreVertice) throws Exception {
        int indice = numVertice(nombreVertice);
        if (indice < 0)
            throw new Exception("Vértice no existe");

        List<Usuario> verticesConArcoIdaYVuelta = new ArrayList<>();
        for (int i = 0; i < numVerts; i++) {
            if (matAd[indice][i] == 1 && matAd[i][indice] == 1) { // Verificar si hay arco de ida y vuelta
                verticesConArcoIdaYVuelta.add(verts[i].info);
            }
        }
        return verticesConArcoIdaYVuelta;
    }
    
    //Enviar solicitud
    public void nuevoArco(Usuario a, Usuario b) throws Exception {
        int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0)
            throw new Exception("Vértice no existe");
        matAd[va][vb] = 1;
    }

    //Sugerencias de amistad
    public List<Usuario> verticesSinRelacionConArcoIdaVuelta(Usuario nombreVertice, int maxVertices) throws Exception {
        int indice = numVertice(nombreVertice);
        if (indice < 0)
            throw new Exception("Vértice no existe");

        List<Usuario> verticesSinRelacion = new ArrayList<>();

        // Verificar si hay vértices con arco de ida y vuelta con el vértice dado
        List<Usuario> verticesConArcoIdaVuelta = verticesConArcoIdaYVuelta(nombreVertice);

        // Iterar sobre todos los vértices para encontrar aquellos que no tengan relación con el vértice dado
        for (int i = 0; i < numVerts; i++) {
            if (i != indice && !verticesConArcoIdaVuelta.contains(verts[i].info)) {
                boolean tieneRelacion = false;
                // Verificar si el vértice tiene alguna relación con otros vértices que tienen arco de ida y vuelta
                for (int j = 0; j < numVerts; j++) {
                    if (matAd[i][j] == 1 && verticesConArcoIdaVuelta.contains(verts[j].info)) {
                        tieneRelacion = true;
                        break;
                    }
                }
                // Si el vértice no tiene relación con vértices que tienen arco de ida y vuelta, agregarlo a la lista
                if (!tieneRelacion) {
                    verticesSinRelacion.add(verts[i].info);
                }
            }
        }

        // Limitar la lista al número máximo de vértices especificado
        if (verticesSinRelacion.size() > maxVertices) {
            // Mezclar aleatoriamente la lista
            Collections.shuffle(verticesSinRelacion, new Random());
            // Retornar una sublista de la lista mezclada con el tamaño máximo especificado
            return verticesSinRelacion.subList(0, maxVertices);
        } else {
            return verticesSinRelacion;
        }
    }
}
