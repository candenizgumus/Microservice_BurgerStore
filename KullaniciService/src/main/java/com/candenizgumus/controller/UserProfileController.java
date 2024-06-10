package com.candenizgumus.controller;

import com.candenizgumus.service.AuthService;
import com.candenizgumus.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.candenizgumus.constants.EndPoints.AUTH;
import static com.candenizgumus.constants.EndPoints.USERPROFILE;

@RequiredArgsConstructor
@RequestMapping(USERPROFILE)
@RestController
public class UserProfileController
{
    private final UserProfileService userProfileService;

}
