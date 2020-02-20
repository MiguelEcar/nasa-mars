package com.ecarsm.preoday.mars.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en-US", timezone = "UTC")
    private Date dateFirst;

    @Column(name = "DATE_LAST")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en-US", timezone = "UTC")
    private Date dateLast;

    @Embedded
    private MarsSensor sensor;

    public MarsSol() {
        this.sensor = new MarsSensor();
    }
}
