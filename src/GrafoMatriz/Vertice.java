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
public class Vertice {
    Usuario info;
    int numVertice;

    public Vertice(Usuario x) {
        info = x;
        numVertice = -1;
    }

    public Usuario nomVertice() {
        return info;
    }

    public boolean equals(Vertice n) {
        return info.equals(n.info);
    }

    public void asigVert(int n) {
        numVertice = n;
    }

    public String toUsuario() {
        return info + " (" + numVertice + ")";
    }
}
