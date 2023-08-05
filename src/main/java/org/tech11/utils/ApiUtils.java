package org.tech11.utils;

import org.tech11.model.domain.ApiResponse;
import static org.tech11.model.enums.ResponseMessage.SUCCESS;

public class ApiUtils {

    public static <T> ApiResponse<T> buildApiResponse(T data , long... start) {

        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(SUCCESS.getCode());
        response.setMessage(SUCCESS.toString());
        response.setData(data);

        // set duration of API call in seconds if start is passed
        if(start.length > 0){
            response.setDuration((System.currentTimeMillis() - start[0])/1000);
        }

        return response;
    }


}
