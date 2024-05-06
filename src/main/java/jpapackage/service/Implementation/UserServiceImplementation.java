package jpapackage.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jpapackage.repo.EmployeeRepo;
import jpapackage.repo.UserRepo;
import jpapackage.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements  UserService {
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private EmployeeRepo emprepo;
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				if(username.contains("@gmail.com")) {
					return userrepo.findByEmail(username)
							.orElseThrow(()-> new UsernameNotFoundException(username+" not found"));
				}
				return emprepo.findByEmail(username)
						.orElseThrow(()-> new UsernameNotFoundException(username+" not found"));
			
			}
		};
		
	}
	

}
