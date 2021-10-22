package com.example.TravelApp.Controllers;


import com.example.TravelApp.Authentication.JwtAuthenticationRequest;
import com.example.TravelApp.Authentication.TokenUtils;
import com.example.TravelApp.Model.Admin;
import com.example.TravelApp.Model.UserTokenState;
import com.example.TravelApp.Service.Implementations.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;

	@Value("36500")
	private int EXPIRES;

	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
																	HttpServletResponse response) {
		List<String> roles = new ArrayList<String>();
		String jwt;
		String expiresIn;
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
							authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			Admin user = (Admin) authentication.getPrincipal();
			jwt = tokenUtils.generateToken(user.getUsername());
			if(authenticationRequest.getKeepMeLoggedIn()){
				expiresIn = "godina";
			}else{
				expiresIn = "300000";
			}

			user.getAuthorities().forEach((a) -> roles.add(a.getAuthority()));
		}
		catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, expiresIn, roles), HttpStatus.OK);
	}

}


