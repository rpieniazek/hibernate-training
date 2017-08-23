package pl.sda.hibernatetraining.model;

import javax.persistence.Entity;

@Entity
public class ItBook extends Book {

    private Boolean hasVirtualVersion;
}
