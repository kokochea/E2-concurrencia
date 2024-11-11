import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    public void testPrioridadPedidos() {
        Pedido urgente = new Pedido(true, "Pedido Urgente");
        Pedido normal = new Pedido(false, "Pedido Normal");

        assertTrue(urgente.compareTo(normal) < 0, "El pedido urgente debe procesarse antes que el normal");
    }
}
