package main.java.engtelecom.std.automationcenter.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

@Service
public class DeviceUpdatePublisherService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishUpdate(String deviceType, JSONObject jsonObject) 
    {
        String queueName = "device_updates_" + deviceType;
        rabbitTemplate.convertAndSend(queueName, jsonObject.toString());
    }
}
