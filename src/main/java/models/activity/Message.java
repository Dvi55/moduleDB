package models.activity;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.Abonent;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor

public class Message {
    private String message;
    private LocalDateTime sendTime;
    private String receiverNumber;
    Abonent abonent;
}
