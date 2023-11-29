package com.example.autocinema.publ;

import com.example.autocinema.models.Message;

import java.util.concurrent.SubmissionPublisher;

public class MessagePublisher extends SubmissionPublisher<Message> {
    public void publishMessage(Message message) {
        submit(message);
    }
}
