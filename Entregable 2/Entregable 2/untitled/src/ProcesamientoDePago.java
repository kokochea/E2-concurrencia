public class ProcesamientoDePago extends Thread {
    private Paquete paquete;
    private Pedido pedido;

    public ProcesamientoDePago(Paquete paquete, Pedido pedido) {
        this.paquete = paquete;
        this.pedido = pedido;
    }

    @Override
    public void run() {
        try {
            System.out.println("Procesando pago del " + pedido);
            Thread.sleep(300);  // Simula el tiempo de procesamiento de pago
            paquete.setProcesado(true);  // Actualiza el estado de pago procesado
            System.out.println("Pago procesado para el " + pedido);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
