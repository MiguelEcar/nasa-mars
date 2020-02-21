package com.ecarsm.preoday.mars.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Ecar. S. M.
 */
@Entity
@Table(name = "LOCAL_USER")
@Data
@FieldNameConstants(innerTypeName = "Field")
public class LocalUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotNull
    @Size(max = 100)
    private String name;

    @Column(name = "EMAIL")
    @NotNull
    @Size(max = 100)
    private String email;

    @Column(name = "PASSWORD")
    @NotNull
    @Size(max = 255)
    private String password;

    @PrePersist
    public void crypt() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }
}
