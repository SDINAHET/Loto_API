package com.fdjloto.api.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 🔹 This class is a JPA attribute converter for `LocalDateTime` values.
 * - Converts `LocalDateTime` to a formatted `String` before storing it in the database.
 * - Converts a `String` from the database back to `LocalDateTime` when retrieving it.
 * - Ensures a consistent date-time format across database operations.
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, String> {

    // ✅ Defines the date-time format for conversion: "yyyy-MM-dd HH:mm:ss"
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 🔹 Converts a `LocalDateTime` entity attribute to a `String` for database storage.
     * - If the input is null, returns null.
     * - Otherwise, formats the `LocalDateTime` as a `String` using the defined pattern.
     *
     * @param localDateTime The `LocalDateTime` value to be converted.
     * @return A formatted date-time string or null if input is null.
     */
    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(FORMATTER) : null;
    }

    /**
     * 🔹 Converts a `String` from the database back to a `LocalDateTime` entity attribute.
     * - If the database value is null, returns null.
     * - Otherwise, parses the date-time string into a `LocalDateTime` object.
     *
     * @param dbData The `String` value retrieved from the database.
     * @return A `LocalDateTime` object or null if input is null.
     */
    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return dbData == null ? null : LocalDateTime.parse(dbData, FORMATTER);
    }
}
