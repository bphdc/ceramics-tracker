package util;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

/**
 * set of utls to help with s3 functionality
 */
public class S3Helper {
    private static final String BUCKET_NAME = "ceramic-tracker-images";
    private static final String REGION = "us-east-2";
    private static Logger logger = LogManager.getLogger(S3Helper.class);

    /**
     * uploads image to s3 bucket
     * @param file the file
     * @param fileName the file name
     * @param accessKeyId the access key id
     * @param secretAccessKey the secret access key
     * @return the url
     */
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

    /**
     * Get teh file name if you need to delete img later
     * @param url
     * @return s3 key
     */
    public static String extractS3KeyFromUrl(String url) {
        return url.replace("https://ceramic-tracker-images.s3.us-east-2.amazonaws.com/", "");
    }

    public static void deleteFile(String fileName, String accessKeyId, String secretAccessKey) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        logger.info("attempting to delete file from S3: {}", fileName);

        try {
            s3Client.deleteObject(BUCKET_NAME, fileName);
            logger.info("File successfully deleted from S3: {}", fileName);
        } catch (Exception e) {
            logger.error("Error deleting file from S3: {}", fileName, e);
        }
    }
}

