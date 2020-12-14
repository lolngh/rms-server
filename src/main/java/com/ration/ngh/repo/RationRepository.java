package com.ration.ngh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ration.ngh.model.RationModel;

@Repository
public interface RationRepository extends JpaRepository<RationModel,Integer> {

	List<RationModel> findByState(String state);
	
	@Query(value="SELECT sum(revenue) FROM Rationshop WHERE state=?1",nativeQuery=true)
	long findRevenueByState(String state);
	
	@Query(value="select distinct state from Rationshop",nativeQuery=true)
	List<String> findDistinctStates();
	
}
