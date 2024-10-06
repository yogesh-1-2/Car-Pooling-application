package com.example.car_pooling;

import com.example.car_pooling.Entities.Trip;
import com.example.car_pooling.Manager.TripManager;
import com.example.car_pooling.Manager.VehicleManager;
import com.example.car_pooling.Repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripManager tripManager;

    @MockBean
    private VehicleManager vehicleManager;

    @MockBean
    private VehicleRepository vehicleRepository;

    private List<Trip> getTrips() {
        return List.of(TestEntities.getTrip1(), TestEntities.getTrip2(), TestEntities.getTrip3(), TestEntities.getTrip4());
    }

    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" + "    \"name\" : \"sggfss\",\n" + "    \"emailId\" : \"ddas@fgsdf.com\",\n"
                                + "    \"phoneNumber\" : \"35445642\",\n" + "    \"password\" : \"adf\"\n" + "}"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    public void testSearchAlgorithmByMostVacant() throws Exception {
        when(tripManager.getAllActiveTrips()).thenReturn(getTrips());
        mockMvc.perform(MockMvcRequestBuilders.get("/trip/search")
                        .param("originState", "8")
                        .param("destinationState", "6")
                        .param("tripSelectionStrategy", "MOST_VACANT")
                        .param("seatsRequired", "1")
                        .param("vehicleType", "Hero")
                ).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(2));
    }

    @Test
    public void testSearchAlgorithmByMostVacantMultipleTrips() throws Exception {
        when(tripManager.getAllActiveTrips()).thenReturn(getTrips());
        mockMvc.perform(MockMvcRequestBuilders.get("/trip/search")
                        .param("originState", "8")
                        .param("destinationState", "22")
                        .param("tripSelectionStrategy", "MOST_VACANT")
                        .param("seatsRequired", "1")
                        .param("vehicleType", "Hero")
                ).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(4));
    }

    @Test
    public void testSearchAlgorithmByVehicle() throws Exception {
        when(tripManager.getAllActiveTrips()).thenReturn(getTrips());
        when(vehicleManager.getVehicleByNumber("af2334")).thenReturn(TestEntities.getVehicle2());
        when(vehicleManager.getVehicleByNumber("af237")).thenReturn(TestEntities.getVehicle1());
        when(vehicleManager.getVehicleByNumber("af23374")).thenReturn(TestEntities.getVehicle3());
        when(vehicleManager.getVehicleByNumber("af8974")).thenReturn(TestEntities.getVehicle4());
        mockMvc.perform(MockMvcRequestBuilders.get("/trip/search")
                .param("originState", "8")
                .param("destinationState", "6")
                .param("tripSelectionStrategy", "PREFERRED_VEHICLE")
                .param("seatsRequired", "1")
                .param("vehicleType", "Hero")
        ).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

        mockMvc.perform(MockMvcRequestBuilders.get("/trip/search")
                        .param("originState", "8")
                        .param("destinationState", "26")
                        .param("tripSelectionStrategy", "PREFERRED_VEHICLE")
                        .param("seatsRequired", "1")
                        .param("vehicleType", "Hero")
                ).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(3));
    }
}