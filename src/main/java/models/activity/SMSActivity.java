package models.activity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Embeddable

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class SMSActivity {
    @Column(name = "message_count")
    private int messageCount;
}
