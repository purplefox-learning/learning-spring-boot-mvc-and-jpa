package com.ycm.data.repository;

import com.ycm.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {
    //extends CrudRepository<Room,Long> where
    //Room is the class that this repository class is serving
    //Long is the class of the unique id of the Room class

    Room findByNumber(String number);

}
