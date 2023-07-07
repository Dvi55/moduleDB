package db.migrations;

import models.Appliance;
import models.Tariff;
import models.activity.CallActivity;
import models.activity.Message;
import models.activity.NetworkActivity;
import models.activity.SMSActivity;
import net.datafaker.Faker;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class V1_2__Create_Table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        Faker faker = new Faker();
        try (PreparedStatement abonentStatement = connection.prepareStatement(
                "INSERT INTO Abonent (phoneNumber, name, smsActivities, callActivities, networkActivity, devices, tariffs) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < 2000; i++) {
                String phoneNumber = faker.phoneNumber().phoneNumber();
                String name = faker.name().fullName();

                SMSActivity smsActivities = generateRandomSMSActivity();
                CallActivity callActivities = generateRandomCallActivity();
                NetworkActivity networkActivity = generateRandomNetworkActivity();
                Appliance appliance = generateRandomDevice();
                Tariff tariff = generateRandomTariff();

                abonentStatement.setString(1, phoneNumber);
                abonentStatement.setString(2, name);
                abonentStatement.setObject(3, smsActivities);
                abonentStatement.setObject(4, callActivities);
                abonentStatement.setObject(5, networkActivity);
                abonentStatement.setObject(6, appliance);
                abonentStatement.setObject(7, tariff);
                abonentStatement.executeUpdate();

                ResultSet generatedKeys = abonentStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long abonentId = generatedKeys.getLong(1);


                    List<Message> messages = generateRandomMessages(faker);
                    insertMessages(connection, messages, abonentId);
                }
            }
        }
    }

    private SMSActivity generateRandomSMSActivity() {
        Faker faker = new Faker();
        SMSActivity smsActivity = new SMSActivity();
        smsActivity.setMessageCount(faker.random().nextInt(1, 1000));
        return smsActivity;
    }

    private CallActivity generateRandomCallActivity() {
        Faker faker = new Faker();
        CallActivity callActivity = new CallActivity();
        callActivity.setDuration(faker.random().nextInt(1, 1000));
        return callActivity;
    }

    private NetworkActivity generateRandomNetworkActivity() {
        Faker faker = new Faker();
        NetworkActivity networkActivity = new NetworkActivity();
        networkActivity.setMegabytes(faker.random().nextInt(1, 1000));
        return networkActivity;
    }

    private Appliance generateRandomDevice() {
        Faker faker = new Faker();
        Appliance appliance = new Appliance();
        appliance.setBrandName(faker.device().modelName());
        return appliance;
    }

    private Tariff generateRandomTariff() {
        Faker faker = new Faker();
        Tariff tariff = new Tariff();
        tariff.setName(faker.lorem().word());
        tariff.setPrice(faker.random().nextInt());
        return tariff;
    }

    private List<Message> generateRandomMessages(Faker faker) {
        List<Message> messages = new ArrayList<>();
        int numMessages = faker.random().nextInt(1, 10);

        for (int i = 0; i < numMessages; i++) {
            Message message = new Message();
            message.setText(faker.lorem().sentence());
            message.setReceiverNumber(faker.phoneNumber().phoneNumber());
            messages.add(message);
        }

        return messages;
    }

    private void insertMessages(Connection connection, List<Message> messages, long abonentId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO messages (text, receiver_number, abonent_id) VALUES (?, ?, ?)")) {

            for (Message message : messages) {
                statement.setString(1, message.getText());
                statement.setString(2, message.getReceiverNumber());
                statement.setLong(3, abonentId);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }
}