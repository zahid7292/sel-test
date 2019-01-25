package com.jazasoft.seltest.model;

import com.jazasoft.mtdb.model.Auditable;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by mdzahidraza on 26/06/17.
 */
@Entity
public class Product extends Auditable {

    @NotEmpty
    private String name;

    private String description;

    @ManyToOne(optional = false)
    private Category category;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
