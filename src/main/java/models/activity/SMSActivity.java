package models.activity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@RequiredArgsConstructor
@EqualsAndHashCode

public class SMSActivity {
    private int messageCount;

    @Override
    public String toString() {
        return String.valueOf(messageCount);
    }
}