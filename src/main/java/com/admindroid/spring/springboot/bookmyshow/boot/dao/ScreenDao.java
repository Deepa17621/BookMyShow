package com.admindroid.spring.springboot.bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Screen;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.ScreenRepo;



@Repository
public class ScreenDao {
	@Autowired
	ScreenRepo sRepo;
	
	public Screen saveScreen(Screen screen) {
		return sRepo.save(screen);
	}
	
	public Screen findScreen(int screenId) {
		Optional<Screen> opScreen=sRepo.findById(screenId);
		if(opScreen.isPresent()) {
			return opScreen.get();
		}
		return null;
	}
	public Screen updateScreen(Screen screen,int screenId) {
		Screen exScreen=findScreen(screenId);
		if(exScreen != null) {
			screen.setScreenId(screenId);
			return sRepo.save(screen);
		}
		return null;
	}
	public Screen deleteScreen(int screenId) {
		Screen screen=findScreen(screenId);
		sRepo.delete(screen);
		return screen;
	}
	public List<Screen> findAllScreen() {
		return sRepo.findAll();
	}

 
}
