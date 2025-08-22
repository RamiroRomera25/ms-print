package ar.edu.utn.frc.tup.p4.controllers;

import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterCreateRequestDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterUpdateRequestDto;
import ar.edu.utn.frc.tup.p4.services.PrintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PrintController {

    private final PrintService printService;

    @GetMapping
    public ResponseEntity<List<PrinterDto>> getAll() {
        List<PrinterDto> printers = printService.getAll();
        return ResponseEntity.ok(printers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrinterDto> getById(@PathVariable Long id) {
        PrinterDto printer = printService.getDtoById(id);
        return ResponseEntity.ok(printer);
    }

    @PostMapping
    public ResponseEntity<PrinterDto> create(@RequestBody @Valid PrinterCreateRequestDto request) {
        PrinterDto printer = printService.create(request);
        return ResponseEntity.ok(printer);
    }

    @PutMapping
    public ResponseEntity<PrinterDto> update(@RequestBody @Valid PrinterUpdateRequestDto request) {
        PrinterDto printer = printService.update(request);
        return ResponseEntity.ok(printer);
    }
}
