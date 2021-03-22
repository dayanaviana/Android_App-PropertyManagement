package com.android_training.propertymanagement

import com.android_training.propertymanagement.testcase.CalculatorService
import com.android_training.propertymanagement.testcase.MathApplication
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MyMokitoTest {

    @InjectMocks
    var mathApplication = MathApplication()

    @Mock
    lateinit var calculatorService: CalculatorService

    @Test
    fun testAdd(){
        //Specify the behaviour
        Mockito.`when`(calculatorService.add(3.0,5.0)).thenReturn(8.0)

        //Test behaviour
        Assert.assertEquals(calculatorService.add(3.0,5.0),8.0,0.0)

    }

}

