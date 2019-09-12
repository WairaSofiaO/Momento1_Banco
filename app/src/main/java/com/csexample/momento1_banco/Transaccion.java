package com.csexample.momento1_banco;

class Transaccion {
    private String nrotrans;
    private String nrocuenta_orig;
    private String nrocuenta_dest;
    private String fecha;
    private String valor;

    public String getNrotrans() {
        return nrotrans;
    }

    public void setNrotrans(String nrotrans) {
        this.nrotrans = nrotrans;
    }

    public String getNrocuenta_orig() {
        return nrocuenta_orig;
    }

    public void setNrocuenta_orig(String nrocuenta_orig) {
        this.nrocuenta_orig = nrocuenta_orig;
    }

    public String getNrocuenta_dest() {
        return nrocuenta_dest;
    }

    public void setNrocuenta_dest(String nrocuenta_dest) {
        this.nrocuenta_dest = nrocuenta_dest;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
