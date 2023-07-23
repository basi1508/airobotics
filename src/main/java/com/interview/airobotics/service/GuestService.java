package com.interview.airobotics.service;

import com.interview.airobotics.data.Guest;
import com.interview.airobotics.data.Guest;
import com.interview.airobotics.repo.GuestRepository;
import com.interview.airobotics.repo.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }


    public void addGuest(Guest guest){
        if(guest==null)
            throw  new RuntimeException("Guest can't be empty");
        guestRepository.save(guest);
    }
    public List<Guest> getGuests(){
        List<Guest>guestList =  guestRepository.findAll();
        guestList.sort((o1, o2) -> {
            if(o1.getFirstName().equals(o2.getFirstName()))
                 return o1.getLastName().compareTo(o2.getLastName());

           return o1.getFirstName().compareTo(o2.getFirstName());

        });

        return guestList;

    }
    public void deleteGuest(long id){
        guestRepository.deleteById(id);
    }


}
