package models.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import models.Abonent;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NetworkActivity {
    private long id;
    private int gigabytes;
    private Abonent abonent;
}