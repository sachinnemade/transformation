package com.demo.transformation.service;

import com.demo.transformation.iservice.TransformationmoduleRepository;
import com.demo.transformation.iservice.iTransformationmoduleService;
import com.demo.transformation.model.Transformationmodule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransformationModuleService implements iTransformationmoduleService {

    @Autowired
    TransformationmoduleRepository transformationmoduleRepository;

    Logger log= LoggerFactory.getLogger(UtilityService.class);

    @Override
    public List<Transformationmodule> getTransformationModules() {

        List<Transformationmodule> modules = transformationmoduleRepository.findAll();
        return modules;

    }

    @Override
    public Transformationmodule getTransformationModule(String moduleid) {

        Transformationmodule module = transformationmoduleRepository.findByModuleid(moduleid);
        return module;

    }

}
