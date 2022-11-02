package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {
	
	CurvePoint addCurvePoint(CurvePoint curvePoint);
	
	List<CurvePoint> getAllCurvePoints();
	
	CurvePoint getCurvePointById(Integer curvePointId);
	
	CurvePoint updateCurvePoint(Integer curvePointId, CurvePoint curvePoint);
	
	void deleteCurvePoint(Integer curvePointId);
	
}
