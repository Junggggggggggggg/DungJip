package com.kh.dungjip.house.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.dungjip.house.model.vo.House;
import com.kh.dungjip.estate.model.service.EstateService;
import com.kh.dungjip.estate.model.vo.Estate;
import com.kh.dungjip.house.model.service.HouseService;

@Controller
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	@Autowired 
	private EstateService estateService;
	
	@RequestMapping("insert.house")
	public String insertHouse(HttpSession session) throws IOException, ParseException {
		Reader reader = new FileReader("C:\\Users\\easyoh\\git\\DungJip\\Dungjip\\src\\main\\webapp\\WEB-INF\\resources\\jik.json");
			
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(reader);
		JSONObject jsonMain = (JSONObject) obj;
		
		System.out.println(jsonMain);
		JSONArray jsonArr = (JSONArray) jsonMain.get("items");
		System.out.println(jsonArr);
		
		ArrayList<House> hlist = new ArrayList<>();
		
		for(int i = 0; i < jsonArr.size(); i++) {
			JSONObject object = (JSONObject) jsonArr.get(i);
			JSONObject sqrtP = (JSONObject) object.get("sqrtP");
			JSONObject location = (JSONObject) object.get("random_location");
			
			String isoDate = String.valueOf(object.get("reg_date"));
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(isoDate, DateTimeFormatter.ISO_DATE_TIME);
			LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
			
			Date sqlDate = Date.valueOf(localDateTime.toLocalDate());
			
			House house = House.builder()
											.housePrice(Integer.parseInt(String.valueOf(object.get("deposit"))))
											.houseSquare(Double.parseDouble(String.valueOf(sqrtP.get("p"))))
											.houseStyle((String)object.get("sales_type"))
											.houseTitle((String)object.get("title"))
											.houseDetails((String)object.get("details"))
											.houseType((String)object.get("service_type"))
											.houseLatitude((Double)location.get("lat"))
											.houseLongitude((Double)location.get("lng"))
											.houseUploadTime(sqlDate)
											.houseAddress((String)object.get("address1"))
											.houseFloor(Integer.parseInt(String.valueOf(object.get("floor"))))
											.houseBuildingFloor(Integer.parseInt(String.valueOf(object.get("building_floor"))))
											.build();
			
			hlist.add(house);
			
		}
		
		int result = 1;
		
		for(House house : hlist) {
			int count = houseService.insertHouseJSON(house);
			
			if(result * count == 0) {
				session.setAttribute("alertMsg", "집 등록 실패");
				return "common/errorPage";
			}
		}

		session.setAttribute("alertMsg", "집 등록 성공");
		return "main";
		
		
	}
	
	@ResponseBody
	@RequestMapping("select.location")
	public ArrayList<House> selectLocation() {
		ArrayList<House> lList = houseService.selectLocations();
		
		return lList;
	}

	
	//집 상세페이지
	@RequestMapping("detail.ho")
	public String houseDetail(Model model){		
		
		
		ArrayList<Estate> elist = estateService.selectEstateList();
		
		//부동산 목록 조회해서 보여주기
		model.addAttribute("elist",elist);
		
		System.out.println("나오는지 보자");
		
	    System.out.println("elist: " + elist);
	    
		
		return "house/houseDetail";
	}
	
	
	@RequestMapping("villa.map")
	public ModelAndView villaMap(@RequestParam(value="locate", defaultValue="서울 영등포구 양평동4가 2") String locate,ModelAndView mv) {
		ArrayList<House> lList = houseService.selectHouse();
		
		mv.addObject("lList", lList).addObject("locate", locate).setViewName("house/houseMap");
		
		return mv;
	}
}






















