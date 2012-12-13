package br.com.ufc.ia.machinelearning.rede;

public class Neuronio {

    private long[] valor;

    public Neuronio() {
        this.valor = new long[10];
    }

    public long getValor(int i) {
        return valor[i];
    }

    public void setValor(int i, long num) {
        this.valor[i] = num;
    }

    public Neuronio produtoReal(int real) {

        Neuronio saida = new Neuronio();

        for (int i = 0; i < 10; ++i) {
            saida.setValor(i, real * valor[i]);
        }

        return saida;
    }

    public long[] getValor() {
        return valor;
    }

    public void setValor(long[] valor) {
        this.valor = valor;
    }

    public double produtoEscalar(Neuronio neuronio) {
        double numero = 0.0;

        for (int i = 0; i < 10; ++i) {
            numero = numero + this.valor[i] * neuronio.getValor(i);
        }

        return numero;
    }

    public Neuronio Diferenca(Neuronio neuronio) {
        Neuronio saida = new Neuronio();

        for (int i = 0; i < 10; ++i) {
            saida.setValor(i, valor[i] - neuronio.getValor(i));
        }
        return saida;
    }

    public Neuronio Soma(Neuronio neuronio) {
        Neuronio saida = new Neuronio();

        for (int i = 0; i < 10; ++i) {
            saida.setValor(i, valor[i] + neuronio.getValor(i));
        }

        return saida;

    }

    public double Norma() {
        double numero;

        numero = this.produtoEscalar(this);
        numero = Math.sqrt(numero);

        return numero;
    }

    public void setValor(Neuronio novo) {
        for (int i = 0; i < 10; i++) {
            this.setValor(i, novo.getValor(i));
        }

    }
}
