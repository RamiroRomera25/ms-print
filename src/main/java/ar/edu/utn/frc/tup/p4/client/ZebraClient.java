package ar.edu.utn.frc.tup.p4.client;

import ar.edu.utn.frc.tup.p4.models.ZebraStatus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ZebraClient implements AutoCloseable {
    private final Socket socket;
    private final BufferedWriter writer;
    private final BufferedReader reader;

    public ZebraClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "windows-1252"));
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "windows-1252"));
    }

    public void sendZpl(String zpl) throws IOException {
        writer.write(zpl);
        writer.flush();
    }

    public ZebraStatus queryStatus() throws IOException {
        writer.write("~HS\r\n");
        writer.flush();

        StringBuilder raw = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String line = reader.readLine();
            if (line != null) raw.append(line).append("\n");
        }

        return ZebraStatus.parse(raw.toString());
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
