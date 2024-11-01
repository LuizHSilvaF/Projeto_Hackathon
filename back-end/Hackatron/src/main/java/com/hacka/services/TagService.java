package com.hacka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacka.entities.Tag;
import com.hacka.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository repo;
	
	public Tag criarTag(Tag tag) {
		return repo.save(tag);
	}
}
