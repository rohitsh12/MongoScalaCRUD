package Services

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}
import Entity.Users
import Repository.UserRepository
import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import org.mongodb.scala.Completed
//import org.mongodb.scala.Completed
import org.mongodb.scala.result.DeleteResult

class UserServices {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContextExecutor = actorSystem.dispatcher
  implicit val mat: ActorMaterializer = ActorMaterializer()

//  def saveEmployeeData: Users => Boolean = (usr: Users) => {
//    UserRepository.insertData(usr)
//  }

  def saveEmployeeData: Users => Future[Completed] = (usr: Users) => {


    UserRepository.insertData(usr)
  }

  def findAll: Source[Users, NotUsed] = {
    Source.fromFuture(UserRepository.findAll())
      .mapConcat {
        identity
      }
  }

  def update(usr:Users, id: String): Future[Users] = {
    UserRepository.update(usr, id)
  }

  def delete(id: String): Future[DeleteResult] ={
    UserRepository.delete(id)
  }
//




}
