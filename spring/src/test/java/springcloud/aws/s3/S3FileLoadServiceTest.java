package springcloud.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class S3FileLoadServiceTest {

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    S3FileLoadService s3FileLoadService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void upload() throws IOException {

        //given
        String classPath = "test/earth.jpeg";
        String fileName = "sample.jpeg";
        String dirName = "test";
        String s3Key = dirName + "/" + fileName;
        ClassPathResource classPathResource = new ClassPathResource(classPath);
        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, null, new FileInputStream(classPathResource.getFile()));

        // when
        s3FileLoadService.upload(multipartFile, dirName);

        // then
        S3Object object = amazonS3.getObject(bucket, s3Key);
        assertThat(object.getKey()).isEqualTo(s3Key);

    }
}