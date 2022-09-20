package br.unicamp.calculadorachico;

public class NoLista<D extends Comparable<D>>
{
    D info;

    public D getInfo() {
        return info;
    }

    public void setInfo(D info) {
        if(info != null)
        {
            this.info = info;
        }
    }

    public NoLista<D> getProx() {
        return prox;
    }

    public void setProx(NoLista<D> prox) {
        this.prox = prox;
    }

    NoLista<D> prox;

    public NoLista(D novaInfo, NoLista<D> proximoNo)
    {
        info = novaInfo;
        prox = proximoNo;
    }

    public NoLista(D novaInfo)
    {
        info = novaInfo;
        prox = null;
    }

}