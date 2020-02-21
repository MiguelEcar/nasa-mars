package com.ecarsm.preoday.mars.entity;

import com.ecarsm.preoday.mars.converters.MyDateConverter;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "LAST_FETCH")
@Data
@FieldNameConstants(innerTypeName = "Field")
public class LastFetch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FETCH_DATE")
    @NotNull
    @Convert(converter = MyDateConverter.class)
    private MyDate date;

    public LastFetch() {
        updateDate();
    }

    public void updateDate() {
        this.date = new MyDate(Calendar.getInstance().getTime());
    }
}
