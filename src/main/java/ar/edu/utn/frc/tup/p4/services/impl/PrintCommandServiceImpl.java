package ar.edu.utn.frc.tup.p4.services.impl;

import ar.edu.utn.frc.tup.p4.client.printerClient.PrinterClient;
import ar.edu.utn.frc.tup.p4.entities.Printer;
import ar.edu.utn.frc.tup.p4.services.PrintCommandService;
import ar.edu.utn.frc.tup.p4.services.PrintService;
import ar.edu.utn.frc.tup.p4.services.languageFactory.PrinterClientHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrintCommandServiceImpl implements PrintCommandService {

    private final PrinterClientHandler factory;

    private final PrintService printService;

    public void printTo(Long printerId, String zpl) {
        Printer printer = printService.getById(printerId);
        PrinterClient client = factory.getHandler(printer.getLanguage());
        client.sendZpl(zpl, printer);
    }

    public String getStatus(Long printerId) {
        Printer printer = printService.getById(printerId);
        PrinterClient client = factory.getHandler(printer.getLanguage());
        return client.queryStatus(printer);
    }
}
