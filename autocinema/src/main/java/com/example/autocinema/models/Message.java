package com.example.autocinema.models;

import com.example.autocinema.publ.MessagePublisher;

public record Message (
        Vehicle vehicle,
        MessagePublisher publisher
) { }
