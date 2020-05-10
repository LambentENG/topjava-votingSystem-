package org.myvotingsystem.app.model;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity {
    public static final int START_SEQ = 100001;

    @Id
    @SequenceGenerator(name = "global_sequence", sequenceName = "global_sequence", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_sequence")
    @Access(AccessType.PROPERTY)
    protected Integer id;

    public BaseEntity(int id) {
        this.id = id;
    }

    protected BaseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
