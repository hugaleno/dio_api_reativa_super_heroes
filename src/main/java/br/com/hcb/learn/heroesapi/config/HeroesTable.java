package br.com.hcb.learn.heroesapi.config;

import br.com.hcb.learn.heroesapi.contants.HeroesConstant;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import java.util.Arrays;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {

  public static void main(String[] args) {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(new EndpointConfiguration(HeroesConstant.ENDPOINT_DYNAMO,HeroesConstant.REGION_DYNAMO)).build();

    DynamoDB dynamoDB = new DynamoDB(client);

    try {
      Table table = dynamoDB.createTable(HeroesConstant.HEROES_TABLE_NAME,
          Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
          Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
          new ProvisionedThroughput(5L, 5L));

      table.waitForActive();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
