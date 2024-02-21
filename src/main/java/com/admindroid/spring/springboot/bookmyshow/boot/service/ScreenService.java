package com.admindroid.spring.springboot.bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.MovieDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.ScreenDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.TheatreAdminDao;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Movie;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Screen;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.TheatreAdmin;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.EmailWrongException;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.MovieNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.PasswordWrongException;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.ScreenNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.TheatreAdminNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.MovieRepo;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;


@Service
public class ScreenService {
	@Autowired
	ScreenDao sDao;
	@Autowired
	MovieRepo mRepo;
	@Autowired
	MovieDao mDao;
	@Autowired
	TheatreAdminDao taDao;
	
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(Screen screen) {
		ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
		structure.setMessage("Screen save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(sDao.saveScreen(screen));
		return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Screen>> findScreen(int screenId) {
		ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
		Screen screen=sDao.findScreen(screenId);
		if(screen != null) {
			structure.setMessage("Screen found success");
			structure.setStatus(HttpStatus .FOUND.value());
			structure.setData(screen);
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.FOUND);
		}
		throw new ScreenNotFound("Screen not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Screen>> deleteScreen(int screenId) {
		ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
		Screen screen=sDao.findScreen(screenId);
		if(screen != null) {
			structure.setMessage("Screen delete success");
			structure.setStatus(HttpStatus .OK.value());
			structure.setData(sDao.deleteScreen(screenId));
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
		}
		throw new ScreenNotFound("we can't delete the screen because,Screen not found for the given id");
	}
	public TheatreAdmin theatreAdminLogin(String theatreAdminEmail,String theatreAdminPassword ) {
		TheatreAdmin tadmin=taDao.findByEmail(theatreAdminEmail);
		if(tadmin.getTheatreAdminEmail().equals(theatreAdminEmail)) {
			if(tadmin.getTheatreAdminPassword().equals(theatreAdminPassword)) {
				return tadmin;
			}
			throw new PasswordWrongException("theatre Admin Password is wrong");
		}
		throw new EmailWrongException("theatre admin email is wrong");
	}
	public ResponseEntity<ResponseStructure<Screen>> assignMoviesToScreen(String theatreAdminEmail,String theatreAdminPassword,int screenId,List<Integer> movieIds) {
		TheatreAdmin tadmin=theatreAdminLogin(theatreAdminEmail, theatreAdminPassword);
		if(tadmin != null) {
		Screen screen=sDao.findScreen(screenId);
		if(screen != null) {
			List<Movie> moviesList=mRepo.findAllById(movieIds);
			screen.setMoviesList(moviesList);
			ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
			structure.setMessage("assign movies to screen success");
			structure.setData(sDao.updateScreen(screen, screenId));
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
		}
		throw new ScreenNotFound("we can't assign movies to screen because,screen not found for given id");
		}
		throw new TheatreAdminNotFound("theatre Admin login required");
	}
	public ResponseEntity<ResponseStructure<Screen>> deleteMovieOrShowInScreen(String theatreAdminEmail,String theatreAdminPassword,int screenId,int movieId) {
		TheatreAdmin tadmin=theatreAdminLogin(theatreAdminEmail, theatreAdminPassword);
		if(tadmin != null) {
		Screen screen=sDao.findScreen(screenId);
		Movie movie=mDao.findMovie(movieId);
		if(screen != null) {
			if(movie != null) {
			List<Movie> exScreens=screen.getMoviesList();
			for (Movie movie2 : exScreens) {
				if(movie2.equals(movie)) {
					exScreens.remove(movie);
				}
			}
			movie.setSeatList(null);
			mDao.deleteMovie(movie.getMovieId());
			screen.setMoviesList(exScreens);
			sDao.updateScreen(screen, screenId);
			ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
			structure.setMessage("delete movies to screen success");
			structure.setData(screen);
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
			}
			throw new MovieNotFound("we can't delete movie In screen because,movie not found for given id");
		}
		throw new ScreenNotFound("we can't delete movie In screen because,screen not found for given id");		
		}
		throw new TheatreAdminNotFound("theatre Admin login required");
	}
	public ResponseEntity<ResponseStructure<Screen>> updateScreen(Screen screen,int screenId) {
		ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
		Screen exScreen=sDao.updateScreen(screen, screenId);
		if(exScreen != null) {
			structure.setMessage("Screen update success");
			structure.setStatus(HttpStatus .OK.value());
			structure.setData(exScreen);;
			return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
		}
		throw new ScreenNotFound("we can't update the screen because,Screen not found for the given id");
		}
	public ResponseEntity<ResponseStructure<List<Screen>>> findAllScreen() {
		ResponseStructure<List<Screen>> structure=new ResponseStructure<List<Screen>>();
		structure.setMessage("find all screen success");
		structure.setData(sDao.findAllScreen());
		structure.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Screen>>>(structure,HttpStatus.FOUND);
	}

}
