import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        PriorityBlockingQueue<Pedido> pedidos = new PriorityBlockingQueue<>();

        // Agregar pedidos a la cola con prioridad
        for (int i = 0; i < 90; i++) {
            pedidos.add(new Pedido(false, "Pedido Normal " + (i + 1)));
        }
        for (int i = 0; i < 10; i++) {
            pedidos.add(new Pedido(true, "Pedido Urgente " + (i + 1)));
        }

        // Crear un solo pool de hilos compartido
        ExecutorService poolCompartido = Executors.newFixedThreadPool(100);

        // Iniciar medición de tiempo total
        long startTimeTotal = System.currentTimeMillis();

        // Procesar los pedidos en base a su urgencia
        while (!pedidos.isEmpty()) {
            Pedido pedido = pedidos.poll();
            Paquete paquete = new Paquete();

            // Procesar el pago en el pool de hilos
            poolCompartido.submit(() -> {
                new ProcesamientoDePago(paquete, pedido).run();

                // Empaquetado en paralelo utilizando streams
                List<Pedido> pedidosAProcesar = List.of(pedido);  // Convertir a una lista temporal
                pedidosAProcesar.parallelStream().forEach(p -> {
                    new EmpaquetadoDePedido(paquete, p).run();
                });

                new EnvioPaquete(paquete, pedido).run();
            });
        }

        // Apagar el pool de hilos compartido y esperar a que finalice
        poolCompartido.shutdown();

        try {
            poolCompartido.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Fin de medición de tiempo total
        long endTimeTotal = System.currentTimeMillis();
        System.out.println("Tiempo total de procesamiento: " + (endTimeTotal - startTimeTotal) + " ms");
    }
}
