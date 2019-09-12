package com.csexample.momento1_banco;

public class cuenta {
    private String idcliente;
    private String nrocuenta;
    private String fechaapert;
    private String saldo;

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getNrocuenta() {
        return nrocuenta;
    }

    public void setNrocuenta(String nrocuenta) {
        this.nrocuenta = nrocuenta;
    }

    public String getFechaapert() {
        return fechaapert;
    }

    public void setFechaapert(String fechaapert) {
        this.fechaapert = fechaapert;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
