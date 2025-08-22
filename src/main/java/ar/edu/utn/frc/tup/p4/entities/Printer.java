package ar.edu.utn.frc.tup.p4.entities;


import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterCreateRequestDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterUpdateRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "printers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Printer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String host;
    private int port;
    private boolean isActive;

    public Printer(PrinterCreateRequestDto printerRequest) {
        this.name = printerRequest.getName();
        this.host = printerRequest.getHost();
        this.port = printerRequest.getPort();
        this.isActive = true;
    }

    public Printer(PrinterUpdateRequestDto printerRequest) {
        this.id = printerRequest.getId();
        this.name = printerRequest.getName();
        this.host = printerRequest.getHost();
        this.port = printerRequest.getPort();
        this.isActive = true;
    }
}
