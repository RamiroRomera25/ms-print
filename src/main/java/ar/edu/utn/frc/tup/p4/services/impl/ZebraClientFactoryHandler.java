package ar.edu.utn.frc.tup.p4.services.impl;

import ar.edu.utn.frc.tup.p4.client.ZebraClient;
import ar.edu.utn.frc.tup.p4.entities.Printer;
import org.springframework.stereotype.Service;

@Service
public class ZebraClientFactoryHandler {

    public ZebraClient createClient(Printer printer) {
        return new ZebraClient(printer.getHost(), printer.getPort());
    }
}
