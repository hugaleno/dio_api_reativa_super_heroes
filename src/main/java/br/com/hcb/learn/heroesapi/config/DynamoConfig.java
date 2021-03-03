package br.com.hcb.learn.heroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {

  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDbEndpoint;

  @Value("${aws_access_key_id}")
  private String amazonAWSAccesskey;

  @Value("${aws_secret_access_key}")
  private String amazonAWSSecretKey;


  @Bean
  public AmazonDynamoDB amazonDynamoDB(){
    AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAwsCredentials());

    if(!amazonDynamoDbEndpoint.isBlank()){
      amazonDynamoDB.setEndpoint(amazonDynamoDbEndpoint);
    }

    return amazonDynamoDB;
  }

  @Bean
  public AWSCredentials amazonAwsCredentials(){
    return new BasicAWSCredentials(amazonAWSAccesskey, amazonAWSSecretKey);
  }
}
