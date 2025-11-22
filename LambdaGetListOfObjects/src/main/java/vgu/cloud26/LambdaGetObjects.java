package vgu.cloud26;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

public class LambdaGetObjects implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        context.getLogger().log("Received request: " + request.getBody());

        String bucketName = "cloud-public-mpg";
        String key = "cat.png";

        S3Client s3Client = S3Client.builder()
                //.credentialsProvider(InstanceProfileCredentialsProvider.create())
                .region(Region.US_EAST_1)
                .build();
        // ListObjectsRequest listObjects = ListObjectsRequest
        //         .builder()
        //         .bucket(bucketName)
        //         .build();
        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        ResponseInputStream<GetObjectResponse> objectResponse = s3Client.getObject(objectRequest);
        try {
            buffer = objectResponse.readAllBytes();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String base64String = java.util.Base64.getEncoder().encodeToString(buffer);

        APIGatewayProxyResponseEvent response
                = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        response.setBody(base64String);
        response.setHeaders(java.util.Collections.singletonMap("Content-Type", "application/json"));
        
        return response;
    }
    //convert bytes to kbs.
    private static long calKb(Long val) {
        return val / 1024;
    }
}