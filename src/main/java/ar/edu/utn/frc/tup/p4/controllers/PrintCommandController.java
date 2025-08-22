package ar.edu.utn.frc.tup.p4.controllers;

import ar.edu.utn.frc.tup.p4.services.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PrintCommandController {

    private final PrintService printService;

    @PostMapping("/{printerId}/print")
    public ResponseEntity<String> print(@PathVariable Long printerId, @RequestBody String zpl) {
        printService.printTo(printerId, zpl);
        return ResponseEntity.ok("Trabajo enviado a la impresora con ID: " + printerId);
    }

    @GetMapping("/{printerId}/status")
    public ResponseEntity<String> getStatus(@PathVariable Long printerId) {
        String status = printService.getStatus(printerId);
        return ResponseEntity.ok(status);
    }
}
