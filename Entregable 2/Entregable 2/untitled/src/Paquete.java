public class Paquete {
    private boolean procesado;
    private boolean empaquetado;
    private boolean enviado;

    // Método sincronizado para el procesamiento de pago
    public synchronized void setProcesado(boolean procesado) {
        this.procesado = procesado;
        notifyAll();  // Notificar a los demás hilos que el pago ha sido procesado
    }

    public synchronized boolean isProcesado() {
        return procesado;
    }

    // Método sincronizado para esperar que el pago se complete
    public synchronized void esperarPago() throws InterruptedException {
        while (!procesado) {
            wait();  // Espera hasta que el pago esté procesado
        }
    }

    // Método sincronizado para el empaquetado
    public synchronized void setEmpaquetado(boolean empaquetado) {
        this.empaquetado = empaquetado;
        notifyAll();  // Notificar que el empaquetado ha sido completado
    }

    public synchronized boolean isEmpaquetado() {
        return empaquetado;
    }

    // Método sincronizado para esperar que el empaquetado se complete
    public synchronized void esperarEmpaquetado() throws InterruptedException {
        while (!empaquetado) {
            wait();  // Espera hasta que el empaquetado esté completo
        }
    }

    // Método sincronizado para el envío
    public synchronized void setEnviado(boolean enviado) {
        this.enviado = enviado;
        notifyAll();  // Notificar que el envío ha sido completado
    }

    public synchronized boolean isEnviado() {
        return enviado;
    }

    // Método sincronizado para esperar que el envío se complete
    public synchronized void esperarEnvio() throws InterruptedException {
        while (!enviado) {
            wait();  // Espera hasta que el pedido haya sido enviado
        }
    }
}
