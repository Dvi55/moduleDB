package models;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.activity.CallActivity;
import models.activity.Message;
import models.activity.NetworkActivity;
import models.activity.SMSActivity;

import java.util.List;

@Data
@NoArgsConstructor
public class Abonent {
    private long id;
    private String name;
    private List<Message> messages;
    private SMSActivity smsActivities;
    private CallActivity callActivities;
    private NetworkActivity networkActivity;
    private List<Device> devices;
    private List<Tariff> tariffs;
}

