import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@WebServlet(urlPatterns = { "/object-bucket-with-role" })
public class GetObjectFromBucketWithRole extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("UTF-8");

                String bucketName = "cloud-public-mpg";
                PrintWriter writer = resp.getWriter();


                S3Client s3Client = S3Client.builder()
                                .credentialsProvider(InstanceProfileCredentialsProvider.create())
                                .region(Region.US_EAST_1)
                                .build();

                ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                                .bucket(bucketName)
                                .build();

                ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);

                List<S3Object> contents = listObjectsV2Response.contents();

                // Write to response stream
                contents.stream()
                                .forEach(s3Object -> writer.println(s3Object.key()));
        }
}