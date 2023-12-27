package com.kh.dungjip.estate.model.service;

import java.util.ArrayList;

import java.util.List;

import com.kh.dungjip.common.model.vo.PageInfo;
import com.kh.dungjip.estate.model.vo.EsReLike;
import com.kh.dungjip.estate.model.vo.Estate;
import com.kh.dungjip.estate.model.vo.EstateReview;
import com.kh.dungjip.member.model.vo.Member;

public interface EstateService {

	//부동산 리스트
	ArrayList<Estate> selectEstateList(int houseNo);

	//조회수 증가
	int increaseCount(int esNo);

	//부동산 상세정보
	Estate estateDetail(int esNo);

	List<Integer> selectSubscribeEstateList();

	//부동산 리뷰 리스트
	ArrayList<EstateReview> selectEstateReviewList(int esNo);

	//부동산 리뷰 총점
	int selectEstateReviewSum(int esNo);

	//부동산 리뷰 개수
	int selectEstateReviewCount(int esNo);

	//각 별의 개수
	int selectEstateFiveStar(int esNo);
	int selectEstateFourStar(int esNo);
	int selectEstateThreeStar(int esNo);
	int selectEstateTwoStar(int esNo);
	int selectEstateOneStar(int esNo);

	//조회
	ArrayList<EstateReview> selectEstateReview(Member m);

	//삭제
	int esReviewDelete(int esReNo);

	//수정
	int updateReview(int esReNo, int esReScore, String esReContent);

	//중개사리뷰공감
	ArrayList<EsReLike> memberMypageReviewLike(Member m, PageInfo pi);

	//중개사리뷰공감페이징
	int selectEstateListCountByMember(Member m);

	//공감삭제
	int myEsReviewDelete(int esReNo);




}
