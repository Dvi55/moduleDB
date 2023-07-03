package models;

import lombok.*;

@ToString
@Data
@NoArgsConstructor
public class Tariff {
    private long id;
    private String name;
    private int price;
    private Abonent abonent;
}
