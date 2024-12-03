package com.example.usersmanagementsystem.Controller;

import com.example.usersmanagementsystem.ApiResponse.ApiResponse;
import com.example.usersmanagementsystem.Model.User;
import com.example.usersmanagementsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user-management/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("New user successfully added."));

    }


    @PutMapping("/update/user-id/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }

        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User details successfully updated."));

    }

    @DeleteMapping("/delete/user-id/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){

        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User successfully deleted."));

    }

    @PostMapping("/login/user-name/{userName}/password/{password}")
    public ResponseEntity login(@PathVariable String userName, @PathVariable String password){

        userService.loginUser(userName,password);
        return ResponseEntity.status(200).body(new ApiResponse("User successfully logged in."));
    }


    @GetMapping("/get-by-email/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){

         User user = userService.findUserByEmail(email);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get-by-role/role/{role}")
    public ResponseEntity getUserByRole(@PathVariable String role){


        return ResponseEntity.status(200).body( userService.findUserByRole(role));
    }

    @GetMapping("get-user-by-age/min/{min}/max/{max}")
    public ResponseEntity getUserByAgeMinMax(@PathVariable Integer min, @PathVariable Integer max){

        return ResponseEntity.status(200).body(userService.findUserByAgeRange(min, max));

    }






}
