package com.fdjloto.api.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 🔹 This class is a JPA attribute converter that converts `LocalDate` to `String` and vice versa.
 * - Used for storing `LocalDate` as a formatted string (`yyyy-MM-dd`) in the database.
 * - Ensures consistent date formatting for database operations.
 */
@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, String> {

    // ✅ Defines the date format used for conversion (ISO format "yyyy-MM-dd")
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 🔹 Converts a `LocalDate` entity attribute to a `String` for database storage.
     * - If the date is null, returns null.
     * - Otherwise, formats the date as a `String` using `yyyy-MM-dd`.
     *
     * @param localDate The `LocalDate` value to be converted.
     * @return A formatted date string or null if input is null.
     */
    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null) ? null : localDate.format(FORMATTER);
    }

    /**
     * 🔹 Converts a `String` from the database into a `LocalDate` entity attribute.
     * - If the database value is null, returns null.
     * - Otherwise, parses the date string into a `LocalDate` object.
     *
     * @param dbData The `String` value retrieved from the database.
     * @return A `LocalDate` object or null if input is null.
     */
    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : LocalDate.parse(dbData, FORMATTER);
    }
}
