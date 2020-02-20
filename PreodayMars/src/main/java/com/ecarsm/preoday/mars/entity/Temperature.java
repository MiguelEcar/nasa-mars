package com.ecarsm.preoday.mars.entity;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Ecar. S. M.
 */
@Data
public class Temperature implements Serializable {

    private Double celsius;
    private Double fahrenheit;

    public Temperature(Double celsius) {
        this.celsius = celsius;
        convert();
    }

    private void convert() {
        this.fahrenheit = (this.celsius * (9 / 5)) + 32;
    }
}
