package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.controller.exception.BadRequestException;
import com.library.model.Library;
import com.library.service.LibraryService;

@RestController
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	@PostMapping("/library/issue")
	public List<Library> issueBook(@RequestBody List<Library> library) {
		return libraryService.addLedgerEntry(library);
	}
	
	@PutMapping("/library/update")
	public Library updateLedgerEntry(@RequestBody Library library) throws BadRequestException {
		if(library.getLeadgerId() == null || library.getLeadgerId().equals(0L)) {
			throw new BadRequestException("Ledgerid is Required");
		}
		return libraryService.updateLedgerEntry(library);
	}
	
	@GetMapping("/library/user")
	public List<Library> getLibrariesListByUserId(@RequestParam("userId")Long userId){
		return libraryService.getList(userId);
	}	
	
}
