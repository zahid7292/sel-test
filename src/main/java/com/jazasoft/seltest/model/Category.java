package com.jazasoft.seltest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jazasoft.mtdb.model.Auditable;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Category extends Auditable {

    @NotEmpty
    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
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
}
