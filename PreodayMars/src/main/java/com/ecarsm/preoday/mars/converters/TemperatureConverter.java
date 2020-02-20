package com.ecarsm.preoday.mars.converters;

import com.ecarsm.preoday.mars.entity.Temperature;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Ecar. S. M.
 */
@Converter(autoApply = true)
public class TemperatureConverter implements AttributeConverter<Temperature, Double> {

    @Override
    public Double convertToDatabaseColumn(Temperature attribute) {
        if (attribute == null) {
            return null;
        } else {
            return attribute.getCelsius();
        }

    }

    @Override
    public Temperature convertToEntityAttribute(Double dbData) {
        return new Temperature(dbData);
    }
}
