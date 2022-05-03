package Repository

import Entity.Users
import db.dbConfig
import org.mongodb.scala.MongoCollection

import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.FindOneAndUpdateOptions
import org.mongodb.scala.model.Updates.{combine, set}
import org.mongodb.scala.result.DeleteResult
import org.mongodb.scala.Completed
import scala.concurrent.Future
import org.mongodb.scala.{MongoCollection}
import utils.JsonUtils

object UserRepository extends JsonUtils{

  private val userDB: MongoCollection[Users] = dbConfig.myuserDB

//  def createCollection(): Unit = {
//    dbConfig.database.createCollection("employee").sub(
//      (result: Completed) => println(s"$result"),
//      (e: Throwable) => println(e.getLocalizedMessage),
//      () => println("complete"))
//  }

  def insertData(emp: Users ): Future[Completed] = {
    userDB.insertOne(emp).toFuture()
  }

//  def insertData(emp: Users): C = {
//   val res= userDB.insertOne(emp).toFuture()
//    println("result in insertion at repository "+res)
//    if(Future.successful(res))
//      res
//    else
//      false
//
//
//  }

  def findAll(): Future[Seq[Users]] = {
    userDB.find().toFuture()
  }

  def update(emp: Users, id: String):Future[Users] = {

    userDB
      .findOneAndUpdate(equal("id", id),
        setBsonValue(emp),
        FindOneAndUpdateOptions().upsert(true)).toFuture()
  }

  def delete(id: String): Future[DeleteResult] = {
    userDB.deleteOne(equal("id", id)).toFuture()
  }

  private def setBsonValue(usr:Users): Bson = {
    combine(
      set("name", usr.name),
      set("age",usr.age),
      set("branch",usr.branch)

    )
  }

}
