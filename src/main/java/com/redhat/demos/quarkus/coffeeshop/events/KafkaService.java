package com.redhat.demos.quarkus.coffeeshop.events;

import com.redhat.demos.quarkus.coffeeshop.events.domain.CoffeeshopEvent;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.transaction.Transactional;
import java.io.StringReader;
import java.sql.Timestamp;
import java.time.Instant;

@ApplicationScoped
public class KafkaService {

    Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Incoming("orders") @Blocking @Transactional
    public void onOrderEvent(String payload) {
        logger.debug("received: {}", payload);

        CoffeeshopEvent coffeeshopEvent = new CoffeeshopEvent(
                getEventType(payload),
                payload,
                Timestamp.from(Instant.now()));

        logger.debug("persisting: {}", coffeeshopEvent);

        coffeeshopEvent.persist();
    }

    String getEventType(String payload) {
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject jsonObject = reader.readObject();
        return jsonObject.getString("eventType");
    }

}
