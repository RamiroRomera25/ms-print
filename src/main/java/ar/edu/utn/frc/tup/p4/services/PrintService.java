package ar.edu.utn.frc.tup.p4.services;

import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterCreateRequestDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterUpdateRequestDto;
import ar.edu.utn.frc.tup.p4.entities.Printer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrintService {
    List<PrinterDto> getAll();
    PrinterDto getDtoById(Long id);
    Printer getById(Long id);
    PrinterDto create(PrinterCreateRequestDto printerRequest);
    PrinterDto update(PrinterUpdateRequestDto printerRequest);
}
