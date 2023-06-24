package sn.ecpi.ecomerce.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.UserDTO;
import sn.ecpi.ecomerce.entite.User;
import sn.ecpi.ecomerce.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {




    private final ModelMapper modelMapper;

    private final UserService userService;


    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public List<UserDTO>getAllUsers(){
        return userService.getAllUsers().stream().map(user->modelMapper.map(user,UserDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> getUserByUUID(@PathVariable(name = "uuid") UUID uuid){
        User user = userService.findByUuid(uuid);
        UserDTO userResponse = modelMapper.map(user,UserDTO.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        // convert DTO to entity
        User userRequest = modelMapper.map(userDTO, User.class);

        User user = userService.createUser(userRequest);

        // convert entity to DTO
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity<UserDTO>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID uuid, @RequestBody UserDTO userDto) {

        // convert DTO to Entity
        User userRequest = modelMapper.map(userDto, User.class);

        User user = userService.updateUser(uuid, userRequest);

        // entity to DTO
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);

        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/{uuid}")
    public void deletePost(@PathVariable(name = "uuid") UUID uuid) {
        userService.deleteUser(uuid);
    }
}