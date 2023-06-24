package sn.ecpi.ecomerce.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.ClientDTO;
import sn.ecpi.ecomerce.entite.Client;
import sn.ecpi.ecomerce.service.ClientService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ModelMapper modelMapper;
    private final ClientService clientService;

    public ClientController(ModelMapper modelMapper, ClientService clientService) {
        this.modelMapper = modelMapper;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientDTOs = clients.stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDTOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ClientDTO> getClientByUUID(@PathVariable("uuid") UUID uuid) {
        Client client = clientService.findByUuid(uuid);
        if (client != null) {
            ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
            return ResponseEntity.ok(clientDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        Client createdClient = clientService.createClient(client);
        ClientDTO createdClientDTO = modelMapper.map(createdClient, ClientDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClientDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable("uuid") UUID uuid,
            @RequestBody ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        Client updatedClient = clientService.updateClient(uuid, client);
        if (updatedClient != null) {
            ClientDTO updatedClientDTO = modelMapper.map(updatedClient, ClientDTO.class);
            return ResponseEntity.ok(updatedClientDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteClient(@PathVariable("uuid") UUID uuid) {
        clientService.deleteClient(uuid);
    }
}
