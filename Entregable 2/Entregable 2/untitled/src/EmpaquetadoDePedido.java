public class EmpaquetadoDePedido extends Thread {
    private Paquete paquete;
    private Pedido pedido;

    public EmpaquetadoDePedido(Paquete paquete, Pedido pedido) {
        this.paquete = paquete;
        this.pedido = pedido;
    }

    @Override
    public void run() {
        try {
            // Espera a que el pago sea procesado antes de empaquetar
            paquete.esperarPago();

            System.out.println("Empaquetando " + pedido);
            Thread.sleep(300);  // Simula el empaquetado
            paquete.setEmpaquetado(true);  // Cambiar el estado a empaquetado
            System.out.println("Pedido empaquetado con éxito: " + pedido);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
