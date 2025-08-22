package ar.edu.utn.frc.tup.p4.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ZebraClient {
    private final String host;
    private final int port;

    public ZebraClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendZpl(String zpl) {
        try (Socket socket = new Socket(host, port);
             OutputStream out = socket.getOutputStream()) {
            out.write(zpl.getBytes());
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("Error enviando ZPL a la impresora " + host + ":" + port, e);
        }
    }

    public String queryStatus() {
        try (Socket socket = new Socket(host, port);
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream()) {

            // Enviar comando ~HS
            out.write("~HS\n".getBytes());
            out.flush();

            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            return new String(buffer, 0, read);

        } catch (Exception e) {
            throw new RuntimeException("Error consultando estado de la impresora " + host + ":" + port, e);
        }
    }
}
