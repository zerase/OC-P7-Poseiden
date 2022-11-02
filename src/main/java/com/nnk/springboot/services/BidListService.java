package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.BidList;

public interface BidListService {
	
	BidList addBidList(BidList bidList);
	
	List<BidList> getAllBidLists();
	
	BidList getBidListById(Integer bidListId);
	
	BidList updateBidList(Integer bidListId, BidList bidList);
	
	void deleteBidList(Integer bidListId);

}
