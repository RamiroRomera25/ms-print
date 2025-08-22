package ar.edu.utn.frc.tup.p4.client.printerClient;

import ar.edu.utn.frc.tup.p4.entities.Printer;

public interface PrinterClient {
    void sendZpl(String zpl, Printer printer);
    String queryStatus(Printer printer);
}
