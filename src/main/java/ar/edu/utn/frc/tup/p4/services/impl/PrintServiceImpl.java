package ar.edu.utn.frc.tup.p4.services.impl;

import ar.edu.utn.frc.tup.p4.client.ZebraClient;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrintRequestDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrintResponseDto;
import ar.edu.utn.frc.tup.p4.dtos.printer.PrinterStatusDto;
import ar.edu.utn.frc.tup.p4.models.PrintersProperties;
import ar.edu.utn.frc.tup.p4.models.ZebraStatus;
import ar.edu.utn.frc.tup.p4.services.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {

    private final PrintersProperties props;

    public PrintResponseDto printLabel(PrintRequestDto request) {
        try (ZebraClient client = new ZebraClient("192.168.1.50", 9100)) {
            client.sendZpl(request.getZpl());

            ZebraStatus status = client.queryStatus();
            PrintResponseDto response = new PrintResponseDto();
            response.setSuccess(status.isReady());
            response.setMessage(status.isReady() ? "Impresión enviada" : "Impresora no lista");
            return response;

        } catch (Exception e) {
            PrintResponseDto response = new PrintResponseDto();
            response.setSuccess(false);
            response.setMessage("Error: " + e.getMessage());
            return response;
        }
    }

    public PrinterStatusDto getPrinterStatus(String printerId) {
        try (ZebraClient client = new ZebraClient("192.168.1.50", 9100)) {
            ZebraStatus status = client.queryStatus();
            PrinterStatusDto dto = new PrinterStatusDto();
            dto.setReady(status.isReady());
            dto.setLabelsRemaining(status.getLabelsRemaining());
            dto.setRawStatus(status.getRawStatus());
            return dto;
        } catch (Exception e) {
            PrinterStatusDto dto = new PrinterStatusDto();
            dto.setReady(false);
            dto.setLabelsRemaining(0);
            dto.setRawStatus("Error: " + e.getMessage());
            return dto;
        }
    }
}
