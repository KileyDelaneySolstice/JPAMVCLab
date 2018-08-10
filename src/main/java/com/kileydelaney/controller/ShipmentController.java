package com.kileydelaney.controller;

import com.kileydelaney.repository.ShipmentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amazon")
public class ShipmentController {

    private ShipmentRepository shipRepository;

}
