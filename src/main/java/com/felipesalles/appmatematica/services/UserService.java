package com.felipesalles.appmatematica.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.domain.dto.UserDTO;
import com.felipesalles.appmatematica.domain.dto.UserNewDTO;
import com.felipesalles.appmatematica.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<UserDTO> findAll(){
		List<User> users = repo.findAll();
		
		List<UserDTO> usersDto = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return usersDto;
	}
	
	public Optional<User> findOne(Integer id) {
		
		return repo.findById(id);
	}
	
	public User insert(UserNewDTO userDto) {
		
		User user = fromDto(userDto);
		
		return repo.save(user);
		
	}
	
	public void delete(Integer id) {
		
		User user = repo.findById(id).get();
		
		repo.delete(user);
	}
	
	public User update(User user) {
		User newUser = repo.findById(user.getId()).get();
		
		updateInfo(user, newUser);
		
		return repo.save(newUser);
	}
	
	public void updateInfo(User user, User newUser) {
		
		newUser.setNome(user.getNome());
		newUser.setEmail(user.getEmail());
		newUser.setSenha(user.getSenha());
		newUser.setUsername(user.getUsername());
		newUser.setPontuacao(user.getPontuacao());
	}
	
	public User fromDto(UserNewDTO userDto) {
		
		return new User(null, userDto.getNome(), userDto.getEmail(), userDto.getUsername(), userDto.getSenha(), 0);
		
	}
	
	
}
