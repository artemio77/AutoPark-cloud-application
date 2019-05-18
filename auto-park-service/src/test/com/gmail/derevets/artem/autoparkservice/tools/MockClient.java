package com.gmail.derevets.artem.autoparkservice.tools;

import com.gmail.derevets.artem.autoparkservice.client.UserClient;
import org.assertj.core.util.Lists;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockClient {

    private static UserClient userClient;

    public static UserClient getUserClient() {
        if (userClient == null) {
            userClient = initUserClient();
        }
        return userClient;
    }

    private static UserClient initUserClient() {
        UserClient userClient = mock(UserClient.class);
        when(userClient.getUserByEmail(any(String.class))).thenReturn(MockModel.getDriver());
        when(userClient.getDrivers()).thenReturn(Lists.newArrayList(MockModel.getDriver()));
        when(userClient.getUserById(any(UUID.class))).thenReturn(MockModel.getDriver());
        when(userClient.getUserList()).thenReturn(Lists.newArrayList(MockModel.getDriver()));
        return userClient;
    }
}
