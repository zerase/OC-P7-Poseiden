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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

	@InjectMocks
	TradeServiceImpl tradeService;
	@Mock
	TradeRepository tradeRepository;


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
	@DisplayName("Create new trade")
	void createTradeTest() {
		//GIVEN
		Trade trade =  new Trade();
		trade.setTradeId(1);
		trade.setAccount("Account Test");
		trade.setType("Type Test");
		trade.setBuyQuantity(10.0);
		
		//WHEN
		tradeService.addTrade(trade);
		
		//THEN
		Mockito.verify(tradeRepository, Mockito.times(1)).save(any());
	}

	@Test
	@DisplayName("Get all trades")
	void getAllTradesTest() {
		//GIVEN
		Trade trade1 =  new Trade();
		trade1.setTradeId(1);
		trade1.setAccount("Account Test1");
		trade1.setType("Type Test1");
		trade1.setBuyQuantity(10.0);
		
		Trade trade2 =  new Trade();
		trade2.setTradeId(2);
		trade2.setAccount("Account Test2");
		trade2.setType("Type Test2");
		trade2.setBuyQuantity(20.0);
		
		List<Trade> tradesList = new ArrayList<>();
		tradesList.add(trade1);
		tradesList.add(trade2);
		
		Mockito.when(tradeRepository.findAll()).thenReturn(tradesList);
		
		//WHEN
		List<Trade> allTrades =  tradeService.getAllTrades();
		
		//THEN
		assertEquals(2, allTrades.size());
		assertEquals("Account Test1", allTrades.get(0).getAccount());
	}
	
	@Test
	@DisplayName("Get trade by id")
	void getTradeByIdTest() {
		//GIVEN
		Trade trade =  new Trade();
		trade.setTradeId(1);
		trade.setAccount("Account Test");
		trade.setType("Type Test");
		trade.setBuyQuantity(10.0);
		
		Mockito.when(tradeRepository.findById(anyInt())).thenReturn(Optional.ofNullable(trade));
		
		//WHEN
		Trade actualTrade = tradeService.getTradeById(1);
		
		//THEN
		assertEquals("Type Test", actualTrade.getType());
	}
	
	@Test
	@DisplayName("Update a trade")
	void updateTradeTest() {
		//GIVEN
		Trade tradeToUpdate =  new Trade();
		tradeToUpdate.setTradeId(1);
		tradeToUpdate.setAccount("Account Test");
		tradeToUpdate.setType("Type Test");
		tradeToUpdate.setBuyQuantity(10.0);
		
		Trade updatedTrade =  new Trade();
		updatedTrade.setTradeId(1);
		updatedTrade.setAccount("Account Updated");
		updatedTrade.setType("Type Updated");
		updatedTrade.setBuyQuantity(10.0);
		
		Mockito.when(tradeRepository.findById(anyInt())).thenReturn(Optional.ofNullable(tradeToUpdate));
		Mockito.when(tradeRepository.save(any(Trade.class))).thenReturn(updatedTrade);
		
		//WHEN
		Trade tradeSaved = tradeService.updateTrade(1, updatedTrade);
		
		//THEN
		Mockito.verify(tradeRepository, Mockito.times(1)).save(tradeSaved);
		assertEquals("Account Updated", updatedTrade.getAccount());
	}
	
	@Test
	@DisplayName("Delete a trade")
	void deleteTradeTest() {
		//GIVEN
		Trade tradeToDelete =  new Trade();
		tradeToDelete.setTradeId(1);
		tradeToDelete.setAccount("Account Test");
		tradeToDelete.setType("Type Test");
		tradeToDelete.setBuyQuantity(10.0);
		
		Mockito.when(tradeRepository.findById(anyInt())).thenReturn(Optional.ofNullable(tradeToDelete));
		
		//WHEN
		tradeService.deleteTrade(1);
		
		//THEN
		Mockito.verify(tradeRepository, Mockito.times(1)).delete(tradeToDelete);
	}

}
