package com.ecarsm.preoday.mars.entity;

import com.ecarsm.preoday.mars.converters.TemperatureConverter;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

/**
 *
 * @author Ecar. S. M.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants(innerTypeName = "Field")
@Embeddable
public class MarsSensor implements Serializable {

    @Column(name = "AVERAGE")
    @NotNull
    @Convert(converter = TemperatureConverter.class)
    private Temperature average;

    @Column(name = "MINIMUM")
    @NotNull
    @Convert(converter = TemperatureConverter.class)
    private Temperature minimum;

    @Column(name = "MAXIMUM")
    @NotNull
    @Convert(converter = TemperatureConverter.class)
    private Temperature maximum;

    public MarsSensor(Double av, Double mn, Double mx) {
        this.average = new Temperature(av);
        this.minimum = new Temperature(mn);
        this.maximum = new Temperature(mx);
    }
}
