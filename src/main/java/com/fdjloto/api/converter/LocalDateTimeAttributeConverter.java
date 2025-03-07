package com.fdjloto.api.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(FORMATTER) : null;
    }

	@Override
	public LocalDateTime convertToEntityAttribute(String dbData) {
		return dbData == null ? null : LocalDateTime.parse(dbData, FORMATTER);
	}
}

