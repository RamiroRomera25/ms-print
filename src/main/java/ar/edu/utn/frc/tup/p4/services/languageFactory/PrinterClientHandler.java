package ar.edu.utn.frc.tup.p4.services.languageFactory;

import ar.edu.utn.frc.tup.p4.client.printerClient.PrinterClient;
import ar.edu.utn.frc.tup.p4.client.printerClient.ZebraClient;
import ar.edu.utn.frc.tup.p4.enums.PrinterLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrinterClientHandler {

    private final Map<PrinterLanguage, PrinterClient> factoryMap;

    @Autowired
    public PrinterClientHandler(
            ZebraClient zebraClient
    ) {

        factoryMap = Map.of(
                PrinterLanguage.ZEBRA_GK420T, zebraClient
        );
    }

    public PrinterClient getHandler(PrinterLanguage language) {
        return this.factoryMap.get(language);
    }
}
