package springcloud.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AmazonS3Test {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 amazonS3;


    @Test
    void putObject() throws IOException {

        //given
        String path = "test/earth.jpeg";
        ClassPathResource classPathResource = new ClassPathResource(path);
        File file = classPathResource.getFile();

        // when
        amazonS3.putObject(new PutObjectRequest(bucket, path, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        // then
        S3Object object = amazonS3.getObject(bucket, path);
        assertThat(object.getKey()).isEqualTo(path);
    }
}
