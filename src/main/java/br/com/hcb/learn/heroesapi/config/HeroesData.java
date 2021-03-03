package br.com.hcb.learn.heroesapi.config;

import br.com.hcb.learn.heroesapi.contants.HeroesConstant;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;


public class HeroesData {

  public static void main(String[] args) {
    AmazonDynamoDB client  =
        AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(new EndpointConfiguration(HeroesConstant.ENDPOINT_DYNAMO,HeroesConstant.REGION_DYNAMO))
            .build();

    DynamoDB dynamoDB = new DynamoDB(client);

    Table table = dynamoDB.getTable(HeroesConstant.HEROES_TABLE_NAME);

    Item hero = new Item()
        .withPrimaryKey("id", "1")
        .withString("name", "Mulher maravilha")
        .withString("universe","dc comics")
        .withNumber("films", 3);

    PutItemOutcome outcome = table.putItem(hero);
  }
}
