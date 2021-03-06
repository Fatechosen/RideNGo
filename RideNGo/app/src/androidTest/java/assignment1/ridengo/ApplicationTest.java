package assignment1.ridengo;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testUS010101() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest);
        assertTrue(rider1.getRequests().contains(newRequest));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS010201() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest1 = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start1 to end", rider1, fare);
        rider1.postRideRequest(newRequest1);

        RideRequest newRequest = new RideRequest(new LatLng(1, 1), new LatLng(1, 1), "From start1 to end", rider1, fare);
        rider1.postRideRequest(newRequest);
        assertTrue(rider1.getRequests().contains(newRequest));

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest);
        assertTrue(rider1.isNotified());

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS010401() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest);
        assertTrue(rider1.getRequests().contains(newRequest));

        rider1.cancelRequest(newRequest);
        assertTrue(newRequest.getStatus().equals("Cancelled"));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS010501() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest);
        assertTrue(rider1.getRequests().contains(newRequest));

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest);
        assertTrue(rider1.isNotified());

        assertTrue(newRequest.getAcceptions().get(0).getUser().getPhoneNum().equals(user2.getPhoneNum()));
        assertTrue(newRequest.getAcceptions().get(0).getUser().getEmail().equals(user2.getEmail()));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS010601() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest);
        assertTrue(rider1.getRequests().contains(newRequest));

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest);
        assertTrue(rider1.isNotified());

        rider1.acceptAcception(newRequest, newRequest.getAcceptions().get(0));
        driver2.completeRide(newRequest);
        assertTrue(newRequest.getStatus().equals("Driver Confirmed Completion"));
        rider1.completeRide(newRequest);
        assertTrue(newRequest.getStatus().equals("Completed"));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS010701() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest);
        assertTrue(rider1.getRequests().contains(newRequest));

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest);
        assertTrue(rider1.isNotified());

        User user3 = new User("User3", "user3@gmail.com", "8888888888");
        UserDriver driver3 = new UserDriver(user3);
        driver3.acceptRequest(newRequest);
        assertTrue(rider1.isNotified());

        rider1.acceptAcception(newRequest, newRequest.getAcceptions().get(0));
        assertTrue(newRequest.getDriver().equals(driver2));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS020101() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest);
        assertTrue(rider1.getRequests().contains(newRequest));
        assertTrue(rider1.getRequests().getRequests().get(0).getStatus().equals("Posted"));

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest);
        assertTrue(rider1.isNotified());

        assertTrue(rider1.getRequests().contains(newRequest));
        assertTrue(rider1.getRequests().getRequests().get(0).getStatus().equals("Accepted By Driver"));
        assertTrue(driver2.getRequests().contains(newRequest));
        assertTrue(driver2.getRequests().getRequests().get(0).getStatus().equals("Accepted By Driver"));

        rider1.acceptAcception(newRequest, driver2);
        assertTrue(rider1.getRequests().getRequests().get(0).getStatus().equals("Driver Confirmed"));
        assertTrue(driver2.getRequests().getRequests().get(0).getStatus().equals("Driver Confirmed"));

        driver2.completeRide(newRequest);
        assertTrue(rider1.getRequests().getRequests().get(0).getStatus().equals("Driver Confirmed Completion"));
        assertTrue(driver2.getRequests().getRequests().get(0).getStatus().equals("Driver Confirmed Completion"));

        rider1.completeRide(newRequest);
        assertTrue(rider1.getRequests().getRequests().get(0).getStatus().equals("Completed"));
        assertTrue(driver2.getRequests().getRequests().get(0).getStatus().equals("Completed"));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS030101() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserList users = UserController.getUserList();
        users.addUser(user1);
        assertTrue(users.contains(user1));
        User user2 = new User("User1", "user1@gmail.com", "8888888888");
        try {
            users.addUser(user2);
            assertTrue("Should not reach here", false);
        } catch (RuntimeException e) {
            assertTrue("User2 not being added", true);
        }

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS030201() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserList users = UserController.getUserList();
        users.addUser(user1);
        assertTrue(users.contains(user1));
        String newEmail = "newuser1@gmail.com";
        user1.setEmail(newEmail);
        assertTrue(user1.getEmail().equals(newEmail));
        String newPhone = "9999999999";
        user1.setPhoneNum(newPhone);
        assertTrue(user1.getPhoneNum().equals(newPhone));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS030301() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserList users = UserController.getUserList();
        users.addUser(user1);
        assertTrue(users.contains(user1));

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        users.addUser(user2);
        assertTrue(users.contains(user2));

        User user = users.getUserByUsername("User2");
        assertTrue(user.getPhoneNum().equals(user2.getPhoneNum()));
        assertTrue(user.getEmail().equals(user2.getEmail()));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS040101() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest);

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);

        RideRequestList requests = driver2.getRequestsByGeoLocation(new LatLng(0, 0));
        assertTrue(requests.contains(newRequest));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS040201() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest);

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);

        RideRequestList requests = driver2.getRequestsByKeyword("start");
        assertTrue(requests.contains(newRequest));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS050101() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest1 = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest1);

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest1);

        rider1.acceptAcception(newRequest1, driver2);
        driver2.completeRide(newRequest1);
        rider1.completeRide(newRequest1);

        assertTrue("Payment not implemented", false);

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS050201() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest1 = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest1);
        RideRequest newRequest2 = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest2);

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest1);
        driver2.acceptRequest(newRequest2);

        assertTrue(driver2.getPendingRequests().contains(newRequest1));
        assertTrue(driver2.getPendingRequests().contains(newRequest2));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS050301() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest1 = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest1);

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest1);

        rider1.acceptAcception(newRequest1, driver2);
        assertTrue(newRequest1.getDriver().equals(driver2));

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS050401() {
        User user1 = new User("User1", "user1@gmail.com", "8888888888");
        UserRider rider1 = new UserRider(user1);
        Double fare = 50.0;
        RideRequest newRequest1 = new RideRequest(new LatLng(0, 0), new LatLng(0, 0), "From start to end", rider1, fare);
        rider1.postRideRequest(newRequest1);

        User user2 = new User("User2", "user2@gmail.com", "8888888888");
        UserDriver driver2 = new UserDriver(user2);
        driver2.acceptRequest(newRequest1);

        rider1.acceptAcception(newRequest1, driver2);
        assertTrue(driver2.isNotified());

        UserController.getUserList().clear();
        RideRequestController.getRequestList().clear();
    }

    public void testUS080101() {
        // Without actually building code for server, runnable test for offline behavior is hard
        // to write. The basic idea is to use local data to store data and retrieve when offline.
        assertTrue(false);
    }

    public void testUS080201() {
        // Without actually building code for server, runnable test for offline behavior is hard
        // to write. The basic idea is to use local data to store data and retrieve when offline.
        assertTrue(false);
    }

    public void testUS080301() {
        // Without actually building code for server, runnable test for offline behavior is hard
        // to write. The basic idea is to use local data to store data and retrieve when offline.
        assertTrue(false);
    }

    public void testUS080401() {
        // Without actually building code for server, runnable test for offline behavior is hard
        // to write. The basic idea is to use local data to store data and retrieve when offline.
        assertTrue(false);
    }

    public void testUS100101() {
        // Without knowledge of google map api, runnable test for selecting and showing location
        // on a map
        assertTrue(false);
    }

    public void testUS100201() {
        // Without knowledge of google map api, runnable test for selecting and showing location
        // on a map
        assertTrue(false);
    }
}