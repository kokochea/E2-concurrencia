public class Pedido implements Comparable<Pedido> {
    private boolean esUrgente;
    private String descripcion;

    public Pedido(boolean esUrgente, String descripcion) {
        this.esUrgente = esUrgente;
        this.descripcion = descripcion;
    }

    public boolean isUrgente() {
        return esUrgente;
    }

    @Override
    public int compareTo(Pedido otroPedido) {
        // Los pedidos urgentes (true) deben procesarse primero
        return Boolean.compare(otroPedido.esUrgente, this.esUrgente);
    }

    @Override
    public String toString() {
        return descripcion + " (Urgente: " + esUrgente + ")";
    }
}
