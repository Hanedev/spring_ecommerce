package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.modelmapper.ModelMapper;
;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import sn.ecpi.ecomerce.dto.PasswordDTO;
import sn.ecpi.ecomerce.dto.UserDTO;
import sn.ecpi.ecomerce.entite.User;
import sn.ecpi.ecomerce.repos.UserRepos;

import java.util.List;

import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class UserService {

    private final UserRepos userRepos;

    private final ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepos.findAll();
    }

    public User findByUuid(UUID uuid){
        return userRepos.findById(uuid).orElseThrow(() -> new ResourceAccessException("User not Found"));
    }

    public User createUser(UserDTO userDTO){
        User userRequest = modelMapper.map(userDTO, User.class);
        userRequest.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepos.save(userRequest);
    }

    public User updateUser(UUID uuid, UserDTO userResquest){
        User userRequest = modelMapper.map(userResquest, User.class);
        User user = userRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("User not found"));
        user.setNom(userResquest.getNom());
        user.setPrenom(userResquest.getPrenom());
        user.setMail(userResquest.getMail());
        user.setTel(userResquest.getTel());
        user.setRole(userResquest.getRole());
        user.setUsername(userResquest.getUsername());
        user.setDateNaissance(userResquest.getDateNaissance());
        return userRepos.save(user);
    }

    public void deleteUser(UUID uuid){
        User user = userRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("User not found"));
        userRepos.delete(user);
    }

    public User updatePassword(UUID uuid, PasswordDTO passwordDTO)  {
        User userRequest = userRepos.findById(uuid).orElseThrow();
        if(passwordEncoder.matches(passwordDTO.getOldPassword(),userRequest.getPassword())) {
            userRequest.setPassword(passwordEncoder.encode(passwordDTO.getNewPassord()));
            return userRepos.save(userRequest);
        } else throw new ResourceAccessException("Password already exists");


    }

}

