package app.helium.projectmanagement.core.application;

public interface MessageProducer<KeyType, ValueType> {
    void sendMessage(KeyType key, ValueType value);
}
