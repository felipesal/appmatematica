package com.felipesalles.appmatematica.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.domain.dto.UserDTO;
import com.felipesalles.appmatematica.domain.dto.UserNewDTO;
import com.felipesalles.appmatematica.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResources {
	
	@Autowired
	private UserService serv;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserDTO> listarTodos() {
		
		return serv.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User findOne(@PathVariable Integer id) {
		
		return serv.findOne(id).get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User insert(@RequestBody UserNewDTO userDto) {
		
		return serv.insert(userDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		
		serv.delete(id);
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User update(@RequestBody User user, @PathVariable Integer id) {
		return serv.update(user);
	}
	
}
