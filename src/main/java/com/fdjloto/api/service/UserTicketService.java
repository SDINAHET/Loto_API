package com.fdjloto.api.service;

import com.fdjloto.api.model.UserTicket;
import com.fdjloto.api.repository.UserRepository;
import com.fdjloto.api.repository.UserTicketRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserTicketService {
    private final UserTicketRepository userTicketRepository;
    private final UserRepository userRepository;

    public UserTicketService(UserTicketRepository userTicketRepository, UserRepository userRepository) {
        this.userTicketRepository = userTicketRepository;
        this.userRepository = userRepository;
    }

    public List<UserTicket> getTicketsByUser(String userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        return userTicketRepository.findByUserId(userId);
    }

    public List<UserTicket> getUsersByTicket(String ticketId) {
        return userTicketRepository.findByTicketId(ticketId);
    }

    public UserTicket assignUserToTicket(UserTicket userTicket) {
        return userTicketRepository.save(userTicket);
    }
}
