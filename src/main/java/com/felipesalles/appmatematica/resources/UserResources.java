package com.felipesalles.appmatematica.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.domain.dto.UserDTO;
import com.felipesalles.appmatematica.domain.dto.UserNewDTO;
import com.felipesalles.appmatematica.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResources {
	
	
	
	private UserService serv;
	
	
	@Autowired
	public UserResources(UserService serv) {
		super();
		this.serv = serv;
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<UserDTO> listarTodos(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "pontuacao") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		
		Page<User> listar = serv.findAll(page, linesPerPage, orderBy, direction);
		
		Page<UserDTO> listarDto = listar.map(x -> new UserDTO(x));
		
		return listarDto;
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User findOne(@PathVariable Integer id) {
		
		return serv.findOne(id).get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void insert(@RequestBody @Valid UserNewDTO userDto) {
		
		serv.insert(userDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
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
