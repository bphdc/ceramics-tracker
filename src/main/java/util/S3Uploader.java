package util;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class S3Uploader {
    private static final String BUCKET_NAME = "ceramic-tracker-images";
    private static final String REGION = "us-east-2";
    private static Logger logger = LogManager.getLogger(S3Uploader.class);


    public static String uploadFile(File file, String fileName, String accessKeyId, String secretAccessKey) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();


        logger.info("does bucket exist {}", s3Client.doesBucketExistV2(BUCKET_NAME));

        String fileUrl = "https://" + BUCKET_NAME + ".s3." + REGION + ".amazonaws.com/" + fileName;

        logger.info("s3 fileurl name {}", fileUrl);

        s3Client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file));

        return fileUrl;
    }
}

