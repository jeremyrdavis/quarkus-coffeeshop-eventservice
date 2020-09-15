package com.redhat.demos.quarkus.coffeeshop.events.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class CoffeeshopEvent extends PanacheEntity {

    String eventType;

    String payload;

    Timestamp timestamp;

    public CoffeeshopEvent() {
    }

    public CoffeeshopEvent(String eventType, String payload, Timestamp timestamp) {
        this.eventType = eventType;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CoffeeshopEvent.class.getSimpleName() + "[", "]")
                .add("eventType='" + eventType + "'")
                .add("payload='" + payload + "'")
                .add("timestamp=" + timestamp)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeshopEvent that = (CoffeeshopEvent) o;
        return Objects.equals(eventType, that.eventType) &&
                Objects.equals(payload, that.payload) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, payload, timestamp);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
