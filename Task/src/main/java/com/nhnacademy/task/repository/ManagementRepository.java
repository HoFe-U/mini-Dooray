package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.TagTaskManagement;
import com.nhnacademy.task.entity.pk.ManagementPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementRepository extends JpaRepository<TagTaskManagement, ManagementPk> {
}
