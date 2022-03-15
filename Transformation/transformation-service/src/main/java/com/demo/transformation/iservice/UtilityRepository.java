package com.demo.transformation.iservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.transformation.model.Utility;



@Repository
public interface UtilityRepository extends JpaRepository<Utility, Integer> {

	Utility findByUtilityName(String utilityName);
}
