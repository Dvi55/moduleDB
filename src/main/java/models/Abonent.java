package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import models.activity.CallActivity;
import models.activity.Message;
import models.activity.NetworkActivity;
import models.activity.SMSActivity;

import java.util.List;


@Entity
@Table(name = "Abonent")
@Getter
@Setter
@RequiredArgsConstructor

public class Abonent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;

    @Column(name = "name", insertable=false, updatable=false)
    private String name;

    @Column(name = "messages")
    @OneToMany(mappedBy = "abonent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;

    @Column(name = "smsActivities")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "messageCount", column = @Column(name = "smsActivities"))
    })
    private SMSActivity smsActivities;

    @Column(name = "callActivities")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "duration", column = @Column(name = "callActivities"))
    })
    private CallActivity callActivities;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "megabytes", column = @Column(name = "networkActivity"))
    })
    private NetworkActivity networkActivity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "brandName", column = @Column(name = "devices"))
    })
    private Appliance appliance;

    @Embedded
    private Tariff tariffs;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}