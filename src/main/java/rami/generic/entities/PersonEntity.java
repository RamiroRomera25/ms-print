package rami.generic.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rami.generic.entities.base.BaseEntity;
import rami.generic.enums.CidiLevel;
import rami.generic.enums.DocumentType;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
@Entity
public class PersonEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private DocumentType documentType;

    private BigInteger documentNumber;

    private LocalDate birthdate;

    private CidiLevel level = CidiLevel.LEVEL_0;

    private LocalDateTime endSanction;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PersonEntity> familiarGroup;
}
