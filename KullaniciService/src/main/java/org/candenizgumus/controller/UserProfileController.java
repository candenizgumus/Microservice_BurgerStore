package org.candenizgumus.controller;

import org.candenizgumus.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.candenizgumus.constants.EndPoints.USERPROFILE;

@RequiredArgsConstructor
@RequestMapping(USERPROFILE)
@RestController
public class UserProfileController
{
    private final UserProfileService userProfileService;

}
