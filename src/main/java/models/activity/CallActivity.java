package models.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import models.Abonent;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CallActivity {
    private long id;
    private int duration;
    private Abonent abonent;
}
