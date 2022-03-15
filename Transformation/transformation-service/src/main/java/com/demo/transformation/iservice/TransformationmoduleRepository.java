package com.demo.transformation.iservice;

import com.demo.transformation.model.Transformationmodule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransformationmoduleRepository  extends JpaRepository<Transformationmodule, Integer> {
    Transformationmodule findByModulename(String modulename);
    Transformationmodule findByModuleid(String moduleid);
}
