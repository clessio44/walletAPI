package com.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.service.UserService;
import com.wallet.response.Response;
import jakarta.validation.Valid;

@RestController
@EnableWebMvc
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result) {
		
		Response<UserDTO> response = new Response<UserDTO>();
		
		User user = service.save(this.convertDtoToEntity(dto));
		response.setData(this.convertEntityToDto(user));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	private User convertDtoToEntity(UserDTO dto) {
		User u = new User();
		u.setEmail(dto.getEmail());
		u.setName(dto.getName());
		u.setPassword(dto.getPassword());
		return u;
	}
	private UserDTO convertEntityToDto(User u) {
		UserDTO dto = new UserDTO();
		dto.setEmail(dto.getEmail());
		dto.setName(dto.getName());
		dto.setPassword(dto.getPassword());
		return dto;
	}
}
