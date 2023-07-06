package models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "Devices")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private Long id;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @ManyToOne
    @JoinColumn(name = "abonent_id")
    private Abonent abonent;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Device device = (Device) o;
        return getId() != null && Objects.equals(getId(), device.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
