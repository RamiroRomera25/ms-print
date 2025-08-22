package ar.edu.utn.frc.tup.p4.services.impl;

import ar.edu.utn.frc.tup.p4.client.printerClient.PrinterClient;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterCreateRequestDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterUpdateRequestDto;
import ar.edu.utn.frc.tup.p4.entities.Printer;
import ar.edu.utn.frc.tup.p4.repositories.PrinterRepository;
import ar.edu.utn.frc.tup.p4.services.PrintService;
import ar.edu.utn.frc.tup.p4.services.languageFactory.PrinterClientHandler;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {

    private final PrinterRepository printerRepository;

    private final PrinterClientHandler factory;

    private static final Logger logger = LoggerFactory.getLogger(PrintServiceImpl.class);

    public List<PrinterDto> getAll() {
        return this.printerRepository.findAllByActiveIsTrue().stream()
                .map(PrinterDto::new)
                .toList();
    }

    public PrinterDto getDtoById(Long id) {
        return new PrinterDto(this.getById(id));
    }

    public Printer getById(Long id) {
        return this.printerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Printer not found with id: " + id));
    }

    public PrinterDto create(PrinterCreateRequestDto printerRequest) {
        Printer printerSaved = printerRepository.save(new Printer(printerRequest));
        return new PrinterDto(printerSaved);
    }

    public PrinterDto update(PrinterUpdateRequestDto printerRequest) {
        Printer printerSaved = printerRepository.save(new Printer(printerRequest));
        return new PrinterDto(printerSaved);
    }


    public void printTo(Long printerId, String zpl) {
        Printer printer = printerRepository.findById(printerId)
                .orElseThrow(() -> new RuntimeException("Impresora no encontrada: id=" + printerId));

        PrinterClient client = factory.getHandler(printer.getLanguage());
        client.sendZpl(zpl, printer);
    }

    public String getStatus(Long printerId) {
        Printer printer = printerRepository.findById(printerId)
                .orElseThrow(() -> new RuntimeException("Impresora no encontrada: id=" + printerId));

        PrinterClient client = factory.getHandler(printer.getLanguage());
        return client.queryStatus(printer);
    }
}
