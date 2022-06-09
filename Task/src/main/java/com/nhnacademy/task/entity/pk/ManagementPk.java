package com.nhnacademy.task.entity.pk;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ManagementPk implements Serializable {
    private Integer tagNo;

    private Integer taskNo;
}
