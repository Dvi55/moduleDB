package models;

import jakarta.persistence.*;
import lombok.*;
import models.activity.CallActivity;
import models.activity.Message;
import models.activity.NetworkActivity;
import models.activity.SMSActivity;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


@Entity
@Table(name = "Abonent")

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Abonent {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "messages")
    @OneToMany(mappedBy = "abonent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;

    @Column(name = "smsActivities")
    @Embedded
    private SMSActivity smsActivities;

    @Column(name = "callActivities")
    @Embedded
    private CallActivity callActivities;

    @Column(name = "networkActivity")
    @Embedded
    private NetworkActivity networkActivity;

    @Column(name = "devices")
    @OneToMany(mappedBy = "abonent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Device> devices;

    @Column(name = "tariffs")
    @OneToMany(mappedBy = "abonent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tariff> tariffs;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}

