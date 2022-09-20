package br.unicamp.calculadorachico;

public class Pilha1 {
    private int[]   v;
    private int     topo;

    public Pilha1(){  // construtor
        v = new int[100];
        topo = -1;
    }

    public void empilhar(int e) throws Exception{
        if (cheia()) throw new Exception("Erro de Overflow");;

        topo++;
        v[topo] = e;
    }


    public int  desempilhar() throws Exception{
        if (vazia()) throw new Exception("Erro de Underflow");

        int retorno = v[topo];
        topo--;
        return retorno;

    }

    public int  consulta() throws Exception{
        if (vazia()) throw new Exception("Pilha Vazia");

        return v[topo];
    }

    public boolean cheia(){
        return (topo == (v.length -1));
    }

    public boolean vazia(){
        return (topo == -1);
    }

    public Pilha1 clonar(){
        Pilha1 nova = new Pilha1();

        nova.v    = this.v;
        nova.topo = this.topo;

        return nova;
    }

    public void inverter(){
        int metade = (topo+1)/2;
        int fim = topo;
        int aux=0;

        for (int inicio=0; inicio<metade; inicio++){
            aux = v[inicio];
            v[inicio]=v[fim];
            v[fim--] = aux;
        }
    }

    public String toString(){
        String ret="Topo --> [ ";

        for (int i=topo; i>-1 ; i--){
            ret = ret + v[i] + " ";
        }

        return ret+"]";
    }
}
