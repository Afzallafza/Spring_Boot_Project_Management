package org.babor.boot_project_management.repository;

import org.babor.boot_project_management.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Integer> {

}
