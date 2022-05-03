package users

import Entity.Users
import Services.UserServices
import akka.actor.{Actor, ActorLogging}

class UserActor extends Actor with ActorLogging{



  private val userService: UserServices = new UserServices()

  override def receive: Receive = {

    case SAVE(usr: Users) =>
      log.info(s" Save employee $usr")
      sender ! userService.saveEmployeeData(usr)

    case SEARCH_ALL =>
      log.info("find all")
      sender() ! userService.findAll

    case UPDATE(emp,id) =>
      log.info("for update ")
      sender() ! userService.update(emp,id)

    case DELETE(id)=>
      log.info(s"delete message received for the id: $id")
      sender() ! userService.delete(id)

    case _ =>
      log.debug("Unhandled message!")
  }
}

sealed trait UserActorMessage

case class SAVE(usr: Users) extends UserActorMessage

case object SEARCH_ALL extends UserActorMessage

case class UPDATE(usr: Users, id: String) extends UserActorMessage

case class DELETE(id: String) extends UserActorMessage
