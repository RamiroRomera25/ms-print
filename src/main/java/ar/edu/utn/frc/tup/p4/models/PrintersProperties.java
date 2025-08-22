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
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer pollInterval;
    private Integer confirmTimeout;
    private String defaultCharset;
    private List<Printer> printers;
}
