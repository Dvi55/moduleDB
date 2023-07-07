package models.activity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import models.Abonent;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "messages")
@Getter
@Setter
@RequiredArgsConstructor

public class Message {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private Long id;
    @Column(name = "text", nullable = false)
    private String text;
    @Column(name = "receiver_number", nullable = false)
    private String receiverNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abonent_id", nullable = false)
    Abonent abonent;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Message message = (Message) o;
        return getId() != null && Objects.equals(getId(), message.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}