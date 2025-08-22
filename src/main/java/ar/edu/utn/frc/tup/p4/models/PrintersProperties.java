package ar.edu.utn.frc.tup.p4.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConfigurationProperties(prefix = "zebra")
public class PrintersProperties {
    private Integer connectTimeoutMs;
    private Integer readTimeoutMs;
    private Integer pollIntervalMs;
    private Integer confirmTimeoutM;
    private String defaultCharset;
    private List<Printer> printers;
}
