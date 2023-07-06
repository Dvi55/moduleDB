package models.activity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class NetworkActivity {
    @NonNull
    private Integer megabytes;
}