package com.ycm.web.application;

import com.ycm.business.domain.RoomReservation;
import com.ycm.business.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)    //test against this controller
class ReservationControllerTest {

    //MockMvc is Spring MVC test engine
    //@MockBean add mock objects to the spring application context
    //@Test performs the testing for a specific scenario

    @Autowired
    private MockMvc mockMvc;

    //underlying service we need to mock to test the V+C components
    //note that the mocks are reset after each test method
    @MockBean
    private ReservationService reservationService;

    @Test
    public void getReservations() throws Exception {
        //prepare the input and expected output objects
        String testDateString = "2017-01-01";
        Date testDate = DATE_FORMAT.parse(testDateString);
        List<RoomReservation> mockReservationList = new ArrayList<>();
        RoomReservation mockRoomReservation = new RoomReservation();
        mockRoomReservation.setLastName("Test");
        mockRoomReservation.setFirstName("JUnit");
        mockRoomReservation.setDate(testDate);
        mockRoomReservation.setGuestId(1);
        mockRoomReservation.setRoomNumber("J1");
        mockRoomReservation.setRoomId(100);
        mockRoomReservation.setRoomName("JUnit Room");
        mockReservationList.add(mockRoomReservation);

        //feed the input/output objects to the mock service
        //from frontend to backend, the flow is
        //user -> Web Front View -> ReservationController -> ReservationService -> Data
        //        (checked)         (being tested)           (mocked)
        given(reservationService.getRoomReservationsForDate(testDateString)).willReturn(mockReservationList);

        //give the above mocked service, we want to make sure if we get some data on the view
        //in other words, we are testing the view+controller (VC) with a mocked model (M)
        this.mockMvc.perform(get("/reservations?date=2017-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test, JUnit")));

        //by the way, get() status() content() are three static utility methods
        //they are imported via 'import static xxx‘
    }

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
}
