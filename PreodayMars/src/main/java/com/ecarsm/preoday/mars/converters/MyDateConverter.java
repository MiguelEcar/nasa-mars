package com.ecarsm.preoday.mars.converters;

import com.ecarsm.preoday.mars.entity.MyDate;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Ecar. S. M.
 */
@Converter(autoApply = true)
public class MyDateConverter implements AttributeConverter<MyDate, Date> {

    @Override
    public Date convertToDatabaseColumn(MyDate attribute) {
        if (attribute == null) {
            return null;
        } else {
            return attribute.getLiteral();
        }

    }

    @Override
    public MyDate convertToEntityAttribute(Date dbData) {
        return new MyDate(dbData);
    }
}
