import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{concat, pathPrefix}
import akka.stream.ActorMaterializer
import api.UsersApi
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object ServerConfig extends App {

  println("this is our server:")

  implicit val system: ActorSystem = ActorSystem("web-app")
  private implicit val dispatcher: ExecutionContextExecutor = system.dispatcher
  private implicit val materialize: ActorMaterializer = ActorMaterializer()

  private val routeConfig = new UsersApi()

  val routes = {
    pathPrefix("api") {
      concat(
        routeConfig.getRoute
      )
    }
  }

  val serverFuture = Http().bindAndHandle(routes, "localhost", 8080)

  println("Server started ...")
  StdIn.readLine()
  serverFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}
