package br.fatec.capsula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.fatec.capsula.model.Integrante;
import br.fatec.capsula.repository.IntegranteRepository;
import br.fatec.capsula.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IntegranteRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Integrante integrante = repo.findByLogin(username);
		if (integrante == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(integrante.getId(), integrante.getLogin(), integrante.getSenha(),
				integrante.getPerfis());
	}

}
