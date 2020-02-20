package com.ecarsm.preoday.mars.entity;

import com.ecarsm.preoday.mars.converters.MyDateConverter;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 *
 * @author Ecar. S. M.
 */
@Entity
@Table(name = "MARS_SOL")
@Data
@FieldNameConstants(innerTypeName = "Field")
public class MarsSol implements Serializable {

    @Id
    @Column(name = "SOL")
    private Integer sol;

    @Column(name = "DATE_FIRST")
    @NotNull
    @Convert(converter = MyDateConverter.class)
    private MyDate dateFirst;

    @Column(name = "DATE_LAST")
    @NotNull
    @Convert(converter = MyDateConverter.class)
    private MyDate dateLast;

    @Embedded
    private MarsSensor sensor;

    public MarsSol() {
        this.sensor = new MarsSensor();
    }
}
