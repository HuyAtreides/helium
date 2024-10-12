package app.helium.projectmanagement.core.application.service;

public interface MessageProducer<KeyType, ValueType> {
    void sendMessage(KeyType key, ValueType value);
}
