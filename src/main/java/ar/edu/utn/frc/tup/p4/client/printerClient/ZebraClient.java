package ar.edu.utn.frc.tup.p4.client.printerClient;

import ar.edu.utn.frc.tup.p4.entities.Printer;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Component
public class ZebraClient implements PrinterClient {

    public void sendZpl(String zpl, Printer printer) {
        try (Socket socket = new Socket(printer.getHost(), printer.getPort());
             OutputStream out = socket.getOutputStream()) {
            out.write(zpl.getBytes());
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("Error enviando ZPL a la impresora " + printer.getHost() + ":" + printer.getPort(), e);
        }
    }

    public String queryStatus(Printer printer) {
        try (Socket socket = new Socket(printer.getHost(), printer.getPort());
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream()) {

            // Enviar comando ~HS
            out.write("~HS\n".getBytes());
            out.flush();

            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            return new String(buffer, 0, read);

        } catch (Exception e) {
            throw new RuntimeException("Error consultando estado de la impresora " + printer.getHost() + ":" + printer.getPort(), e);
        }
    }
}
