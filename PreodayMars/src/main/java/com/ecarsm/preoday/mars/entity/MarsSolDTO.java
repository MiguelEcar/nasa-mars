package com.ecarsm.preoday.mars.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 *
 * @author Ecar. S. M.
 */
@Data
@FieldNameConstants(innerTypeName = "Field")
@AllArgsConstructor
public class MarsSolDTO implements Serializable {

    private Integer sol;

    private Temperature average;

    private MyDate dateLast;

}
