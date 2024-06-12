package org.candenizgumus.controller;

import org.candenizgumus.constants.EndPoints;
import org.candenizgumus.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.candenizgumus.constants.EndPoints.USERPROFILE;

@RequiredArgsConstructor
@RequestMapping(USERPROFILE)
@RestController
public class UserProfileController
{
    private final UserProfileService userProfileService;

    @PutMapping(EndPoints.BAKIYEEKLE)
    public String bakiyeEkle(@RequestParam Double eklenecekBakiye, @RequestParam Long userId)
    {
        return userProfileService.bakiyeEkle(   eklenecekBakiye, userId);
    }

}
