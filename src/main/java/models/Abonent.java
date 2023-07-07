package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

public class Abonent {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private Long id;
    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "messages")
    @OneToMany(mappedBy = "abonent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;

    @Embedded
    private SMSActivity smsActivities;

    @Column(name = "callActivities")
    @Embedded
    private CallActivity callActivities;

    @Embedded
    private NetworkActivity networkActivity;

    @Embedded
    private Appliance appliance;

    @Embedded
    private Tariff tariffs;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}