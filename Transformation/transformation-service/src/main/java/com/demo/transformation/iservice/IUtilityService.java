package com.demo.transformation.iservice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.transformation.model.Utility;



@Service
public interface IUtilityService {

	public void save(Utility utility);
	
	public Optional<Utility> update(int utilityId,Utility utility);
	
	public void delete(int utilityId);
	
	public Optional<Utility> getUtilityById(int utilityId);
	
	public Utility getUtilityByUtilityName(String utilityName);

	public List<Utility> getUtilities();
	
	
}
