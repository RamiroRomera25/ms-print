package ar.edu.utn.frc.tup.p4.services;

import ar.edu.utn.frc.tup.p4.dtos.printer.PrintRequestDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrintResponseDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterStatusDto;
import org.springframework.stereotype.Service;

@Service
public interface PrintService {
    PrintResponseDto printLabel(PrintRequestDto request);
    PrinterStatusDto getPrinterStatus(String printerId);
}
