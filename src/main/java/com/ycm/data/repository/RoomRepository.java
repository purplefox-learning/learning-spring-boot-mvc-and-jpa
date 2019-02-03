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

    //there are many interesting methods we can add here
    //as long as we stick to the convention, spring will automatically add
    //the implementation code according for us

    /*

    //the convention for the method name is
    //findBy (First/Top/Top3) AttrName (SubAttrName) (And/Or 2ndAttrName) (OrderBy AttrName Asc/Desc)
    //Search for "Repository Query Keywords" for more

    Student findByName(String name);
    Student findByNameIgnoreCase(String name);
    Student findByNameLike(String likeName);
    Student findByAgeGreaterThan(int minAge);

    Student findFirstNameByOrderByLastNameAsc();
    Student findTopByOrderByAgeDesc();
    List<Student> findTop3ByOrderByAgeDesc();


     */

}
