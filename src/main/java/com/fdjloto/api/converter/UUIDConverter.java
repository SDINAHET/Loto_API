package com.fdjloto.api.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return (uuid == null) ? null : uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String uuid) {
        if (uuid == null || uuid.trim().isEmpty()) {
            return null;
        }
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erreur de conversion UUID : " + uuid, e);
        }
    }
}
