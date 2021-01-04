package com.boot.ppp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boot.ppp.beans.CandidateCv;

@Repository
public interface CvRepo extends JpaRepository<CandidateCv, Long> {

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM CandidateCv u WHERE u.email = ?1")
    public Boolean existsByEmail(String email);
	public List<CandidateCv> findByEmail(String email);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM CandidateCv u WHERE u.qualification = ?1")
	public Boolean existsByQualification(String qualification);
	public List<CandidateCv> findByQualification(String qualification);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM CandidateCv u WHERE u.experience = ?1")
	public Boolean existsByExperience(int experience);
	public List<CandidateCv> findByExperience(int experience);
	
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM CandidateCv u WHERE u.skill = ?1")
	public Boolean existsBySkill(String skill);
	public List<CandidateCv> findBySkill(String skill);

}
