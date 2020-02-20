package com.ecarsm.preoday.mars.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Data;

/**
 *
 * @author Ecar. S. M.
 */
@Data
public class Temperature implements Serializable {

    private String celsius;
    private String fahrenheit;

    public Temperature(Double celsius) {
        this.celsius = formatAndRound(celsius);
        convert(celsius);
    }

    private void convert(Double celsius) {
        Double fahrenheit = (celsius * (9 / 5)) + 32;
        this.fahrenheit = formatAndRound(fahrenheit);
    }

    private String formatAndRound(Double value) {
        BigDecimal big = new BigDecimal(value);
        big = big.setScale(0, RoundingMode.HALF_EVEN);

        return big.toString();
    }
}
