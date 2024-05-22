package ong.bonanza.beneficiarioapi.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "localizacoes", uniqueConstraints = { @UniqueConstraint(columnNames = "geocode") })
public class Localizacao {

    @Id
    @GeneratedValue
    private UUID id;

    private String geocode;

    private String geohash;

    private BigDecimal latitude;

    private BigDecimal longitude;

}
