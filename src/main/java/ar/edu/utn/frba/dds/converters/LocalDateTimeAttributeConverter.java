package ar.edu.utn.frba.dds.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {


    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}