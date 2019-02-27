package com.ycm.data.repository;

import com.ycm.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {

    //this is an interface, where is the implementation? Spring will do it for us.

    //extends CrudRepository<Room,Long> where
    //Room is the class that this repository class is serving
    //Long is the class of the unique id of the Room class

    //the following standard methods come with CrudRepository in Spring JPA:
    //in other words, in latest spring data 2.0, "All" "ById" postfix are commonly seen in operations
    //findAll(), saveAll(), deleteAll()
    //findById(key)             //Old Spring Data 1.0 : findOne(key)
    //findAllById(iterable)     //Old Spring Data 1.0 : findAll(iterable)
    //existsById(key)           //Old Spring Data 1.0 : exists(obj)
    //saveAll(), save(obj),     //Old Spring Data 1.0 : save(iterable)
    //deleteById(key)           //Old Spring Data 1.0 : delete(key)
    //deleteAll(iterable)       //Old Spring Data 1.0 : delete(iterable)

    //below are application-specific methods
    //as long as we stick to the convention, spring will automatically add
    //the implementation code according for us
    Room findByNumber(String number);

    /*

    //the convention for the method name is
    //findBy (First/Top/Top3) AttrName (SubAttrName) (And/Or 2ndAttrName) (OrderBy AttrName Asc/Desc)
    //Search for "Repository Query Keywords" on offical documentation for more details

    Student findByName(String name);
    Student findByNameIgnoreCase(String name);
    Student findByNameLike(String likeName);
    Student findByAgeGreaterThan(int minAge);

    //attribute and sub-attributes
    //the 1st method is obvious, we search all students in a particular school
    //the 2nd method is two level redirections, assume we dont have school objects,
    //but we have school principal's name. what we can do is to find the schools with
    //the same principal, and then use those school objects to find the list of students
    List<Student> findBySchool(School school)
    List<Student> findBySchoolPricipal(String schoolPrincipalName)

    Student findFirstNameByOrderByLastNameAsc();
    Student findTopByOrderByAgeDesc();
    List<Student> findTop3ByOrderByAgeDesc();

    */

}
