package sn.ecpi.ecomerce.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.PasswordDTO;
import sn.ecpi.ecomerce.dto.UserDTO;
import sn.ecpi.ecomerce.entite.User;
import sn.ecpi.ecomerce.pojo.UserPOJO;
import sn.ecpi.ecomerce.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {


    private final ModelMapper modelMapper;

    private final UserService userService;


    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping
    public List<UserPOJO>getAllUsers(){
        return userService.getAllUsers().stream().map(user->modelMapper.map(user,UserPOJO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserPOJO> getUserByUUID(@PathVariable(name = "uuid") UUID uuid){
        User user = userService.findByUuid(uuid);
        UserPOJO userResponse = modelMapper.map(user,UserPOJO.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping
    public ResponseEntity<UserPOJO> createUser(@RequestBody UserDTO userDTO){
        User user = userService.createUser(userDTO);

        UserPOJO userResponse = modelMapper.map(user, UserPOJO.class);
        return new ResponseEntity<UserPOJO>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserPOJO> updateUser(@PathVariable UUID uuid, @RequestBody UserDTO userDto) {

        User user = userService.updateUser(uuid, userDto);


        UserPOJO userResponse = modelMapper.map(user, UserPOJO.class);

        return ResponseEntity.ok().body(userResponse);
    }

    @PutMapping("/password/{uuid}")
    public ResponseEntity<UserPOJO> updatePassword(@PathVariable UUID uuid, @RequestBody PasswordDTO passwordDTO){
        User user = userService.updatePassword(uuid, passwordDTO);

        UserPOJO userResponse = modelMapper.map(user, UserPOJO.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/{uuid}")
    public void deletePost(@PathVariable(name = "uuid") UUID uuid) {
        userService.deleteUser(uuid);
    }
}