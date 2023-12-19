package com.kh.dungjip.enquiry.model.service;

import java.util.ArrayList;

import com.kh.dungjip.enquiry.model.vo.Enquiry;

public interface EnquiryService {
	//1:1문의 등록
	int insertEnquiry(Enquiry en);
	//관리자 댓글 등록
	int insertReply(Enquiry en);
	//더보기
	ArrayList<Enquiry> moreEnquiry(Enquiry en);
	
}