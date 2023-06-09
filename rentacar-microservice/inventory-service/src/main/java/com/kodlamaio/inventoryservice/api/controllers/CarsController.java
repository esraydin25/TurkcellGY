package com.kodlamaio.inventoryservice.api.controllers;


import com.kodlamaio.commonpackage.utils.constants.Roles;
import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.GetCarResponse;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.dto.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.dto.responses.update.UpdateCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService service;

    @GetMapping
    //@Secured("ROLE_admin")
    @PreAuthorize(Roles.AdminOrModerator)
    public List<GetAllCarsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize(Roles.AdminOrModerator + "|| returnObject.modelYear == 2019")
    public GetCarResponse getById(@PathVariable  UUID id, @AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt.getClaims().get("preferred_username"));
        System.out.println(jwt.getClaims().get("email"));
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize(Roles.AdminOrModerator)
    public CreateCarResponse add(@Valid @RequestBody CreateCarRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCarRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize(Roles.AdminOrModerator)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("/check-car-available/{id}")
    public ClientResponse checkIfCarAvailable(@PathVariable UUID id) {
        return service.checkIfCarAvailable(id);
    }

}
