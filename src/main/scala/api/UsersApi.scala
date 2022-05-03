package api

import Entity.Users
import Services.UserServices
import akka.NotUsed
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, concat, delete, entity, get, parameter, path, post, put}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.{PathDirectives, RouteDirectives}
import akka.pattern.Patterns
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.util.ByteString
import users.{DELETE, SAVE, SEARCH_ALL, UPDATE, UserActor}
import utils.{JsonUtils, TimeUtils}

import scala.concurrent.Await

class UsersApi (implicit val system: ActorSystem) extends JsonUtils  {

  val userActor: ActorRef = system.actorOf(Props(new UserActor))


  implicit val mat: ActorMaterializer = ActorMaterializer()


  val getRoute: Route =

    PathDirectives.pathPrefix("users") {
      concat(
        path("create") {
          post {
            entity(as[Users]) { employee =>
           val future = Patterns.ask(userActor, SAVE(employee), TimeUtils.timeoutMills) //

              Await.result(future, TimeUtils.atMostDuration)
              RouteDirectives.complete(HttpEntity("Data saved successfully!"))
            }
          }
        },

        path("search") {
          get {
            val resultFuture = Patterns.ask(userActor, SEARCH_ALL, TimeUtils.timeoutMills)
            val resultSource = Await.result(resultFuture, TimeUtils.atMostDuration).asInstanceOf[Source[Users, NotUsed]]
           val resultByteString = resultSource.map { it => ByteString.apply(it.toString.getBytes()) }
            RouteDirectives.complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`,resultByteString))
          }
        },
//        (it.toJson.toString.getBytes())
        path("update") {
          put {
            parameter("id") { id =>
              entity(as[Users]) { employee =>
                val future = Patterns.ask(userActor, UPDATE(employee, id), TimeUtils.timeoutMills)
                Await.result(future, TimeUtils.atMostDuration)
                RouteDirectives.complete(HttpEntity("Data updated saved successfully!"))
              }
            }
          }
        },

        path("delete") {
          delete {
            parameter("id") { id =>
              val resultFuture = Patterns.ask(userActor, DELETE(id), TimeUtils.timeoutMills)
              Await.result(resultFuture, TimeUtils.atMostDuration)
              RouteDirectives.complete(HttpEntity(s"Data updated saved successfully!"))
            }
          }
        }
      )
    }


}
