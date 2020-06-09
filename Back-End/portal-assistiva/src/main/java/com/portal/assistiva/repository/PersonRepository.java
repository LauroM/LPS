package com.portal.assistiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portal.assistiva.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
