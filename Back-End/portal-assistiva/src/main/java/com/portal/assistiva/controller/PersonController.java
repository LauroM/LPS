package com.portal.assistiva.controller;

import com.portal.assistiva.exeption.ResourceNotFoundException;
import com.portal.assistiva.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.assistiva.repository.PersonRepository;

@RestController
@RequestMapping("/api/v1/")
public class PersonController {



	@Autowired
	private PersonRepository personRepository;
	
	
	@GetMapping("assistiva")
	public List<Person> getAllPerson(){
		return this.personRepository.findAll();
	}

	@GetMapping("/assistiva/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") long personId) throws ResourceNotFoundException{
		
		Person person = personRepository.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Pessoa não encontrado com este id :: " + personId ));
		return ResponseEntity.ok().body(person);
	
	}
	
	@PostMapping("assistiva")
	public Person createPerson(@RequestBody Person person) {
		return this.personRepository.save(person);
	}
	
	@PutMapping("assistiva/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId, @Valid @RequestBody Person personDetail) throws ResourceNotFoundException{
		Person person = personRepository.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Pessoa não encontrado com este id :: " + personId ));
		
		person.setName(personDetail.getName());
		person.setLastname(personDetail.getLastname());
		person.setEmail(personDetail.getEmail()); 
		
		return ResponseEntity.ok(this.personRepository.save(person));
	}
	
	@DeleteMapping("assistiva/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException{
		Person person = personRepository.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Pessoa não encontrado com este id :: " + personId ));
		
		this.personRepository.delete(person);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}

}
