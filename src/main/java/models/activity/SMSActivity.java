package models.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import models.Abonent;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SMSActivity {
    private long id;
    private int messageCount;
    private Abonent abonent;
}
