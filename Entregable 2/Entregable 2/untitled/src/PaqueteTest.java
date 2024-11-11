import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaqueteTest {

    @Test
    public void testProcesamientoDePaquete() throws InterruptedException {
        Paquete paquete = new Paquete();
        Pedido pedido = new Pedido(true, "Pedido Urgente");

        ProcesamientoDePago pago = new ProcesamientoDePago(paquete, pedido);
        pago.start();
        pago.join();

        assertTrue(paquete.isProcesado(), "El paquete debe estar procesado");
    }
}
