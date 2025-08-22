package ar.edu.utn.frc.tup.p4.repositories;

import ar.edu.utn.frc.tup.p4.entities.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long> {
    Optional<Printer> findByName(String name);

    List<Printer> findAllByActiveIsTrue();
}
