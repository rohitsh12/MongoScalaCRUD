package db

import Entity.Users
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistries._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

object dbConfig {

  val client:MongoClient=MongoClient("mongodb://localhost:27017")
  val database=client.getDatabase("mydb")
  database.createCollection("users")
  val customCodecs = fromProviders(classOf[Users])
  val codecRegistry = fromRegistries(customCodecs, DEFAULT_CODEC_REGISTRY)
  val myuserDB=database.getCollection[Users]("users").withCodecRegistry(codecRegistry)

}
