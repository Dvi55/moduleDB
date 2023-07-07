package models.activity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@RequiredArgsConstructor
public class NetworkActivity {
    private Integer megabytes;
}