package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
@Transactional
public class TradeServiceImpl implements TradeService {
	private static final Logger logger = LoggerFactory.getLogger(TradeService.class);
	
	private final TradeRepository tradeRepository;
	
	@Autowired
	public TradeServiceImpl(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	
	// ======= CREATE =========================================================
	/**
	 * Add a new trade
	 * 
	 * @param trade The new trade to add
	 * @return the saved trade
	 */
	@Override
	public Trade addTrade(Trade trade) {
		logger.info("Call to service that add a new trade");
		Trade tradeToAdd = tradeRepository.save(trade);
		logger.info("New trade {} is created sucessfully", trade);
		return tradeToAdd;
	}

	// ======= READ ALL =======================================================
	/**
	 * Get all trades
	 * 
	 * @return the list of all trades
	 */
	@Override
	public List<Trade> getAllTrades() {
		logger.info("Call to service that get list of all trades");
		List<Trade> allTrades = tradeRepository.findAll();
		
		if(allTrades.isEmpty()) {
			logger.info("No trade in database");
			return new ArrayList<>();
		}
		
		logger.info("Retrieve list of all trade successfully");
		return allTrades;
	}

	// ======= READ ===========================================================
	/**
	 * Get a trade by id
	 * 
	 * @param tradeId The id of the trade to find
	 * @return The trade with the corresponding id requested
	 */
	@Override
	public Trade getTradeById(Integer tradeId) {
		logger.info("Call to service that get a trade by id");
		Trade existingTrade = tradeRepository.findById(tradeId).orElseThrow(() -> {
			logger.error("Failed to retrieve trade with id {}", tradeId);
			throw new IllegalArgumentException("Invalid trade Id : " + tradeId);
		});
		logger.info("Retrieve trade by id successfully");
		return existingTrade;
	}

	// ======= UPDATE =========================================================
	/**
	 * Update an existing trade
	 * 
	 * @param tradeId The id of the requested trade
	 * @param trade The trade to update
	 * @return The updated trade
	 */
	@Override
	public Trade updateTrade(Integer tradeId, Trade trade) {
		logger.info("Call to service that update an existing trade");
		tradeRepository.findById(tradeId).orElseThrow(() -> {
			logger.error("Failed to retrieve trade with id {}", tradeId);
			throw new IllegalArgumentException("Invalid trade Id : " + tradeId);
		});
		trade.setTradeId(tradeId);
		Trade updatedTrade = tradeRepository.save(trade);
		logger.info("Updated trade successfully");
		return updatedTrade;
	}

	// ======= DELETE =========================================================
	/**
	 * Delete a trade
	 * 
	 * @param tradeId The id of the trade to delete
	 */
	@Override
	public void deleteTrade(Integer tradeId) {
		logger.info("Call to service that delete a trade");
		Optional<Trade> tradeToDelete = tradeRepository.findById(tradeId);
		if(tradeToDelete.isPresent()) {
			tradeRepository.delete(tradeToDelete.get());
			logger.info("Trade with id {} deleted successfully", tradeId);
		} else {
			logger.error("Failed to delete trade with id {}", tradeId);
		}
	}
	
}
