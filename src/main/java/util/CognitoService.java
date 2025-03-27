package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDeleteUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;

/**
 * Class for special cognito functions
 */
public class CognitoService {

    private Logger logger = LogManager.getLogger(CognitoService.class);

    private final CognitoIdentityProviderClient cognitoClient;

    /**
     * instantiates class
     */
    public CognitoService(String accessKeyId, String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId,secretAccessKey);
        this.cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    /**
     * Deletes user from cognito
     * @param username - username of user to delete
     * @param userPoolId - the aws pool id
     */
    public void deleteUserFromCognito(String username, String userPoolId) {
        try {
            AdminDeleteUserRequest deleteUserRequest = AdminDeleteUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .build();
            cognitoClient.adminDeleteUser(deleteUserRequest);
            logger.info("User deleted successfully from Cognito: {}", username);
        } catch (CognitoIdentityProviderException e) {
            logger.error("Error deleting user: {}", e.awsErrorDetails().errorMessage());
        }
    }
}
