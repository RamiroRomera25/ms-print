package ar.edu.utn.frc.tup.p4.services;

import org.springframework.stereotype.Service;

@Service
public interface PrintCommandService {
    void printTo(Long printerId, String zpl);
    String getStatus(Long printerId);
}
