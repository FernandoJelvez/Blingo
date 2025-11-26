package com.blingo.lingdyo.services;

import com.blingo.lingdyo.ConexionMySQL;
import com.blingo.lingdyo.User;
import com.blingo.lingdyo.configuration.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void registerNewUserAccount(User user) {
        System.out.println(user.getId());
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        String username = user.getUsername();
        String password = user.getPswd();
        String name = user.getName();
        String lastname = user.getLastname();
        String email = user.getLastname();
        conexionMySQL.addUser(username,password,name,lastname,email);
    }
}
