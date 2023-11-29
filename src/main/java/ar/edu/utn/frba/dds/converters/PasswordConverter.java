package ar.edu.utn.frba.dds.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.apache.commons.codec.digest.DigestUtils; // Necesitas una biblioteca para el cifrado (por ejemplo, Apache Commons Codec)

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return DigestUtils.md5Hex(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // Aquí puedes descifrar la contraseña si es necesario.
        // En este ejemplo, no necesitas descifrar porque usamos MD5, que es irreversible.
        return dbData;
    }
}

