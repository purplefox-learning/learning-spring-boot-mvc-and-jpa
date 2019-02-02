package com.ycm.business.service;

import com.ycm.business.domain.RoomReservation;
import com.ycm.data.entity.Guest;
import com.ycm.data.entity.Reservation;
import com.ycm.data.entity.Room;
import com.ycm.data.repository.GuestRepository;
import com.ycm.data.repository.ReservationRepository;
import com.ycm.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public ReservationService(RoomRepository roomRepo, GuestRepository guestRepo, ReservationRepository reservationRepo) {
        this.roomRepository = roomRepo;
        this.guestRepository = guestRepo;
        this.reservationRepository = reservationRepo;
    }

    //the high level requirement is, give a date, return a list of (room,guestWhoReserveThatRoom)
    //if there is no guest reserve that room, guest info is left as null
    //but on the UI, guest column will be shown as vacant
    public List<RoomReservation> getRoomReservationsForDate(String dateString) {
        Date date = createDateFromDateString(dateString);
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach( room-> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if (reservations!=null) {
            reservations.forEach(reservation -> {
                Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
                if (guest!=null) {
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());
                }
            });
        }
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId:roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }
        return roomReservations;
    }

    private Date createDateFromDateString(String dateString) {
        Date date = null;
        if (dateString != null) {
            try {
                date = DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                date = new Date();
            }
        } else {
            date = new Date();
        }
        return date;
    }
}
