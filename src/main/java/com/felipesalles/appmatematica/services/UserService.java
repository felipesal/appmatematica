package com.felipesalles.appmatematica.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipesalles.appmatematica.domain.User;
import com.felipesalles.appmatematica.domain.dto.UserNewDTO;
import com.felipesalles.appmatematica.domain.enums.Perfil;
import com.felipesalles.appmatematica.repositories.UserRepository;
import com.felipesalles.appmatematica.security.UserSS;
import com.felipesalles.appmatematica.services.exceptions.AuthorizationException;
import com.felipesalles.appmatematica.services.exceptions.BusinessException;
import com.felipesalles.appmatematica.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private UserRepository repo;
	
	@Autowired
	public UserService(UserRepository repo) {
		super();
		this.repo = repo;
		
	}

	public Page<User> findAll(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);

	}

	public Optional<User> findOne(Integer id) {
		
		UserSS loggedUser = authenticated();
		
		if(loggedUser == null || !loggedUser.hasRole(Perfil.ADMIN) && !id.equals(loggedUser.getId()) ) {
			throw new AuthorizationException("Acesso negado");
		}

		 Optional<User> obj =repo.findById(id);
		 
		 return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")));
	}

	public void insert(UserNewDTO userDto) {

		User user = fromDto(userDto);

		if (repo.findByUsername(userDto.getUsername()) != null) {
			throw new BusinessException("Username já cadastrado");
		}

		if(repo.findByEmail(userDto.getEmail()) != null) {
			throw new BusinessException("Email já cadastrado");
		}
		else {
			
			user = repo.save(user);
			emailService.enviaEmailDeConfirmacaoComHtml(user);
		}

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

		return new User(null, userDto.getNome(), userDto.getEmail(), userDto.getUsername(), pe.encode(userDto.getSenha()), 0);

	}
	
	//retorna user logado
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

}
