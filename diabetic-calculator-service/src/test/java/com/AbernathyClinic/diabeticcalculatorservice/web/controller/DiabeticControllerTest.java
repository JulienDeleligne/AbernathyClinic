package com.AbernathyClinic.diabeticcalculatorservice.web.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.AbernathyClinic.diabeticcalculatorservice.service.DiabeticService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DiabeticControllerTest {

  @InjectMocks
  DiabeticController diabeticController;
  @Mock
  DiabeticService diabeticService;

  @Test
  void assessTest() {

    //ACT
    diabeticController.assess(1);
    //ASSERT
    verify(diabeticService, times(1)).assess(1);
  }
}
