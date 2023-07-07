package models.activity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Embeddable
@Data
@RequiredArgsConstructor
@ToString
public class CallActivity {
    private int duration;
    @Override
    public String toString() {
        return String.valueOf(duration);
    }
}