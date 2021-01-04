package com.boot.ppp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ppp.beans.CandidateCv;
import com.boot.ppp.exception.ResourceAlreadyExistsException;
import com.boot.ppp.exception.ResourceNotFoundException;
import com.boot.ppp.repository.CvRepo;

@Transactional
@Service
public class CandidateCvServiceImpl implements CandidateCvService {

	@Autowired
	CvRepo repository;

	@Override
	public List<CandidateCv> getAllCandiateCv() {
		return (List<CandidateCv>) repository.findAll();
	}

	@Override
	public CandidateCv createCandidateCv(CandidateCv e) throws ResourceAlreadyExistsException {

			if (repository.findById(e.getCandId()) != null && repository.existsById(e.getCandId())) {
				throw new ResourceAlreadyExistsException("Candidate with id: " + e.getCandId() + " already exists");
			}
		else {
			return repository.save(e);
		}
	}

	@Override
	public List<CandidateCv> getCandidateCvByEmail(String email) throws ResourceNotFoundException {
		List<CandidateCv> cand = repository.findByEmail(email);
		if (cand == null)
			throw new ResourceNotFoundException("Cannot find candidate with email " + email);
		else
			return cand;
	}

	@Override
	public List<CandidateCv> getCandidateCvByQualification(String qualification) throws ResourceNotFoundException {
		List<CandidateCv> cand = repository.findByQualification(qualification);
		if (cand == null)
			throw new ResourceNotFoundException("Cannot find candidate with qualification " + qualification);
		else
			return cand;
	}

	@Override
	public List<CandidateCv> getByExperience(int experience) throws ResourceNotFoundException {
		List<CandidateCv> cand = repository.findByExperience(experience);
		if (cand == null)
			throw new ResourceNotFoundException("Cannot find candidate with experience " + experience);
		else
			return cand;
	}

	@Override
	public List<CandidateCv> getBySkill(String skill) throws ResourceNotFoundException {
		List<CandidateCv> cand = repository.findBySkill(skill);
		if (cand == null)
			throw new ResourceNotFoundException("Cannot find candidate with skill " + skill);
		else
			return cand;
	}

	@Override
	public void updateCandidateCv(CandidateCv cv) throws ResourceNotFoundException {
		Long id = cv.getCandId();
		if (!repository.existsById(id))
			throw new ResourceNotFoundException("Cannot find candiadte with id " + id);
		else
			repository.saveAndFlush(cv);
	}

	@Override
	public void removeCv(long id) throws ResourceNotFoundException {
		if (!repository.existsById(id))
			throw new ResourceNotFoundException("Cannot find candiadte with id "  + id);
		else
			repository.deleteById(id);		
	}

	@Override
	public CandidateCv getCandidateCvById(long id) throws ResourceNotFoundException {
		CandidateCv cand = repository.findById(id).orElse(null);
		if (cand == null)
			throw new ResourceNotFoundException("Cannot find candidate with id " + id);
		else
			return cand;
	}


}
