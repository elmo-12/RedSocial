/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafoMatriz;

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

    public void nuevoArco(Usuario a, Usuario b) throws Exception {
        int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0)
            throw new Exception("Vértice no existe");
        matAd[va][vb] = 1;
    }

    public void nuevoArco(int va, int vb) throws Exception {
        if (va < 0 || vb < 0)
            throw new Exception("Vértice no existe");
        matAd[va][vb] = 1;
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
}
