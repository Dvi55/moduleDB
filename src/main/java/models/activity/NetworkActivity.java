package models.activity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Embeddable
@Data
@RequiredArgsConstructor
@ToString
public class NetworkActivity {
    private Integer megabytes;
    @Override
    public String toString() {
        return String.valueOf(megabytes);
    }
}