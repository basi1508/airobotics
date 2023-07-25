package com.interview.airobotics.webservice;

import com.interview.airobotics.models.AuthenticatationRequest;
import com.interview.airobotics.models.AuthenticationResponse;
import com.interview.airobotics.service.GuestService;
import com.interview.airobotics.data.Guest;
import com.interview.airobotics.service.GuestService;
import com.interview.airobotics.data.Guest;
import com.interview.airobotics.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/guest")
public class GuestController {
    private final GuestService guestService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }
    @RequestMapping(path = "/addGuest",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@RequestBody Guest guest){
        guestService.addGuest(guest);
        return guest;
    }
    @RequestMapping(path="/getGuests", method = RequestMethod.GET)
    public List<Guest> getGuest(){
      return  guestService.getGuests();
    }
    @RequestMapping(path="/updateGuest",method = RequestMethod.PUT)
    public Guest updateGuest(@RequestParam(value ="id", required = true)long id,
                                @RequestBody Guest guest){
       return guestService.updateGuest(id,guest);
    }
    @RequestMapping(path = "/deleteGuest")
    public void deleteGeust(@RequestParam(value = "id",required = true) long id){
        guestService.deleteGuest(id);
    }
    @RequestMapping(value="/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticatationRequest authenticatationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticatationRequest.getUserName(), authenticatationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect user/password",e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticatationRequest.getUserName());

        final String jwt = jwtUtils.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    @RequestMapping(value="/authenticate1",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestAttribute(value = "Username",required = true) String userName, @RequestParam(value="Password",required = true) String password ) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect user/password",e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        final String jwt = jwtUtils.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
