package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

/**
 * BidListService implementing the business logic
 */
@Service
@Transactional
public class BidListServiceImpl implements BidListService {

	private static final Logger logger = LoggerFactory.getLogger(BidListService.class);
	
	private final BidListRepository bidListRepository;
	
	@Autowired
	public BidListServiceImpl(BidListRepository bidListRepository) {
		this.bidListRepository = bidListRepository;
	}

	
	// ======= CREATE =========================================================
	/**
	 * Add a new bidList
	 * 
	 * @param bidList The new bidList to add
	 * @return the saved bidList
	 */
	@Override
	public BidList addBidList(BidList bidList) {
		logger.info("Call to service that add a new bidList");
		BidList bidListToAdd = bidListRepository.save(bidList);
		logger.info("New bidList {} is created sucessfully", bidList);
		return bidListToAdd;
	}

	// ======= READ ALL =======================================================
	/**
	 * Get all bidLists
	 * 
	 * @return the list of all bidLists
	 */
	@Override
	public List<BidList> getAllBidLists() {
		logger.info("Call to service that get list of all bidLists");
		List<BidList> allBidLists = bidListRepository.findAll();
		
		if(allBidLists.isEmpty()) {
			logger.info("No bidList in database");
			return new ArrayList<>();
		}
		
		logger.info("Retrieve list of all bidList successfully");
		return allBidLists;
	}

	// ======= READ ===========================================================
	/**
	 * Get a bidList by id
	 * 
	 * @param bidListId The id of the bidList to find
	 * @return The bidList with the corresponding id requested
	 */
	@Override
	public BidList getBidListById(Integer bidListId) {
		logger.info("Call to service that get a bidList by id");
		BidList existingBidList = bidListRepository.findById(bidListId).orElseThrow(() -> {
			logger.error("Failed to retrieve bidList with id {}", bidListId);
			throw new IllegalArgumentException("Invalid bidList Id : " + bidListId);
		});
		logger.info("Retrieve bidList by id successfully");
		return existingBidList;
	}

	// ======= UPDATE =========================================================
	/**
	 * Update an existing bidList
	 * 
	 * @param bidListId The id of the requested bidList
	 * @param bidList The bidList to update
	 * @return The updated bidList
	 */
	@Override
	public BidList updateBidList(Integer bidListId, BidList bidList) {
		logger.info("Call to service that update an existing bidList");
		bidListRepository.findById(bidListId).orElseThrow(() -> {
			logger.error("Failed to retrieve bidList with id {}", bidListId);
			throw new IllegalArgumentException("Invalid bidList Id : " + bidListId);
		});
		bidList.setBidListId(bidListId);
		BidList updatedBidList = bidListRepository.save(bidList);
		logger.info("Updated bidList successfully");
		return updatedBidList;
	}

	// ======= DELETE =========================================================
	/**
	 * Delete a bidList
	 * 
	 * @param bidListId The id of the bidList to delete
	 */
	@Override
	public void deleteBidList(Integer bidListId) {
		logger.info("Call to service that delete a bidList");
		Optional<BidList> bidListToDelete = bidListRepository.findById(bidListId);
		if(bidListToDelete.isPresent()) {
			bidListRepository.delete(bidListToDelete.get());
			logger.info("BidList with id {} deleted successfully", bidListId);
		} else {
			logger.error("Failed to delete bidList with id {}", bidListId);
		}
	}
	
}
