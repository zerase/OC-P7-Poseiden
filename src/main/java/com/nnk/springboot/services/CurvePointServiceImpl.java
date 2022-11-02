package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;


@Service
@Transactional
public class CurvePointServiceImpl implements CurvePointService {

	private static final Logger logger = LoggerFactory.getLogger(CurvePointService.class);
	
	private final CurvePointRepository curvePointRepository;
	
	@Autowired
	public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
		this.curvePointRepository = curvePointRepository;
	}

	
	// ======= CREATE =========================================================
	/**
	 * Add a new curvePoint
	 * 
	 * @param curvePoint The new curvePoint to add
	 * @return the saved curvePoint
	 */
	@Override
	public CurvePoint addCurvePoint(CurvePoint curvePoint) {
		logger.info("Call to service that add a new curvePoint");
		
		CurvePoint curvePointToAdd = curvePointRepository.save(curvePoint);
		logger.info("New curvePoint {} is created sucessfully", curvePoint);
		return curvePointToAdd;
	}

	// ======= READ ALL =======================================================
	/**
	 * Get all curvePoints
	 * 
	 * @return the list of all curvePoints
	 */
	@Override
	public List<CurvePoint> getAllCurvePoints() {
		logger.info("Call to service that get list of all curvePoints");
		
		List<CurvePoint> allCurvePoints = curvePointRepository.findAll();
		
		if(allCurvePoints.isEmpty()) {
			logger.info("No curvePoint in database");
			return new ArrayList<>();
		}
		
		logger.info("Retrieve list of all curvePoint successfully");
		return allCurvePoints;
	}

	// ======= READ ===========================================================
	/**
	 * Get a curvePoint by id
	 * 
	 * @param curvePointId The id of the curvePoint to find
	 * @return The curvePoint with the corresponding id requested
	 */
	@Override
	public CurvePoint getCurvePointById(Integer curvePointId) {
		logger.info("Call to service that get a curvePoint by id");
		
		CurvePoint existingCurvePoint = curvePointRepository.findById(curvePointId).orElseThrow(() -> {
			logger.error("Failed to retrieve curvePoint with id {}", curvePointId);
			throw new IllegalArgumentException("Invalid curvePoint Id : " + curvePointId);
		});
		logger.info("Retrieve curvePoint by id successfully");
		return existingCurvePoint;
	}

	// ======= UPDATE =========================================================
	/**
	 * Update an existing curvePoint
	 * 
	 * @param curvePointId The id of the requested curvePoint
	 * @param curvePoint The curvePoint to update
	 * @return The updated curvePoint
	 */
	@Override
	public CurvePoint updateCurvePoint(Integer curvePointId, CurvePoint curvePoint) {
		logger.info("Call to service that update an existing curvePoint");
		
		curvePointRepository.findById(curvePointId).orElseThrow(() -> {
			logger.error("Failed to retrieve curvePoint with id {}", curvePointId);
			throw new IllegalArgumentException("Invalid curvePoint Id : " + curvePointId);
		});
		curvePoint.setId(curvePointId);
		CurvePoint updatedCurvePoint = curvePointRepository.save(curvePoint);
		logger.info("Updated curvePoint successfully");
		return updatedCurvePoint;
	}

	// ======= DELETE =========================================================
	/**
	 * Delete a curvePoint
	 * 
	 * @param curvePointId The id of the curvePoint to delete
	 */
	@Override
	public void deleteCurvePoint(Integer curvePointId) {
		logger.info("Call to service that delete a curvePoint");
		
		Optional<CurvePoint> curvePointToDelete = curvePointRepository.findById(curvePointId);
		if(curvePointToDelete.isPresent()) {
			curvePointRepository.delete(curvePointToDelete.get());
			logger.info("CurvePoint with id {} deleted successfully", curvePointId);
		} else {
			logger.error("Failed to delete curvePoint with id {}", curvePointId);
		}
	}

}
