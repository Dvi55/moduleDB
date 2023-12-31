package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Embeddable
@Data
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode

public class Tariff {
    private String name;
}