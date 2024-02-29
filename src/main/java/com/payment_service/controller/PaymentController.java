package com.payment_service.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.payment_service.domain.Payment;
import com.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makePayment")
    public ResponseEntity<?> makePayment(@RequestBody Payment payment){
        Payment newPayment=paymentService.makePayment(payment);
        return new ResponseEntity<Payment>(newPayment, HttpStatus.OK);
//        return ResponseEntity.ok(newPayment);
    }


    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPayment(@PathVariable("paymentId") Long id){
        Payment payment=paymentService.getPayment(id);
        if (payment != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            try {
                String json = mapper.writeValueAsString(payment);
                Payment payment1 = mapper.readValue(json, Payment.class);
                return ResponseEntity.ok(payment1);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
        //new ResponseEntity<>(payment, HttpStatus.NOT_FOUND);
//        return ResponseEntity.ok(newPayment);
    }
























//    @GetMapping("/getUser/{userId}")
//    public ResponseEntity<?> getUser(@PathVariable("userId") int id){
//        User availUser= userService.getUser(id);
//        return new ResponseEntity<User>(availUser,HttpStatus.OK);
//    }
//
//    @GetMapping("/allUsers")
//    public ResponseEntity<?> getAllUsers(){
//        List<User> userList=userService.getAllUsers();
//        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
//    }
//
//    //Is not working
//    @GetMapping("/getUserByVacCenter/{vacId}")
//    public ResponseEntity<?> getUserByVacCenter(@PathVariable int vacId){
//        List<User> users=userService.getUsersByVacCenter(vacId);
//        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
//    }
//
//    @PutMapping("/updateUser/{userId}")
//    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user){
//        User updatedUser=userService.updateUser(userId,user);
//        return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deleteUser/{userId}")
//    public ResponseEntity<?> deleteUser(@PathVariable int userId){
//        userService.deleteUser(userId);
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
}
