package ar.edu.utn.frc.tup.p4.controllers;

import ar.edu.utn.frc.tup.p4.dtos.printer.PrintRequestDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrintResponseDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterStatusDto;
import ar.edu.utn.frc.tup.p4.services.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PrintController {

    private final PrintService printService;

    @PostMapping
    public ResponseEntity<PrintResponseDto> print(@RequestBody PrintRequestDto request) {
        PrintResponseDto response = printService.printLabel(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{printerId}")
    public ResponseEntity<PrinterStatusDto> status(@PathVariable String printerId) {
        PrinterStatusDto status = printService.getPrinterStatus(printerId);
        return ResponseEntity.ok(status);
    }
}
