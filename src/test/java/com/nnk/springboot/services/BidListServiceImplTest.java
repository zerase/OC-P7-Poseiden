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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@ExtendWith(MockitoExtension.class)
class BidListServiceImplTest {

	@InjectMocks
	BidListServiceImpl bidListService;
	@Mock
	BidListRepository bidListRepository;


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
	@DisplayName("Create new bidList")
	void createBidListTest() {
		//GIVEN
		BidList bidList =  new BidList();
		bidList.setBidListId(1);
		bidList.setAccount("Account Test");
		bidList.setType("Type Test");
		bidList.setBidQuantity(10.0);
		
		//WHEN
		bidListService.addBidList(bidList);
		
		//THEN
		Mockito.verify(bidListRepository, Mockito.times(1)).save(any());
	}

	@Test
	@DisplayName("Get all bidLists")
	void getAllBidListsTest() {
		//GIVEN
		BidList bidList1 =  new BidList();
		bidList1.setBidListId(1);
		bidList1.setAccount("Account Test1");
		bidList1.setType("Type Test1");
		bidList1.setBidQuantity(10.0);
		
		BidList bidList2 =  new BidList();
		bidList2.setBidListId(2);
		bidList2.setAccount("Account Test2");
		bidList2.setType("Type Test2");
		bidList2.setBidQuantity(20.0);
		
		List<BidList> bidListsList = new ArrayList<>();
		bidListsList.add(bidList1);
		bidListsList.add(bidList2);
		
		Mockito.when(bidListRepository.findAll()).thenReturn(bidListsList);
		
		//WHEN
		List<BidList> allBidLists =  bidListService.getAllBidLists();
		
		//THEN
		assertEquals(2, allBidLists.size());
		assertEquals("Account Test1", allBidLists.get(0).getAccount());
	}
	
	@Test
	@DisplayName("Get bidList by id")
	void getBidListByIdTest() {
		//GIVEN
		BidList bidList =  new BidList();
		bidList.setBidListId(1);
		bidList.setAccount("Account Test");
		bidList.setType("Type Test");
		bidList.setBidQuantity(10.0);
		
		Mockito.when(bidListRepository.findById(anyInt())).thenReturn(Optional.ofNullable(bidList));
		
		//WHEN
		BidList actualBidList = bidListService.getBidListById(1);
		
		//THEN
		assertEquals("Type Test", actualBidList.getType());
	}
	
	@Test
	@DisplayName("Update a bidList")
	void updateBidListTest() {
		//GIVEN
		BidList bidListToUpdate =  new BidList();
		bidListToUpdate.setBidListId(1);
		bidListToUpdate.setAccount("Account Test");
		bidListToUpdate.setType("Type Test");
		bidListToUpdate.setBidQuantity(10.0);
		
		BidList updatedBidList =  new BidList();
		updatedBidList.setBidListId(1);
		updatedBidList.setAccount("Account Updated");
		updatedBidList.setType("Type Updated");
		updatedBidList.setBidQuantity(10.0);
		
		Mockito.when(bidListRepository.findById(anyInt())).thenReturn(Optional.ofNullable(bidListToUpdate));
		Mockito.when(bidListRepository.save(any(BidList.class))).thenReturn(updatedBidList);
		
		//WHEN
		BidList bidListSaved = bidListService.updateBidList(1, updatedBidList);
		
		//THEN
		Mockito.verify(bidListRepository, Mockito.times(1)).save(bidListSaved);
		assertEquals("Account Updated", updatedBidList.getAccount());
	}
	
	@Test
	@DisplayName("Delete a bidList")
	void deleteBidListTest() {
		//GIVEN
		BidList bidListToDelete =  new BidList();
		bidListToDelete.setBidListId(1);
		bidListToDelete.setAccount("Account Test");
		bidListToDelete.setType("Type Test");
		bidListToDelete.setBidQuantity(10.0);
		
		Mockito.when(bidListRepository.findById(anyInt())).thenReturn(Optional.ofNullable(bidListToDelete));
		
		//WHEN
		bidListService.deleteBidList(1);
		
		//THEN
		Mockito.verify(bidListRepository, Mockito.times(1)).delete(bidListToDelete);
	}
}
