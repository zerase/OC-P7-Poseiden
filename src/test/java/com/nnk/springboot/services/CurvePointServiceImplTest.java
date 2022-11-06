package com.nnk.springboot.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@ExtendWith(MockitoExtension.class)
class CurvePointServiceImplTest {

	@InjectMocks
	CurvePointServiceImpl curvePointService;
	@Mock
	CurvePointRepository curvePointRepository;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	@DisplayName("Create new curvePoint")
	void createCurvePointTest() {
		//GIVEN
		CurvePoint curvePoint =  new CurvePoint();
		curvePoint.setId(1);
		curvePoint.setCurveId(10);
		curvePoint.setTerm(10.0);
		curvePoint.setValue(10.0);
		
		//WHEN
		curvePointService.addCurvePoint(curvePoint);
		
		//THEN
		Mockito.verify(curvePointRepository, Mockito.times(1)).save(any());
	}

	@Test
	@DisplayName("Get all curvePoints")
	void getAllCurvePointsTest() {
		//GIVEN
		CurvePoint curvePoint1 =  new CurvePoint();
		curvePoint1.setId(1);
		curvePoint1.setCurveId(10);
		curvePoint1.setTerm(10.0);;
		curvePoint1.setValue(10.0);
		
		CurvePoint curvePoint2 =  new CurvePoint();
		curvePoint2.setCurveId(2);
		curvePoint2.setCurveId(20);
		curvePoint2.setTerm(20.0);
		curvePoint2.setValue(20.0);
		
		List<CurvePoint> curvePointsList = new ArrayList<>();
		curvePointsList.add(curvePoint1);
		curvePointsList.add(curvePoint2);
		
		Mockito.when(curvePointRepository.findAll()).thenReturn(curvePointsList);
		
		//WHEN
		List<CurvePoint> allCurvePoints =  curvePointService.getAllCurvePoints();
		
		//THEN
		assertEquals(2, allCurvePoints.size());
		assertEquals(10.0, allCurvePoints.get(0).getTerm());
	}
	
	@Test
	@DisplayName("Get curvePoint by id")
	void getCurvePointByIdTest() {
		//GIVEN
		CurvePoint curvePoint =  new CurvePoint();
		curvePoint.setId(1);
		curvePoint.setCurveId(10);
		curvePoint.setTerm(10.0);
		curvePoint.setValue(10.0);
		
		Mockito.when(curvePointRepository.findById(anyInt())).thenReturn(Optional.ofNullable(curvePoint));
		
		//WHEN
		CurvePoint actualCurvePoint = curvePointService.getCurvePointById(1);
		
		//THEN
		assertEquals(10.0, actualCurvePoint.getValue());
	}
	
	@Test
	@DisplayName("Update a curvePoint")
	void updateCurvePointTest() {
		//GIVEN
		CurvePoint curvePointToUpdate =  new CurvePoint();
		curvePointToUpdate.setId(1);
		curvePointToUpdate.setCurveId(10);
		curvePointToUpdate.setTerm(10.0);
		curvePointToUpdate.setValue(10.0);
		
		CurvePoint updatedCurvePoint =  new CurvePoint();
		updatedCurvePoint.setId(1);
		updatedCurvePoint.setCurveId(100);
		updatedCurvePoint.setTerm(100.0);
		updatedCurvePoint.setValue(100.0);
		
		Mockito.when(curvePointRepository.findById(anyInt())).thenReturn(Optional.ofNullable(curvePointToUpdate));
		Mockito.when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(updatedCurvePoint);
		
		//WHEN
		CurvePoint curvePointSaved = curvePointService.updateCurvePoint(1, updatedCurvePoint);
		
		//THEN
		Mockito.verify(curvePointRepository, Mockito.times(1)).save(curvePointSaved);
		assertEquals(100, updatedCurvePoint.getCurveId());
	}
	
	@Test
	@DisplayName("Delete a curvePoint")
	void deleteCurvePointTest() {
		//GIVEN
		CurvePoint curvePointToDelete =  new CurvePoint();
		curvePointToDelete.setId(1);
		curvePointToDelete.setCurveId(10);
		curvePointToDelete.setTerm(10.0);
		curvePointToDelete.setValue(10.0);
		
		Mockito.when(curvePointRepository.findById(anyInt())).thenReturn(Optional.ofNullable(curvePointToDelete));
		
		//WHEN
		curvePointService.deleteCurvePoint(1);
		
		//THEN
		Mockito.verify(curvePointRepository, Mockito.times(1)).delete(curvePointToDelete);
	}

}
