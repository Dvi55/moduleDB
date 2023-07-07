package models.activity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@RequiredArgsConstructor

public class CallActivity {
    @Column(name = "duration")
    private int duration;
}