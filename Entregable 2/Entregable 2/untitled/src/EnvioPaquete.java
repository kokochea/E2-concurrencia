public class EnvioPaquete extends Thread {
    private Paquete paquete;
    private Pedido pedido;

    public EnvioPaquete(Paquete paquete, Pedido pedido) {
        this.paquete = paquete;
        this.pedido = pedido;
    }

    @Override
    public void run() {
        try {
            // Esperar que el pedido esté empaquetado antes de enviar
            paquete.esperarEmpaquetado();

            System.out.println("Enviando " + pedido);
            Thread.sleep(300);  // Simula el envío
            paquete.setEnviado(true);  // Actualizar el estado a enviado
            System.out.println("Pedido enviado con éxito: " + pedido);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
