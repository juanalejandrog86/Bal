/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.bal.bean;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author reballesteros
 */
@ManagedBean
@RequestScoped
public class BalBean implements Serializable {

    public BalBean() {
    }

    private String primerNumero;
    private String segundoNumero;
    private String tercerNumero;
    private String cuartoNumero;
    private String quintoNumero;
    private String sextoNumero;

    private String mensajeError;

    private Integer[][] array;
    private Map<Integer, Integer> mapaSalidaUno;
    private Map<Integer, Integer> mapaSalidaDos;
    private Map<Integer, Integer> mapaSalidaTres;
    private Map<Integer, Integer> mapaSalidaCuatro;
    private Map<Integer, Integer> mapaSalidaCinco;
    private Map<Integer, Integer> mapaSalidaSeis;

    public Boolean validarEntrada() {
        if (vacio(primerNumero)
                || vacio(segundoNumero)
                || vacio(tercerNumero)
                || vacio(cuartoNumero)
                || vacio(quintoNumero)
                || vacio(sextoNumero)) {
            return false;
        }
        return true;
    }

    public Boolean validarNumeros() {
        try {
            Long.decode(primerNumero);
            Long.decode(segundoNumero);
            Long.decode(tercerNumero);
            Long.decode(cuartoNumero);
            Long.decode(quintoNumero);
            Long.decode(sextoNumero);
            if ((Integer.valueOf(primerNumero) == 0 || Integer.valueOf(primerNumero) > 45)
                    || (Integer.valueOf(segundoNumero) == 0 || Integer.valueOf(segundoNumero) > 45)
                    || (Integer.valueOf(tercerNumero) == 0 || Integer.valueOf(tercerNumero) > 45)
                    || (Integer.valueOf(cuartoNumero) == 0 || Integer.valueOf(cuartoNumero) > 45)
                    || (Integer.valueOf(quintoNumero) == 0 || Integer.valueOf(quintoNumero) > 45)
                    || (Integer.valueOf(sextoNumero) == 0 || Integer.valueOf(sextoNumero) > 45)) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private Boolean validarRepetidos() {
        if (primerNumero.equals(segundoNumero)
                || primerNumero.equals(tercerNumero)
                || primerNumero.equals(cuartoNumero)
                || primerNumero.equals(quintoNumero)
                || primerNumero.equals(sextoNumero)
                || segundoNumero.equals(tercerNumero)
                || segundoNumero.equals(cuartoNumero)
                || segundoNumero.equals(quintoNumero)
                || segundoNumero.equals(sextoNumero)
                || tercerNumero.equals(cuartoNumero)
                || tercerNumero.equals(quintoNumero)
                || tercerNumero.equals(sextoNumero)
                || cuartoNumero.equals(quintoNumero)
                || cuartoNumero.equals(sextoNumero)
                || quintoNumero.equals(sextoNumero)) {
            return false;
        }
        return true;
    }

    private void resetearValores() {
        mensajeError = "";
        array = null;
        mapaSalidaUno = null;
        mapaSalidaDos = null;
        mapaSalidaTres = null;
        mapaSalidaCuatro = null;
        mapaSalidaCinco = null;
        mapaSalidaSeis = null;
    }

    public void matriz() {
        resetearValores();
        if (validarEntrada()) {
            if (validarNumeros()) {
                if (validarRepetidos()) {
                    Integer[] arregloEntrada;
                    arregloEntrada = convertirEntrada();
                    Integer[][] matriz = new Integer[45][45];
                    Integer numeros = 1;
                    Integer iteradorEntrada = 0;
                    Map<Integer, Integer> tablaSalida1 = new TreeMap<Integer, Integer>();
                    for (int limiteMapa = 1; limiteMapa < 46; limiteMapa++) {
                        tablaSalida1.put(limiteMapa, 0);
                    }
                    for (int columna = 0; columna < 45; columna++) {
                        // Para resetear el recorrido de la entrada
                        if (iteradorEntrada > 5) {
                            iteradorEntrada = 0;
                        }
                        numeros = arregloEntrada[iteradorEntrada];
                        iteradorEntrada++;
                        for (int fila = 0; fila < 45; fila++) {
                            if (numeros > 45) {
                                numeros = 1;
                            }
                            matriz[columna][fila] = numeros;
                            numeros++;
                        }
                    }
                    this.array = matriz;
                    int actual = 0;
                    for (int fila = 0; fila < 45; fila++) {
                        actual = tablaSalida1.get(matriz[fila][fila]);
                        tablaSalida1.put(matriz[fila][fila], actual + 1);
                        
                    }
                    this.mapaSalidaUno = tablaSalida1;
                    salida(arregloEntrada, matriz);
                } else {
                    this.mensajeError = "Hay numeros repetidos";
                }
            } else {
                this.mensajeError = "Solo se pueden ingresar numeros del 1 al 45";
            }
        } else {
            this.mensajeError = "Las entradas No pueden Ser Vacias";
        }
    }

    public Integer[] convertirEntrada() {
        Integer[] retorno = {0, 0, 0, 0, 0, 0};
        retorno[0] = Integer.valueOf(primerNumero);
        retorno[1] = Integer.valueOf(segundoNumero);
        retorno[2] = Integer.valueOf(tercerNumero);
        retorno[3] = Integer.valueOf(cuartoNumero);
        retorno[4] = Integer.valueOf(quintoNumero);
        retorno[5] = Integer.valueOf(sextoNumero);
        return retorno;
    }

    /**
     * Metodo salida.
     *
     * @param arregloEntrada
     * @param matriz
     * @param tablaSalida
     */
    private void salida(Integer[] arregloEntrada, Integer[][] matriz) {
        Integer iteradorEntrada, actual = 0;
        iteradorEntrada = 1;
        while (true) {
            if (iteradorEntrada == 6) {
                break;
            }
            int fil = 0, col = 0;
            boolean izqDer = true, arrAba = true;
            Map<Integer, Integer> tablaSalida2 = new TreeMap<Integer, Integer>();
            for (int limiteMapa = 1; limiteMapa < 46; limiteMapa++) {
                tablaSalida2.put(limiteMapa, 0);
            }
            Map<Integer, Integer> tablaSalida3 = new TreeMap<Integer, Integer>();
            for (int limiteMapa = 1; limiteMapa < 46; limiteMapa++) {
                tablaSalida3.put(limiteMapa, 0);
            }
            Map<Integer, Integer> tablaSalida4 = new TreeMap<Integer, Integer>();
            for (int limiteMapa = 1; limiteMapa < 46; limiteMapa++) {
                tablaSalida4.put(limiteMapa, 0);
            }
            Map<Integer, Integer> tablaSalida5 = new TreeMap<Integer, Integer>();
            for (int limiteMapa = 1; limiteMapa < 46; limiteMapa++) {
                tablaSalida5.put(limiteMapa, 0);
            }
            Map<Integer, Integer> tablaSalida6 = new TreeMap<Integer, Integer>();
            for (int limiteMapa = 1; limiteMapa < 46; limiteMapa++) {
                tablaSalida6.put(limiteMapa, 0);
            }
            for (int columna = 0; columna < 45; columna++) {

                fil = 0;
                izqDer = true;
                arrAba = true;
                if (matriz[0][columna] == Integer.valueOf(this.segundoNumero)) {
                    boolean especial = false;
                    if (columna == 44) {
                        especial = true;
                    }
                    boolean salir2 = true;
                    col = columna;
                    while (salir2) {
                        if (fil == 44) {
                            arrAba = false;
                        }
                        if (col == 44) {
                            izqDer = false;
                        }
                        if (fil == 0 && izqDer && !arrAba) {
                            salir2 = false;
                        }
                        if (col == 0) {
                            izqDer = true;
                        }
                        if (izqDer) {
                            col++;
                        } else {
                            col--;
                        }
                        if (arrAba) {
                            fil++;
                        } else {
                            fil--;
                        }
                        if (salir2) {
                            if (especial && fil == 44) {
                                salir2 = false;
                                actual = tablaSalida2.get(matriz[0][44]);
                                tablaSalida2.put(matriz[0][44], actual + 1);
                            }
                            actual = tablaSalida2.get(matriz[fil][col]);
                            tablaSalida2.put(matriz[fil][col], actual + 1);
                        }
                    }
                    this.mapaSalidaDos = tablaSalida2;
                } else if (matriz[0][columna] == Integer.valueOf(this.tercerNumero)) {
                    boolean especial = false;
                    if (columna == 44) {
                        especial = true;
                    }
                    boolean salir3 = true;
                    col = columna;
                    while (salir3) {
                        if (fil == 44) {
                            arrAba = false;
                        }
                        if (col == 44) {
                            izqDer = false;
                        }
                        if (fil == 0 && izqDer && !arrAba) {
                            salir3 = false;
                        }
                        if (col == 0) {
                            izqDer = true;
                        }
                        if (izqDer) {
                            col++;
                        } else {
                            col--;
                        }
                        if (arrAba) {
                            fil++;
                        } else {
                            fil--;
                        }
                        if (salir3) {
                            if (especial && fil == 44) {
                                salir3 = false;
                                actual = tablaSalida3.get(matriz[0][44]);
                                tablaSalida3.put(matriz[0][44], actual + 1);
                            }
                            actual = tablaSalida3.get(matriz[fil][col]);
                            tablaSalida3.put(matriz[fil][col], actual + 1);
                        }
                    }
                    this.mapaSalidaTres = tablaSalida3;
                } else if (matriz[0][columna] == Integer.valueOf(this.cuartoNumero)) {
                    boolean especial = false;
                    if (columna == 44) {
                        especial = true;
                    }
                    boolean salir4 = true;
                    col = columna;
                    while (salir4) {
                        if (fil == 44) {
                            arrAba = false;
                        }
                        if (col == 44) {
                            izqDer = false;
                        }
                        if (fil == 0 && izqDer && !arrAba) {
                            salir4 = false;
                        }
                        if (col == 0) {
                            izqDer = true;
                        }
                        if (izqDer) {
                            col++;
                        } else {
                            col--;
                        }
                        if (arrAba) {
                            fil++;
                        } else {
                            fil--;
                        }
                        if (salir4) {
                            if (especial && fil == 44) {
                                salir4 = false;
                                actual = tablaSalida4.get(matriz[0][44]);
                                tablaSalida4.put(matriz[0][44], actual + 1);
                            }
                            actual = tablaSalida4.get(matriz[fil][col]);
                            tablaSalida4.put(matriz[fil][col], actual + 1);
                        }
                    }
                    this.mapaSalidaCuatro = tablaSalida4;
                } else if (matriz[0][columna] == Integer.valueOf(this.quintoNumero)) {
                    boolean especial = false;
                    if (columna == 44) {
                        especial = true;
                    }
                    boolean salir5 = true;
                    col = columna;
                    while (salir5) {
                        if (fil == 44) {
                            arrAba = false;
                        }
                        if (col == 44) {
                            izqDer = false;
                        }
                        if (fil == 0 && izqDer && !arrAba) {
                            salir5 = false;
                        }
                        if (col == 0) {
                            izqDer = true;
                        }
                        if (izqDer) {
                            col++;
                        } else {
                            col--;
                        }
                        if (arrAba) {
                            fil++;
                        } else {
                            fil--;
                        }
                        if (salir5) {
                            if (especial && fil == 44) {
                                salir5 = false;
                                actual = tablaSalida5.get(matriz[0][44]);
                                tablaSalida5.put(matriz[0][44], actual + 1);
                            }
                            actual = tablaSalida5.get(matriz[fil][col]);
                            tablaSalida5.put(matriz[fil][col], actual + 1);
                        }

                    }
                    this.mapaSalidaCinco = tablaSalida5;
                } else if (matriz[0][columna] == Integer.valueOf(this.sextoNumero)) {
                    boolean especial = false;
                    if (columna == 44) {
                        especial = true;
                    }
                    boolean salir6 = true;
                    col = columna;
                    while (salir6) {
                        if (fil == 44) {
                            arrAba = false;
                        }
                        if (col == 44) {
                            izqDer = false;
                        }
                        if (fil == 0 && izqDer && !arrAba) {
                            salir6 = false;
                        }
                        if (col == 0) {
                            izqDer = true;
                        }
                        if (izqDer) {
                            col++;
                        } else {
                            col--;
                        }
                        if (arrAba) {
                            fil++;
                        } else {
                            fil--;
                        }
                        if (salir6) {
                            if (especial && fil == 44) {
                                salir6 = false;
                                actual = tablaSalida6.get(matriz[0][44]);
                                tablaSalida6.put(matriz[0][44], actual + 1);
                            }
                            actual = tablaSalida6.get(matriz[fil][col]);
                            tablaSalida6.put(matriz[fil][col], actual + 1);
                        }

                    }
                    this.mapaSalidaSeis = tablaSalida6;
                }
            }
            iteradorEntrada++;
        }
    }

    public Integer[][] getArray() {
        return array;
    }

    public void setArray(Integer[][] array) {
        this.array = array;
    }

    public String getPrimerNumero() {
        return primerNumero;
    }

    public void setPrimerNumero(String primerNumero) {
        this.primerNumero = primerNumero;
    }

    public String getSegundoNumero() {
        return segundoNumero;
    }

    public void setSegundoNumero(String segundoNumero) {
        this.segundoNumero = segundoNumero;
    }

    public String getTercerNumero() {
        return tercerNumero;
    }

    public void setTercerNumero(String tercerNumero) {
        this.tercerNumero = tercerNumero;
    }

    public String getCuartoNumero() {
        return cuartoNumero;
    }

    public void setCuartoNumero(String cuartoNumero) {
        this.cuartoNumero = cuartoNumero;
    }

    public String getQuintoNumero() {
        return quintoNumero;
    }

    public void setQuintoNumero(String quintoNumero) {
        this.quintoNumero = quintoNumero;
    }

    public String getSextoNumero() {
        return sextoNumero;
    }

    public void setSextoNumero(String sextoNumero) {
        this.sextoNumero = sextoNumero;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Map<Integer, Integer> getMapaSalidaUno() {
        return mapaSalidaUno;
    }

    public void setMapaSalidaUno(Map<Integer, Integer> mapaSalidaUno) {
        this.mapaSalidaUno = mapaSalidaUno;
    }

    private Boolean vacio(String parametro) {
        return parametro == null || parametro.trim().isEmpty();
    }

    public Map<Integer, Integer> getMapaSalidaDos() {
        return mapaSalidaDos;
    }

    public void setMapaSalidaDos(Map<Integer, Integer> mapaSalidaDos) {
        this.mapaSalidaDos = mapaSalidaDos;
    }

    public Map<Integer, Integer> getMapaSalidaTres() {
        return mapaSalidaTres;
    }

    public void setMapaSalidaTres(Map<Integer, Integer> mapaSalidaTres) {
        this.mapaSalidaTres = mapaSalidaTres;
    }

    public Map<Integer, Integer> getMapaSalidaCuatro() {
        return mapaSalidaCuatro;
    }

    public void setMapaSalidaCuatro(Map<Integer, Integer> mapaSalidaCuatro) {
        this.mapaSalidaCuatro = mapaSalidaCuatro;
    }

    public Map<Integer, Integer> getMapaSalidaCinco() {
        return mapaSalidaCinco;
    }

    public void setMapaSalidaCinco(Map<Integer, Integer> mapaSalidaCinco) {
        this.mapaSalidaCinco = mapaSalidaCinco;
    }

    public Map<Integer, Integer> getMapaSalidaSeis() {
        return mapaSalidaSeis;
    }

    public void setMapaSalidaSeis(Map<Integer, Integer> mapaSalidaSeis) {
        this.mapaSalidaSeis = mapaSalidaSeis;
    }

}
