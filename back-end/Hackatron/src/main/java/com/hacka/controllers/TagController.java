package com.hacka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hacka.entities.Tag;
import com.hacka.services.TagService;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

	@Autowired
	private TagService service;
	
	@PostMapping("/criarTag")
	public Tag criarTag(@RequestBody Tag tag) {
		return service.criarTag(tag);
	}
	
	
}
