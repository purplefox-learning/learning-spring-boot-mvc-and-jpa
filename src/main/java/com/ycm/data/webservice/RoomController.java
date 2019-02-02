package com.ycm.data.webservice;

import com.ycm.data.entity.Room;
import com.ycm.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//this class is just a sample, used to demonstrate that we can even expose our repository
//as a service, and with this service we can also verify the data is indeed
//there in the embedded database

//visit http://localhost:8080/data/rooms or http://localhost:8080/data/rooms?roomNumber=P1
//or simply click in Intellij menu Tools -> Test Restful Web Service

@RestController
public class RoomController {

    @Autowired
    private RoomRepository repository;

    @RequestMapping(value="/data/rooms", method=RequestMethod.GET)
    List<Room> findAll(@RequestParam(required=false) String roomNumber){
        List<Room> rooms = new ArrayList<>();
        if (roomNumber==null) {
            Iterable<Room> results = this.repository.findAll();
            results.forEach(r->rooms.add(r));
        } else {
            Room room = this.repository.findByNumber(roomNumber);
            if (room!=null) rooms.add(room);
        }
        return rooms;
    }
}
