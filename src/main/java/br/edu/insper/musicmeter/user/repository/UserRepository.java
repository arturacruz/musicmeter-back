package br.edu.insper.musicmeter.user.repository;

import br.edu.insper.musicmeter.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

