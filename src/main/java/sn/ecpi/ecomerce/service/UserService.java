package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.embedded.undertow.UndertowReactiveWebServerFactory;
import org.springframework.web.client.ResourceAccessException;
import sn.ecpi.ecomerce.entite.User;
import sn.ecpi.ecomerce.repos.UserRepos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserService {

    private final UserRepos userRepos;

    public List<User> getAllUsers(){
        return userRepos.findAll();
    }

    public User findByUuid(UUID uuid){
        Optional<User> result = userRepos.findById(uuid);
        if(result.isPresent()){
            return result.get();

        }else {
            throw new ResourceAccessException("User not Found");
        }


    }

    public User createUser(User user){
        return userRepos.save(user);
    }

    public User updateUser(UUID uuid, User userResquest){
        User user = userRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("User not found"));
        user.setNom(userResquest.getNom());
        user.setPrenom(userResquest.getPrenom());
        user.setMail(userResquest.getMail());
        user.setTel(userResquest.getTel());
        user.setRole(userResquest.getRole());
        user.setUsername(userResquest.getUsername());
        user.setPassword(userResquest.getPassword());
        user.setDateNaissance(userResquest.getDateNaissance());
        return userRepos.save(user);
    }

    public void deleteUser(UUID uuid){
        User user = userRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("User not found"));
        userRepos.delete(user);
    }
}

