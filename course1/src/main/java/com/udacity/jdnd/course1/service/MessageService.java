package com.udacity.jdnd.course1.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import  java.util.*;

@Service
public class MessageService {
    private List<String> messages;

@PostConstruct
public void postConstruct(){
    this.messages =new ArrayList<>();
}


    public List<String> getMessages() {
        return new ArrayList<>(this.messages);
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
