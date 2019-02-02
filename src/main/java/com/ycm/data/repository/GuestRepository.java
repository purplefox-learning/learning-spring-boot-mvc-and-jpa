package com.ycm.data.repository;

import com.ycm.data.entity.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}