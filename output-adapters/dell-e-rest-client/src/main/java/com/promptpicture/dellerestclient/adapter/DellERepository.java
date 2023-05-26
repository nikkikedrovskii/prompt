package com.promptpicture.dellerestclient.adapter;

import com.promptpicture.backend.core.delle.adapter.DellEAdapter;
import com.promptpicture.dellerestclient.client.DellERestClient;
import com.promptpicture.dellerestclient.factory.DellERequestFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DellERepository implements DellEAdapter {

    private final DellERequestFactory dellERequestFactory;
    private final DellERestClient dellERestClient;

    public String generatePicture(String promptText) {
        var request = dellERequestFactory.create(promptText);
        var response = dellERestClient.getPicture(request);
        return response.getData().get(0).getB64Json();
    }

}
