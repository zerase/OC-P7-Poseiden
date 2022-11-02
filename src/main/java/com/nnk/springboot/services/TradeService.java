package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Trade;

public interface TradeService {

	Trade addTrade(Trade trade);
	List<Trade> getAllTrades();
	Trade getTradeById(Integer tradeId);
	Trade updateTrade(Integer tradeId, Trade trade);
	void deleteTrade(Integer tradeId);
}
